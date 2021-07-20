package com.calculator.services.impl;

import com.calculator.services.CalculatorService;
import com.calculator.utils.CalculationUtils;

import java.text.DecimalFormat;

public class SimpleCalculatorService implements CalculatorService {

    private DecimalFormat df = new DecimalFormat("0.#");
    private int FRACTION_DIGITS = 3;


    @Override
    public String add(String operand1, String operand2) {
        df.setMaximumFractionDigits(FRACTION_DIGITS);
        if (CalculationUtils.isNumeric(operand1) && CalculationUtils.isNumeric(operand2)) {
            Double result = Double.valueOf(operand1) + Double.valueOf(operand2);
            return String.valueOf(df.format(result));
        }
        return "";
    }

    @Override
    public String subtract(String operand1, String operand2) {
        df.setMaximumFractionDigits(FRACTION_DIGITS);
        if (CalculationUtils.isNumeric(operand1) && CalculationUtils.isNumeric(operand2)) {
            Double result = Double.valueOf(operand1) - Double.valueOf(operand2);
            return String.valueOf(df.format(result));
        }
        return "";
    }

    @Override
    public String multiply(String operand1, String operand2) {
        if (CalculationUtils.isNumeric(operand1) && CalculationUtils.isNumeric(operand2)) {
            Double result = Double.valueOf(operand1) * Double.valueOf(operand2);
            return String.valueOf(df.format(result));
        }
        return "";
    }

    @Override
    public String divide(String operand1, String operand2) {
        if (CalculationUtils.isNumeric(operand1) && CalculationUtils.isNumeric(operand2)) {
            if (Double.valueOf(operand2) == 0.0) {
                throw new ArithmeticException("Cannot divide by zero");
            }

            Double result = Double.valueOf(operand1) / Double.valueOf(operand2);
            return String.valueOf(df.format(result));
        }
        return "";
    }
}
