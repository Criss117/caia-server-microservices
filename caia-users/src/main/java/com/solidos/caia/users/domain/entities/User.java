package com.solidos.caia.users.domain.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String affiliation;
  private String password;
  private String token;

  @Builder.Default
  private Boolean isEnabled = false;

  @Builder.Default
  private Boolean accountNoExpired = false;

  @Builder.Default
  private Boolean accountNoLocked = false;

  @Builder.Default
  private Boolean credentialsNoExpired = false;

  public void confirmAccount() {
    this.token = null;
    this.isEnabled = true;
    this.accountNoExpired = true;
    this.accountNoLocked = true;
    this.credentialsNoExpired = true;
  }

}
