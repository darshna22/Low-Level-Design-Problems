package LLD_Elevator_System.dispatchers

import LLD_Elevator_System.controller.ElevatorController
import LLD_Elevator_System.enums.Direction

class InternalButtonReqDispatcher(private val elevatorController: ElevatorController) {
    fun pressButton(destinationFloor: Int, direction: Direction) {
        elevatorController.submitNewReq(destinationFloor, direction)
    }
}