package uk.gov.dwp.uc.pairtest.domain;

public class TicketCostCalculatorImpl implements TicketCostCalculator {

  public static final int ADULT_TICKET_COST = 20;
  public static final int CHILD_TICKET_COST = 10;
  public static final int INFANT_TICKET_COST = 0;

  @Override
  public int calculateTicketsCost(TicketTypeRequest... ticketTypeRequests) {

    int adultsCost =
        TicketRequestsCounter.calculateTicketsForType(
                TicketTypeRequest.Type.ADULT, ticketTypeRequests)
            * ADULT_TICKET_COST;
    int childrenCost =
        TicketRequestsCounter.calculateTicketsForType(
                TicketTypeRequest.Type.CHILD, ticketTypeRequests)
            * CHILD_TICKET_COST;
    int infantCost =
        TicketRequestsCounter.calculateTicketsForType(
                TicketTypeRequest.Type.INFANT, ticketTypeRequests)
            * INFANT_TICKET_COST;

    return adultsCost + childrenCost + infantCost;
  }
}
