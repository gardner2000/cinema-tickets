package uk.gov.dwp.uc.pairtest;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.account.AccountValidator;
import uk.gov.dwp.uc.pairtest.domain.TicketCostCalculator;
import uk.gov.dwp.uc.pairtest.domain.TicketRequestsCounter;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestsValidator;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

  static final Logger logger = LogManager.getLogger(AccountValidator.class);

  private final SeatReservationService seatReservationService;
  private final TicketPaymentService ticketPaymentService;
  private final TicketTypeRequestsValidator ticketTypeRequestsValidator;
  private final AccountValidator accountValidator;
  private final TicketCostCalculator ticketCostCalculator;

  @Override
  public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests)
      throws InvalidPurchaseException {

    if (!accountValidator.isAccountValid(accountId)) {
      logger.warn("Account is invalid");
      throw new InvalidPurchaseException("Account is invalid");
    }

    if (!ticketTypeRequestsValidator.areTicketRequestsValid(ticketTypeRequests)) {
      logger.warn("TicketTypeRequests are invalid");
      throw new InvalidPurchaseException("TicketTypeRequests are invalid");
    }

    reserveSeats(accountId, ticketTypeRequests);
    makePayment(accountId, ticketTypeRequests);
    logger.info("Tickets successfully purchased");
  }

  private void reserveSeats(Long accountId, TicketTypeRequest[] ticketTypeRequests) {
    int totalSeats =
        TicketRequestsCounter.calculateTicketsForType(Type.ADULT, ticketTypeRequests)
            + TicketRequestsCounter.calculateTicketsForType(Type.CHILD, ticketTypeRequests);

    seatReservationService.reserveSeat(accountId, totalSeats);
    logger.info("Called seatReservationService to reserve {} seats", totalSeats);
  }

  private void makePayment(Long accountId, TicketTypeRequest[] ticketTypeRequests) {
    int totalTicketCost = ticketCostCalculator.calculateTicketsCost(ticketTypeRequests);
    ticketPaymentService.makePayment(accountId, totalTicketCost);
    logger.info(
        "Called TicketPaymentService make payment of {} (account number redacted)",
        totalTicketCost);
  }
}
