import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

public class MainLocalDate {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate Five5 = LocalDate.of(2022,5,5);
        System.out.println(Five5);

        LocalDate May5th = LocalDate.of(2022, Month.MAY, 5);
        System.out.println(May5th);

        LocalDate Day125 = LocalDate.ofYearDay(2022, 125);
        System.out.println(Day125);

        LocalDate May5 = LocalDate.parse("2022-05-05");
        System.out.println(May5);


        System.out.println(May5.get(ChronoField.YEAR));
        System.out.println(May5.getYear());

        /* we can change value of LocalDate object with "with" methods
        * these methods returns new instantiate, so it is not changes original object */
        System.out.println(May5.withYear(1));
        System.out.println(May5.withMonth(8));
        System.out.println(May5.withDayOfYear(1));

        System.out.println(May5); //May5 was not change because LocalDate is immutable.


        System.out.println("May5 > today? " + May5.isAfter(today));

    }
}