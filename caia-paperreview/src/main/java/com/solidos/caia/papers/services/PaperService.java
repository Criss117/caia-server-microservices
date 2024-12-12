package com.solidos.caia.paperreview.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.solidos.caia.papers.dtos.CreatePaperDto;
import com.solidos.caia.papers.entities.PaperEntity;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private EvaluationStrategy evaluationStrategy;

    public Paper evaluatePaper(Long paperId, List<Integer> scores) {
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(() -> new RuntimeException("Paper no encontrado"));

        double average = calculateAverage(scores);
        boolean isApproved = evaluationStrategy.isApproved(average);

        paper.setEstado(isApproved ? "Aprobado" : "No Aprobado");
        paper.setScores(scores);

        return paperRepository.save(paper); // Actualiza y guarda el estado
    }

    private double calculateAverage(List<Integer> scores) {
        return scores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
}