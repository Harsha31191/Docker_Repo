package com.digitalwallet.backend.dto;

/*
 ORIGINAL:
 public class RegisterRequest {
   private String fullName;
   private String email;
   private String password;

   public RegisterRequest() {}
   public String getFullName() { return fullName; }
   public void setFullName(String fullName) { this.fullName = fullName; }
   public String getEmail() { return email; }
   public void setEmail(String email) { this.email = email; }
   public String getPassword() { return password; }
   public void setPassword(String password) { this.password = password; }
 }
*/

// ---------- UPDATED (keeps original lines as comment above) ----------
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * RegisterRequest with validation and added optional phoneNumber.
 * Original DTO lines are preserved above as comments.
 */
public class RegisterRequest {
  @NotBlank
  private String fullName;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String password;

  // new optional phone number field
  private String phoneNumber;

  public RegisterRequest() {}

  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
  public String getPhoneNumber() { return phoneNumber; }
  public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
