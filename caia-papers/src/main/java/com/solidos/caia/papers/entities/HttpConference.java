package com.solidos.caia.papers.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpConference {
  private Long id;
  private String name;
  private String slug;
}
