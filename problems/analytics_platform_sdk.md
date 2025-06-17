# Designing a Analytics Platform SDK
## Requirements
* The mobile app should send system-generated and user-generated events to the Analytics SDK. 
* The Analytics SDK should categorize events as either System or User. 
* The SDK must route events to the correct third-party vendor(s) based on predefined rules:
 * System events → WebEngage, Facebook 
 * User events → WebEngage, Firebase
* Vendors should be pluggable and configurable via a routing interface. 
* The SDK should support sending events asynchronously to avoid blocking the main app thread.
* Events should be retried in case of transient failures (e.g., network issues), with limited retry attempts. 
* Each event should support a standardized structure including name, type, timestamp, and key-value data.
* The SDK should log or queue events locally in case of app being offline or API failure. 
* Vendor-specific implementations should conform to a shared client interface to allow clean abstraction. 
* Code coverage for the SDK should meet at least 80% unit test threshold.
* The SDK should expose debug logs or developer mode to inspect event flow during development. 
* Integration with tools like Firebase, Gauge, and Petrol framework for analytics and testing should be supported. 
* SDK must be modular to allow future vendor integrations with minimal code changes. 
* The SDK should handle concurrent event submissions and ensure thread safety.
## Implementations
[Java Implementation ](https://github.com/darshna22/Low-Level-Design-Problems/tree/main/src/main/kotlin/LLD_Analytics_Event_Routing)

## UML Diagram
![Parking Lot](https://github.com/user-attachments/assets/925c4ae1-0a8a-46c9-82d0-4ad58b73c02d)

