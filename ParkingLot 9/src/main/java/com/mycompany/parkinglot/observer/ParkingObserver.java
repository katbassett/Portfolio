/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.observer;

/**
 *
 * @author katbassett
 */
import com.mycompany.parkinglot.parking.TransactionManager;
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.ParkingPermit;
import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.CarType;
import java.time.Instant;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.ZoneId;

public class ParkingObserver implements ParkingAction {

    private final TransactionManager transactionManager;

    public ParkingObserver(TransactionManager transactionManager, List<ParkingLot> lots) {
        this.transactionManager = transactionManager;
        for (ParkingLot lot : lots) {
            lot.registerObserver(this);
        }
    }

    @Override
    public void update(ParkingEvent event) {
        Instant transactionDate = (event.getTimeOut() != null)
                ? event.getTimeOut()
                : event.getTimeIn();

        LocalDate localDate = transactionDate.atZone(ZoneId.systemDefault()).toLocalDate();

        ParkingPermit permit = event.getPermit();
        ParkingLot lot = event.getLot();
        CarType carType = permit.getCar().getType();
        boolean isWeekend = isWeekend(transactionDate);
        boolean isPeakHours = isPeakHours(transactionDate);
        boolean isSpecialDay = lot.isSpecialDay(transactionDate);

        Money charge = new Money(event.getLot().calculateParkingRate(event));

        transactionManager.park(
                transactionDate,
                permit,
                lot,
                charge,
                carType,
                isWeekend,
                isPeakHours,
                isSpecialDay
        );
    }

    private boolean isWeekend(Instant transactionDate) {
        LocalDate date = transactionDate.atZone(ZoneId.systemDefault()).toLocalDate();
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isPeakHours(Instant transactionDate) {
        LocalDateTime dateTime = transactionDate.atZone(ZoneId.systemDefault()).toLocalDateTime();
        int hour = dateTime.getHour();
        return hour >= 8 && hour <= 18;
    }
}
