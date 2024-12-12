package com.solidos.caia.paperreview.dtos;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePaperDto {
  @NotNull(message = "Title cannot be null")
  private String title;

  @NotNull(message = "Description cannot be null")
  private String description;

  @NotNull(message = "Keys cannot be null")
  private List<String> keys;

  @NotNull(message = "slug cannot be null")
  private String conferenceSlug;
}
