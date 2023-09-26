package Seat;

import java.util.Random;

public record Seat(char rowMarker, int seatNumber, double price, boolean isReserved) {
    public Seat(char rowMarker, int seatNumber)
    {
        this(rowMarker, seatNumber,
                rowMarker > 'C' && (seatNumber <= 2 ||seatNumber >= 9) ? 50 : 75, new Random().nextBoolean());
    }

    @Override
    public String toString() {
        return "%c%03d %.0f$ %10s".formatted(rowMarker, seatNumber, price, !isReserved ? "free" : " reserved");
    }
}
