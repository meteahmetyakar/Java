package dev.lpa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String sentence = "I like B.M.W. motorcycles.";
        boolean matched = Pattern.matches("[A-Z].*[.]", sentence);
        System.out.println(matched + ": " + sentence);

        Pattern firstPattern = Pattern.compile("[A-Z](.*)?[.]");
        var matcher = firstPattern.matcher(sentence);
        System.out.println(matcher.matches() + ": " + sentence);
        System.out.println(matcher.group(1) + ": " + sentence);
        System.out.println("sentence.length: " + sentence.length());
        System.out.println("Matched Ending Index: " + matcher.end());

        System.out.println(matcher.lookingAt() + ": " + sentence);
        System.out.println("Matched Ending Index: " + matcher.end());
        System.out.println("Matched on : " +
                sentence.substring(0, matcher.end()));

        matcher.reset();
        System.out.println(matcher.find() + ": " + sentence);
        System.out.println("Matched Ending Index: " + matcher.end());
        System.out.println("Matched on : " +
                sentence.substring(matcher.start(), matcher.end()));
        System.out.println("Matched on : " + matcher.group());

        String htmlSnippet = """
                <H1>My Heading</H1>
                <h2>Sub-heading</h2>
                <p>This is a paragraph about something.</p>
                <p>This is another paragraph about something else.</p>
                <h3>Summary</h3>
                asldkakjdaskldajds
                """;

        Pattern htmlPattern = Pattern.compile("<[hH](?<level>\\d)>(.*)</[hH]\\d>");
        Matcher htmlMatcher = htmlPattern.matcher(htmlSnippet);

        while (htmlMatcher.find()) {
//            System.out.println("group: " + htmlMatcher.group());
//            System.out.println("group0: " + htmlMatcher.group(0));
            System.out.println(htmlMatcher.group("level") + " " +
                    htmlMatcher.group(2));
            System.out.println("index = " + htmlMatcher.start("level"));
        }

        htmlMatcher.reset();
        htmlMatcher.results().forEach(s -> System.out.println(
                s.group(1) + " " + s.group(2)));

        String tabbedText = """
                group1  group2  group3
                1   2   3
                a   b   d
                """;

        tabbedText.lines()
                .flatMap(s -> Pattern.compile("\\t").splitAsStream(s))
                .forEach(System.out::println);

        htmlMatcher.reset();

        //String updatedSnippet = htmlMatcher.replaceFirst("First Header");

        //this throw an error, because groups array is empty before run any matches methods
        //String updatedSnippet = htmlMatcher.replaceFirst("<em>" + htmlMatcher.group(2) + "</em>");
        String updatedSnippet = htmlMatcher.replaceFirst((mr) -> "<em>" + mr.group(2) + "</em>");
        System.out.println("-------------------");
        System.out.println(updatedSnippet);
        System.out.println(htmlMatcher.start() + " : " + htmlMatcher.end());
        System.out.println(htmlMatcher.group(2));

        htmlMatcher.usePattern(Pattern.compile("<([hH]\\d)>(.*)</\\1>")); // back reference with "\\capturingIndex"

        htmlMatcher.reset();
        System.out.println("----------------------");
        System.out.println("Using Back Reference: \n" +
                htmlMatcher.replaceAll("<em>$2</em>")); //back reference

        //If a more specific replace is desired, it can be customized as in this example.
        htmlMatcher.reset();
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while (htmlMatcher.find())
        {
            htmlMatcher.appendReplacement(sb,
                    switch (htmlMatcher.group(1).toLowerCase()){
                        case "h1" -> "<head>$2</head>";
                        case "h2" -> "<em>$2</em>";
                        default -> "<$1>" + index++ + ". $2</$1>";
                    });
        }

        //Since chars will be added to sb as the cursor progresses,
        //with this command, we ensure that the cursor continues from where it left off to the end and fills the sb.
        htmlMatcher.appendTail(sb);
        System.out.println(sb);

    }
}
