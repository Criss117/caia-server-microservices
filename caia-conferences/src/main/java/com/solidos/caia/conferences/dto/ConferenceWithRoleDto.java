package com.solidos.caia.conferences.dto;

import com.solidos.caia.conferences.entities.ConferenceEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConferenceWithRoleDto {
  private ConferenceEntity conference;
  private Boolean isOrganizer;
}
