package com.solidos.caia.users.application.adapters;

import com.solidos.caia.users.application.dtos.SignUpDto;
import com.solidos.caia.users.domain.entities.User;

public class UserAppAdapter {

  public static User signupDtoToDomain(SignUpDto signUpDto) {
    return User.builder()
        .firstName(signUpDto.getFirstName())
        .lastName(signUpDto.getLastName())
        .email(signUpDto.getEmail())
        .affiliation(signUpDto.getAffiliation())
        .password(signUpDto.getPassword())
        .build();
  }

}
