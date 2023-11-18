package uk.gov.dwp.uc.pairtest.domain;

public interface TicketCostCalculator {

  int calculateTicketsCost(TicketTypeRequest... ticketTypeRequests);
}
