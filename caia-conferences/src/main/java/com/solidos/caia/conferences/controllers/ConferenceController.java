package com.solidos.caia.conferences.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.conferences.dto.CreateConferenceDto;
import com.solidos.caia.conferences.entities.ConferenceEntity;
import com.solidos.caia.conferences.entities.User;
import com.solidos.caia.conferences.services.ConferenceService;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class ConferenceController {
  private final ConferenceService conferenceService;

  public ConferenceController(ConferenceService conferenceService) {
    this.conferenceService = conferenceService;
  }

  @PostMapping("create")
  public ConferenceEntity createConference(@RequestBody @Validated CreateConferenceDto createConferenceDto,
      @RequestHeader String userEmail) {
    ConferenceEntity conference = conferenceService.createConference(createConferenceDto, userEmail);
    return conference;
  }

}
