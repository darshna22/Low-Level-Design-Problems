package LLD_Analytics_Event_Routing.entities.vendor

import LLD_Analytics_Event_Routing.entities.enum.EventType
import LLD_Analytics_Event_Routing.entities.event.Event

//record user event
class FacebookVendor : Vendor {
    override fun send(event: Event) {
        println("Facebook uploaded ${event.name} event at time ${event.timestamp}")
    }

    override fun supports(type: EventType) = type == EventType.USER
}