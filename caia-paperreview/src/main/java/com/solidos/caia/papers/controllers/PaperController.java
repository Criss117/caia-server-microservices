package com.solidos.caia.paperreview.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solidos.caia.paperreview.dtos.CreatePaperDto;
import com.solidos.caia.paperreview.entities.PaperEntity;
import com.solidos.caia.paperreview.services.PaperService;
import com.solidos.caia.paperreview.utils.CommonResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class PaperController {

  private final PaperService paperService;

  public PaperController(PaperService paperService) {
    this.paperService = paperService;
  }
  
  @PostMapping("/{paperId}/evaluate")
    public PaperEntity evaluatePaper(
            @PathVariable Long paperId,
            @RequestBody List<Integer> scores) {
        return paperService.evaluatePaper(paperId, scores);
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

}
