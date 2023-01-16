package LLD_Elevator_System.model.elevator

import LLD_Elevator_System.enums.Direction
import LLD_Elevator_System.enums.State
import LLD_Elevator_System.model.Display
import LLD_Elevator_System.model.InternalButton

abstract class ElevatorCar {
    var elevatorCarId: Int = 0
    var currentFloor:Int=0
    var display: Display=Display(Direction.UP,0)
    var internalButton: InternalButton? = null
    var direction: Direction = Direction.UP
    var state: State = State.IDLE


    abstract fun move(destinationFloor: Int, direction: Direction)

}