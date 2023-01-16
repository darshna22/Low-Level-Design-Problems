package LLD_Elevator_System.model

import LLD_Elevator_System.dispatchers.ExternalButtonDispatchers
import LLD_Elevator_System.enums.Direction

class Floor(val floorId: Int, val externalButtonDispatchers: ExternalButtonDispatchers) {

    fun pressBtn(destinationFloor: Int, direction: Direction) {
        externalButtonDispatchers.pressButton(floorId, direction)
    }


}