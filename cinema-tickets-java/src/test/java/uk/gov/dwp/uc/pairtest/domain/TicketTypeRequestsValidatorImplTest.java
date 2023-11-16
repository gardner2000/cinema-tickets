package uk.gov.dwp.uc.pairtest.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicketTypeRequestsValidatorImplTest {

    private TicketTypeRequestsValidator ticketTypeRequestsValidator;

    @Before
    public void setUp() {
        ticketTypeRequestsValidator = new TicketTypeRequestsValidatorImpl();
    }

    @Test
    public void negativeTicketRequestIsInvalid() {

        TicketTypeRequest negativeTicketRequest =
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT,-1);

        Assert.assertFalse("Requests for negative number of tickets should be invalid",
                ticketTypeRequestsValidator.areTicketRequestsValid(negativeTicketRequest));
    }

    @Test
    public void noAdultsTicketRequestsAreInvalid() {

        TicketTypeRequest childTicketRequest =
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD,4);

        Assert.assertFalse("Requests with no adults should be invalid",
                ticketTypeRequestsValidator.areTicketRequestsValid(childTicketRequest));
    }

    @Test
    public void moreInfantsThanAdultTicketRequestsAreInvalid() {

        TicketTypeRequest childTicketRequest1 =
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT,1);

        TicketTypeRequest childTicketRequest2 =
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT,2);

        TicketTypeRequest adultTicketRequest =
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT,2);

        Assert.assertFalse("Requests with more infants than adults are invalid",
                ticketTypeRequestsValidator.areTicketRequestsValid(childTicketRequest1,
                        childTicketRequest2, adultTicketRequest));
    }

    @Test
    public void sameInfantsAsAdultTicketRequestsAre() {

        TicketTypeRequest childTicketRequest =
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT,8);

        TicketTypeRequest adultTicketRequest =
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT,8);

        Assert.assertFalse("Requests with same number of infants as adults are valid",
                ticketTypeRequestsValidator.areTicketRequestsValid(childTicketRequest,
                        adultTicketRequest, adultTicketRequest));
    }

    @Test
    public void requestsForMoreThanMaximumTicketsAreInvalid() {

        TicketTypeRequest infantTicketRequest =
                new TicketTypeRequest(TicketTypeRequest.Type.INFANT,5);

        TicketTypeRequest childTicketRequest =
                new TicketTypeRequest(TicketTypeRequest.Type.CHILD,5);

        TicketTypeRequest adultTicketRequest =
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT,11);

        Assert.assertFalse("Requests with more than maximum number of tickets are invalid",
                ticketTypeRequestsValidator.areTicketRequestsValid(infantTicketRequest,
                        childTicketRequest, adultTicketRequest));
    }

    @Test
    public void requestsForMaximumTicketsAreValid() {

        TicketTypeRequest adultTicketRequest =
                new TicketTypeRequest(TicketTypeRequest.Type.ADULT,20);

        Assert.assertTrue("Requests with maximum number of tickets are valid",
                ticketTypeRequestsValidator.areTicketRequestsValid(adultTicketRequest));
    }
}