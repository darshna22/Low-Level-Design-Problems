package LLD_Elevator_System.controller

import LLD_Elevator_System.enums.Direction
import LLD_Elevator_System.model.elevator.ElevatorCar
import java.util.*


class ElevatorController : ElevatorScheduler {
    var elevatorCarList = mutableListOf<ElevatorCar>()
    private var upFloorsReq = PriorityQueue<Int>()
    private var downFloorsReq = PriorityQueue(Collections.reverseOrder<Int>())
    private val upPendingReq = mutableListOf<Int>()
    private val downPendingReq = mutableListOf<Int>()

    @Volatile
    private var isRunning = true // need to use this thread safe variable isRunning, using volatile
    private var runningThread: Thread? = null;
    private lateinit var elevatorCar: ElevatorCar

    override fun submitNewReq(floor: Int, elevatorDirection: Direction) {
        elevatorCar = elevatorCarList[0]

        when (elevatorDirection) {
            Direction.UP -> {
                if (floor >= elevatorCar.currentFloor) {
                    upFloorsReq.add(floor)
                } else {
                    upPendingReq.add(floor)
                }

            }

            Direction.DOWN -> {
                if (floor <= elevatorCar.currentFloor) {
                    downFloorsReq.add(floor)
                } else {
                    downPendingReq.add(floor)
                }
            }
        }
        if(runningThread == null) {
             this.runningThread = Thread {
                 moveOrderToElevator()
             }
            this.runningThread?.start()
        }
    }

   private fun moveOrderToElevator() {
        println("${Thread.currentThread()} has run.")
        try {
            while (isRunning) {
                Thread.sleep(1000)
                if(downFloorsReq.size + upFloorsReq.size == 0) {
                    //this.runningThread?.interrupt() // graceful exit, this.runningThread?.interrupt() throws exception
                    isRunning = false;
                    runningThread = null;
                }
                println( "[Sleep 1s] Req Size " + (downFloorsReq.size + upFloorsReq.size))
                if (!upFloorsReq.isEmpty()) {
                    while (!upFloorsReq.isEmpty()) {
                        elevatorCar.move(upFloorsReq.poll(), Direction.UP)
                        Thread.sleep(1000);

                    }
                    upPendingReq.forEach { upPendingReq ->
                        upFloorsReq.add(upPendingReq)
                    }
                    upPendingReq.clear()
                    elevatorCar.display.currentFloor=elevatorCar.currentFloor
                    elevatorCar.direction=Direction.DOWN

                }

                if (!downFloorsReq.isEmpty()) {
                    while (!downFloorsReq.isEmpty()) {
                        elevatorCar.move(downFloorsReq.poll(), Direction.DOWN)
                        Thread.sleep(1000);
                    }
                    downPendingReq.forEach { upPendingReq ->
                        downFloorsReq.add(upPendingReq)
                    }
                    downPendingReq.clear()
                    elevatorCar.display.currentFloor=elevatorCar.currentFloor
                    elevatorCar.direction=Direction.UP

                }
            }
        }catch (ex: Exception) {
            isRunning = false;
            runningThread = null;
            println("Error occurred, message " + ex.message);
        }
    }


}