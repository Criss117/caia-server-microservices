package com.solidos.caia.papers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.solidos.caia.papers.enums.PaperStateEnum;
import com.solidos.caia.papers.states.FormuladoState;
import com.solidos.caia.papers.states.FormuladoConObservacionesState;
import com.solidos.caia.papers.states.PaperState;
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

  @ManyToOne
  @JoinColumn(name = "owner_id", updatable = false)
  @JsonBackReference
  private AuthorEntity authorEntity;

  @ManyToOne
  @JoinColumn(name = "conference_id", updatable = false)
  private ConferenceEntity conferenceEntity;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String fileName;

  @Column(nullable = false)
  private String keys;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private PaperStateEnum state = PaperStateEnum.EN_EVALUACION;

  @Embedded
  private AuditMetadata auditMetadata;




  // Agregar este método a PaperEntity.java
private PaperState paperState;

public void setEstado(PaperStateEnum estado) {
    this.state = estado;
    switch (estado) {
        case FORMULADO:
            this.paperState = new FormuladoState();
            break;
        case FORMULADO_CON_OBSERVACIONES:
            this.paperState = new FormuladoConObservacionesState();
            break;
        // ... otros casos
    }
}

public boolean cambiarEstado(PaperStateEnum nuevoEstado) {
    return paperState.cambiarEstado(this, nuevoEstado);
}
}
