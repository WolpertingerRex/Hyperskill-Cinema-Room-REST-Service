package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
    @JsonIgnore
    private Cinema cinema;
    @JsonProperty("current_income")
    private int income;
    @JsonProperty("number_of_available_seats")
    private int availableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private int purchasedTickets;

    public Stats(Cinema cinema) {
        this.cinema = cinema;
        income = cinema.getIncome();
        availableSeats = cinema.getAvailableSeats().size() - cinema.getPurchasedTickets().size();
        purchasedTickets = cinema.getPurchasedTickets().size();
    }

    public Stats() {
    }

    @JsonIgnore
    public Cinema getCinema() {
        return cinema;
    }

    @JsonIgnore
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }

    public void setPurchasedTickets(int purchasedTickets) {
        this.purchasedTickets = purchasedTickets;
    }
}
