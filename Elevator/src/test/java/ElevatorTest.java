import core.impl.ControllerImpl;
import entities.impl.ElevatorImpl;
import entities.impl.Passenger;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ElevatorTest {

    public static final String MAKE_REQUEST_WITH_NO_EXIST_PASSENGER_MESSAGE = "There is no passenger with that name.";
    public static final String ADD_PASSENGER_IN_FULL_ELEVATOR_MESSAGE = "Passenger with name Pesho " +
            "successfully make request to elevator system. Elevator with id 1 cannot pick up passenger with name Pesho. ";

    private ElevatorImpl elevator;
    private Passenger passenger;
    private ControllerImpl controller;

    @Before
    public void setUp() {
        elevator = new ElevatorImpl(1, 1, 100);
        passenger = new Passenger("Naiden", 1, 3, 82);
        controller = new ControllerImpl();
    }

    @Test
    public void testAddElevatorSuccessful() {
        controller.addElevator(this.elevator);
        assertEquals(1, controller.elevatorRepository.getElevators().size());
    }

    @Test
    public void testAddElevatorWithSameIds() {
        controller.addElevator(this.elevator);
        controller.addElevator(this.elevator);
        assertEquals(1, controller.elevatorRepository.getElevators().size());
    }

    @Test
    public void testAddPassengerSuccessful() {
        controller.addPassenger(this.passenger);
        assertEquals(1, controller.passengerRepository.getPassengers().size());
    }

    @Test
    public void testMakeRequestWithNoPassengers() {
        String makeRequest = controller.makeRequest("Ivan");
        assertEquals(MAKE_REQUEST_WITH_NO_EXIST_PASSENGER_MESSAGE, makeRequest);
    }

    @Test
    public void testMoveUpElevator() {
        controller.addElevator(this.elevator);
        controller.moveElevator("up", 1);

        ElevatorImpl elevator = controller.elevatorRepository.find(new ElevatorImpl(1));

        assertEquals(2, elevator.getCurrentFloor());
    }

    @Test
    public void testMoveDownElevator() {
        controller.addElevator(new ElevatorImpl(2, 2, 100));
        controller.moveElevator("down", 2);

        ElevatorImpl elevator = controller.elevatorRepository.find(new ElevatorImpl(2));

        assertEquals(1, elevator.getCurrentFloor());
    }

    @Test
    public void testMoveDownElevatorWithCurrentFloorOne() {
        controller.addElevator(this.elevator);
        controller.moveElevator("down", 1);

        ElevatorImpl elevator = controller.elevatorRepository.find(new ElevatorImpl(1));

        assertEquals(1, elevator.getCurrentFloor());
    }

    @Test
    public void testAddPassengerToElevatorAtTheSameFloor() {
        addEntities();
        controller.makeRequest(this.passenger.getName());

        assertEquals(1, controller.elevatorRepository.find(this.elevator).getPassengers().size());
    }

    private void addEntities() {
        controller.addElevator(this.elevator);
        controller.addPassenger(this.passenger);
    }

    @Test
    public void testRemovePassengerFromElevatorAtTheTargetFloor() {
        addEntities();
        controller.makeRequest(this.passenger.getName());
        controller.moveElevator("up", 1);
        controller.moveElevator("up", 1);

        assertEquals(0, controller.elevatorRepository.find(elevator).getPassengers().size());
    }

    @Test
    public void testCannotAddPassengerInFullElevator() {
        addEntities();
        controller.addPassenger(new Passenger("Pesho", 1, 3, 1000));
        assertEquals(ADD_PASSENGER_IN_FULL_ELEVATOR_MESSAGE, controller.makeRequest("Pesho"));
    }
}
