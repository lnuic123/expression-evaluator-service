package com.exercise.expressionevaluator.util;

import com.exercise.expressionevaluator.dao.repository.ExpressionRepository;
import com.exercise.expressionevaluator.domain.request.EvaluateExpressionRequest;
import com.exercise.expressionevaluator.domain.request.StoreExpressionRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class RequestValidationHelper {
    private final ExpressionRepository expressionRepository;

    public RequestValidationHelper(final ExpressionRepository expressionRepository) {
        this.expressionRepository = expressionRepository;
    }

    public String validateStoreExpressionRequest(final StoreExpressionRequest request){
        if(request == null) {
            return "Request body empty";
        }
        if(StringUtils.isEmpty(request.getName())){
            return "Expression name empty";
        }
        if(expressionRepository.findExpressionByName(request.getName()) != null){
            return "Expression name already taken";
        }
        return null;
    }

    public String validateEvaluateObjectRequest(final EvaluateExpressionRequest request){
        if(request == null){
            return "Request body empty";
        }
        if(StringUtils.isEmpty(request.getExpressionUuid())){
            return "Expression uuid empty";
        }
        if(request.getEvaluationObject() == null){
            return "Evaluation object empty";
        }
        return null;
    }
}
