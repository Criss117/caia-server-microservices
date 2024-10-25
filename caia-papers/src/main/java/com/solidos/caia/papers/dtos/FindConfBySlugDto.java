package com.solidos.caia.papers.dtos;

import com.solidos.caia.papers.entities.ConferenceEntity;

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
