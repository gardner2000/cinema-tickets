package uk.gov.dwp.uc.pairtest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.account.AccountValidator;
import uk.gov.dwp.uc.pairtest.domain.TicketCostCalculator;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequestsValidator;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImplTest {

  private TicketService ticketService;

  @Mock SeatReservationService seatReservationService;

  @Mock TicketPaymentService ticketPaymentService;

  @Mock AccountValidator accountValidator;

  @Mock TicketCostCalculator ticketCostCalculator;

  @Mock TicketTypeRequestsValidator ticketTypeRequestsValidator;

  TicketTypeRequest adultTicketRequest;

  @Before
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    ticketService =
        new TicketServiceImpl(
            seatReservationService,
            ticketPaymentService,
            ticketTypeRequestsValidator,
            accountValidator,
            ticketCostCalculator);

    adultTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
  }

  @Test
  public void validRequestCallsPaymentAndSeatReservationServices() {

    Mockito.when(ticketTypeRequestsValidator.areTicketRequestsValid(Mockito.any()))
        .thenReturn(true);
    Mockito.when(accountValidator.isAccountValid(1L)).thenReturn(true);
    Mockito.when(ticketCostCalculator.calculateTicketsCost(Mockito.any())).thenReturn(50);

    ticketService.purchaseTickets(1L, adultTicketRequest);

    Mockito.verify(ticketPaymentService).makePayment(1L, 50);
    Mockito.verify(seatReservationService).reserveSeat(1L, 2);
  }

  @Test(expected = InvalidPurchaseException.class)
  public void invalidAccountThrowsException() {

    Mockito.when(ticketTypeRequestsValidator.areTicketRequestsValid(Mockito.any()))
        .thenReturn(true);
    Mockito.when(accountValidator.isAccountValid(1L)).thenReturn(false);

    ticketService.purchaseTickets(1L, adultTicketRequest);
  }

  @Test(expected = InvalidPurchaseException.class)
  public void invalidTicketRequestThrowsException() {

    Mockito.when(ticketTypeRequestsValidator.areTicketRequestsValid(Mockito.any()))
        .thenReturn(false);
    Mockito.when(accountValidator.isAccountValid(1L)).thenReturn(true);

    ticketService.purchaseTickets(1L, adultTicketRequest);
    
  }
}
