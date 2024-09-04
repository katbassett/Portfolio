/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkinglot;

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

    public Address(String streetAddress1, String streetAddress2, String city, String state, String zipCode) {
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
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
