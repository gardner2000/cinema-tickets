package uk.gov.dwp.uc.pairtest.domain;

public interface TicketCostCalculator {

  public int calculateTicketsCost(TicketTypeRequest... ticketTypeRequests);
}
