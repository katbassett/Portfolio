/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.decisionmodeling;

/**
 *
 * @author katbassett
 */

import java.time.DayOfWeek;
import java.time.LocalDate;
       

public class DecisionModeling {

    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        
        // Setting the "Cut" condition to true
        boolean cut = true;
        
        
        System.out.println ("Today is " + dayOfWeek);
        
                if (null != dayOfWeek) switch (dayOfWeek) {
            case MONDAY:
                System.out.println("Glute Workout");
                break;
            case TUESDAY:
                System.out.println("Chest & Shoulder Workout");
                if (cut) {
                   System.out.println("& Cardio");
                }
                break;
            case WEDNESDAY:
                System.out.println("Quads & Calf Workout");
                break;
            case THURSDAY:
                System.out.println("Back Workout");
                if (cut) {
                    System.out.println("& Cardio");
                }
                break;
            case FRIDAY:
                System.out.println("Glute and Hamstrings Workout");
                break;
            case SUNDAY:
                System.out.println("Ab Workout & Cardio");
            default:
                break;
        }
        
    }
}
