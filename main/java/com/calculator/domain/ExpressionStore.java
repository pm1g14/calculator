package com.calculator.domain;

import java.util.Stack;

public class ExpressionStore {

    private Stack<String> expressionStore = new Stack<>();


    public void addToStore(String element) {
        expressionStore.push(element);
    }

    public Stack<String> getStore() {
        return expressionStore;
    }

    public int size() {
        return expressionStore.size();
    }


    public String[] toStringArray() {
        String[] newArray = new String[size()];
        Stack<String> copied = (Stack<String>) expressionStore.clone();
        int i = size() -1;
        while (copied.size() > 0) {
            newArray[i] = copied.pop();
            i--;
        }
        return newArray;
    }
}
