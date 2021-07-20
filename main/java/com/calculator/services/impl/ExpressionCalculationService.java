package com.calculator.services.impl;

import com.calculator.domain.ExpressionStore;
import com.calculator.enums.Operators;
import com.calculator.services.AdvancedCalculationService;
import com.calculator.utils.CalculationUtils;

import java.util.function.Predicate;

public class ExpressionCalculationService extends SimpleCalculatorService implements AdvancedCalculationService {

    @Override
    public String calculate(ExpressionStore expression) {
        String[] expressionString = expression.toStringArray();
        Predicate<String> isOperand = (c) -> CalculationUtils.isOperand(c);
        ExpressionStore resultStore = new ExpressionStore();

        for (int i=0; i<expressionString.length ;++i) {
            String valueOfChar = expressionString[i];

            if (isOperand.test(valueOfChar)) {

                if (resultStore.size() > 1) {
                    calculateAndStore(resultStore, valueOfChar);
                }
            } else {
                resultStore.addToStore(valueOfChar);
            }
        }
        return (resultStore.size() > 0) ? resultStore.getStore().peek() : "";
    }


    private void calculateAndStore(ExpressionStore resultStore, String valueOfChar) {
        Operators toOperatorEnum = Operators.fromString(valueOfChar);
        String operand1 = resultStore.getStore().pop();
        String operand2 = resultStore.getStore().pop();

        String result;
        switch (toOperatorEnum) {
            case ADD:
                result = add(operand1, operand2);
                resultStore.addToStore(result);
                break;
            case SUBTRACT:
                result = subtract(operand2, operand1);
                resultStore.addToStore(result);
                break;
            case MULTIPLY:
                result = multiply(operand1, operand2);
                resultStore.addToStore(result);
                break;
            case DIVIDE:
                result = divide(operand2, operand1);
                resultStore.addToStore(result);
                break;
            default:
                break;
        }
    }
}
