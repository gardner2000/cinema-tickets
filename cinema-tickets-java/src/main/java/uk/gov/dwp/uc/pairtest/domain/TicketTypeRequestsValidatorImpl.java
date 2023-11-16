package uk.gov.dwp.uc.pairtest.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.gov.dwp.uc.pairtest.account.AccountValidator;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TicketTypeRequestsValidatorImpl implements TicketTypeRequestsValidator {

  static final Logger logger = LogManager.getLogger(AccountValidator.class);

  public static final Integer MAXIMUM_NUMBER_OF_TICKETS = 20;

  @Override
  public boolean areTicketRequestsValid(TicketTypeRequest... ticketTypeRequests) {

    if (areAnyRequestsForNegativeTickets(ticketTypeRequests)) {
      logger.warn("Ticket type request contains negative ticket number request");
      return false;
    }

    if (areAnyRequestsForNegativeTickets(ticketTypeRequests)) {
      logger.warn("Ticket type request contains negative ticket number request");
      return false;
    }

    if (getTotalTickets(ticketTypeRequests) > MAXIMUM_NUMBER_OF_TICKETS) {
      logger.warn("Ticket request exceeds maximum number of tickets");
      return false;
    }

    if (TicketRequestsCounter.calculateTicketsForType(
            TicketTypeRequest.Type.ADULT, ticketTypeRequests)
        == 0) {
      logger.warn("Ticket request does not include at least 1 adult");
      return false;
    }

    if (areThereMoreAdultsThanInfants(ticketTypeRequests)) {
      logger.warn("Requests must have at least the same amount of adults as infants");
      return false;
    }

    return true;
  }

  private boolean areAnyRequestsForNegativeTickets(TicketTypeRequest... ticketTypeRequests) {
    return Arrays.stream(ticketTypeRequests)
        .anyMatch(ticketTypeRequest -> ticketTypeRequest.getNoOfTickets() < 0);
  }

  private boolean areThereMoreAdultsThanInfants(TicketTypeRequest... ticketTypeRequests) {
    return TicketRequestsCounter.calculateTicketsForType(
            TicketTypeRequest.Type.INFANT, ticketTypeRequests)
        > TicketRequestsCounter.calculateTicketsForType(
            TicketTypeRequest.Type.ADULT, ticketTypeRequests);
  }

  private Integer getTotalTickets(TicketTypeRequest... ticketTypeRequests) {
    return Arrays.stream(ticketTypeRequests)
        .collect(Collectors.summingInt(TicketTypeRequest::getNoOfTickets));
  }
}
