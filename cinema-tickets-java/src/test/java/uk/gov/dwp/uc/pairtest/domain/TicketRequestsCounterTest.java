package uk.gov.dwp.uc.pairtest.domain;

import org.junit.Assert;
import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;

public class TicketRequestsCounterTest {

  TicketTypeRequest adultTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);

  TicketTypeRequest childTicketRequest1 = new TicketTypeRequest(Type.CHILD, 3);

  TicketTypeRequest childTicketRequest2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 4);

  @Test
  public void calculatesZeroWhenNoneOfThatType() {

    Assert.assertEquals(
        "Should calculate 0 when no requests for given type",
        TicketRequestsCounter.calculateTicketsForType(Type.CHILD, adultTicketRequest),
        0);
  }

  @Test
  public void calculatesCorrectlyWhenOneOfThatType() {

    Assert.assertEquals(
        "Should calculate correctly when 1 request for given type",
        TicketRequestsCounter.calculateTicketsForType(Type.ADULT, adultTicketRequest),
        2);
  }

  @Test
  public void calculatesCorrectlyWhenMultipleOfThatType() {

    Assert.assertEquals(
        "Should calculate correctly when multiple requests for given type",
        TicketRequestsCounter.calculateTicketsForType(
            Type.CHILD, adultTicketRequest, childTicketRequest1, childTicketRequest2),
        7);
  }
}
