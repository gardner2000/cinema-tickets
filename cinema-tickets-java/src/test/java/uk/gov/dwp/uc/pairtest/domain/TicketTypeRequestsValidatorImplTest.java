package uk.gov.dwp.uc.pairtest.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;

public class TicketTypeRequestsValidatorImplTest {

  TicketTypeRequest adult2TicketRequest;
  TicketTypeRequest adult15TicketRequest;
  TicketTypeRequest negativeTicketRequest;
  TicketTypeRequest zeroTicketRequest;
  TicketTypeRequest child4TicketRequest;
  TicketTypeRequest infant1TicketRequest;
  TicketTypeRequest infant2TicketRequest;
  private TicketTypeRequestsValidator ticketTypeRequestsValidator;

  @Before
  public void setUp() {
    ticketTypeRequestsValidator = new TicketTypeRequestsValidatorImpl();

    adult2TicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
    adult15TicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 15);
    negativeTicketRequest = new TicketTypeRequest(Type.ADULT, -1);
    zeroTicketRequest = new TicketTypeRequest(Type.ADULT, 0);
    child4TicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 4);
    infant1TicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);
    infant2TicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2);
  }

  @Test
  public void requestsContainingNullInvalid() {

    Assert.assertFalse(
        "Requests for negative number of tickets should be invalid",
        ticketTypeRequestsValidator.areTicketRequestsValid(adult2TicketRequest, null));
  }

  @Test
  public void zeroTicketRequestIsInvalid() {
      Assert.assertFalse(
            "Requests for negative number of tickets should be invalid",
            ticketTypeRequestsValidator.areTicketRequestsValid(adult2TicketRequest, zeroTicketRequest));
    }

  @Test
  public void negativeTicketRequestIsInvalid() {

    Assert.assertFalse(
        "Requests for negative number of tickets should be invalid",
        ticketTypeRequestsValidator.areTicketRequestsValid(negativeTicketRequest));
  }

  @Test
  public void noAdultsTicketRequestsAreInvalid() {

    Assert.assertFalse(
        "Requests with no adults should be invalid",
        ticketTypeRequestsValidator.areTicketRequestsValid(child4TicketRequest));
  }

  @Test
  public void moreInfantsThanAdultTicketRequestsAreInvalid() {

    Assert.assertFalse(
        "Requests with more infants than adults are invalid",
        ticketTypeRequestsValidator.areTicketRequestsValid(
            infant1TicketRequest, infant2TicketRequest, adult2TicketRequest));
  }

  @Test
  public void sameInfantsAsAdultTicketRequestsAreValid() {

    Assert.assertTrue(
        "Requests with same number of infants as adults are valid",
        ticketTypeRequestsValidator.areTicketRequestsValid(
            infant2TicketRequest, adult2TicketRequest));
  }

  @Test
  public void requestsForMoreThanMaximumTicketsAreInvalid() {

    Assert.assertFalse(
        "Requests with more than maximum number of tickets are invalid",
        ticketTypeRequestsValidator.areTicketRequestsValid(
            infant2TicketRequest, child4TicketRequest, adult15TicketRequest));
  }

  @Test
  public void requestsForMaximumTicketsAreValid() {

    Assert.assertTrue(
        "Requests with maximum number of tickets are valid",
        ticketTypeRequestsValidator.areTicketRequestsValid(
            infant1TicketRequest, child4TicketRequest, adult15TicketRequest));
  }
}
