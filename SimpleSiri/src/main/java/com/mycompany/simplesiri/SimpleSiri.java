/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package com.mycompany.simplesiri;
 import java.util.Scanner;
 import java.time.DayOfWeek;
 import java.time.LocalDate;
 
 /**
  *
  * @author katbassett
  */
 public class SimpleSiri {
 
     public static void main(String[] args) {
         Scanner input = new Scanner(System.in); 
         
         boolean continueSolving = true;
         
         while (continueSolving) {
            System.out.println("Hi, how can I help you today?");
            System.out.println("Select one of the three options (Ex: 1, 2, 3 ):");
            System.out.println("1.) Help me solve a math problem");
            System.out.println("2.) Tell me more about a date");
            System.out.println("3.) Analyze text");
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
              
          System.out.println("Can I help with anything else? (Yes/No)");
          String response = input.nextLine();
          if (!response.equalsIgnoreCase("Yes")) {
              continueSolving = false;
            } 
      } else if (choice == 2){
              System.out.println("Enter a date (YYYY-MM-DD):");
              input.nextLine();
              String dateString = input.nextLine();
              
              try {
                  LocalDate date = LocalDate.parse(dateString);
                  DayOfWeek dayOfWeek = date.getDayOfWeek();
                  int dayOfYear = date.getDayOfYear(); 
                  
                  System.out.println("The day of the week for " + dateString + " is " + dayOfWeek);
                  System.out.println("It is the " + dayOfYear + "th day of the year.");
              } catch (Exception e) {
                  System.out.println("Invalid date format.");
              }
              
              System.out.println("Can I help with anything else? (Yes/No)");
              String response = input.nextLine();
              if (!response.equalsIgnoreCase("Yes")) {
                 continueSolving = false;
          }
  
      } else if (choice == 3) {
              input.nextLine();
              System.out.println("Enter the text you want to analyze:");
              String text = input.nextLine();
              
              int wordCount = calculateWordCount(text);
              int characterCount = text.length();
              double averageWordLength = calculateAverageWordLength(text);
              
              System.out.println("Text Analysis Results:");
              System.out.println("Word Count: " + wordCount);
              System.out.println("Character Count: " + characterCount);
              System.out.println("Average Word Length: " + averageWordLength);
          
               System.out.println("Can I help with anything else? (Yes/No)");
               String response = input.nextLine();
               if (!response.equalsIgnoreCase("Yes")) {
                   continueSolving = false;
                  }
           } else {
                   System.out.println("Invalid choice. please try again.");
       }
   }
}
 
 private static double evaluateExpression(String expression) {
     expression = expression.trim();
     String[] tokens = expression.split("\\s+");
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
 
 private static int calculateWordCount(String text) {
     String[] words = text.split("\\s+");
     return words.length;
 }
 
 private static double calculateAverageWordLength(String text) {
     String[] words = text.split("\\s+");
     int totalCharacters = 0;
     for (String word : words) {
         totalCharacters += word.length();
     }
     return (double) totalCharacters / words.length;
 }
}