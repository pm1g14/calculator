package com.calculator.parsers.impl;

import com.calculator.domain.ExpressionStore;
import com.calculator.enums.Operators;
import com.calculator.exceptions.InvalidExpressionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExpressionParserTest {

    private ExpressionParser parser = new ExpressionParser();

    @Test
    public void parse_shouldReturnExpectedShuntingYardAlgorithmOutput() throws InvalidExpressionException {
        char[] input = {'5', '+', '4'};
        ExpressionStore expected = new ExpressionStore();
        expected.addToStore("5");
        expected.addToStore("4");
        expected.addToStore("+");
        ExpressionStore actual = parser.parse(input);
        Assertions.assertEquals(expected.getStore(), actual.getStore());
        Assertions.assertEquals(3, actual.size());

        char[] input2 = {'2', '*', '5', '+', '2'};
        ExpressionStore expected2 = new ExpressionStore();
        expected2.addToStore("2");
        expected2.addToStore("5");
        expected2.addToStore("*");
        expected2.addToStore("2");
        expected2.addToStore("+");
        ExpressionStore actual2 = parser.parse(input2);
        Assertions.assertEquals(expected2.getStore(), actual2.getStore());
        Assertions.assertEquals(5, actual2.size());
    }

    @Test
    public void parse_shouldReturnExpectedShuntingYardAlgorithmOutputWhenGivenBracketsInExpression() throws InvalidExpressionException {
        char[] input = {'5', '+', '(', '4', '-', '2', ')'};
        ExpressionStore expected = new ExpressionStore();
        expected.addToStore("5");
        expected.addToStore("4");
        expected.addToStore("2");
        expected.addToStore("-");
        expected.addToStore("+");
        ExpressionStore actual = parser.parse(input);
        Assertions.assertEquals(expected.getStore(), actual.getStore());
        Assertions.assertEquals(5, actual.size());
    }

    @Test
    public void parse_shouldReturnExpectedShuntingYardAlgorithmOutputWhenGivenBracketsInExpressionAndNegativeFirstNum() throws InvalidExpressionException {
        char[] input = {'-', '5', '+', '(', '4', '-', '2', ')'};
        ExpressionStore expected = new ExpressionStore();
        expected.addToStore("-5");
        expected.addToStore("4");
        expected.addToStore("2");
        expected.addToStore("-");
        expected.addToStore("+");
        ExpressionStore actual = parser.parse(input);
        Assertions.assertEquals(expected.getStore(), actual.getStore());
        Assertions.assertEquals(5, actual.size());

        char[] input2 = {'-', '5', '+', '6'};
        ExpressionStore expected2 = new ExpressionStore();
        expected2.addToStore("-5");
        expected2.addToStore("6");
        expected2.addToStore("+");
        ExpressionStore actual2 = parser.parse(input2);
        Assertions.assertEquals(expected2.getStore(), actual2.getStore());
        Assertions.assertEquals(3, actual2.size());
    }


    @Test
    public void parse_shouldReturnExpectedShuntingYardAlgorithmOutputWhenGivenBracketsInExpression2() throws InvalidExpressionException {
        char[] input = {'5', '+', '(', '4', '-', '2', ')', '/', '5'};
        ExpressionStore expected = new ExpressionStore();
        expected.addToStore("5");
        expected.addToStore("4");
        expected.addToStore("2");
        expected.addToStore("-");
        expected.addToStore("5");
        expected.addToStore("/");
        expected.addToStore("+");
        ExpressionStore actual = parser.parse(input);
        Assertions.assertEquals(expected.getStore(), actual.getStore());
        Assertions.assertEquals(7, actual.size());
    }

    @Test
    public void parse_shouldReturnExpectedShuntingYardAlgorithmOutputWhenGivenBracketsInExpression3() throws InvalidExpressionException {
        char[] input = {'5', '+', '(', '4', '-', '2', '*', '4', ')', '/', '5'};
        ExpressionStore expected = new ExpressionStore();
        expected.addToStore("5");
        expected.addToStore("4");
        expected.addToStore("2");
        expected.addToStore("4");
        expected.addToStore("*");
        expected.addToStore("-");
        expected.addToStore("5");
        expected.addToStore("/");
        expected.addToStore("+");
        ExpressionStore actual = parser.parse(input);
        Assertions.assertEquals(expected.getStore(), actual.getStore());
        Assertions.assertEquals(9, actual.size());
    }

    @Test
    public void parse_shouldReturnExpectedShuntingYardAlgorithmOutputWhenGivenBracketsInExpression4() throws InvalidExpressionException {
        char[] input = {'(', '4', '+', '(', '-', '2', '*', '3', ')', ')'};
        ExpressionStore expected = new ExpressionStore();
        expected.addToStore("4");
        expected.addToStore("-2");
        expected.addToStore("3");
        expected.addToStore("*");
        expected.addToStore("+");
        ExpressionStore actual = parser.parse(input);
        Assertions.assertEquals(expected.getStore(), actual.getStore());
        Assertions.assertEquals(5, actual.size());

        char[] input2 = {'-', '5', '/', '(', '2', '+', '3', ')'};
        ExpressionStore expected2 = new ExpressionStore();
        expected2.addToStore("-5");
        expected2.addToStore("2");
        expected2.addToStore("3");
        expected2.addToStore("+");
        expected2.addToStore("/");
        ExpressionStore actual2 = parser.parse(input2);
        Assertions.assertEquals(expected2.getStore(), actual2.getStore());
        Assertions.assertEquals(5, actual2.size());

        char[] input3 = {'-', '5', '6', '/', '(', '2', '0', '+', '3', ')'};
        ExpressionStore expected3 = new ExpressionStore();
        expected3.addToStore("-56");
        expected3.addToStore("20");
        expected3.addToStore("3");
        expected3.addToStore("+");
        expected3.addToStore("/");
        ExpressionStore actual3 = parser.parse(input3);
        Assertions.assertEquals(expected3.getStore(), actual3.getStore());
        Assertions.assertEquals(5, actual3.size());

    }

    @Test
    public void parse_shouldReturnExpectedShuntingYardAlgorithmOutputForNumsWithManyDigits() throws InvalidExpressionException {
        char[] input = {'5', '0', '0', '+', '4', '0', '0'};
        ExpressionStore expected = new ExpressionStore();
        expected.addToStore("500");
        expected.addToStore("400");
        expected.addToStore("+");
        ExpressionStore actual = parser.parse(input);
        Assertions.assertEquals(expected.getStore(), actual.getStore());
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    public void parse_shouldReturnExpectedShuntingYardAlgorithmOutputForExpressionStartingWithOperand() throws InvalidExpressionException {
        char[] input = {'-', '5', '+', '4'};
        ExpressionStore expected = new ExpressionStore();
        expected.addToStore("-5");
        expected.addToStore("4");
        expected.addToStore("+");
        ExpressionStore actual = parser.parse(input);
        Assertions.assertEquals(expected.getStore(), actual.getStore());
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    public void parse_shouldThrowExceptionForConsecutiveOperandsInExpression() throws InvalidExpressionException {
        char[] input = {'5', '0', '0', '+', '-', '0', '0'};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse(input));

        char[] input2 = {'5', '0', '0', '+', '-'};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse(input2));

        char[] input3 = {'+', '/', '5', '0', '0', '+', '-'};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse(input3));
    }

    @Test
    public void parse_shouldThrowExceptionForLetterInExpression() {
        char[] input = {'5', '0', '0', '+', '4', '0', '0', 'a'};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse(input));

        char[] input2 = {'a', '0', '0', '+', '4', '0', '0', '5'};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse(input2));
    }


    @Test
    public void parse_shouldThrowExceptionForNonMatchingOpeningAndClosingBrackets() {
        char[] input = {'(', '5', '('};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse(input));

        char[] input2 = {'(', '5', ')', ')'};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse(input2));

        char[] input3 = {'(', '(', '('};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse(input3));

        char[] input4 = {')', '(',')'};
        Assertions.assertThrows(InvalidExpressionException.class, () -> parser.parse(input4));
    }

    @Test
    public void hasPrescedence_shouldReturnTrueForMultiplyVsAddition() {
        Assertions.assertTrue(parser.hasPrecedence(Operators.MULTIPLY.operation, Operators.ADD.operation));
    }

    @Test
    public void hasPrescedence_shouldReturnTrueForMultiplyVsSubtraction() {
        Assertions.assertTrue(parser.hasPrecedence(Operators.MULTIPLY.operation, Operators.SUBTRACT.operation));
    }

    @Test
    public void hasPrescedence_shouldReturnFalseForMultiplyVsMultiply() {
        Assertions.assertFalse(parser.hasPrecedence(Operators.MULTIPLY.operation, Operators.MULTIPLY.operation));
    }

    @Test
    public void hasPrescedence_shouldReturnFalseForMultiplyVsDivision() {
        Assertions.assertFalse(parser.hasPrecedence(Operators.MULTIPLY.operation, Operators.DIVIDE.operation));
        Assertions.assertFalse(parser.hasPrecedence(Operators.DIVIDE.operation, Operators.MULTIPLY.operation));

    }

    @Test
    public void hasPrescedence_shouldReturnTrueForDivisionVsAddition() {
        Assertions.assertTrue(parser.hasPrecedence(Operators.DIVIDE.operation, Operators.ADD.operation));
    }

    @Test
    public void hasPrescedence_shouldReturnTrueForDivisionVsSubraction() {
        Assertions.assertTrue(parser.hasPrecedence(Operators.DIVIDE.operation, Operators.SUBTRACT.operation));
    }
}
