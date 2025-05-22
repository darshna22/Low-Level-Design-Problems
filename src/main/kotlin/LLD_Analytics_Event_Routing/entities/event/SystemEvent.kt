package LLD_Analytics_Event_Routing.entities.event

import LLD_Analytics_Event_Routing.entities.enum.EventType

class SystemEvent(override val name: String) :
    Event(type = EventType.SYSTEM, name = name, timestamp = System.currentTimeMillis())