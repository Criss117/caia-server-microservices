package com.solidos.caia.conferences.services;

import com.solidos.caia.conferences.entities.OrganizerEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserByEmailResponse {
  private boolean userIsOrganizer;
  private OrganizerEntity organizer;

  public static FindUserByEmailResponse of(OrganizerEntity organizer, boolean userIsOrganizer) {
    return FindUserByEmailResponse.builder()
        .userIsOrganizer(userIsOrganizer)
        .organizer(organizer)
        .build();
  }
}
