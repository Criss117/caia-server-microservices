package com.solidos.caia.authors.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solidos.caia.authors.dtos.CreatePaperDto;
import com.solidos.caia.authors.entities.PaperEntity;
import com.solidos.caia.authors.services.AuthorService;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @PostMapping("new")
  public PaperEntity createPaper(@RequestHeader String userEmail,
      @ModelAttribute @Validated CreatePaperDto createPaperDto,
      MultipartFile file) {

    return authorService.createPaper(userEmail, createPaperDto, file);
  }

  @GetMapping("own-papers")
  public List<PaperEntity> findOwnPapers(@RequestHeader String userEmail) {
    return authorService.findOwnPapers(userEmail);
  }
}
