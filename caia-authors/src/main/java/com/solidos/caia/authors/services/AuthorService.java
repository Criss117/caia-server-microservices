package com.solidos.caia.authors.services;

import org.springframework.web.multipart.MultipartFile;

import com.solidos.caia.authors.dtos.CreatePaperDto;
import com.solidos.caia.authors.entities.PaperEntity;

public interface AuthorService {
  public PaperEntity createPaper(String userEmail, CreatePaperDto createPaperDto, MultipartFile file);
}
