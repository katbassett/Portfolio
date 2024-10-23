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
import com.mycompany.parkinglot.currency.ParkingCharge;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.DayOfWeek;

public class ParkingOffice {

    private final String name;
    private final Address address;
    private final List<Customer> customers;
    private final List<Car> cars;
    private final List<ParkingLot> lots;
    private final List<ParkingCharge> charges;
    private static int customerCounter = 0; // for unique customerIds

    public ParkingOffice(String name, Address address) {
        this.name = name;
        this.address = address;
        this.customers = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.lots = new ArrayList<>();
        this.charges = new ArrayList<>();
    }

    public String getParkingOfficeName() {
        return name;
    }

    public void addParkingLot(ParkingLot lot) {
        lots.add(lot);
    }

    public Customer register(String name, Address address, String phone) {
        String customerId = generateCustomerId();

        Customer customer = new Customer.Builder()
                .setCustomerId(customerId)
                .setName(name)
                .setAddress(address)
                .setPhoneNumber(phone)
                .build();
        customers.add(customer);
        return customer;
    }

    public Car registerCar(Customer customer, String license, String model, CarType type) {
        String permit = createPermit(license);
        LocalDate permitExpiration = LocalDate.now().plusYears(1);
        Car car = new Car(permit, permitExpiration, license, model, type, customer.getCustomerId());
        cars.add(car);
        customer.addCar(car);
        return car;
    }

    private String createPermit(String license) {
        return "PERMIT-" + license;
    }

    public Customer getCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public Customer getCustomer2(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public Set<String> getCustomerIds() {
        return customers.stream()
                .map(Customer::getCustomerId)
                .collect(Collectors.toSet());
    }

    public ParkingTransaction park(Instant date, ParkingPermit permit, ParkingLot lot, Money feeCharged) {
        CarType carType = permit.getCar().getType();
        
        boolean isWeekend = date.atZone(ZoneId.systemDefault()).getDayOfWeek() == DayOfWeek.SATURDAY
                || date.atZone(ZoneId.systemDefault()).getDayOfWeek() == DayOfWeek.SUNDAY;
        
        LocalTime transactionTime = date.atZone(ZoneId.systemDefault()).toLocalTime();
        boolean isPeakHours = transactionTime.isAfter(LocalTime.of(8, 0)) && transactionTime.isBefore(LocalTime.of(18, 0));
        
        Set<LocalDate> specialDays = Set.of(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 25));
        boolean isSpecialDay = specialDays.contains(date.atZone(ZoneId.systemDefault()).toLocalDate());

        ParkingTransaction transaction = new ParkingTransaction.Builder()
                .setTransactionDate(date)
                .setPermit(permit)
                .setParkingLot(lot)
                .setFeeCharged(feeCharged)
                .setCarType(carType)
                .setIsWeekend(isWeekend)
                .setIsPeakHours(isPeakHours)
                .setIsSpecialDay(isSpecialDay)
                .build();

        lot.entry(permit.getCar());
        return transaction;
    }

    public Money getParkingCharges(ParkingPermit permit) {
        return charges.stream()
                .filter(charge -> charge.getPermitId().equals(permit.getId()))
                .map(ParkingCharge::getAmount)
                .reduce(new Money(0), Money::add);
    }

    public Money getParkingCharges(Customer customer) {
        return charges.stream()
                .filter(charge -> customer.getCars().stream()
                .anyMatch(car -> car.getPermit().equals(charge.getPermitId())))
                .map(ParkingCharge::getAmount)
                .reduce(new Money(0), Money::add);
    }

    public Money addCharge(ParkingCharge charge) {
        charges.add(charge);
        return charge.getAmount();
    }

    public Set<String> getPermitIds() {
        return cars.stream()
                .map(Car::getPermit)
                .collect(Collectors.toSet());
    }

    public Set<String> getPermitIds(Customer customer) {
        return cars.stream()
                .filter(car -> car.getOwner().equals(customer.getCustomerId()))
                .map(Car::getPermit)
                .collect(Collectors.toSet());
    }

    public List<Car> getCustomerCars(Customer customer) {
        return cars.stream()
                .filter(car -> car.getOwner().equals(customer.getCustomerId()))
                .collect(Collectors.toList());
    }

    public boolean isPermitValid(String permitId) {
        return cars.stream().anyMatch(car -> car.getPermit().equals(permitId));
    }

    private static synchronized String generateCustomerId() {
        return "CUS-" + (++customerCounter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingOffice that = (ParkingOffice) o;
        return name.equalsIgnoreCase(that.name)
                && address.equals(that.address)
                && customers.equals(that.customers)
                && cars.equals(that.cars)
                && lots.equals(that.lots)
                && charges.equals(that.charges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, customers, cars, lots, charges);
    }
}
