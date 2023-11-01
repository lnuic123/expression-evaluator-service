package com.exercise.expressionevaluator.util;

import org.springframework.stereotype.Service;

@Service
public class ServiceConstants {
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String OR_OPERATOR = " OR ";
    public static final String AND_OPERATOR = " && ";

    public static final String EQUALS_OPERATOR = " == ";
    public static final String NOT_EQUALS_OPERATOR = " != ";
    public static final String LESS_OPERATOR = " < ";
    public static final String MORE_OPERATOR = " > ";
    public static final String LESS_EQUALS_OPERATOR = " <= ";
    public static final String MORE_EQUALS_OPERATOR = " >= ";

    public static final String[] comparisonOperators = {EQUALS_OPERATOR, NOT_EQUALS_OPERATOR, LESS_OPERATOR,
            MORE_OPERATOR, LESS_EQUALS_OPERATOR, MORE_EQUALS_OPERATOR};
}
