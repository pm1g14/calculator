package com.calculator;

import com.calculator.domain.ExpressionStore;
import com.calculator.exceptions.InvalidExpressionException;
import com.calculator.parsers.InputParser;
import com.calculator.parsers.impl.ExpressionParser;
import com.calculator.services.AdvancedCalculationService;
import com.calculator.services.impl.ExpressionCalculationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CalculatorApplication {


    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);

        calculateOrThrow();
    }

    private static void calculateOrThrow() {
        boolean valid = false;

        while (!valid) {

            char[] expression = getInput().trim().replaceAll(" ", "").toCharArray();

            try {
                String result = parseAndCalculate(expression);
                System.out.println("Result is: " + result);
                valid = true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                valid = false;
            }

        }
    }

    static String parseAndCalculate(char[] expression) throws InvalidExpressionException {
        AdvancedCalculationService advService = new ExpressionCalculationService();
        InputParser<ExpressionStore, char[]> parser = new ExpressionParser();
        ExpressionStore output = parser.parse(expression);
        return advService.calculate(output);
    }


    private static String getInput() {
        Scanner scanObj = new Scanner(System.in);
        System.out.println("Enter expression to calculate:");
        return scanObj.nextLine();
    }

}
