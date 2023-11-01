package com.exercise.expressionevaluator.service.impl;

import com.exercise.expressionevaluator.dao.model.Expression;
import com.exercise.expressionevaluator.dao.repository.ExpressionRepository;
import com.exercise.expressionevaluator.service.api.ExpressionEvaluatorService;
import com.exercise.expressionevaluator.util.ExpressionEvaluatorHelper;
import com.exercise.expressionevaluator.util.ServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Profile("dev")
public class ExpressionEvaluatorServiceImpl implements ExpressionEvaluatorService {
    private static final Logger logger = LoggerFactory.getLogger(ExpressionEvaluatorServiceImpl.class);

    private final ExpressionRepository expressionRepository;
    private final ExpressionEvaluatorHelper expressionEvaluatorHelper;

    @Autowired
    public ExpressionEvaluatorServiceImpl(final ExpressionRepository expressionRepository,
                                          final ExpressionEvaluatorHelper expressionEvaluatorHelper) {
        this.expressionRepository = expressionRepository;
        this.expressionEvaluatorHelper = expressionEvaluatorHelper;
    }

    @Override
    public String storeExpression(String expressionName, String expressionValue) {
        String uuid = UUID.randomUUID().toString();

        Expression expression = new Expression();
        expression.setName(expressionName);
        expression.setExpValue(expressionValue);
        expression.setUuid(uuid);
        expressionRepository.save(expression);

        logger.info("Successfully stored expression: [name] " + expressionName);

        return uuid;
    }

    @Override
    public Boolean evaluateObject(String expressionUuid, String evaluationObject) {

        Expression expression = expressionRepository.findExpressionByUuid(expressionUuid);
        logger.info("Expression found: [name] " + expression.getName());

        return expressionEvaluatorHelper.evaluateObjectWithExpression(expression.getExpValue(), evaluationObject);
    }

}
