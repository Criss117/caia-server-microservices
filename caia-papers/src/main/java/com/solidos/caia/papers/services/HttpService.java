package com.solidos.caia.papers.services;

import com.solidos.caia.papers.dtos.FindConfBySlugDto;
import com.solidos.caia.papers.dtos.FindUserByEmailDto;

public interface HttpService {
  public FindConfBySlugDto findConference(String slug);

  public FindUserByEmailDto findUserByEmail(String email);
}
