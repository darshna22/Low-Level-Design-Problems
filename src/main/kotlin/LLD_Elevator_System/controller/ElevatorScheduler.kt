package LLD_Elevator_System.controller

import LLD_Elevator_System.enums.Direction

interface ElevatorScheduler {
    fun submitNewReq(floor: Int, elevatorDirection: Direction)
}