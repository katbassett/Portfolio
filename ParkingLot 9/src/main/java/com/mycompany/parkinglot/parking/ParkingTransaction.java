/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.parking;

/**
 *
 * @author katbassett
 */
import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.parking.ParkingLot;
import java.time.Instant;
import java.util.Objects;

public class ParkingTransaction {

    private final Instant transactionDate;
    private final ParkingPermit permit;
    private final ParkingLot lot;
    private final Money feeCharged;
    private final CarType carType;
    private final boolean isWeekend;
    private final boolean isPeakHours;
    private final boolean isSpecialDay;

    public ParkingTransaction(Builder builder) {
        this.transactionDate = builder.transactionDate;
        this.permit = builder.permit;
        this.lot = builder.lot;
        this.feeCharged = builder.feeCharged;
        this.carType = builder.carType;
        this.isWeekend = builder.isWeekend;
        this.isPeakHours = builder.isPeakHours;
        this.isSpecialDay = builder.isSpecialDay;
    }

    public static class Builder {

        private Instant transactionDate;
        private ParkingPermit permit;
        private ParkingLot lot;
        private Money feeCharged;
        private CarType carType;
        private boolean isWeekend;
        private boolean isPeakHours;
        private boolean isSpecialDay;

        public Builder setTransactionDate(Instant transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public Builder setPermit(ParkingPermit permit) {
            this.permit = permit;
            return this;
        }

        public Builder setParkingLot(ParkingLot lot) {
            this.lot = lot;
            return this;
        }

        public Builder setFeeCharged(Money feeCharged) {
            this.feeCharged = feeCharged;
            return this;
        }

        public Builder setCarType(CarType carType) {
            this.carType = carType;
            return this;
        }

        public Builder setIsWeekend(boolean isWeekend) {
            this.isWeekend = isWeekend;
            return this;
        }

        public Builder setIsPeakHours(boolean isPeakHours) {
            this.isPeakHours = isPeakHours;
            return this;
        }

        public Builder setIsSpecialDay(boolean isSpecialDay) {
            this.isSpecialDay = isSpecialDay;
            return this;
        }

        public ParkingTransaction build() {
            return new ParkingTransaction(this);
        }
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

    public CarType getVehicleType() {
        return carType;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public boolean isPeakHours() {
        return isPeakHours;
    }

    public boolean isSpecialDay() {
        return isSpecialDay;
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
