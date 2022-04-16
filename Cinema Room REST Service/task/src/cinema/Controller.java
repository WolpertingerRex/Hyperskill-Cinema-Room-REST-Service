package cinema;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class Controller {

    private Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }

    @PostMapping("/purchase")
    public Ticket purchase(@RequestBody Order order) {
        if (order.getRow() <= 0 || order.getColumn() <= 0 || order.getRow() > 9 || order.getColumn() > 9)
            throw new NoAvailableSeatsException("The number of a row or a column is out of bounds!");

        Seat seat = cinema.getTicket(order.getRow(), order.getColumn());

        if (seat != null && seat.isAvailable()) {
            seat.setAvailable(false);
            int price = seat.getPrice();
            cinema.setIncome(cinema.getIncome() + price);

            Ticket ticket = new Ticket(seat);
            cinema.addTicketToPurchased(ticket);
            return ticket;
        } else {
            throw new NoAvailableSeatsException("The ticket has been already purchased!");
        }
    }

    @PostMapping("/return")
    public Map<String, Seat> returnTicket(@RequestBody Ticket ticket) {
        Ticket toReturn = cinema.returnTicket(ticket.getToken());
        if (toReturn == null) throw new NoAvailableSeatsException("Wrong token!");
        toReturn.getSeat().setAvailable(true);
        int price = toReturn.getSeat().getPrice();
        cinema.setIncome(cinema.getIncome() - price);
        return Map.of("returned_ticket", toReturn.getSeat());
    }

    @PostMapping("/stats")
    public Stats getStats(@RequestParam(value = "password", required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            throw new WrongPasswordException("The password is wrong!");
        }
        return new Stats(cinema);
    }
}

@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(NoAvailableSeatsException.class)
    public ResponseEntity<Map<String, String>> handleNoSeatsException(NoAvailableSeatsException e) {
        // other option is to make Error class
        // with field String message and return ResponseEntity<Error>
        // Error error = new Error(e.getMessage());

        return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Map<String, String>> handlePasswordException(WrongPasswordException e) {
        return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
