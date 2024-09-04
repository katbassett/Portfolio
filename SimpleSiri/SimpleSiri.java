/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.simplesiri;
import java.util.Scanner; 

/**
 *
 * @author katbassett
 */
public class SimpleSiri {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        
        System.out.println("Hi, how can I help you today?");
        System.out.println("Select one of the three options Ex: 1, 2, 3 :");
        System.out.println("1.) Help me solve a math problem");
        System.out.println("2.) Tell me the weather for today");
        System.out.println("3.) ");
        int choice = input.nextInt();
        
         if (choice == 1) {
             System.out.println ("You selected help me solve a math problem");
             System.out.println ("Please enter a math problem to solve (Ex: 1 + 2): ");
             input.nextLine();
             String mathProblem = input.nextLine();
             
             try {
                 double result;
                 result = evaluateExpression(mathProblem);
                 System.out.println("The result is: " + result);
             } catch (Exception e) {
                 System.out.println("Invalid math problem");
             }
             
        }
    }


private static double evaluateExpression(String expression) {
    String[] tokens = expression.split("//s+");
    double operand1 = Double.parseDouble(tokens[0]);
    String operator = tokens[1];
    double operand2 = Double.parseDouble(tokens[2]);
    
    switch (operator) {
        case "+":
            return operand1 + operand2;
        case "-":
            return operand1 - operand2;
        case "*":
            return operand1 * operand2;
        case "/":
            if (operand2 == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return operand1 / operand2;
            
        default: 
            throw new IllegalArgumentException("Invalid operator: " + operator);
       }
    }
}