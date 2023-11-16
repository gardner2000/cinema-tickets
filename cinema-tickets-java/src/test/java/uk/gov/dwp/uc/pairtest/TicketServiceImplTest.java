package uk.gov.dwp.uc.pairtest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;


public class TicketServiceImplTest {

    private TicketService ticketService;

    @Mock
    SeatReservationService seatReservationService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        ticketService = new TicketServiceImpl(seatReservationService);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void nullTicketRequestIsInvalidPurchase() {
        ticketService.purchaseTickets(1L,null);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void emptyTicketRequestIsInvalidPurchase() {
        ticketService.purchaseTickets(1L);
    }

    @Test
    public void setSeatReservationServiceIsCalled() {
        ticketService.purchaseTickets(1L, new TicketTypeRequest(TicketTypeRequest.Type.ADULT,1));
        Mockito.verify(seatReservationService).reserveSeat(1L,2);
    }

}