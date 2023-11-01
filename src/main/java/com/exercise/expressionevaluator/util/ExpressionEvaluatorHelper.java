package com.exercise.expressionevaluator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExpressionEvaluatorHelper {

    public Boolean evaluateObjectWithExpression(String expression, final String evaluationObject) {

        while (expression.contains("(")) {
            String toValidate = findSubstringToValidate(expression);

            Boolean validationResult = validateSubString(toValidate, evaluationObject);

            expression = expression.replace("(" + toValidate + ")", validationResult.toString());

        }
        return validateSubString(expression, evaluationObject);
    }

    private Boolean validateSubString(String substr, String evaluationObject) {


        String[] orValues = substr.split(ServiceConstants.OR_OPERATOR);

        boolean[] orValuesBool = validateOrValues(orValues, evaluationObject);

        for (boolean b : orValuesBool) if (b) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    private boolean[] validateOrValues(String[] orValues, String evaluationObject) {
        boolean[] orValuesBool = new boolean[orValues.length];

        for (int i = 0; i < orValues.length; i++) {
            if (ServiceConstants.TRUE.equals(orValues[i]) || ServiceConstants.FALSE.equals(orValues[i])) {
                orValuesBool[i] = ServiceConstants.TRUE.equals(orValues[i]);
            }
            else {
                String[] andVars = orValues[i].split(ServiceConstants.AND_OPERATOR);
                boolean[] andVarsBool = validateAndValues(andVars, evaluationObject);

                orValuesBool[i] = true;
                for (boolean b : andVarsBool)
                    if (!b) {
                        orValuesBool[i] = false;
                        break;
                    }
            }
        }
        return orValuesBool;
    }

    private boolean[] validateAndValues(String[] andValues, String evaluationObject) {
        boolean[] andValuesBool = new boolean[andValues.length];

        for (int i = 0; i < andValues.length; i++) {
            if (ServiceConstants.TRUE.equals(andValues[i]) || ServiceConstants.FALSE.equals(andValues[i])) {
                andValuesBool[i] = ServiceConstants.TRUE.equals(andValues[i]);
            }
            else {
                for (String operator : ServiceConstants.comparisonOperators) {
                    if (andValues[i].contains(operator)) {
                        String[] temp = andValues[i].split(operator);
                        String value = getValueFromObject(temp[0], evaluationObject);

                        andValuesBool[i] = compareValues(value, temp[1], operator);
                    }
                }
            }
        }
        return andValuesBool;
    }

    private Boolean compareValues(String expressionValue, String objectValue, String operator) {
        return switch (operator) {
            case ServiceConstants.EQUALS_OPERATOR ->
                    expressionValue.equals(removeQuotationsFromString(objectValue));
            case ServiceConstants.NOT_EQUALS_OPERATOR ->
                    !expressionValue.equals(removeQuotationsFromString(objectValue));
            case ServiceConstants.LESS_OPERATOR ->
                    Integer.parseInt(expressionValue) < Integer.parseInt(objectValue);
            case ServiceConstants.LESS_EQUALS_OPERATOR ->
                    Integer.parseInt(expressionValue) <= Integer.parseInt(objectValue);
            case ServiceConstants.MORE_OPERATOR ->
                    Integer.parseInt(expressionValue) > Integer.parseInt(objectValue);
            case ServiceConstants.MORE_EQUALS_OPERATOR ->
                    Integer.parseInt(expressionValue) >= Integer.parseInt(objectValue);
            default -> null;
        };
    }

    private String getValueFromObject(String str, String evaluationObject) {
        ObjectMapper mapper = new ObjectMapper();
        Map map;
        try {
            map = mapper.readValue(evaluationObject, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String[] params = str.split("\\.");

        for (int i = 0; i < params.length - 1; i++) {
            if(map.get(params[i]) != null) {
                map = mapper.convertValue(map.get(params[i]), Map.class);
            }
        }
        return map.get(params[params.length - 1]) != null ?
                map.get(params[params.length - 1]).toString() : "";
    }

    private String findSubstringToValidate(String exp) {
        int closingIndex = exp.indexOf(")");
        String temp = exp.substring(0, closingIndex);
        int openingIndex = temp.lastIndexOf("(");
        return exp.substring(openingIndex + 1, closingIndex);
    }

    private String removeQuotationsFromString(String str) {
        return str.replaceAll("\"", "");
    }
}
