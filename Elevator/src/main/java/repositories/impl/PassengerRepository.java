package repositories.impl;

import entities.impl.Passenger;
import repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassengerRepository implements Repository<Passenger> {

    public List<Passenger> passengers;

    public PassengerRepository() {
        this.passengers = new ArrayList<>();
    }

    public List<Passenger> getPassengers() {
        return Collections.unmodifiableList(this.passengers);
    }

    @Override
    public void add(Passenger model) {
        this.passengers.add(model);
    }

    @Override
    public void remove(Passenger model) {
        this.passengers.remove(model);
    }

    @Override
    public Passenger find(Passenger data) {
        return this.passengers.stream().filter(e -> e.getName().equals(data.getName()))
                .findFirst().orElse(null);
    }
}
