package org.lunatech.airports.console.step;

import org.lunatech.airports.service.ServiceRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Anastasia on 19.09.2015.
 */
public abstract class AbstractStep {
    protected ServiceRequest serviceRequest = new ServiceRequest();

    protected int getCommand() {
        System.out.println("Enter a command: ");
        String s = readConsoleValue();
        if (s.matches("[0-2]{1}")) return Integer.parseInt(s);
        else {
            System.out.println("Wrong command. Try again.");
            return getCommand();
        }
    }

    protected void unknownCommand() {
        System.out.println("Unknown Command. Try again");
    }

    protected String readConsoleValue() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error while reading from console.");
            throw new RuntimeException(e);
        }
    }

    protected abstract void doExecute();
}
