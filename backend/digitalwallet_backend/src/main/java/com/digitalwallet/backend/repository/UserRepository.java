package com.digitalwallet.backend.repository;

import com.digitalwallet.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);

  /**
   * Use the actual User entity property name: phoneNumber
   * (Previously you had existsByPhone(...) which fails because User has no 'phone' field.)
   */
  boolean existsByPhoneNumber(String phoneNumber);

  /**
   * Optional helper to look up a user by phone number if you need it.
   */
  Optional<User> findByPhoneNumber(String phoneNumber);
}
