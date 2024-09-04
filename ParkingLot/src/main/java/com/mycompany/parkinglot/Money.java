/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot;

/**
 *
 * @author katbassett
 */
public class Money {

    private final long cents;

    public Money(long cents) {
        this.cents = cents;
    }

    public double getDollars() {
        return cents / 100.0;
    }

    public long getCents() {
        return cents;
    }

    public Money add(Money other) {
        return new Money(this.cents + other.cents);
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
