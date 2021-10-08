package entities.impl;

public class Passenger {

    public String name;
    public int currentFloor;
    public int targetFloor;
    public int weight;

    public Passenger(String name, int currentFloor, int targetFloor, int weight) {
        this.name = name;
        this.currentFloor = currentFloor;
        this.targetFloor = targetFloor;
        this.weight = weight;
    }

    public Passenger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
