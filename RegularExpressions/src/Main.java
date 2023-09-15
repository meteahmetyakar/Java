import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {

        String helloWorld = "%s %s".formatted("Hello", "World");
        String helloWorld2 = String.format("%s %s", "Hello", "World");
        System.out.println("Using string's formatted method: " + helloWorld);
        System.out.println("Using String.format: " + helloWorld2);

        String helloWorld3 = Main.format("%s %s", "Hello", "World");
        System.out.println("Using Main.format: " + helloWorld3);


        String testString = "Anyone can Learn abc's, 123's, and any regular expression";
        String replacement = "(-)";

        /*
            String[] patterns = {
                    "abc",
                    "123",
                    "A"
            };
        */

        /*
            aboves are normally strings but these are regex
            when we'll use replaceFirst method, this method search with regex rules not whole string (find any of inside parenthesis)
            String[] patterns = {
                    "[abc]",
                    "[123]",
                    "[A]"
            };
        */

        /*
            String[] patterns = {
                    "a|b|c", // it is the same thing with [abc]. a or b or c
                    "[0-9]", // zero through nine
                    "[a-zA-Z]" //a through z and A through Z
            };
        */

        /*
            String[] patterns = {
                    "[a-zA-Z]*", // zero or more
                    "[0-9]+", // one or more
                    "[A-Z]{2}" // only two
            };

         */

        String[] patterns = {
                "[a-zA-Z]*$", // zero or more check reverse
                "^[a-zA-Z]{3}", // only 3
                "[aA]ny\\b" // whole word
        };

        for(String pattern :  patterns)
        {
            String output = testString.replaceFirst(pattern, replacement);
            System.out.println("Pattern: " + pattern + " => " + output);
        }

        String paragraph = """
                Double, double toil and trouble;
                Fire burn and caldron bubble.
                Fillet of a fenny snake,
                In the caldron boil and bake;
                Eye of newt and toe of frog,
                Wool of bat and tongue of dog,
                Adder's fork and blind-worm's sting,
                Lizard's leg and howlet's wing,
                For a charm of powerful trouble,
                Like a hell-broth boil and bubble.
                """;

        // \\R is same thing \\n but \\n coming from ASCII Table
        // java uses unicode, and it can run in any operating system
        // \\R can run in any operating system but \\n cannot

        String[] lines = paragraph.split("\\R"); //
        System.out.println("This paragraph has " + lines.length + " lines");
        String[] words = paragraph.split("\\s");
        System.out.println("This paragraph has " + words.length + " words");
        System.out.println(paragraph.replaceAll("[a-zA-Z]+ble", "[GRUB]"));

        Scanner scanner = new Scanner(paragraph);
        // this prints \p{javaWhitespace}+,
        // The expression "\p{}" represents Unicode character properties
        // and allows you to match specific types of characters through the enclosed character properties.
        // it is same thing with \\s+
        System.out.println(scanner.delimiter());
        scanner.useDelimiter("\\R"); //change delimiter with unicode newline
        /*while (scanner.hasNext())
        {
            String element = scanner.next();
            System.out.println(element);
        }*/

        /*
            // this prints only "double", because there are punctuations in words
            scanner.tokens() //this convert source to stream with delimiter
                    .flatMap(s -> Arrays.stream(s.split("\\s+")))
                    .filter(s -> s.matches("[a-zA-Z]+ble"))
                    .forEach(System.out::println);

            scanner.close();

         */

        //we can delete punctuations with regex
        scanner.tokens() //this convert source to stream with delimiter
                .map(s -> s.replaceAll("\\p{Punct}", "")) //this line will delete punctuations
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .filter(s -> s.matches("[a-zA-Z]+ble"))
                .forEach(System.out::println);

        scanner.close();
        scanner = new Scanner(paragraph);

        System.out.println(scanner.findInLine("[a-zA-Z]+ble"));

        /* ---------------Little Exercises------------------ */

        String regex = "[A-Z].*\\."; //start with uppercase, end with .
        String regex2 = "[A-Z][a-z\\s]\\.";
        String regex3 = "[A-Z][a-z\\s]+[.]\\.";

        for(String s : List.of("The bike is red.",
        "I am a new student.",
        "hello world.",
        "How are you?")) {
            boolean matched = s.matches(regex3);
            System.out.println(matched + " : " + s);
        }

        String regex4 = "^[A-Z][\\p{all}]+[!.?]$";

        for(String s : List.of("The bike is red, and has flat tires.",
                "I love being a new M.A.Y student!",
                "Hello, friends and family: Welcome!",
                "How are you, Mary?")) {
            boolean matched = s.matches(regex4);
            System.out.println(matched + " : " + s);
        }



    }

    private static String format(String regexp, String... args) {

        int index = 0;
        while (regexp.matches(".*%s.*")) {
            regexp = regexp.replaceFirst("%s", args[index++]);
        }
        return regexp;
    }

}