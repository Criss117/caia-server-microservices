package com.solidos.caia.papers.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.solidos.caia.papers.enums.PaperStateEnum;
import com.solidos.caia.papers.states.*;
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
import jakarta.persistence.Transient;
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
    private PaperStateEnum state = PaperStateEnum.FORMULADO;

    @Embedded
    private AuditMetadata auditMetadata;

    @Transient
    private EstadoInt estado;

    public void setState(PaperStateEnum state) {
        this.state = state;
        initializeEstado();
    }

    private void initializeEstado() {
        switch (this.state) {
            case FORMULADO:
                this.estado = new EstadoFormulado();
                break;
            case EN_EVALUACION:
                this.estado = new EstadoEnEvaluacion();
                break;
            case FORMULADO_CON_OBSERVACIONES:
                this.estado = new EstadoFormuladoConObservaciones();
                break;
            case APROBADO:
                this.estado = new EstadoAprobado();
                break;
            case NO_APROBADO:
                this.estado = new EstadoNoAprobado();
                break;
        }
    }

    public Resultado enviarParaEvaluacion() {
        if (estado == null) {
            initializeEstado();
        }
        return estado.enviarParaEvaluacion(this);
    }

    public Resultado aprobarArticulo() {
        if (estado == null) {
            initializeEstado();
        }
        return estado.aprobarArticulo(this);
    }

    public Resultado fijarObservacionesArticulo() {
        if (estado == null) {
            initializeEstado();
        }
        return estado.fijarObservacionesArticulo(this);
    }

    public Resultado noAprobarArticulo() {
        if (estado == null) {
            initializeEstado();
        }
        return estado.noAprobarArticulo(this);
    }


    

    // Método auxiliar para facilitar la creación en el builder
    private void initializeOnCreate() {
        if (this.state == null) {
            this.state = PaperStateEnum.FORMULADO;
        }
        initializeEstado();
    }
}