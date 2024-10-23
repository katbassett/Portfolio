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

public class Address {

    private final String streetAddress1;
    private final String streetAddress2;
    private final String city;
    private final String state;
    private final String zipCode;

    public Address(Builder builder) {
        this.streetAddress1 = builder.streetAddress1;
        this.streetAddress2 = builder.streetAddress2;
        this.city = builder.city;
        this.state = builder.state;
        this.zipCode = builder.zipCode;
    }
    
    public static class Builder {
        
        private String streetAddress1;
        private String streetAddress2;
        private String city;
        private String state;
        private String zipCode;
        
        public Builder setStreetAddress1(String streetAddress1) {
            this.streetAddress1 = streetAddress1;
            return this;
        }
        
        public Builder setStreetAddress2(String streetAddress2) {
            this.streetAddress2 = streetAddress2;
            return this;
        }
        
        public Builder setCity(String city) {
            this.city = city;
            return this;
        }
        
        public Builder setState(String state) {
            this.state = state;
            return this;
        }
        
        public Builder setZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }
        
        public Address build() {
            return new Address(this);
        }
              
    }

    public String getAddressInfo() {
        return streetAddress1 + ", " + streetAddress2 + ", " + city + ", " + state + ", " + zipCode;
    }

    @Override
    public String toString() {
        return getAddressInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return streetAddress1.equals(address.streetAddress1)
                && streetAddress2.equals(address.streetAddress2)
                && city.equalsIgnoreCase(address.city)
                && state.equalsIgnoreCase(address.state)
                && zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetAddress1, streetAddress2, city, state, zipCode);
    }
}
