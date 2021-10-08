package entities.impl;

import entities.interfaces.Elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElevatorImpl implements Elevator {

    public static final Double TEMPERATURE_IN_ELEVATOR = 24.5;

    public int id;
    public int currentFloor;
    public int maxWeight;
    public int currentWeight;
    public Double temperature;
    public List<Passenger> passengers;

    public ElevatorImpl(int id, int currentFloor, int maxWeight) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.maxWeight = maxWeight;
        this.temperature = TEMPERATURE_IN_ELEVATOR;
        this.passengers = new ArrayList<>();
        this.currentWeight = 0;
    }

    public ElevatorImpl(int id) {
        this.id = id;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(this.passengers);
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void moveUp() {
        this.currentFloor++;
    }

    @Override
    public void moveDown() {
        this.currentFloor--;
    }

    @Override
    public void addPassengerToElevator(Passenger passenger) {
        this.passengers.add(passenger);
    }

    @Override
    public void removePassenger(Passenger passenger) {
        this.passengers.remove(passenger);
    }

}
