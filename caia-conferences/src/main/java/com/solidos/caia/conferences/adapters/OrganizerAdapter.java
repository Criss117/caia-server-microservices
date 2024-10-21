package com.solidos.caia.conferences.adapters;

import com.solidos.caia.conferences.entities.OrganizerEntity;
import com.solidos.caia.conferences.entities.User;

public class OrganizerAdapter {
  public static OrganizerEntity domainToEntity(User user) {
    return OrganizerEntity.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .affiliation(user.getAffiliation())
        .build();
  }
}
