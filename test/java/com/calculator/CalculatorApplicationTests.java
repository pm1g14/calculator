package com.calculator;

import com.calculator.exceptions.InvalidExpressionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorApplicationTests extends CalculatorApplication{

    @Test
    void contextLoads() {
    }

    @Test
    public void parseAndCalculate_shouldReturnCorrectResultForExpression() throws InvalidExpressionException {
        char[] input = {'-','5', '+','6'};
        Assertions.assertEquals("1", parseAndCalculate(input));

        char[] input2 = {'-','2', '*', '5', '+','6'};
        Assertions.assertEquals("-4", parseAndCalculate(input2));

        char[] input3 = {'(','-', '5', '+','6', ')', '*', '4'};
        Assertions.assertEquals("4", parseAndCalculate(input3));

        char[] input4 = {'(','5', '+','6', ')', '*', '(', '4', '-', '3', ')'};
        Assertions.assertEquals("11", parseAndCalculate(input4));

        char[] input5 = {'(','6', '+','6', ')', '/', '(', '4', '*', '3', ')'};
        Assertions.assertEquals("1", parseAndCalculate(input5));

    }

    @Test
    public void parseAndCalculate_shouldThrowForInvalidSymbols() throws InvalidExpressionException {
        char[] input = {')'};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parseAndCalculate(input));

        char[] input2 = {','};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parseAndCalculate(input2));

        char[] input3 = {'5', '+'};
        char[] input4 = {'5', '-'};
        char[] input5 = {'5', '*'};
        char[] input6 = {'5', '/'};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parseAndCalculate(input3));
        Assertions.assertThrows(InvalidExpressionException.class, () -> parseAndCalculate(input4));
        Assertions.assertThrows(InvalidExpressionException.class, () -> parseAndCalculate(input5));
        Assertions.assertThrows(InvalidExpressionException.class, () -> parseAndCalculate(input6));
    }

    @Test
    public void parseAndCalculate_shouldCalculateExpressionGivenDecimals() throws InvalidExpressionException {
        char[] input = {'-','0', '.', '5', '+','6'};
        Assertions.assertEquals("5.5", parseAndCalculate(input));

        char[] input2 = {'-','0', '.', '5', '5', '1', '+','0', '.', '5', '5', '2'};
        Assertions.assertEquals("0.001", parseAndCalculate(input2));
    }

}
