package com.solidos.caia.papers.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solidos.caia.papers.dtos.CreatePaperDto;
import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;
import com.solidos.caia.papers.services.PaperService;
import com.solidos.caia.papers.utils.CommonResponse;

import ch.qos.logback.core.status.Status;

@RestController
public class PaperController {

  private final PaperService paperService;

  public PaperController(PaperService paperService) {
    this.paperService = paperService;
  }

  @PostMapping("new")
  public ResponseEntity<CommonResponse<PaperEntity>> createPaper(@RequestHeader String userEmail,
      @ModelAttribute @Validated CreatePaperDto createPaperDto,
      MultipartFile file) {

    PaperEntity paper = paperService.createPaper(userEmail, createPaperDto, file);

    return ResponseEntity.ok().body(CommonResponse.success("Paper created successfully", paper));
  }

  @GetMapping("own-papers")
  public ResponseEntity<CommonResponse<List<PaperEntity>>> findOwnPapers(@RequestHeader String userEmail) {
    List<PaperEntity> papers = paperService.findOwnPapers(userEmail);

    return ResponseEntity.ok().body(CommonResponse.success("Paper found successfully", papers));
  }

  @GetMapping("find/{slug}")
  public PaperEntity getMethodName(@PathVariable Long paperId) {
    return paperService.findPaper(paperId);
  }

  @GetMapping("find-by-conference/{slug}")
  public ResponseEntity<CommonResponse<List<PaperEntity>>> findPapersByConference(
      @PathVariable String slug) {
    List<PaperEntity> papers = paperService.findPapersByConference(slug);

    return ResponseEntity.ok().body(CommonResponse.success("Papers found successfully", papers));
  }

  @PatchMapping("/{paperId}/estado")
  public ResponseEntity<CommonResponse<PaperEntity>> cambiarEstado(
      @PathVariable Long paperId,
      @RequestBody PaperStateEnum nuevoEstado) {
      
      boolean cambioExitoso = paperService.cambiarEstadoPaper(paperId, nuevoEstado);
      
      if (cambioExitoso) {
          PaperEntity paper = paperService.findPaper(paperId);
          return ResponseEntity.ok()
              .body(CommonResponse.success("Estado actualizado exitosamente", paper));
      }
      
      return ResponseEntity.badRequest()
          .body(CommonResponse.errorResponse("No se puede cambiar el estado", Status.ERROR));
  }

}
