package com.solidos.caia.authors.services;

import com.solidos.caia.authors.dtos.FindConfBySlugDto;
import com.solidos.caia.authors.dtos.FindUserByEmailDto;

public interface HttpService {
  public FindConfBySlugDto findConference(String slug);

  public FindUserByEmailDto findUserByEmail(String email);
}
