package com.solidos.caia.papers.states;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;

public class EstadoFormuladoConObservaciones implements EstadoInt {

    @Override
    public Resultado enviarParaEvaluacion(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.EN_EVALUACION);
        return new Resultado(true, "Artículo con observaciones enviado a evaluación");
    }

    @Override
    public Resultado aprobarArticulo(PaperEntity articulo) {
        return new Resultado(false, "No se puede aprobar un artículo que tiene observaciones pendientes");
    }

    @Override
    public Resultado fijarObservacionesArticulo(PaperEntity articulo) {
        return new Resultado(false, "El artículo ya tiene observaciones fijadas");
    }

    @Override
    public Resultado noAprobarArticulo(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.NO_APROBADO);
        return new Resultado(true, "Artículo rechazado debido a las observaciones");
    }
}