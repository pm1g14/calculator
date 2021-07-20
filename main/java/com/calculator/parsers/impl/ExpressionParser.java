package com.calculator.parsers.impl;

import com.calculator.constants.OperatorPriority;
import com.calculator.domain.ExpressionStore;
import com.calculator.enums.Operators;
import com.calculator.exceptions.InvalidExpressionException;
import com.calculator.parsers.InputParser;
import com.calculator.utils.CalculationUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class ExpressionParser implements InputParser<ExpressionStore, char[]> {

    private static Map<String, Integer> OPERATOR_PRESCEDENCE = new HashMap<>();
    static {
        OPERATOR_PRESCEDENCE.put(Operators.OPENING_BRACKET.operation, OperatorPriority.MAX_PRIORITY);
        OPERATOR_PRESCEDENCE.put(Operators.CLOSING_BRACKET.operation, -1);
        OPERATOR_PRESCEDENCE.put(Operators.MULTIPLY.operation, OperatorPriority.MEDIUM_PRIORITY);
        OPERATOR_PRESCEDENCE.put(Operators.DIVIDE.operation, OperatorPriority.MEDIUM_PRIORITY);
        OPERATOR_PRESCEDENCE.put(Operators.ADD.operation, OperatorPriority.MIN_PRIORITY);
        OPERATOR_PRESCEDENCE.put(Operators.SUBTRACT.operation, OperatorPriority.MIN_PRIORITY);
    }


    @Override
    public ExpressionStore parse(char[] expression) throws InvalidExpressionException {
        ExpressionStore outputStore = new ExpressionStore();
        ExpressionStore operatorsStore = new ExpressionStore();
        Predicate<String> isBracket = (String c) -> CalculationUtils.isBracket(String.valueOf(c));
        Predicate<StringBuilder> nonEmpty = (s) -> s.length() > 0;
        Predicate<Character> isNumeric = (Character c) -> CalculationUtils.isNumeric(String.valueOf(c));
        Predicate<Character> isDecimalDelimiter = (Character c) -> CalculationUtils.isDecimalDelimiter(String.valueOf(c));
        Predicate<Character> isOperand = (Character c) -> CalculationUtils.isOperand(String.valueOf(c));

        StringBuilder buffer = new StringBuilder();

        validateBrackets(expression);
        validateEndingChar(expression, isOperand, isBracket);

        int i = 0;


        if(isOperand.test(expression[0]) && !isBracket.test(String.valueOf(expression[0]))) {
            buffer.append(expression[0]);
            i = 1;
        }

        while (i < expression.length) {
            String currentCharToStr = String.valueOf(expression[i]);
            int operatorsStoreSize = operatorsStore.size();

            if (isOperand.test(expression[i])) {
                throwForConsequtiveOperators(i, expression, isOperand, isBracket, buffer);

                if (i + 1 < expression.length &&
                        isOperand.test(expression[i+1]) &&
                        String.valueOf(expression[i]).equals(Operators.OPENING_BRACKET.operation)) {
                    operatorsStore.addToStore(currentCharToStr);
                    buffer.insert(0, expression[i+1]);
                    i += 2;
                    continue;
                }

                if (nonEmpty.test(buffer)) outputStore.addToStore(buffer.toString());
                handleOperators(outputStore, operatorsStore, isBracket, buffer, currentCharToStr, operatorsStoreSize);

            } else if (isNumeric.test(expression[i]) || isDecimalDelimiter.test(expression[i])) {
                buffer.append(currentCharToStr);

            } else throwException("The given expression has invalid characters: " +currentCharToStr);
            i++;
        }

        if (nonEmpty.test(buffer)) {
            outputStore.addToStore(buffer.toString());
        }

        extractRestOfOperatorsFromOperatorToOutputStore(outputStore, operatorsStore, isBracket);
        return outputStore;
    }


    private void handleOperators(ExpressionStore outputStore, ExpressionStore operatorsStore, Predicate<String> isBracket, StringBuilder buffer, String currentCharToStr, int operatorsStoreSize) {
        if (operatorsStoreSize > 0 && !hasPrecedence(currentCharToStr, operatorsStore.getStore().peek())) {
            while (operatorsStore.size() > 0 && !isBracket.test(operatorsStore.getStore().peek())) {
                outputStore.addToStore(operatorsStore.getStore().pop());
            }
            operatorsStore.addToStore(currentCharToStr);
        } else {
            operatorsStore.addToStore(currentCharToStr);
        }
        buffer.delete(0, buffer.length());
    }


    private void extractRestOfOperatorsFromOperatorToOutputStore(ExpressionStore outputStore, ExpressionStore operandsStore, Predicate<String> isBracket) {
        while (operandsStore.size() > 0) {
            String headOfStack = operandsStore.getStore().peek();

            if (isBracket.test(headOfStack)) {
                operandsStore.getStore().pop();

            } else outputStore.addToStore(operandsStore.getStore().pop());
        }
    }


    boolean hasPrecedence(String currentOperand, String headOfStack) {
        int currentOperandIndex = OPERATOR_PRESCEDENCE.get(currentOperand);
        int headOfStackIndex = OPERATOR_PRESCEDENCE.get(headOfStack);
        return currentOperandIndex > headOfStackIndex;
    }


    private void throwException(String message) throws InvalidExpressionException {
        throw new InvalidExpressionException(message);
    }


    private void throwForConsequtiveOperators(int i, char[] expression, Predicate<Character> isOperand, Predicate<String> isBracket, StringBuilder buffer) throws InvalidExpressionException {
        if (i + 1 < expression.length &&
                isOperand.test(expression[i]) &&
                isOperand.test(expression[i+1]) &&
                !(isBracket.test(String.valueOf(expression[i])) || isBracket.test(String.valueOf(expression[i+1])))) {
            throw new InvalidExpressionException("The given expression has consequtive operands at position: "+i);
        }
    }


    private void validateBrackets(char[] expression) throws InvalidExpressionException {
        ExpressionStore brackets = new ExpressionStore();

        for (int i=0; i < expression.length; i++) {
            String currentElement = String.valueOf(expression[i]);

            if (currentElement.equals(Operators.OPENING_BRACKET.operation)) {
                brackets.addToStore(currentElement);
                continue;
            }
            if (currentElement.equals(Operators.CLOSING_BRACKET.operation)) {

                if (brackets.size() > 0 && brackets.getStore().peek().equals(Operators.OPENING_BRACKET.operation)) {
                    brackets.getStore().pop();

                } else throwException("The opening and closing brackets don't match in this expression");

            }
        }
        if (brackets.size() != 0) throwException("The opening and closing brackets don't match in this expression");
    }

    private void validateEndingChar(char[] expression, Predicate<Character> isOperand, Predicate<String> isBracket) throws InvalidExpressionException {
        int lastElementIdx = expression.length -1;
        char lastElement = expression[lastElementIdx];
        if (isOperand.test(lastElement) && ! isBracket.test(String.valueOf(lastElement))) {
            throwException("Expression ends with operator");
        }
    }
}
