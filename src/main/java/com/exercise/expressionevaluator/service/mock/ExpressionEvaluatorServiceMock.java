package com.exercise.expressionevaluator.service.mock;

import com.exercise.expressionevaluator.service.api.ExpressionEvaluatorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Profile("mock")
public class ExpressionEvaluatorServiceMock implements ExpressionEvaluatorService {
    @Override
    public String storeExpression(String expressionName, String expressionValue) {
        return UUID.randomUUID().toString();
    }

    @Override
    public Boolean evaluateObject(String expressionUuid, String evaluationObject) {
        return Math.random() < 0.5;
    }
}
