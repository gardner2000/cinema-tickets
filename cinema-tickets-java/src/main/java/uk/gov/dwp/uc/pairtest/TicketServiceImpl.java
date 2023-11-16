package uk.gov.dwp.uc.pairtest;


import lombok.RequiredArgsConstructor;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    private final SeatReservationService seatReservationService;

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {

        if (ticketTypeRequests == null ||ticketTypeRequests.length < 1) {
            throw new InvalidPurchaseException();
        }

        seatReservationService.reserveSeat(1,2);
    }

}
