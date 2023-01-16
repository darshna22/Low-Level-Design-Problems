package LLD_Elevator_System

import LLD_Elevator_System.controller.ElevatorController
import LLD_Elevator_System.dispatchers.ExternalButtonDispatchers
import LLD_Elevator_System.dispatchers.InternalButtonReqDispatcher
import LLD_Elevator_System.enums.Direction
import LLD_Elevator_System.model.elevator.ElevatorCarNormal

fun main(args: Array<String>) {
    //step 1.
    val normalElevator = ElevatorCarNormal()
    normalElevator.elevatorCarId = 1
    val elevatorController = ElevatorController()

    elevatorController.elevatorCarList.add(normalElevator)
    val externalButtonDispatchers = ExternalButtonDispatchers(elevatorController)
    val internalButtonDispatchers = InternalButtonReqDispatcher(elevatorController)

    externalButtonDispatchers.pressButton(1, Direction.UP)
    internalButtonDispatchers.pressButton(5, Direction.UP)

    externalButtonDispatchers.pressButton(10, Direction.UP)
    internalButtonDispatchers.pressButton(0, Direction.DOWN);

    externalButtonDispatchers.pressButton(2, Direction.UP)
    internalButtonDispatchers.pressButton(7, Direction.UP)

    externalButtonDispatchers.pressButton(3, Direction.UP)
    internalButtonDispatchers.pressButton(6, Direction.UP)

    externalButtonDispatchers.pressButton(8, Direction.DOWN)
    Thread.sleep(2000)
    internalButtonDispatchers.pressButton(-1, Direction.DOWN)

}
