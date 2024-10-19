package com.solidos.caia.users.infraestructure.adapters;

import com.solidos.caia.users.domain.entities.User;
import com.solidos.caia.users.infraestructure.entites.UserEntity;

public class UserAdapter {

  public static UserEntity toEntity(User user) {
    return UserEntity.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .affiliation(user.getAffiliation())
        .password(user.getPassword())
        .token(user.getToken())
        .isEnabled(user.getIsEnabled())
        .accountNoExpired(user.getAccountNoExpired())
        .accountNoLocked(user.getAccountNoLocked())
        .credentialsNoExpired(user.getCredentialsNoExpired())
        .build();
  }

  public static User toDomain(UserEntity userEntity) {
    return User.builder()
        .firstName(userEntity.getFirstName())
        .lastName(userEntity.getLastName())
        .email(userEntity.getEmail())
        .affiliation(userEntity.getAffiliation())
        .password(userEntity.getPassword())
        .token(userEntity.getToken())
        .isEnabled(userEntity.getIsEnabled())
        .accountNoExpired(userEntity.getAccountNoExpired())
        .accountNoLocked(userEntity.getAccountNoLocked())
        .credentialsNoExpired(userEntity.getCredentialsNoExpired())
        .build();
  }
}
