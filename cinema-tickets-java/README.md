# Cinema Tickets Java

A java package to validate ticket requests and call paymentgateway and seatbooking systems  

## Prerequisites
Java 17  
Maven

## Testing

To execute all the tests:

```bash
mvn clean test
```

## Assumptions

It is not possible to buy negative number of tickets (they are not refunds)
Requests for zero tickets are also invalid
Only one infant can sit on an adults lap
It is possible to have multiple requests for the same ticket type to purchase tickets (e.g. 5 adult followed by 3 adult)

## Implementation 

There are separate classes for account and ticket 
request validation and a cost calculator. 
It is hard to tell if this is overkill for this scenario but it did ask for reusable code,
plus it adheres to the single responsibility principle.   
A simpler approach would to have coded it all in TicketServiceImpl using private methods.

Lombok is used to reduce the boilerplate code required to inject the dependencies into TicketServiceImpl.
Basic logging is in place using log4j

Properties such as Ticket costs are hard coded, but it is beyond the scope of the excercise to obtain them from the environment.  
As the properties are hard coded the tests are coupled to those values, this needs resolving.