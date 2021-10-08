package core.impl;

import core.interfaces.Controller;
import core.interfaces.Engine;
import entities.Command;
import entities.impl.ElevatorImpl;
import entities.impl.Passenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EngineImpl implements Engine {

    private Controller controller;
    private BufferedReader reader;

    public EngineImpl() {
        this.controller = new ControllerImpl();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (true) {
            String result = null;

            try {
                result = processInput();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (result != null && result.equals("Exit")) {
                break;
            }

            System.out.println(result);
            System.out.println();
        }
    }

    private String processInput() throws IOException {

        System.out.println("Please choose one of this options: " + System.lineSeparator() +
                "addPassenger, addElevator, moveElevator, makeRequest or Exit to finish the program.");
        String input = this.reader.readLine();

        Command command = Command.valueOf(input);
        String result = null;


        switch (command) {
            case addPassenger:
                System.out.println("Now please write name of passenger, " +
                        "current floor, target floor and weight of passenger with white space.");
                String[] data = parseCommand();
                result = addPassenger(data);
                break;
            case addElevator:
                System.out.println("Now please write id of elevator, " +
                        "current floor, and max weight of elevator with white space.");
                data = parseCommand();
                result = addElevator(data);
                break;
            case moveElevator:
                System.out.println("Now please write position to move and id of elevator with white space. " +
                        "Positions must be up or down");
                data = parseCommand();
                result = moveElevator(data);
                break;
            case makeRequest:
                System.out.println("Now please write name of passenger who want to take elevator.");
                data = parseCommand();
                result = makeRequest(data);
                break;
            case Exit:
                result = "Exit";
                break;
        }

        return result;
    }

    private String makeRequest(String[] data) {
        return controller.makeRequest(data[0]);
    }

    private String[] parseCommand() throws IOException {
        return reader.readLine().split("\\s+");
    }

    private String moveElevator(String[] data) {
        return controller.moveElevator(data[0], Integer.parseInt(data[1]));
    }

    private String addElevator(String[] data) {
        return controller.addElevator(new ElevatorImpl(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                Integer.parseInt(data[2])));
    }

    private String addPassenger(String[] data) {
        return controller.addPassenger(new Passenger(data[0], Integer.parseInt(data[1]),
                Integer.parseInt(data[2]), Integer.parseInt(data[3])));
    }
}
