package com.example.phonestudentproject.service.impl.factory;

import com.example.phonestudentproject.service.api.strategy.ProbabilityStrategy;
import com.example.phonestudentproject.service.impl.strategy.*;
import org.springframework.stereotype.Component;

@Component
public class ProbabilityStrategyFactory {

    public ProbabilityStrategy getStrategy(int callLogSize) {
        if (callLogSize > 10 && callLogSize <= 20) {
            return new LowProbabilityStrategy();
        } else if (callLogSize > 20 && callLogSize <= 30) {
            return new MediumProbabilityStrategy();
        } else if (callLogSize > 30 && callLogSize <= 40){
            return new HighProbabilityStrategy();
        } else if(callLogSize > 40) {
            return new TheHighestProbabilityStrategy();
        } else {
            return new BadProbabilityStrategy();
        }
    }
}
