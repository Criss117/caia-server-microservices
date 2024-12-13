package com.solidos.caia.papers.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.solidos.caia.papers.dtos.CreatePaperDto;
import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;

public interface PaperService {
  public PaperEntity createPaper(String userEmail, CreatePaperDto createPaperDto, MultipartFile file);

  public List<PaperEntity> findOwnPapers(String userEmail);

  public PaperEntity findPaper(Long id);

  public List<PaperEntity> findPapersByConference(String slug);

  boolean cambiarEstadoPaper(Long paperId, PaperStateEnum nuevoEstado);

}
