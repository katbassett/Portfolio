package com.mycompany.parkinglot.command;

/**
 *
 * @author katbassett
 */

 import java.util.Properties;

public interface Command {
    String getCommandName();
    String getDisplayName();
    String execute(Properties params);
}
