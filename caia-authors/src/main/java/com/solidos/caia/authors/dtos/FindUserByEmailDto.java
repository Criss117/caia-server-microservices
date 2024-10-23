package com.solidos.caia.authors.dtos;

import com.solidos.caia.authors.entities.AuthorEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindUserByEmailDto {
  private AuthorEntity user;
  private Boolean isAuthor;

  public static FindUserByEmailDto isAuthor(AuthorEntity user) {
    return builder().user(user).isAuthor(true).build();
  }

  public static FindUserByEmailDto noAuthor(AuthorEntity user) {
    return builder().user(user).isAuthor(false).build();
  }
}
