package com.exercise.expressionevaluator.api;

import com.exercise.expressionevaluator.domain.ResponseStatus;
import com.exercise.expressionevaluator.domain.ServiceResponseWrapper;
import com.exercise.expressionevaluator.domain.request.EvaluateExpressionRequest;
import com.exercise.expressionevaluator.domain.request.StoreExpressionRequest;
import com.exercise.expressionevaluator.service.api.ExpressionEvaluatorService;
import com.exercise.expressionevaluator.util.RequestValidationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExpressionEvaluatorRestController {

    private final ExpressionEvaluatorService expressionEvaluatorService;
    private final RequestValidationHelper requestValidationHelper;
    private static final Logger logger = LoggerFactory.getLogger(ExpressionEvaluatorRestController.class);

    @Autowired
    public ExpressionEvaluatorRestController(final ExpressionEvaluatorService expressionEvaluatorService,
                                             final RequestValidationHelper requestValidationHelper) {
        this.expressionEvaluatorService = expressionEvaluatorService;
        this.requestValidationHelper = requestValidationHelper;
    }

    @PostMapping(value = "/expression")
    public ServiceResponseWrapper<String> storeExpression(@RequestBody final StoreExpressionRequest request) {

        String validationResponse = requestValidationHelper.validateStoreExpressionRequest(request);
        if (validationResponse != null) {
            return new ServiceResponseWrapper<>(ResponseStatus.ERROR, validationResponse);
        }

        logger.info("storeExpression request name: " + request.getName() + ", value: " + request.getValue());
        String id = expressionEvaluatorService.storeExpression(request.getName(), request.getValue());
        logger.info("storeExpression response id: " + id);
        return new ServiceResponseWrapper<>(id);
    }

    @PostMapping(value = "/evaluate")
    public ServiceResponseWrapper<Boolean> evaluateObject(@RequestBody final EvaluateExpressionRequest request) {
        String validationResponse = requestValidationHelper.validateEvaluateObjectRequest(request);
        if (validationResponse != null) {
            return new ServiceResponseWrapper<>(ResponseStatus.ERROR, validationResponse);
        }

        logger.info("evaluateObject request id: " + request.getExpressionUuid() + ", obj: " + request.getEvaluationObject());
        Boolean response = expressionEvaluatorService.evaluateObject(request.getExpressionUuid(), request.getEvaluationObject());
        logger.info("evaluateObject response: " + response);
        return new ServiceResponseWrapper<>(response);
    }


}
