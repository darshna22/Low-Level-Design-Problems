package LLD_Elevator_System.model.elevator

import LLD_Elevator_System.enums.Direction
import LLD_Elevator_System.enums.State
import LLD_Elevator_System.model.Display


class ElevatorCarNormal : ElevatorCar() {
    override fun move(destinationFloor: Int, direction: Direction) {
        this.state = State.RUNNING
        this.direction = direction
        this.currentFloor=destinationFloor
        this.display = Display(direction = direction, currentFloor = destinationFloor)
        displayCurrentFloor()
    }

    private fun displayCurrentFloor() {
        println("Reached at ${display.direction.name} floor $currentFloor")

    }
}