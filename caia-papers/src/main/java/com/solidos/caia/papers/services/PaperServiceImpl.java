package com.solidos.caia.papers.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.papers.dtos.CreatePaperDto;
import com.solidos.caia.papers.dtos.FindConfBySlugDto;
import com.solidos.caia.papers.dtos.FindUserByEmailDto;
import com.solidos.caia.papers.entities.AuthorEntity;
import com.solidos.caia.papers.entities.ConferenceEntity;
import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.repositories.AuthorRepository;
import com.solidos.caia.papers.repositories.ConferenceRepository;
import com.solidos.caia.papers.repositories.PaperRepository;
import com.solidos.caia.papers.utils.PaperKeys;

import jakarta.transaction.Transactional;

@Service
public class PaperServiceImpl implements PaperService {

  private final FileService fileService;
  private final HttpService httpService;
  private final AuthorRepository authorRepository;
  private final ConferenceRepository conferenceRepository;
  private final PaperRepository paperRepository;

  public PaperServiceImpl(
      FileService fileService,
      HttpService httpService,
      AuthorRepository authorRepository,
      ConferenceRepository conferenceRepository,
      PaperRepository paperRepository) {
    this.fileService = fileService;
    this.httpService = httpService;
    this.authorRepository = authorRepository;
    this.conferenceRepository = conferenceRepository;
    this.paperRepository = paperRepository;
  }

  @Transactional
  @Override
  public PaperEntity createPaper(String userEmail, CreatePaperDto createPaperDto, MultipartFile file) {
    FindUserByEmailDto user = httpService.findUserByEmail(userEmail);
    FindConfBySlugDto conference = httpService.findConference(createPaperDto.getConferenceSlug());

    String fileName = fileService.uploadFile(file);

    PaperEntity paperEntity = PaperEntity.builder()
        .title(createPaperDto.getTitle())
        .description(createPaperDto.getDescription())
        .fileName(fileName)
        .keys(PaperKeys.toString(createPaperDto.getKeys()))
        .build();

    try {
      if (!user.getIsAuthor()) {
        AuthorEntity newAuthor = authorRepository.save(user.getUser());
        user.setUser(newAuthor);
      }

      if (!conference.getIsPresent()) {
        ConferenceEntity newConference = conferenceRepository.save(conference.getConference());
        conference.setConference(newConference);
      }

      paperEntity.setAuthorEntity(user.getUser());
      paperEntity.setConferenceEntity(conference.getConference());

      PaperEntity savedPaper = paperRepository.save(paperEntity);

      return savedPaper;
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating paper");
    }
  }

  @Override
  public List<PaperEntity> findOwnPapers(String userEmail) {
    AuthorEntity author = authorRepository.findByEmail(userEmail)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    return paperRepository.findByAuthorId(author.getId()).stream().map(paper -> {
      return PaperEntity.builder()
          .id(paper.getId())
          .title(paper.getTitle())
          .description(paper.getDescription())
          .fileName(paper.getFileName())
          .conferenceEntity(paper.getConferenceEntity())
          .auditMetadata(paper.getAuditMetadata())
          .build();
    }).toList();
  }

  @Override
  public PaperEntity findPaper(Long id) {
    return paperRepository.findPaper(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paper not found"));
  }

}
