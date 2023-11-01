package com.exercise.expressionevaluator.domain.request;

public class EvaluateExpressionRequest {
    private String expressionUuid;
    private String evaluationObject;

    public String getExpressionUuid() {
        return expressionUuid;
    }

    public void setExpressionUuid(String expressionUuid) {
        this.expressionUuid = expressionUuid;
    }

    public String getEvaluationObject() {
        return evaluationObject;
    }

    public void setEvaluationObject(String evaluationObject) {
        this.evaluationObject = evaluationObject;
    }
}
