package com.solidos.caia.conferences.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberComposeId implements Serializable {
  @Column(name = "organizer_id", nullable = false)
  private Long organizerId;

  @Column(name = "conference_id", nullable = false)
  private Long conferenceId;

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    MemberComposeId that = (MemberComposeId) o;
    return Objects.equals(organizerId, that.organizerId) &&
        Objects.equals(conferenceId, that.conferenceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(organizerId, conferenceId);
  }
}
