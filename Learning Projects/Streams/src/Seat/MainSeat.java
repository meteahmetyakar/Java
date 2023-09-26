package Seat;

import Seat.Seat;

import java.util.Comparator;
import java.util.stream.Stream;

public class MainSeat {

    public static void main(String[] args) {

        int maxSeats = 100;
        int seatsInRow = 10;
        var stream = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i->new Seat((char)('A' + i / seatsInRow), i % seatsInRow + 1))
                        .skip(5)
                                .limit(10)
                                        .peek(s -> System.out.println("--> " + s))
                                                .sorted(Comparator.comparing(Seat::price).thenComparing(Seat::toString));


                //.mapToDouble(record.Seat::price)
                //.mapToObj("%.2f"::formatted);


        stream.forEach(System.out::println);



    }

}
