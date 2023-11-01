package com.exercise.expressionevaluator.service.api;

public interface ExpressionEvaluatorService {
    String storeExpression(String expressionName, String expressionValue);

    Boolean evaluateObject(String expressionUuid, String evaluationObject);

}