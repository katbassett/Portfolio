/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot;

/**
 *
 * @author katbassett
 */
import java.time.Instant;
import java.util.Objects;

public class ParkingTransaction {

    private final Instant transactionDate;
    private final ParkingPermit permit;
    private final ParkingLot lot;
    private final Money feeCharged;

    public ParkingTransaction(Instant transactionDate, ParkingPermit permit, ParkingLot lot, Money feeCharged) {
        this.transactionDate = transactionDate;
        this.permit = permit;
        this.lot = lot;
        this.feeCharged = feeCharged;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public ParkingPermit getPermit() {
        return permit;
    }

    public ParkingLot getParkingLot() {
        return lot;
    }

    public Money getFeeCharged() {
        return feeCharged;
    }

    @Override
    public String toString() {
        return "Parking Transaction: "
                + "Transaction Date: " + transactionDate
                + ", Permit: " + permit
                + ", Lot: " + lot
                + ", Fee: " + feeCharged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingTransaction that = (ParkingTransaction) o;
        return transactionDate.equals(that.transactionDate)
                && permit.equals(that.permit)
                && lot.equals(that.lot)
                && feeCharged.equals(that.feeCharged);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, permit, lot, feeCharged);
    }
}
