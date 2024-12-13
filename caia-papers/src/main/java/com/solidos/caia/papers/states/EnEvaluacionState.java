package com.solidos.caia.papers.states;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;

public class EnEvaluacionState implements PaperState {
    @Override
    public boolean cambiarEstado(PaperEntity paper, PaperStateEnum nuevoEstado) {
        switch (nuevoEstado) {
            case NO_APROBADO:
            case APROBADO:
                paper.setState(nuevoEstado);
                return true;
            default:
                return false;
        }
    }

    @Override
    public PaperStateEnum getEstado() {
        return PaperStateEnum.EN_EVALUACION;
    }
}