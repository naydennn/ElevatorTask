package core.impl;

import core.interfaces.Controller;
import entities.impl.ElevatorControlSystemImpl;
import entities.impl.ElevatorImpl;
import entities.impl.Passenger;
import entities.interfaces.ElevatorControlSystem;
import repositories.impl.ElevatorRepository;
import repositories.impl.PassengerRepository;

import java.util.List;

public class ControllerImpl implements Controller {

    public ElevatorRepository elevatorRepository;
    public PassengerRepository passengerRepository;
    public ElevatorControlSystem elevatorControlSystem;

    public ControllerImpl() {
        this.elevatorRepository = new ElevatorRepository();
        this.passengerRepository = new PassengerRepository();
        this.elevatorControlSystem = new ElevatorControlSystemImpl();
    }

    @Override
    public String addPassenger(Passenger passenger) {
        this.passengerRepository.add(passenger);
        return String.format("Successful added passenger - %s. ", passenger.getName());
    }

    @Override
    public String addElevator(ElevatorImpl elevator) {
        StringBuilder result = new StringBuilder();

        ElevatorImpl elevatorToCheck = elevatorRepository.find(new ElevatorImpl(elevator.getId()));

        if (elevatorToCheck != null) {
            return String.format("Already exist elevator with  id %d. ", elevator.getId());
        }

        this.elevatorRepository.add(elevator);
        result.append(String.format("Successful added elevator with id - %d. ", elevator.getId()));
        result.append(elevatorControlSystem.getBestElevatorForPassenger(elevator));

        return result.toString();
    }

    @Override
    public String moveElevator(String position, Integer elevatorId) {
        StringBuilder result = new StringBuilder();
        ElevatorImpl elevator = elevatorRepository.find(new ElevatorImpl(elevatorId));

        if (elevator == null) {
            return "There is no elevator with this id.";
        }

        switch (position.toLowerCase()) {
            case "up":
                elevator.moveUp();
                result.append(elevatorControlSystem.getBestElevatorForPassenger(elevator));
                result.append(elevatorControlSystem.checkForPassengerToLeave(elevator));
                break;
            case "down":
                if (elevator.getCurrentFloor() - 1 <= 0) {
                    result.append("There is no floor under floor 1. ");
                } else {
                    elevator.moveDown();
                    result.append(elevatorControlSystem.getBestElevatorForPassenger(elevator));
                    result.append(elevatorControlSystem.checkForPassengerToLeave(elevator));
                }
                break;
            default:
                return "Please enter the correct destination!";
        }
        result.append(String.format("Elevator with id - %d now is on %d floor",
                elevator.getId(), elevator.getCurrentFloor())).append(System.lineSeparator());
        return result.toString();
    }

    @Override
    public String makeRequest(String name) {

        StringBuilder result = new StringBuilder();

        Passenger passenger = passengerRepository.find(new Passenger(name));
        if (passenger == null) {
            return "There is no passenger with that name.";
        }
        elevatorControlSystem.makeRequest(passenger);
        result.append(String.format("Passenger with name %s successfully make request to elevator system. ",
                passenger.getName()));

        List<ElevatorImpl> elevators = this.elevatorRepository.getElevators();
        if (elevators.isEmpty()) {
            result.append("No added elevators, please add elevator to make the request. ");
        } else  {
            for (ElevatorImpl elevator : elevators) {
               result.append(elevatorControlSystem.getBestElevatorForPassenger(elevator));
            }
        }
        return result.toString();
    }
}
