package com.calculator.services.impl;

import com.calculator.domain.ExpressionStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExpressionCalculationServiceTest extends ExpressionCalculationService {

    @Test
    public void calculate_shouldReturnCorrectResultForAddExpression() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("5");
        expressionStore.addToStore("4");
        expressionStore.addToStore("+");
        Assertions.assertEquals("9", calculate(expressionStore));

        ExpressionStore expressionStore2 = new ExpressionStore();
        expressionStore2.addToStore("5");
        expressionStore2.addToStore("4");
        expressionStore2.addToStore("4");
        expressionStore2.addToStore("+");
        expressionStore2.addToStore("+");
        Assertions.assertEquals("13", calculate(expressionStore2));
    }

    @Test
    public void calculate_shouldReturnCorrectResultForSubtracting() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("5");
        expressionStore.addToStore("4");
        expressionStore.addToStore("-");
        Assertions.assertEquals("1", calculate(expressionStore));

        ExpressionStore expressionStore2 = new ExpressionStore();
        expressionStore2.addToStore("5");
        expressionStore2.addToStore("4");
        expressionStore2.addToStore("3");
        expressionStore2.addToStore("-");
        expressionStore2.addToStore("-");
        Assertions.assertEquals("4", calculate(expressionStore2));
    }

    @Test
    public void calculate_shouldReturnCorrectResultForMultiplying() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("5");
        expressionStore.addToStore("4");
        expressionStore.addToStore("*");
        Assertions.assertEquals("20", calculate(expressionStore));

        ExpressionStore expressionStore2 = new ExpressionStore();
        expressionStore2.addToStore("5");
        expressionStore2.addToStore("4");
        expressionStore2.addToStore("2");
        expressionStore2.addToStore("*");
        expressionStore2.addToStore("*");
        Assertions.assertEquals("40", calculate(expressionStore2));
    }

    @Test
    public void calculate_shouldReturnCorrectResultForDividing() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("8");
        expressionStore.addToStore("4");
        expressionStore.addToStore("/");
        Assertions.assertEquals("2", calculate(expressionStore));

        ExpressionStore expressionStore2 = new ExpressionStore();
        expressionStore2.addToStore("8");
        expressionStore2.addToStore("4");
        expressionStore2.addToStore("2");
        expressionStore2.addToStore("/");
        expressionStore2.addToStore("/");
        Assertions.assertEquals("4", calculate(expressionStore2));
    }

    @Test
    public void calculate_shouldReturnCorrectResultForAdditionSubtraction() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("8");
        expressionStore.addToStore("4");
        expressionStore.addToStore("4");
        expressionStore.addToStore("-");
        expressionStore.addToStore("+");
        Assertions.assertEquals("8", calculate(expressionStore));
    }

    @Test
    public void calculate_shouldReturnCorrectResultForAdditionMultiplication() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("8");
        expressionStore.addToStore("4");
        expressionStore.addToStore("4");
        expressionStore.addToStore("*");
        expressionStore.addToStore("+");
        Assertions.assertEquals("24", calculate(expressionStore));
    }

    @Test
    public void calculate_shouldReturnCorrectResultForAdditionDivision() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("8");
        expressionStore.addToStore("4");
        expressionStore.addToStore("4");
        expressionStore.addToStore("/");
        expressionStore.addToStore("+");
        Assertions.assertEquals("9", calculate(expressionStore));
    }

    @Test
    public void calculate_shouldReturnCorrectResultForSubtractionMultiplication() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("8");
        expressionStore.addToStore("4");
        expressionStore.addToStore("4");
        expressionStore.addToStore("-");
        expressionStore.addToStore("*");
        Assertions.assertEquals("0", calculate(expressionStore));
    }

    @Test
    public void calculate_shouldReturnCorrectResultForSubtractionDivision() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("8");
        expressionStore.addToStore("4");
        expressionStore.addToStore("3");
        expressionStore.addToStore("-");
        expressionStore.addToStore("/");
        Assertions.assertEquals("8", calculate(expressionStore));
    }

    @Test
    public void calculate_shouldReturnCorrectResultForMultiplicationDivision() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("8");
        expressionStore.addToStore("4");
        expressionStore.addToStore("3");
        expressionStore.addToStore("*");
        expressionStore.addToStore("/");
        Assertions.assertEquals("0.7", calculate(expressionStore));
    }

    @Test
    public void calculate_shouldThrowForDivisionByZero() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("8");
        expressionStore.addToStore("0");
        expressionStore.addToStore("/");
        Assertions.assertThrows(ArithmeticException.class, () -> calculate(expressionStore));
    }

    @Test
    public void calculate_shouldReturnValidResultForAllOperations() {
        ExpressionStore expressionStore = new ExpressionStore();
        expressionStore.addToStore("8");
        expressionStore.addToStore("0");
        expressionStore.addToStore("+");
        expressionStore.addToStore("5");
        expressionStore.addToStore("4");
        expressionStore.addToStore("-");
        expressionStore.addToStore("-");
        expressionStore.addToStore("3");
        expressionStore.addToStore("6");
        expressionStore.addToStore("/");
        expressionStore.addToStore("+");
        expressionStore.addToStore("4");
        expressionStore.addToStore("8");
        expressionStore.addToStore("*");
        expressionStore.addToStore("+");
        Assertions.assertEquals("39.5", calculate(expressionStore));
    }
}
