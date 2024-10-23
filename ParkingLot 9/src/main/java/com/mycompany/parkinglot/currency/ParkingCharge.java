/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.currency;

/**
 *
 * @author katbassett
 */
import java.time.Instant;
import java.util.Objects;

public class ParkingCharge {

    private final String permitId;
    private final String lotId;
    private final Instant incurred;
    private final Money amount;

    public ParkingCharge(String permitId, String lotId, Instant incurred, Money amount) {
        this.permitId = permitId;
        this.lotId = lotId;
        this.incurred = incurred;
        this.amount = amount;
    }

    public String getPermitId() {
        return permitId;
    }

    public String getLotId() {
        return lotId;
    }

    public Instant getIncurred() {
        return incurred;
    }

    public Money getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Parking Charge: "
                + "permitId:'" + permitId + '\''
                + ", lotId:'" + lotId + '\''
                + ", incurred:" + incurred
                + ", amount:" + amount
                + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingCharge that = (ParkingCharge) o;
        return permitId.equals(that.permitId)
                && lotId.equals(that.lotId)
                && incurred.equals(that.incurred)
                && amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permitId, lotId, incurred, amount);
    }
}
