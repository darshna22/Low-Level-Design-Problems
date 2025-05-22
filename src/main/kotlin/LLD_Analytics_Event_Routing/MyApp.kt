package LLD_Analytics_Event_Routing

import AnalyticsPlatform
import LLD_Analytics_Event_Routing.entities.event.SystemEvent
import LLD_Analytics_Event_Routing.entities.event.UserEvent

fun main() {
    val analyticsPlatform=AnalyticsPlatform()
    analyticsPlatform.recordEvent(UserEvent(name = "Button click"))
    analyticsPlatform.recordEvent(SystemEvent(name = "Activity Closed"))
}