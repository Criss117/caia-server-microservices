package com.solidos.caia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
        paper.setState(PaperStateEnum.FORMULADO); // Usando setState en lugar de setEstado
    }

    @Test
    void testFormuladoAEvaluacion() {
        // Arrange - ya está en FORMULADO por el setUp()
        
        // Act
        Resultado resultado = paper.enviarParaEvaluacion();
        
        // Assert
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.EN_EVALUACION, paper.getState());
    }

    @Test
    void testEnEvaluacionAAprobado() {
        // Arrange
        paper.setState(PaperStateEnum.EN_EVALUACION);
        
        // Act
        Resultado resultado = paper.aprobarArticulo();
        
        // Assert
        assertTrue(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.APROBADO, paper.getState());
    }

    @Test 
    void testFormuladoNoPermiteAprobacion() {
        // Arrange - ya está en FORMULADO por el setUp()
        
        // Act
        Resultado resultado = paper.aprobarArticulo();
        
        // Assert
        assertFalse(resultado.cambioPermitido());
        assertEquals(PaperStateEnum.FORMULADO, paper.getState());
    }
}