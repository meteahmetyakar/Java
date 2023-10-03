package StreamTerminal;

import Seat.Seat;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MainStream {

    public static void main(String[] args) {
        var result = IntStream
                .iterate(0, i -> i <= 1000, i -> i = i + 3)
                .summaryStatistics();
        System.out.println("Result = " + result);

        var leapYearData = IntStream
                .iterate(2000, i -> i <= 2025, i -> i = i + 1)
                .filter(i -> i % 4 == 0)
                .peek(System.out::println)
                .summaryStatistics();
        System.out.println("Leap Year Data = " + leapYearData);

        Seat[] seats = new Seat[100];
        Arrays.setAll(seats, i -> new Seat((char)('A' + i / 10), i % 10 + 1));
        //Arrays.asList(seats).forEach(System.out::println);

        var strm = Stream.of(seats)
                .filter(Seat::isReserved)
                        .count();

        System.out.println(strm);

    }
}
