package com.solidos.caia.papers.states;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;

public interface PaperState {
    boolean cambiarEstado(PaperEntity paper, PaperStateEnum nuevoEstado);
    PaperStateEnum getEstado();
}