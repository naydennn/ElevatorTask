package entities.interfaces;

import entities.impl.Passenger;

public interface Elevator {

    public void moveUp();
    public void moveDown();
    public void addPassengerToElevator(Passenger passenger);
    public void removePassenger(Passenger passenger);
}
