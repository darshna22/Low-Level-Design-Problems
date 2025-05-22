package LLD_Analytics_Event_Routing.entities.event

import LLD_Analytics_Event_Routing.entities.enum.EventType

class UserEvent(override val name: String) :
    Event(type = EventType.USER, name = name, timestamp = System.currentTimeMillis())