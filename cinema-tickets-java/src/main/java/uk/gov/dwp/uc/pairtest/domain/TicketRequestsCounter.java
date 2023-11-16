package uk.gov.dwp.uc.pairtest.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TicketRequestsCounter {

  private TicketRequestsCounter() {}

  public static int calculateTicketsForType(
      TicketTypeRequest.Type type, TicketTypeRequest... ticketTypeRequests) {
    return Arrays.stream(ticketTypeRequests)
        .filter(ticketTypeRequest -> ticketTypeRequest.getTicketType() == type)
        .collect(Collectors.summingInt(TicketTypeRequest::getNoOfTickets));
  }
}
