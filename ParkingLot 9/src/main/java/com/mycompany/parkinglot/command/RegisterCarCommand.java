package com.mycompany.parkinglot.command;

import com.mycompany.parkinglot.command.Command;
import com.mycompany.parkinglot.parking.ParkingOffice;
import com.mycompany.parkinglot.parking.Customer;
import com.mycompany.parkinglot.parking.CarType;
import com.mycompany.parkinglot.parking.Car;
import java.util.Properties;

/**
 * 
 * author @katbassett
 */


public class RegisterCarCommand implements Command {
    
    private final ParkingOffice office;

    public RegisterCarCommand(ParkingOffice office) {
        this.office = office;
    }

    @Override
    public String getCommandName() {
        return "CAR";
    }

    @Override 
    public String getDisplayName() {
        return "Register Car";
    }

     @Override
    public String execute(Properties params) {
        String customerId = params.getProperty("customerId");
        String license = params.getProperty("license");
        String model = params.getProperty("model");
        String carType = params.getProperty("carType");
        
        Customer customer = office.getCustomer(customerId);
        if (customer != null) {
            Car car = office.registerCar(customer, license, model, CarType.valueOf(carType));
            return "Car registered with permit: " + car.getPermit();
        }
        return "Customer not found.";
    }
}
