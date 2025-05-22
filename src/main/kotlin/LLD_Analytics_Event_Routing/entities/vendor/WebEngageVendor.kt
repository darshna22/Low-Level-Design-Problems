package LLD_Analytics_Event_Routing.entities.vendor

import LLD_Analytics_Event_Routing.entities.enum.EventType
import LLD_Analytics_Event_Routing.entities.enum.EventType.*
import LLD_Analytics_Event_Routing.entities.event.Event

//record user and system e
class WebEngageVendor : Vendor {
    override fun send(event: Event) {
        when (event.type) {
            USER -> recordUserEvent(event = event)
            SYSTEM -> recordSystemEvent(event = event)
        }
    }

    override fun supports(type: EventType) = true

    private fun recordUserEvent(event: Event) {
        println("WebEngage uploaded ${event.name} event at time ${event.timestamp}")
    }

    private fun recordSystemEvent(event: Event) {
        println("WebEngage uploaded ${event.name} event at time ${event.timestamp}")
    }
}