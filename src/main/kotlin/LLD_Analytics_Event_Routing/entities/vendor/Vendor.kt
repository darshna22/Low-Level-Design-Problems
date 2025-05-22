package LLD_Analytics_Event_Routing.entities.vendor

import LLD_Analytics_Event_Routing.entities.enum.EventType
import LLD_Analytics_Event_Routing.entities.event.Event

interface Vendor {
    fun send(event: Event)
    fun supports(type: EventType): Boolean
}