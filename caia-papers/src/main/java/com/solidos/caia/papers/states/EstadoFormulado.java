package com.solidos.caia.papers.states;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;

public class EstadoFormulado implements EstadoInt {

    @Override
    public Resultado enviarParaEvaluacion(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.EN_EVALUACION);
        return new Resultado(true, "Artículo enviado a evaluación exitosamente");
    }

    @Override
    public Resultado aprobarArticulo(PaperEntity articulo) {
        return new Resultado(false, "Un artículo en estado Formulado no puede ser aprobado directamente");
    }

    @Override
    public Resultado fijarObservacionesArticulo(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.FORMULADO_CON_OBSERVACIONES);
        return new Resultado(true, "Se han fijado observaciones al artículo");
    }

    @Override
    public Resultado noAprobarArticulo(PaperEntity articulo) {
        return new Resultado(false, "Un artículo en estado Formulado no puede ser rechazado directamente");
    }
}