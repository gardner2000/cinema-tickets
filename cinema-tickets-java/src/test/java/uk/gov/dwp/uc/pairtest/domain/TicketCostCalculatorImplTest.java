package uk.gov.dwp.uc.pairtest.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicketCostCalculatorImplTest {

  TicketTypeRequest adult1TicketRequest;
  TicketTypeRequest adult5TicketRequest;
  TicketTypeRequest infant1TicketRequest;
  TicketTypeRequest child3TicketRequest;
  TicketTypeRequest child4TicketRequest;
  private TicketCostCalculator ticketCostCalculator;

  @Before
  public void setUp() {
    ticketCostCalculator = new TicketCostCalculatorImpl();

    adult1TicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 1);
    adult5TicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 5);
    infant1TicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);
    child3TicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);
    child4TicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 4);
  }

  @Test
  public void singleTicketCostsAreCalculatedCorrectly() {

    Assert.assertEquals(
        "Single ticket request cost must be correct",
        ticketCostCalculator.calculateTicketsCost(adult1TicketRequest),
        20);
  }

  @Test
  public void singleRequestForMultipleTicketsAreCalculatedCorrectly() {

    Assert.assertEquals(
        "Single request for multiple tickets total ticket request cost must be correct",
        ticketCostCalculator.calculateTicketsCost(adult5TicketRequest),
        100);
  }

  @Test
  public void multipleRequestForMultipleTicketsAreCalculatedCorrectly() {

    Assert.assertEquals(
        "Multiple total ticket cost must be correct",
        ticketCostCalculator.calculateTicketsCost(
            infant1TicketRequest, child3TicketRequest, child4TicketRequest, adult5TicketRequest),
        170);
  }

}
