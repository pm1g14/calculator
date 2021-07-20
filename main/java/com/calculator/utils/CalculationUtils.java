package com.calculator.utils;

import com.calculator.enums.Operators;

import java.util.Arrays;

public class CalculationUtils {

    private static String DECIMAL_DELIMITER = ".";

    public static boolean isNumeric(String value) {
        if (value == null) return false;

        try {
            double result = Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }


    public static boolean isDecimalDelimiter(String value) {
        return DECIMAL_DELIMITER.equals(value);
    }


    public static boolean isOperand(String value) {
        String[] VALID_OPERANDS = {
                Operators.ADD.operation,
                Operators.SUBTRACT.operation,
                Operators.MULTIPLY.operation,
                Operators.DIVIDE.operation,
                Operators.OPENING_BRACKET.operation,
                Operators.CLOSING_BRACKET.operation};
        return Arrays.asList(VALID_OPERANDS).contains(value);
    }


    public static boolean isBracket(String value) {
        return value.equals(Operators.OPENING_BRACKET.operation) || value.equals(Operators.CLOSING_BRACKET.operation);
    }
}
