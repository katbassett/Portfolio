/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.currency;

/**
 *
 * @author katbassett
 */
public class Money {

    private final long cents;

    public Money(double dollars) {
        this.cents = Math.round(dollars * 100);  
    }

    public double getDollars() {
        return cents / 100.0;
    }

    public long getCents() {
        return cents;
    }

    public Money add(Money other) {
        return new Money((this.cents + other.cents) /100.0);
    }
    
      public Money multiply(double factor) {
        double multipliedDollars = this.getDollars() * factor;
        return new Money(multipliedDollars);
    }

    @Override
    public String toString() {
        return "$" + getDollars();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return cents == money.cents;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(cents);
    }
}
