package com.calculator.parsers;

import com.calculator.exceptions.InvalidExpressionException;

public interface InputParser<T, R> {

    T parse(R expression) throws InvalidExpressionException;
}
