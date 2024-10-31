package com.solidos.caia.users.infraestructure.mappers;

import com.solidos.caia.users.domain.entities.AuditMetadata;
import com.solidos.caia.users.domain.entities.User;
import com.solidos.caia.users.infraestructure.entites.EntityAuditMetadata;
import com.solidos.caia.users.infraestructure.entites.UserEntity;

public class UserMapper {

  public static UserEntity toEntity(User user) {
    AuditMetadata auditMetadata = user.getAuditMetadata();

    EntityAuditMetadata entityAuditMetadata = EntityAuditMetadata.builder()
        .createdAt(auditMetadata.getCreatedAt())
        .updatedAt(auditMetadata.getUpdatedAt())
        .deletedAt(auditMetadata.getDeletedAt())
        .isEnabled(auditMetadata.getIsEnabled())
        .build();

    return UserEntity.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .affiliation(user.getAffiliation())
        .password(user.getPassword())
        .token(user.getToken())
        .auditMetadata(entityAuditMetadata)
        .build();
  }

  public static User toDomain(UserEntity userEntity) {
    EntityAuditMetadata entityAuditMetadata = userEntity.getAuditMetadata();

    AuditMetadata auditMetadata = AuditMetadata.builder()
        .createdAt(entityAuditMetadata.getCreatedAt())
        .updatedAt(entityAuditMetadata.getUpdatedAt())
        .deletedAt(entityAuditMetadata.getDeletedAt())
        .isEnabled(entityAuditMetadata.getIsEnabled())
        .build();

    return User.builder()
        .id(userEntity.getId())
        .firstName(userEntity.getFirstName())
        .lastName(userEntity.getLastName())
        .email(userEntity.getEmail())
        .affiliation(userEntity.getAffiliation())
        .password(userEntity.getPassword())
        .token(userEntity.getToken())
        .auditMetadata(auditMetadata)
        .build();
  }
}
