package com.calculator.services;

import java.math.BigInteger;

public interface CalculatorService {

    String add(String operand1, String operand2);

    String subtract(String operand1, String operand2);

    String multiply(String operand1, String operand2);

    String divide(String operand1, String operand2);

}
