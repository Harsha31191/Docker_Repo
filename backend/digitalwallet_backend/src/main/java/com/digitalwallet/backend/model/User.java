package com.digitalwallet.backend.model;

/*
 ORIGINAL:
 @Entity
 @Table(name = "users")
 public class User {
   @Id
   @GeneratedValue
   private UUID id;

   @Column(nullable = false)
   private String fullName;

   @Column(nullable = false, unique = true)
   private String email;

   @Column(nullable = false)
   private String passwordHash;

   @Column(nullable = false)
   private Instant createdAt = Instant.now();

   public User() {}

   public UUID getId() { return id; }
   public void setId(UUID id) { this.id = id; }

   public String getFullName() { return fullName; }
   public void setFullName(String fullName) { this.fullName = fullName; }

   public String getEmail() { return email; }
   public void setEmail(String email) { this.email = email; }

   public String getPasswordHash() { return passwordHash; }
   public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

   public Instant getCreatedAt() { return createdAt; }
   public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
 }
*/

// ---------- UPDATED (keeps original lines as comment above) ----------
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String passwordHash;

  // NEW optional phone number column (kept nullable to avoid migration issues)
  @Column(nullable = true, unique = true, length = 20)
  private String phoneNumber;

  @Column(nullable = false)
  private Instant createdAt = Instant.now();

  public User() {}

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getPasswordHash() { return passwordHash; }
  public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

  public String getPhoneNumber() { return phoneNumber; }
  public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
