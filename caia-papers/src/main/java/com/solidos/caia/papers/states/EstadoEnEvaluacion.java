package com.solidos.caia.papers.states;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;

public class EstadoEnEvaluacion implements EstadoInt {

    @Override
    public Resultado enviarParaEvaluacion(PaperEntity articulo) {
        return new Resultado(false, "El artículo ya está en evaluación");
    }

    @Override
    public Resultado aprobarArticulo(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.APROBADO);
        return new Resultado(true, "Artículo aprobado exitosamente");
    }

    @Override
    public Resultado fijarObservacionesArticulo(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.FORMULADO_CON_OBSERVACIONES);
        return new Resultado(true, "Se han fijado observaciones al artículo");
    }

    @Override
    public Resultado noAprobarArticulo(PaperEntity articulo) {
        articulo.setState(PaperStateEnum.NO_APROBADO);
        return new Resultado(true, "Artículo no aprobado");
    }
}