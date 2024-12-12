package com.solidos.caia.paperreview.services;

import org.springframework.stereotype.Component;

@Component
public class DefaultEvaluationStrategy implements EvaluationStrategy {

    @Override
    public boolean isApproved(double averageScore) {
        return averageScore > 6;
    }
}
