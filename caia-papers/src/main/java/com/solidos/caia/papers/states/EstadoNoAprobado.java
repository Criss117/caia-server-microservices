package com.solidos.caia.papers.states;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;

public class EstadoNoAprobado implements EstadoInt {

    @Override
    public Resultado enviarParaEvaluacion(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.EN_EVALUACION);
        return new Resultado(true, "Artículo previamente rechazado enviado a nueva evaluación");
    }

    @Override
    public Resultado aprobarArticulo(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.APROBADO);
        return new Resultado(true, "Artículo previamente rechazado ha sido aprobado");
    }

    @Override
    public Resultado fijarObservacionesArticulo(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.FORMULADO_CON_OBSERVACIONES);
        return new Resultado(true, "Se han fijado observaciones al artículo rechazado");
    }

    @Override
    public Resultado noAprobarArticulo(PaperEntity articulo) {
        return new Resultado(false, "El artículo ya está en estado no aprobado");
    }
}