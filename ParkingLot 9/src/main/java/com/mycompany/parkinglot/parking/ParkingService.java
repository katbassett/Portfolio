package com.mycompany.parkinglot.parking;

/**
 *
 * @author katbassett
 */
import com.mycompany.parkinglot.command.Command;
import com.mycompany.parkinglot.parking.ParkingOffice;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ParkingService {

    private final ParkingOffice office;
    private final Map<String, Command> commands;

    public ParkingService(ParkingOffice office) {
        this.office = office;
        this.commands = new HashMap<>();
    }

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public String performCommand(String commandName, String[] parameters) {
        Command command = commands.get(commandName);
        if (command != null) {
            return command.execute(parseParameters(parameters));
        }
        return "Command not found.";
    }

    private Properties parseParameters(String[] parameters) {
        Properties properties = new Properties();
        for (String parameter : parameters) {
            if (parameter.contains("=")) {
                String[] keyValue = parameter.split("=", 2);
                if (keyValue.length == 2) {
                    properties.put(keyValue[0], keyValue[1]);
                } else {
                    properties.put(keyValue[0], "");
                }
            } else {
                System.err.println("Invalid parameter: " + parameter);
            }
        }
        return properties;
    }

}
