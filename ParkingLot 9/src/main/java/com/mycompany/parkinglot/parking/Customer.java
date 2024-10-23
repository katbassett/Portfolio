/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot.parking;

/**
 *
 * @author katbassett
 */
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class Customer {

    private final String customerId;
    private final String name;
    private final Address address;
    private final String phoneNumber;
    private final List<Car> cars;

    public Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.name = builder.name;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.cars = builder.cars;
    }
    
    public static class Builder {
        
        private String customerId;
        private String name;
        private Address address;
        private String phoneNumber;
        private final List<Car> cars = new ArrayList<>();
        
        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }
        
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder setAddress(Address address){
            this.address = address;
            return this;
        }
        
        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        
        public Builder addCar(Car car) {
            this.cars.add(car);
            return this;
        }
        
        public Builder setCars(List<Car> cars) {
            this.cars.clear();
            this.cars.addAll(cars);
            return this;
        }
        
        public Customer build() {
            return new Customer(this);
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    @Override
    public String toString() {
        return "Customer{"
                + "customerId='" + customerId + '\''
                + ", name='" + name + '\''
                + ", address=" + address
                + ", phoneNumber=" + phoneNumber + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId)
                && name.equalsIgnoreCase(customer.name)
                && address.equals(customer.address)
                && phoneNumber.equals(customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, address, phoneNumber);
    }

}
