package com.solidos.caia.conferences.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.conferences.dto.CreateConferenceDto;
import com.solidos.caia.conferences.entities.ConferenceEntity;
import com.solidos.caia.conferences.services.ConferenceService;
import com.solidos.caia.conferences.utils.CommonResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ConferenceController {
  private final ConferenceService conferenceService;

  public ConferenceController(ConferenceService conferenceService) {
    this.conferenceService = conferenceService;
  }

  @PostMapping("create")
  public ResponseEntity<CommonResponse<ConferenceEntity>> createConference(
      @RequestBody @Validated CreateConferenceDto createConferenceDto,
      @RequestHeader String userEmail) {
    ConferenceEntity conference = conferenceService.createConference(createConferenceDto, userEmail);
    return ResponseEntity.ok().body(CommonResponse.success("Conference created successfully", conference));
  }

  @GetMapping("public")
  public ResponseEntity<CommonResponse<List<ConferenceEntity>>> findManyWithOutAuth() {
    List<ConferenceEntity> conferences = conferenceService.findMany();

    return ResponseEntity.ok().body(CommonResponse.success("Conferences found successfully", conferences));
  }

  @GetMapping("find")
  public ResponseEntity<CommonResponse<List<ConferenceEntity>>> findMany(@RequestHeader String userEmail) {
    List<ConferenceEntity> conferences = conferenceService.findMany(userEmail);

    return ResponseEntity.ok().body(CommonResponse.success("Conferences found successfully", conferences));
  }

}
