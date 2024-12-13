package com.solidos.caia.papers.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solidos.caia.papers.entities.PaperEntity;
import com.solidos.caia.papers.enums.PaperStateEnum;
import com.solidos.caia.papers.services.PaperService;
import com.solidos.caia.papers.states.Resultado;
import com.solidos.caia.papers.utils.CommonResponse;

@RestController
@RequestMapping("/papers")
public class PaperController {
    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @PatchMapping("/{paperId}/estado")
    public ResponseEntity<CommonResponse<PaperEntity>> cambiarEstado(
        @PathVariable Long paperId,
        @RequestBody PaperStateEnum nuevoEstado) {
        
        Resultado resultado = paperService.cambiarEstadoPaper(paperId, nuevoEstado);
        
        if (resultado.cambioPermitido()) {
            PaperEntity paper = paperService.findPaper(paperId);
            return ResponseEntity.ok()
                .body(CommonResponse.success(resultado.mensaje(), paper));
        }
        
        return ResponseEntity.badRequest()
            .body(CommonResponse.errorResponse("error", 0));
    }
}