package com.solidos.caia.conferences.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateConferenceDto {
  @NotBlank(message = "Name cannot be blank")
  @NotNull(message = "Name cannot be null")
  private String name;

  @NotBlank(message = "Description cannot be blank")
  @NotNull(message = "Description cannot be null")
  @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
  private String description;
}
