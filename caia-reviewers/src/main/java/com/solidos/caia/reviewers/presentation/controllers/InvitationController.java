package com.solidos.caia.reviewers.presentation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.reviewers.application.dtos.ResponseInvitationDto;
import com.solidos.caia.reviewers.application.dtos.SendInvitationDto;
import com.solidos.caia.reviewers.application.services.InvitationService;
import com.solidos.caia.reviewers.domain.entities.Invitation;
import com.solidos.caia.reviewers.utils.CommonResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/invitations")
public class InvitationController {
  private final InvitationService invitationService;

  public InvitationController(InvitationService invitationService) {
    this.invitationService = invitationService;
  }

  @PostMapping("send")
  public ResponseEntity<CommonResponse<Void>> sendInvitaion(
      @RequestBody @Validated SendInvitationDto sendInvitationDto) {
    invitationService.sendInvitaion(sendInvitationDto);

    return ResponseEntity.ok(CommonResponse.success("Invitation sent successfully"));
  }

  @PostMapping("response/{token}")
  public ResponseEntity<CommonResponse<Void>> postMethodName(
      @RequestHeader String userEmail,
      @PathVariable String token,
      @RequestBody @Validated ResponseInvitationDto responseInvitationDto) {

    invitationService.responseInvitation(userEmail, token, responseInvitationDto);

    return ResponseEntity.ok(CommonResponse.success("Invitation responded"));
  }

  @GetMapping("find-own")
  public ResponseEntity<CommonResponse<List<Invitation>>> findOwn(@RequestHeader String userEmail) {
    List<Invitation> invitations = invitationService.findByReviewer(userEmail);

    return ResponseEntity.ok().body(CommonResponse.success("Invitations found successfully", invitations));
  }

}
