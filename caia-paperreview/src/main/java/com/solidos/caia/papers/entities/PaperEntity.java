package com.solidos.caia.paperreview.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.solidos.caia.papers.enums.PaperStateEnum;
import com.solidos.caia.papers.utils.AuditMetadata;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "papers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaperEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
    private String description;

    @ElementCollection
    private List<Integer> scores;

    private String estado;
}
