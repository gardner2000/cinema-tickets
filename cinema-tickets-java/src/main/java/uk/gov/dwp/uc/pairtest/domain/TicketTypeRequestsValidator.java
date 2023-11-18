package uk.gov.dwp.uc.pairtest.domain;

public interface TicketTypeRequestsValidator {

    boolean areTicketRequestsValid(TicketTypeRequest... ticketTypeRequests);
}
