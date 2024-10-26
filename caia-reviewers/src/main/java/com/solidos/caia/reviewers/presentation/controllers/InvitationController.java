package com.solidos.caia.reviewers.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.reviewers.application.dtos.ResponseInvitationDto;
import com.solidos.caia.reviewers.application.dtos.SendInvitationDto;
import com.solidos.caia.reviewers.application.services.InvitationService;
import com.solidos.caia.reviewers.utils.CommonResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/invitations")
public class InvitationController {
  private final InvitationService reviewInvitationService;

  public InvitationController(InvitationService reviewInvitationService) {
    this.reviewInvitationService = reviewInvitationService;
  }

  @PostMapping("send")
  public ResponseEntity<CommonResponse<Void>> sendInvitaion(
      @RequestBody @Validated SendInvitationDto sendInvitationDto) {
    reviewInvitationService.sendInvitaion(sendInvitationDto);

    return ResponseEntity.ok(CommonResponse.success("Invitation sent successfully"));
  }

  @PostMapping("response/{token}")
  public ResponseEntity<CommonResponse<Void>> postMethodName(@PathVariable String token,
      @RequestBody @Validated ResponseInvitationDto responseInvitationDto) {

    reviewInvitationService.responseInvitation(token, responseInvitationDto);

    return ResponseEntity.ok(CommonResponse.success("Invitation responded"));

  }
}
