package com.calculator.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculationUtilsTest {

    @Test
    public void isNumeric_shouldReturnTrueForNumericValue() {
        Assertions.assertTrue(CalculationUtils.isNumeric("100"));
        Assertions.assertTrue(CalculationUtils.isNumeric("1000000000000000000000000000000000000000000"));
        Assertions.assertTrue(CalculationUtils.isNumeric("10.0"));
        Assertions.assertTrue(CalculationUtils.isNumeric("01"));
    }

    @Test
    public void isNumeric_shouldReturnFalseForNonNumericValue() {
        Assertions.assertFalse(CalculationUtils.isNumeric("1..1001991."));
        Assertions.assertFalse(CalculationUtils.isNumeric("1LL"));
        Assertions.assertFalse(CalculationUtils.isNumeric("01LL"));
        Assertions.assertFalse(CalculationUtils.isNumeric(null));
    }

    @Test
    public void isOperand_shouldReturnTrueForValidJavaOperand() {
        Assertions.assertTrue(CalculationUtils.isOperand("+"));
        Assertions.assertTrue(CalculationUtils.isOperand("-"));
        Assertions.assertTrue(CalculationUtils.isOperand("*"));
        Assertions.assertTrue(CalculationUtils.isOperand("/"));
        Assertions.assertTrue(CalculationUtils.isOperand(")"));
        Assertions.assertTrue(CalculationUtils.isOperand("("));
    }

    @Test
    public void isOperand_shouldReturnFalseForInvalidJavaOperand() {
        Assertions.assertFalse(CalculationUtils.isOperand("5"));
        Assertions.assertFalse(CalculationUtils.isOperand("a"));
        Assertions.assertFalse(CalculationUtils.isOperand("%"));
        Assertions.assertFalse(CalculationUtils.isOperand("!"));
        Assertions.assertFalse(CalculationUtils.isOperand("|"));
        Assertions.assertFalse(CalculationUtils.isOperand("+++"));
        Assertions.assertFalse(CalculationUtils.isOperand("--"));
        Assertions.assertFalse(CalculationUtils.isOperand("//"));
        Assertions.assertFalse(CalculationUtils.isOperand("***"));
    }

    @Test
    public void isBracket_shouldReturnTrueForBracket() {
        Assertions.assertTrue(CalculationUtils.isBracket("("));
        Assertions.assertTrue(CalculationUtils.isBracket(")"));
    }

    @Test
    public void isBracket_shouldReturnFalseForBracket() {
        Assertions.assertFalse(CalculationUtils.isBracket("f"));
        Assertions.assertFalse(CalculationUtils.isBracket("6"));
    }
}
