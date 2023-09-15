import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        System.out.println("Default Locale = " + Locale.getDefault());

        Locale en = new Locale("en");
        Locale enAU = new Locale("en", "AU");
        Locale enCA = new Locale("en", "CA");

        Locale enIN = new Locale.Builder().setLanguage("en").setRegion("IN").build();
        Locale enNZ = new Locale.Builder().setLanguage("en").setRegion("NZ").build();

        var dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        /* This code snippet will format the current date and time under different locales and print it to the screen. */
        for(var locale : List.of(
                Locale.getDefault(), Locale.US, en, enAU, enCA, Locale.UK, enNZ, enIN)) {

            System.out.println(locale.getDisplayName() + " = " + LocalDateTime.now().format(dtf.withLocale(locale)));
        }

        DateTimeFormatter wdayMonth = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");

        LocalDate may5 = LocalDate.of(2020,5,5);

        System.out.println("-----------------------");

        for(var locale : List.of(Locale.CANADA, Locale.CANADA_FRENCH,
                Locale.FRANCE, Locale.GERMANY, Locale.TAIWAN,
                Locale.JAPAN, Locale.ITALY)) {
            System.out.println(locale.getDisplayName(locale) + " =\n\t" +
                    may5.format(wdayMonth.withLocale(locale)));

            //it prints same thing previous println, only a different way
            System.out.printf(locale, "\t%1$tA, %1$tB, %1$te, %1$tY %n", may5);

            NumberFormat decimalInfo = NumberFormat.getNumberInstance(locale);

            //fraction part consist of 3 digit, we can change it wit setMaximumFrac...
            decimalInfo.setMaximumFractionDigits(6);

            System.out.println(decimalInfo.format(123456789.12345));

            NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
            System.out.print(currency.format(555.555));

            //there is also Currency class
            Currency localCurrency = Currency.getInstance(locale);
            System.out.println(" ["+localCurrency.getCurrencyCode()+"]" +
                    localCurrency.getDisplayName(locale) + "/" +
                    localCurrency.getDisplayName()
            );
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the loan amount : ");

        //Scanner.nextBigDecimal() method accepts a Locale's group separator and
        //decimal separator characters.
        BigDecimal myLoan = scanner.nextBigDecimal();
        System.out.println("My Loan " + myLoan);


    }
}