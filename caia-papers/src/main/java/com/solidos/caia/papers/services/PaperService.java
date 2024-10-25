package com.solidos.caia.papers.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.solidos.caia.papers.dtos.CreatePaperDto;
import com.solidos.caia.papers.entities.PaperEntity;

public interface PaperService {
  public PaperEntity createPaper(String userEmail, CreatePaperDto createPaperDto, MultipartFile file);

  public List<PaperEntity> findOwnPapers(String userEmail);
}
