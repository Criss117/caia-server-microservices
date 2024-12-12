package com.solidos.caia.paperreview.services;

public interface EvaluationStrategy {
    boolean isApproved(double averageScore);
}