package entities.interfaces;

import entities.impl.ElevatorImpl;
import entities.impl.Passenger;

public interface ElevatorControlSystem {

    public String getBestElevatorForPassenger(ElevatorImpl elevator);
    public void makeRequest(Passenger passenger);
    public String checkForPassengerToLeave(ElevatorImpl elevator);
}
