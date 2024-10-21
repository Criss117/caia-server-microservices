package com.solidos.caia.conferences.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.solidos.caia.conferences.adapters.OrganizerAdapter;
import com.solidos.caia.conferences.dto.CreateConferenceDto;
import com.solidos.caia.conferences.entities.ConferenceEntity;
import com.solidos.caia.conferences.entities.MemberComposeId;
import com.solidos.caia.conferences.entities.MemberEntity;
import com.solidos.caia.conferences.entities.OrganizerEntity;
import com.solidos.caia.conferences.entities.User;
import com.solidos.caia.conferences.repositories.ConferenceRepository;
import com.solidos.caia.conferences.repositories.MemberRepository;
import com.solidos.caia.conferences.repositories.OrganizerRepository;
import com.solidos.caia.conferences.repositories.UserHttpClient;
import com.solidos.caia.conferences.utils.CommonResponse;
import com.solidos.caia.conferences.utils.SlugGenerator;

import jakarta.transaction.Transactional;

@Service
public class ConferenceServiceImpl implements ConferenceService {
  private final ConferenceRepository conferencesRepository;
  private final MemberRepository memberRepository;
  private final OrganizerRepository organizerRepository;
  private final UserHttpClient userHttpClient;

  public ConferenceServiceImpl(ConferenceRepository conferencesRepository, UserHttpClient userHttpClient,
      OrganizerRepository organizerRepository, MemberRepository memberRepository) {
    this.conferencesRepository = conferencesRepository;
    this.userHttpClient = userHttpClient;
    this.organizerRepository = organizerRepository;
    this.memberRepository = memberRepository;
  }

  @Transactional
  public ConferenceEntity createConference(CreateConferenceDto createConferenceDto, String userEmail) {
    FindUserByEmailResponse user = findUserByEmail(userEmail);

    ConferenceEntity conference = conferencesRepository.save(ConferenceEntity.builder()
        .name(createConferenceDto.getName())
        .slug(SlugGenerator.generate(createConferenceDto.getName()))
        .description(createConferenceDto.getDescription())
        .build());

    OrganizerEntity organizer = user.getOrganizer();
    if (!user.isUserIsOrganizer()) {

      organizer = organizerRepository.save(OrganizerEntity.builder()
          .firstName(organizer.getFirstName())
          .lastName(organizer.getLastName())
          .email(organizer.getEmail())
          .affiliation(organizer.getAffiliation())
          .build());
    }

    memberRepository.save(MemberEntity.builder()
        .memberComposeId(
            MemberComposeId.builder()
                .conferenceId(conference.getId())
                .organizerId(organizer.getId())
                .build())
        .build());

    return conference;
  }

  public FindUserByEmailResponse findUserByEmail(String email) {
    Optional<OrganizerEntity> organizer = organizerRepository.findByEmail(email);

    if (organizer.isPresent()) {
      return FindUserByEmailResponse.of(organizer.get(), true);
    }

    CommonResponse<User> response = userHttpClient.findByEmail(email).getBody();

    if (response == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    User user = response.getData();

    return FindUserByEmailResponse.of(OrganizerAdapter.domainToEntity(user), false);
  }

  public List<ConferenceEntity> findMany() {
    return conferencesRepository.findMany();
  }

  public List<ConferenceEntity> findMany(String email) {
    OrganizerEntity organizer = organizerRepository.findByEmail(email).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found"));

    List<MemberEntity> members = memberRepository.findConferencesIdByConferencesId(organizer.getId());

    return members.stream().map(
        member -> member.getConferenceEntity()).toList();
  }
}
