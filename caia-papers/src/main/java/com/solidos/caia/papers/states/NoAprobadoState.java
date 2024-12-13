package com.solidos.caia.papers.states;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;

public class NoAprobadoState implements PaperState {
    @Override
    public boolean cambiarEstado(PaperEntity paper, PaperStateEnum nuevoEstado) {
        switch (nuevoEstado) {
            case FORMULADO:
            case FORMULADO_CON_OBSERVACIONES:
                paper.setState(nuevoEstado);
                return true;
            default:
                return false;
        }
    }

    @Override
    public PaperStateEnum getEstado() {
        return PaperStateEnum.NO_APROBADO;
    }
}