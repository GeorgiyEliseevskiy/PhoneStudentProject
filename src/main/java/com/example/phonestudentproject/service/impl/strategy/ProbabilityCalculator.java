package com.example.phonestudentproject.service.impl.strategy;

import com.example.phonestudentproject.service.api.strategy.ProbabilityStrategy;

public class ProbabilityCalculator {

    private ProbabilityStrategy strategy;

    // Конструктор для установки начальной стратегии
    public ProbabilityCalculator(ProbabilityStrategy strategy) {
        this.strategy = strategy;
    }

    // Метод установки стратегии
    public void setStrategy(ProbabilityStrategy strategy) {
        this.strategy = strategy;
    }
    public double calculate(int size) {
        return strategy.calculateProbability(size);
    }
}
