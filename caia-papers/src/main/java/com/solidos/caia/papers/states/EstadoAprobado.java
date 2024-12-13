package com.solidos.caia.papers.states;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;

public class EstadoAprobado implements EstadoInt {

    @Override
    public Resultado enviarParaEvaluacion(PaperEntity articulo) {
        return new Resultado(false, "No se puede enviar a evaluación un artículo ya aprobado");
    }

    @Override
    public Resultado aprobarArticulo(PaperEntity articulo) {
        return new Resultado(false, "El artículo ya está aprobado");
    }

    @Override
    public Resultado fijarObservacionesArticulo(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.FORMULADO_CON_OBSERVACIONES);
        return new Resultado(true, "Se han fijado nuevas observaciones al artículo aprobado");
    }

    @Override
    public Resultado noAprobarArticulo(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.NO_APROBADO);
        return new Resultado(true, "Se ha revocado la aprobación del artículo");
    }
}