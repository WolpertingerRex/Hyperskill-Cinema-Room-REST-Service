package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Ticket {
    @JsonProperty("ticket")
    private Seat seat;
    private UUID token;

    public Ticket(Seat seat) {
        this.seat = seat;
        token = UUID.randomUUID();
    }

    public Ticket() {
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
