package com.solidos.caia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;
import com.solidos.caia.papers.states.Resultado;

public class PaperStateTest {
    
    private PaperEntity paper;

    @BeforeEach
    void setUp() {
        paper = new PaperEntity();
        paper.setTitle("Paper de prueba");
        paper.setDescription("Descripción de prueba");
        paper.setFileName("test.pdf");
        paper.setKeys("test,paper");
        paper.setState(PaperStateEnum.FORMULADO);
    }

    // Tests para estado FORMULADO
    @Test
    void testFormuladoAEvaluacion() {
        Resultado resultado = paper.enviarParaEvaluacion();
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.EN_EVALUACION, paper.getState());
        assertNotNull(resultado.mensaje());
    }

    @Test
    void testFormuladoAObservaciones() {
        Resultado resultado = paper.fijarObservacionesArticulo();
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.FORMULADO_CON_OBSERVACIONES, paper.getState());
    }

    @Test
    void testFormuladoTransicionesInvalidas() {
        // No puede ir a Aprobado directamente
        Resultado resultado1 = paper.aprobarArticulo();
        assertFalse(resultado1.cambioPermitido());
        assertEquals(PaperStateEnum.FORMULADO, paper.getState());

        // No puede ir a No Aprobado directamente
        Resultado resultado2 = paper.noAprobarArticulo();
        assertFalse(resultado2.cambioPermitido());
        assertEquals(PaperStateEnum.FORMULADO, paper.getState());
    }

    // Tests para estado EN_EVALUACION
    @Test
    void testEnEvaluacionAAprobado() {
        paper.setState(PaperStateEnum.EN_EVALUACION);
        Resultado resultado = paper.aprobarArticulo();
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.APROBADO, paper.getState());
    }

    @Test
    void testEnEvaluacionANoAprobado() {
        paper.setState(PaperStateEnum.EN_EVALUACION);
        Resultado resultado = paper.noAprobarArticulo();
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.NO_APROBADO, paper.getState());
    }

    @Test
    void testEnEvaluacionAObservaciones() {
        paper.setState(PaperStateEnum.EN_EVALUACION);
        Resultado resultado = paper.fijarObservacionesArticulo();
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.FORMULADO_CON_OBSERVACIONES, paper.getState());
    }

    // Tests para estado FORMULADO_CON_OBSERVACIONES
    @Test
    void testObservacionesAEvaluacion() {
        paper.setState(PaperStateEnum.FORMULADO_CON_OBSERVACIONES);
        Resultado resultado = paper.enviarParaEvaluacion();
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.EN_EVALUACION, paper.getState());
    }

    @Test
    void testObservacionesANoAprobado() {
        paper.setState(PaperStateEnum.FORMULADO_CON_OBSERVACIONES);
        Resultado resultado = paper.noAprobarArticulo();
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.NO_APROBADO, paper.getState());
    }

    // Tests para estado APROBADO
    @Test
    void testAprobadoANoAprobado() {
        paper.setState(PaperStateEnum.APROBADO);
        Resultado resultado = paper.noAprobarArticulo();
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.NO_APROBADO, paper.getState());
    }

    @Test
    void testAprobadoTransicionesInvalidas() {
        paper.setState(PaperStateEnum.APROBADO);
        
        Resultado resultado = paper.enviarParaEvaluacion();
        assertFalse(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.APROBADO, paper.getState());
    }

    // Tests para estado NO_APROBADO
    @Test
    void testNoAprobadoAEvaluacion() {
        paper.setState(PaperStateEnum.NO_APROBADO);
        Resultado resultado = paper.enviarParaEvaluacion();
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.EN_EVALUACION, paper.getState());
    }

    // Tests de verificación de mensajes
    @Test
    void testMensajesResultado() {
        // Verificar mensaje de transición válida
        Resultado resultadoValido = paper.enviarParaEvaluacion();
        assertTrue(resultadoValido.cambioPermitido());
        assertNotNull(resultadoValido.mensaje());
        assertFalse(resultadoValido.mensaje().isEmpty());

        // Verificar mensaje de transición inválida
        paper.setState(PaperStateEnum.FORMULADO);
        Resultado resultadoInvalido = paper.aprobarArticulo();
        assertFalse(resultadoInvalido.cambioPermitido());
        assertNotNull(resultadoInvalido.mensaje());
        assertFalse(resultadoInvalido.mensaje().isEmpty());
    }

    // Test de inicialización
    @Test
    void testInitialState() {
        PaperEntity newPaper = new PaperEntity();
        assertEquals(PaperStateEnum.FORMULADO, newPaper.getState());
    }
}