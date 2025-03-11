package com.mireyaserrano.tema06.pilasycolas.ejercicio05;

import java.util.Arrays;
import com.mireyaserrano.tema06.pilasycolas.Ejercicio02.Pila;

public class ExpresionAritmetica {
    private final String[] expresion;

    public ExpresionAritmetica(String s) {
        expresion = parse(s);
    }

    private double solve(char c, double op1, double op2) {
        return switch (c) {
            case '+' -> op1 + op2;
            case '-' -> op1 - op2;
            case '/' -> op1 / op2;
            case '*' -> op1 * op2;
            case '%' -> op1 % op2;
            default -> Double.MIN_VALUE;
        };
    }

    private boolean isNumeric(String strNum) {
        for (int i = 0; i < strNum.length(); i++) {
            if (!Character.isDigit(strNum.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public double evaluate() {
        Pila pila = new Pila(expresion.length);
        for (String s : expresion) {
            if (isNumeric(s)) {
                pila.push(Double.parseDouble(s));
            } else if (isOperator(s.charAt(0))) {
                char operator = s.charAt(0);
                double op2 = pila.pop();
                double op1 = pila.pop();
                double res = solve(operator, op1, op2);
                pila.push(res);
            }
        }
        return pila.pop();
    }

    private String[] parse(String s) {
        s = s.trim();
        return s.split("\\s+");
    }

    private boolean isOperator(char c) {
        return switch (c) {
            case '+', '-', '/', '*', '%' -> true;
            default -> false;
        };
    }

    @Override
    public String toString() {
        return "ExpresionAlgebraica{" +
                "expresion=" + Arrays.toString(expresion) +
                '}';
    }
}
