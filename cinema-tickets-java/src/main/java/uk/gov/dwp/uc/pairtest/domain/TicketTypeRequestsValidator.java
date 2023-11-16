package uk.gov.dwp.uc.pairtest.domain;

public interface TicketTypeRequestsValidator {

    public boolean areTicketRequestsValid(TicketTypeRequest... ticketTypeRequests);
}
