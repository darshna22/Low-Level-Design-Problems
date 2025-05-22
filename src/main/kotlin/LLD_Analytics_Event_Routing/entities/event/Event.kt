package LLD_Analytics_Event_Routing.entities.event

import LLD_Analytics_Event_Routing.entities.enum.EventType

// Abstract Event class
abstract class Event(
    val type: EventType,
    open val name: String,
    val timestamp: Long
)

