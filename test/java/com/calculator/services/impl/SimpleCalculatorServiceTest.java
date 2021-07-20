package com.calculator.services.impl;

import com.calculator.services.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleCalculatorServiceTest {

    private CalculatorService calculatorService = new SimpleCalculatorService();

    @Test
    public void add_shouldProperlyAddTwoIntegerNumbers() {
        Assertions.assertEquals("3", calculatorService.add("1","2"));
    }

    @Test
    public void add_shouldProperlyAddTwoDoubleNumbers() {
        Assertions.assertEquals("3.2", calculatorService.add("1.1", "2.1"));
        Assertions.assertEquals("3", calculatorService.add("1.0", "2.0"));
    }

    @Test
    public void add_shouldProperlyAddAnIntAndDoubleNumber() {
        Assertions.assertEquals("3", calculatorService.add("1.0", "2"));
    }

    @Test
    public void add_shouldProperlyAddTwoBigNumbers() {
        Assertions.assertEquals("2147483649", calculatorService.add(String.valueOf(Integer.MAX_VALUE), "2"));
    }

    @Test
    public void add_shouldReturnEmptyResultForInvalidPassedValues() {
        Assertions.assertEquals("", calculatorService.add("2", "1LL"));
    }

    @Test
    public void subtract_shouldProperlySubtractTwoIntegerNumbers() {
        Assertions.assertEquals("1", calculatorService.subtract("2", "1"));
        Assertions.assertEquals("-1", calculatorService.subtract("1", "2"));
        Assertions.assertEquals("-1", calculatorService.subtract("1.0", "2"));
    }

    @Test
    public void subtract_shouldReturnEmptyResultForNonValidPassedValues() {
        Assertions.assertEquals("", calculatorService.subtract("2", "1LL"));
        Assertions.assertEquals("", calculatorService.subtract(null, "1LL"));
    }

    @Test
    public void subtract_shouldReturnDoubleForSubtractingDoubleValues() {
        Assertions.assertEquals("1.1", calculatorService.subtract("2.2", "1.1"));
    }

    @Test
    public void subtract_shouldReturnNumberForSubtractingBigValues() {
        Assertions.assertEquals("2147483648", calculatorService.subtract("2147483649", "1"));
    }

    @Test
    public void multiply_shouldProperlyMultiplyTwoValidNumbers() {
        Assertions.assertEquals("2", calculatorService.multiply("1", "2"));
        Assertions.assertEquals("2", calculatorService.multiply("1.0", "2.0"));
        Assertions.assertEquals("2.5", calculatorService.multiply("1.1", "2.3"));
        Assertions.assertEquals("0", calculatorService.multiply("1.1", "0"));
        Assertions.assertEquals("-10", calculatorService.multiply("-1", "10"));
    }

    @Test
    public void multiply_shouldReturnNumberForMultiplyingBigValues() {
        Assertions.assertEquals("21474836470000", calculatorService.multiply(String.valueOf(Integer.MAX_VALUE), "10000"));
    }

    @Test
    public void multiply_shouldReturnEmptyResultForInvalidPassedOperands() {
        Assertions.assertEquals("", calculatorService.multiply("1LL", "2"));
        Assertions.assertEquals("", calculatorService.multiply(null, "2"));
    }


    @Test
    public void divide_shouldReturnCorrectResultForDividingIntegerNumbers() {
        Assertions.assertEquals("2", calculatorService.divide("2", "1"));
        Assertions.assertEquals("0.5", calculatorService.divide("1", "2"));
        Assertions.assertEquals("-1", calculatorService.divide("-3", "3"));
        Assertions.assertEquals("-1", calculatorService.divide("-3.0", "3.0"));
        Assertions.assertEquals("-1", calculatorService.divide("-3.0", "3"));
    }

    @Test
    public void divide_byZeroShouldThrowArithmeticException() {
        Assertions.assertThrows(ArithmeticException.class, () -> calculatorService.divide("5", "0"));
        Assertions.assertThrows(ArithmeticException.class, () -> calculatorService.divide("5", "0.0"));
    }

    @Test
    public void divide_shouldReturnEmptyResultForNonValidPassedValues() {
        Assertions.assertEquals("", calculatorService.divide("5", "0L"));
        Assertions.assertEquals("", calculatorService.divide(null, null));

    }
}
