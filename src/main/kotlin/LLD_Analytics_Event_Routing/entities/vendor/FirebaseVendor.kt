package LLD_Analytics_Event_Routing.entities.vendor

import LLD_Analytics_Event_Routing.entities.enum.EventType
import LLD_Analytics_Event_Routing.entities.event.Event
//record system event
class FirebaseVendor:Vendor {
    override fun send(event: Event) {
        println("Firebase uploaded ${event.name} event at time ${event.timestamp} ")
    }

    override fun supports(type: EventType) = type == EventType.SYSTEM
}