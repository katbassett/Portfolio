/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.parkinglot;

import com.mycompany.parkinglot.strategy.ParkingChargeStrategy;
import com.mycompany.parkinglot.currency.ParkingCharge;
import com.mycompany.parkinglot.currency.Money;
import com.mycompany.parkinglot.parking.ParkingOffice;
import com.mycompany.parkinglot.parking.ParkingLot;
import com.mycompany.parkinglot.parking.Customer;
import com.mycompany.parkinglot.parking.Car;
import com.mycompany.parkinglot.parking.CarType;
import com.mycompany.parkinglot.parking.Address;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.Set;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 *
 * @author katbassett
 */
public class ParkingOfficeTest {

    @Test
    public void testRegisterCustomer() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);

        Customer customer = office.register("Jane Smith", address, "464-3333");
        assertNotNull(customer);
        assertEquals("Jane Smith", customer.getName());
        assertEquals(address, customer.getAddress());
        assertEquals("464-3333", customer.getPhoneNumber());
    }

    @Test
    public void testRegisterCar() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);
        Customer customer = office.register("Jane Smith", address, "464-3333");

        Car car = office.registerCar(customer, "BBB-333", "Toyota Camry", CarType.SUV);
        assertNotNull(car);
        assertEquals("BBB-333", car.getLicensePlate());
        assertEquals("Toyota Camry", car.getModel());
        assertEquals(CarType.SUV, car.getType());
        assertEquals(customer.getCustomerId(), car.getOwner());
        assertTrue(customer.getCars().contains(car));
    }

    @Test
    public void testGetCustomer() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);
        Customer customer = office.register("Jane Smith", address, "464-3333");

        Customer retrievedCustomer = office.getCustomer(customer.getCustomerId());
        assertNotNull(retrievedCustomer);
        assertEquals(customer.getCustomerId(), retrievedCustomer.getCustomerId());
    }

    @Test
    public void testAddCharge() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);

        Money amount = new Money(1500);
        ParkingCharge charge = new ParkingCharge("PERMIT-002", "LOT-002", Instant.now(), amount);
        Money returnedAmount = office.addCharge(charge);

        assertNotNull(returnedAmount);
        assertEquals(amount.getCents(), returnedAmount.getCents());
    }

    @Test
    public void testEquals() {
        Address address1 = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        Address address2 = new Address.Builder()
                .setStreetAddress1("202 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78704")
                .build();

        ParkingOffice office1 = new ParkingOffice("Main Office", address1);
        ParkingOffice office2 = new ParkingOffice("Main Office", address1);
        ParkingOffice office3 = new ParkingOffice("Secondary Office", address2);

        assertEquals(office1, office2); // true
        assertNotEquals(office1, office3); // false
    }

    @Test
    public void testHashCode() {
        Address address1 = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        Address address2 = new Address.Builder()
                .setStreetAddress1("202 Main St")
                .setStreetAddress2("")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78704")
                .build();

        ParkingOffice office1 = new ParkingOffice("Main Office", address1);
        ParkingOffice office2 = new ParkingOffice("Main Office", address1);
        ParkingOffice office3 = new ParkingOffice("Secondary Office", address2);

        assertEquals(office1.hashCode(), office2.hashCode());
        assertNotEquals(office1.hashCode(), office3.hashCode());
    }

    @Test
    public void testGetCustomerIds() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);

        Customer customer1 = office.register("Jane Smith", address, "464-3333");
        Customer customer2 = office.register("John Clark", address, "858-8989");

        Set<String> customerIds = office.getCustomerIds();

        assertTrue(customerIds.contains(customer1.getCustomerId()));
        assertTrue(customerIds.contains(customer2.getCustomerId()));
        assertEquals(2, customerIds.size());
    }

    @Test
    public void testGetPermitIds() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);
        Customer customer1 = office.register("Jane Smith", address, "464-3333");
        Customer customer2 = office.register("John Clark", address, "858-8989");

        Car car1 = office.registerCar(customer1, "MVM-M99", "Honda CRV", CarType.SUV);
        Car car2 = office.registerCar(customer2, "POP-P34", "Ford Focus", CarType.COMPACT);

        Set<String> permitIds = office.getPermitIds();
        assertTrue(permitIds.contains(car1.getPermit()));
        assertTrue(permitIds.contains(car2.getPermit()));
        assertEquals(2, permitIds.size());
    }

    @Test
    public void testGetPermitIdsForCustomer() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);
        Customer customer1 = office.register("Jane Smith", address, "464-3333");
        Customer customer2 = office.register("John Clark", address, "858-8989");

        Car car1 = office.registerCar(customer1, "MVM-M99", "Honda CRV", CarType.SUV);
        Car car2 = office.registerCar(customer2, "POP-P34", "Ford Focus", CarType.COMPACT);

        Set<String> janePermitIds = office.getPermitIds(customer1);
        assertTrue(janePermitIds.contains(car1.getPermit()));
        assertFalse(janePermitIds.contains(car2.getPermit()));
        assertEquals(1, janePermitIds.size());
    }

    @Test
    public void testGetCustomerCars() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);
        Customer customer = office.register("Jane Smith", address, "464-3333");

        Car car1 = office.registerCar(customer, "MVM-M99", "Honda CRV", CarType.SUV);
        Car car2 = office.registerCar(customer, "POP-P34", "Ford Focus", CarType.COMPACT);

        List<Car> customerCars = office.getCustomerCars(customer);
        assertEquals(2, customerCars.size());
        assertTrue(customerCars.contains(car1));
        assertTrue(customerCars.contains(car2));
    }

    @Test
    public void testIsPermitValid() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);
        Customer customer = office.register("Jane Smith", address, "464-3333");

        Car car = office.registerCar(customer, "MVM-M99", "Honda CRV", CarType.SUV);

        assertTrue(office.isPermitValid(car.getPermit()));
        assertFalse(office.isPermitValid("Invalid Permit"));
    }

    @Test
    public void testAddParkingLot() {
        Address address = new Address.Builder()
                .setStreetAddress1("512 West Ave")
                .setStreetAddress2("Unit 550")
                .setCity("Austin")
                .setState("TX")
                .setZipCode("78701")
                .build();

        ParkingOffice office = new ParkingOffice("Main Office", address);

        ParkingChargeStrategy dummyStrategy = transaction -> 1500.0;
        ParkingLot lot = new ParkingLot("LOT-001", address, 100, "Main Lot", 5.0, dummyStrategy, LocalDate.of(2024, Month.SEPTEMBER, 4));

        office.addParkingLot(lot);
        assertTrue(office.getPermitIds().isEmpty());
        assertTrue(lot.getParkedCars().isEmpty());
    }
}
