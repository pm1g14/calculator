package com.calculator.enums;

public enum Operators {

    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    OPENING_BRACKET("("),
    CLOSING_BRACKET(")"),
    EMPTY("");

    public final String operation;

    Operators(String operation) {
        this.operation = operation;
    }

    public static Operators fromString(String value) {
        if (value.equals("+")) return ADD;
        if (value.equals("-")) return SUBTRACT;
        if (value.equals("*")) return MULTIPLY;
        if (value.equals("/")) return DIVIDE;
        if (value.equals("(")) return OPENING_BRACKET;
        if (value.equals(")")) return CLOSING_BRACKET;
        return EMPTY;
    }
}
