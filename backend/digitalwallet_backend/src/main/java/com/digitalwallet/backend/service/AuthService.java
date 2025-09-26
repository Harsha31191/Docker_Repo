package com.digitalwallet.backend.service;

import com.digitalwallet.backend.model.AuthToken;
import com.digitalwallet.backend.model.User;
import com.digitalwallet.backend.model.Wallet;
import com.digitalwallet.backend.repository.AuthTokenRepository;
import com.digitalwallet.backend.repository.UserRepository;
import com.digitalwallet.backend.repository.WalletRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * AuthService implementation:
 * - register(fullName, email, password, phoneNumber) creates user + wallet inside transaction
 * - loginAndCreateToken(email, password) validates and saves AuthToken
 * - findUserByToken(token) used by TokenAuthenticationFilter
 */
@Service
public class AuthService {
  private final UserRepository userRepository;
  private final WalletRepository walletRepository;
  private final AuthTokenRepository authTokenRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public AuthService(UserRepository userRepository,
                     WalletRepository walletRepository,
                     AuthTokenRepository authTokenRepository,
                     BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.walletRepository = walletRepository;
    this.authTokenRepository = authTokenRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Register a user and create an empty wallet.
   * Throws IllegalArgumentException on duplicate email or invalid input.
   */
  @Transactional
  public void register(String fullName, String email, String password, String phoneNumber) {
    if (fullName == null || fullName.isBlank()) {
      throw new IllegalArgumentException("fullName is required");
    }
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("email is required");
    }
    if (password == null || password.isBlank()) {
      throw new IllegalArgumentException("password is required");
    }

    if (userRepository.existsByEmail(email)) {
      throw new IllegalArgumentException("email already registered");
    }

    User u = new User();
    u.setFullName(fullName);
    u.setEmail(email);
    u.setPasswordHash(passwordEncoder.encode(password));
    if (phoneNumber != null && !phoneNumber.isBlank()) {
      u.setPhoneNumber(phoneNumber);
    }
    userRepository.save(u);

    // ensure wallet creation right after user saved
    Wallet w = new Wallet();
    w.setUser(u);
    w.setCurrency("INR");
    w.setBalance(java.math.BigDecimal.ZERO);
    walletRepository.save(w);
  }

  /**
   * Login and create an auth token (simple UUID token). Returns token string.
   * In production, prefer JWT.
   */
  public String loginAndCreateToken(String email, String password) {
    Optional<User> ou = userRepository.findByEmail(email);
    if (ou.isEmpty()) {
      throw new IllegalArgumentException("invalid credentials");
    }
    User u = ou.get();

    if (!passwordEncoder.matches(password, u.getPasswordHash())) {
      throw new IllegalArgumentException("invalid credentials");
    }

    String token = UUID.randomUUID().toString();

    AuthToken t = new AuthToken();
    t.setToken(token);
    t.setUser(u);
    authTokenRepository.save(t);

    return token;
  }

  /**
   * Lookup user by token for TokenAuthenticationFilter.
   */
  public Optional<User> findUserByToken(String token) {
    return authTokenRepository.findByToken(token).map(AuthToken::getUser);
  }

  /**
   * Optional: delete a token (logout)
   */
  public void deleteToken(String token) {
    authTokenRepository.deleteByToken(token);
  }
}
