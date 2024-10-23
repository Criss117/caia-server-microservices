package com.solidos.caia.authors.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.authors.dtos.FindConfBySlugDto;
import com.solidos.caia.authors.dtos.FindUserByEmailDto;
import com.solidos.caia.authors.entities.AuthorEntity;
import com.solidos.caia.authors.entities.ConferenceEntity;
import com.solidos.caia.authors.repositories.AuthorRepository;
import com.solidos.caia.authors.repositories.ConferenceHttpClient;
import com.solidos.caia.authors.repositories.ConferenceRepository;
import com.solidos.caia.authors.repositories.UserHttpClient;
import com.solidos.caia.authors.utils.CommonResponse;

@Service
public class HttpServiceImpl implements HttpService {
  private final UserHttpClient userHttpClient;
  private final ConferenceHttpClient conferenceHttpClient;
  private final AuthorRepository authorRepository;
  private final ConferenceRepository conferenceRepository;

  public HttpServiceImpl(
      UserHttpClient userHttpClient,
      ConferenceHttpClient conferenceHttpClient,
      AuthorRepository authorRepository,
      ConferenceRepository conferenceRepository) {
    this.userHttpClient = userHttpClient;
    this.conferenceHttpClient = conferenceHttpClient;
    this.authorRepository = authorRepository;
    this.conferenceRepository = conferenceRepository;
  }

  @Override
  public FindConfBySlugDto findConference(String slug) {
    Optional<ConferenceEntity> conference = conferenceRepository.findBySlug(slug);

    if (conference.isPresent()) {
      return FindConfBySlugDto.present(conference.get());
    }

    CommonResponse<ConferenceEntity> response = conferenceHttpClient.publicFindConference(slug).getBody();

    if (response == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conference not found");
    }

    return FindConfBySlugDto.notPresent(response.getData());
  }

  @Override
  public FindUserByEmailDto findUserByEmail(String email) {
    Optional<AuthorEntity> author = authorRepository.findByEmail(email);

    if (author.isPresent()) {
      return FindUserByEmailDto.isAuthor(author.get());
    }

    CommonResponse<AuthorEntity> response = userHttpClient.findByEmail(email).getBody();

    if (response == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    return FindUserByEmailDto.noAuthor(response.getData());
  }
}
