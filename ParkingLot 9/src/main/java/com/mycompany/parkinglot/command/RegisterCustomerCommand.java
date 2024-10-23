package com.mycompany.parkinglot.command;

/**
 *
 * @author katbassett
 */

import com.mycompany.parkinglot.command.Command;
import com.mycompany.parkinglot.parking.ParkingOffice;
import com.mycompany.parkinglot.parking.Customer;
import com.mycompany.parkinglot.parking.Address;
import java.util.Properties;

public class RegisterCustomerCommand implements Command{
    
    private final ParkingOffice office;

    public RegisterCustomerCommand(ParkingOffice office) {
        this.office = office;
    }

    @Override
    public String getCommandName() {
        return "CUSTOMER";
    }

    @Override
    public String getDisplayName() {
        return "Register Customer";
    }

    @Override 
    public String execute(Properties params) {
        String name = params.getProperty("name");
        String streetAddress1 = params.getProperty("streetAddress1");
        String streetAddress2 = params.getProperty("streetAddress2");
        String city = params.getProperty("city");
        String state = params.getProperty("state");
        String zipCode = params.getProperty("zipCode");
        String phone = params.getProperty("phone");
        
        if (name == null || streetAddress1 == null || city == null || state == null || zipCode == null || phone == null) {
            return "Missing parameters.";
        }
        
         Address address = new Address.Builder()
                .setStreetAddress1(streetAddress1)
                .setStreetAddress2(streetAddress2)
                .setCity(city)
                .setState(state)
                .setZipCode(zipCode)
                .build();
         
        Customer customer = office.register(name, address, phone);
        return "Customer registered: " + customer.getCustomerId();
    }
}
