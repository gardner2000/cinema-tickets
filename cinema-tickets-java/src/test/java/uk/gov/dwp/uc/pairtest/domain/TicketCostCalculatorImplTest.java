package uk.gov.dwp.uc.pairtest.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicketCostCalculatorImplTest {

  private TicketCostCalculator ticketCostCalculator;

  @Before
  public void setUp() {
    ticketCostCalculator = new TicketCostCalculatorImpl();
  }

  @Test
  public void ticketCostsAreCalculatedCorrectly() {

    TicketTypeRequest infantTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);

    TicketTypeRequest childTicketRequest1 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);

    TicketTypeRequest childTicketRequest2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 4);

    TicketTypeRequest adultTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5);

    Assert.assertEquals(
        "Total ticket cost must be correct",
        ticketCostCalculator.calculateTicketsCost(
            infantTicketRequest, childTicketRequest1, childTicketRequest2, adultTicketRequest),
        170);
  }

}
