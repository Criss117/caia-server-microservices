package com.solidos.caia.papers.states;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;

public class AprobadoState implements PaperState {
    @Override
    public boolean cambiarEstado(PaperEntity paper, PaperStateEnum nuevoEstado) {
        return false; // No se puede cambiar el estado una vez aprobado
    }

    @Override
    public PaperStateEnum getEstado() {
        return PaperStateEnum.APROBADO;
    }
}