package repositories.impl;

import entities.impl.ElevatorImpl;
import repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElevatorRepository implements Repository<ElevatorImpl> {

    public List<ElevatorImpl> elevators;

    public ElevatorRepository() {
        this.elevators = new ArrayList<>();
    }

    public List<ElevatorImpl> getElevators() {
        return Collections.unmodifiableList(this.elevators);
    }

    @Override
    public void add(ElevatorImpl model) {
        this.elevators.add(model);
    }

    @Override
    public void remove(ElevatorImpl model) {
        this.elevators.remove(model);
    }

    @Override
    public ElevatorImpl find(ElevatorImpl data) {
        return this.elevators.stream().filter(e -> e.getId() == data.getId())
                .findFirst().orElse(null);
    }
}
