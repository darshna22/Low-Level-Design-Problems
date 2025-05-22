import LLD_Analytics_Event_Routing.entities.event.Event
import LLD_Analytics_Event_Routing.entities.event.SystemEvent
import LLD_Analytics_Event_Routing.entities.event.UserEvent
import LLD_Analytics_Event_Routing.entities.vendor.FacebookVendor
import LLD_Analytics_Event_Routing.entities.vendor.FirebaseVendor
import LLD_Analytics_Event_Routing.entities.vendor.Vendor
import LLD_Analytics_Event_Routing.entities.vendor.WebEngageVendor

class AnalyticsPlatform {

    private val vendors = listOf(
        WebEngageVendor(),
        FirebaseVendor(),
        FacebookVendor()
    )

    fun recordEvent(event: Event) {
        for (vendor in vendors) {
            if (shouldSendEventToVendor(event, vendor)) {
                vendor.send(event)
            }
        }
    }

    private fun shouldSendEventToVendor(event: Event, vendor: Vendor): Boolean {
        return when (vendor) {
            is WebEngageVendor -> true // handles all events
            is FirebaseVendor -> event is UserEvent
            is FacebookVendor -> event is SystemEvent
            else -> false
        }
    }
}
