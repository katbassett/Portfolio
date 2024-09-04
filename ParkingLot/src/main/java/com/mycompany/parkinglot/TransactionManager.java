/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot;

/**
 *
 * @author katbassett
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class TransactionManager {

    private final ArrayList<ParkingTransaction> transactions;
    private final HashMap<String, ArrayList<ParkingTransaction>> vehicleTransaction;

    public TransactionManager() {
        this.transactions = new ArrayList<>();
        this.vehicleTransaction = new HashMap<>();
    }

    public ParkingTransaction park(Calendar date, ParkingPermit permit, ParkingLot lot) {
        Money feeCharged = calculateFee(lot);
        ParkingTransaction transaction = new ParkingTransaction(date.toInstant(), permit, lot, feeCharged);
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

    private Money calculateFee(ParkingLot lot) {
        return new Money(1500);
    }
}
