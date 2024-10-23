package com.solidos.caia.authors.dtos;

import com.solidos.caia.authors.entities.ConferenceEntity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FindConfBySlugDto {
  private ConferenceEntity conference;
  private Boolean isPresent;

  public static FindConfBySlugDto present(ConferenceEntity conference) {
    return FindConfBySlugDto.builder()
        .conference(conference)
        .isPresent(true)
        .build();
  }

  public static FindConfBySlugDto notPresent(ConferenceEntity conference) {
    return FindConfBySlugDto.builder()
        .conference(conference)
        .isPresent(false)
        .build();
  }
}
