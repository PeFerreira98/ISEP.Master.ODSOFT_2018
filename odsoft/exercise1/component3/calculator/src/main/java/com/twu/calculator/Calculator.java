package com.twu.calculator;

public class Calculator {

    private double accumulator;

    public double doOperation(String operation, double operand) {
        switch (operation) {
            case "add":
                accumulator += operand;
                break;
            case "subtract":
                accumulator -= operand;
                break;
            case "multiply":
                accumulator *= operand;
                break;
            case "divide":
                accumulator /= operand;
                break;
            case "abs":
                accumulator = Math.abs(accumulator);
                break;
            case "neg":
                accumulator = -accumulator;
                break;
            case "sqrt":
                accumulator = Math.sqrt(accumulator);
                break;
            case "sqr":
                accumulator = Math.pow(accumulator, 2);
                break;
            case "cube":
                accumulator = Math.pow(accumulator, 3);
                break;
            case "cubert":
                accumulator = Math.cbrt(accumulator);
                break;
            // New operators
            case "factorial":
                accumulator = computeFactorial(operand);
                break;
            case "double":
                accumulator = computeDouble(operand);
                break;
            case "third":
                accumulator = computeThird(operand);
                break;
            case "exponential":
                accumulator = computeExponential(operand);
                break;
            case "cancel":
                accumulator = 0;
                break;
            case "exit":
                System.exit(0);
        }
        return accumulator;
    }

    public double computeFactorial(double operand)
    {
        if (operand <= 1.0)
        {
            return 1.0;
        }
        return operand * computeFactorial(operand - 1.0);
    }

    private double computeDouble(double operand)
    {
        return operand * 2;
    }

    private double computeThird(double operand)
    {
        return operand / 3;
    }
    
    private double computeExponential(double operand)
    {
        return Math.pow(accumulator, operand);
    }        
}
