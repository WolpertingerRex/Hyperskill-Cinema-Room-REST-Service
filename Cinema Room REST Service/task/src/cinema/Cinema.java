package cinema;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cinema {
    @JsonProperty("total_rows")
    private int totalRows;
    @JsonProperty("total_columns")
    private int totalColumns;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;

    @JsonIgnore
    private List<Ticket> purchasedTickets;
    @JsonIgnore
    private int income;

       public Cinema() {
    }

    public Cinema(int rows, int columns) {
        this.totalRows = rows;
        this.totalColumns = columns;
        availableSeats = new ArrayList<>();
        for (int i = 1; i <= totalRows; i++){
            for (int j = 1; j <= totalColumns; j++){
                int price = i <= 4 ? 10 : 8;
                Seat seat = new Seat(i, j, price);
                availableSeats.add(seat);
            }
        }
        purchasedTickets = new ArrayList<>();
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void addTicketToPurchased(Ticket ticket){
        purchasedTickets.add(ticket);
    }

    public Seat getTicket(int row, int column){
        return availableSeats.stream()
                .filter(s-> s.getRow() == row && s.getColumn() == column)
                .findFirst().orElse(null);
    }

    public Ticket returnTicket(UUID token){
       Ticket ticket = purchasedTickets.stream()
                .filter(t-> t.getToken().equals(token))
               .findFirst().orElse(null);

       if(ticket != null) purchasedTickets.remove(ticket);
       return ticket;
    }

    @JsonIgnore
    public int getIncome() {
        return income;
    }
    @JsonIgnore
    public void setIncome(int income) {
        this.income = income;}

    @JsonIgnore
    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }
}

