package com.solidos.caia.papers.states;

import com.solidos.caia.papers.entities.PaperEntity;

public interface EstadoInt {
    Resultado enviarParaEvaluacion(PaperEntity articulo);
    Resultado aprobarArticulo(PaperEntity articulo);
    Resultado fijarObservacionesArticulo(PaperEntity articulo);
    Resultado noAprobarArticulo(PaperEntity articulo);
}