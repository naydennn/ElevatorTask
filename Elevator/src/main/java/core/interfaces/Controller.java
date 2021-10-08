package core.interfaces;

import entities.impl.ElevatorImpl;
import entities.impl.Passenger;

public interface Controller {

    public String addPassenger(Passenger passenger);
    public String addElevator(ElevatorImpl elevator);
    public String moveElevator(String position, Integer elevatorId);
    public String makeRequest(String name);
}
