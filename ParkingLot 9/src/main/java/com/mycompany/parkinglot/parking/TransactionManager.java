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
import com.mycompany.parkinglot.parking.ParkingTransaction;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.observer.ParkingEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.time.Instant;

public class TransactionManager {

    private final ArrayList<ParkingTransaction> transactions;
    private final HashMap<String, ArrayList<ParkingTransaction>> vehicleTransaction;

    public TransactionManager() {
        this.transactions = new ArrayList<>();
        this.vehicleTransaction = new HashMap<>();
    }

    public ParkingTransaction park(Instant transactionDate, ParkingPermit permit, ParkingLot lot, Money feeCharged,
            CarType carType, boolean isWeekend, boolean isPeakHours, boolean isSpecialDay) {

        ParkingTransaction transaction = new ParkingTransaction.Builder()
                .setTransactionDate(transactionDate)
                .setPermit(permit)
                .setParkingLot(lot)
                .setFeeCharged(feeCharged)
                .setCarType(carType)
                .setIsWeekend(isWeekend)
                .setIsPeakHours(isPeakHours)
                .setIsSpecialDay(isSpecialDay)
                .build();

        transactions.add(transaction);

        String licensePlate = permit.getCar().getLicensePlate();
        vehicleTransaction.computeIfAbsent(licensePlate, k -> new ArrayList<>()).add(transaction);

        return transaction;
    }

    public Money getParkingCharges(ParkingPermit permit) {
        return vehicleTransaction.getOrDefault(permit.getCar().getLicensePlate(), new ArrayList<>())
                .stream()
                .map(ParkingTransaction::getFeeCharged)
                .reduce(new Money(0), Money::add);
    }

    public Money getParkingCharges(String licensePlate) {
        return vehicleTransaction.getOrDefault(licensePlate, new ArrayList<>())
                .stream()
                .map(ParkingTransaction::getFeeCharged)
                .reduce(new Money(0), Money::add);
    }

    private Money calculateFee(ParkingLot lot, ParkingPermit permit, Calendar date) {
        Instant transactionDate = date.getTime().toInstant();

        String carType = permit.getCar().getType().toString();
        boolean isWeekend = (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
        boolean isPeakHours = false;
        boolean isSpecialDay = false;

        ParkingEvent event;
        event = new ParkingEvent(
                lot,
                permit,
                transactionDate,
                null 
                        );

        double calculatedFee = lot.calculateParkingRate(event);
        return new Money(Math.round(calculatedFee * 100));
    }
}
