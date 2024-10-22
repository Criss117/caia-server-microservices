package com.solidos.caia.conferences.services;

import java.util.List;

import com.solidos.caia.conferences.dto.CreateConferenceDto;
import com.solidos.caia.conferences.entities.ConferenceEntity;

public interface ConferenceService {
  public ConferenceEntity createConference(CreateConferenceDto createConferenceDto, String userEmail);

  public FindUserByEmailResponse findUserByEmail(String email);

  public List<ConferenceEntity> findMany();

  public List<ConferenceEntity> findMany(String email);

  public ConferenceEntity findBySlug(String slug);
}