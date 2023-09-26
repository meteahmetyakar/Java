import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> bingoPool = new ArrayList<>(75);

        int start = 1;

        for (char c : "BINGO".toCharArray()) {
            for (int i = start; i < (start + 15); i++) {
                bingoPool.add("" + c + i);
                //System.out.println("" + c + i);
            }
            start += 15;
        }

        Collections.shuffle(bingoPool);
        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }

        System.out.println("------------------------------");

        //List<String> firstOnes = bingoPool.subList(0, 15);
        //subList returns a view. So, when firstOnes changed, bingoPool will change. That's why we must use defensive copy
        List<String> firstOnes = new ArrayList<>(bingoPool.subList(0, 15));
        firstOnes.sort(Comparator.naturalOrder());
        firstOnes.replaceAll(s -> {
            if (s.indexOf('G') == 0 || s.indexOf("O") == 0) {
                String updated = s.charAt(0) + "-" + s.substring(1);
                System.out.print(updated + " ");
                return updated;
            }
            return s;
        });
        System.out.println("\n------------------------------");


        for (int i = 0; i < 15; i++) {
            System.out.println(bingoPool.get(i));
        }

        System.out.println("------------------------------");

        //This stream pipeline doing same thing with 32-41 code block. But this is more readable, more easy to code.
        bingoPool.stream()
                .limit(15)
                .filter(s -> s.indexOf('G') == 0 || s.indexOf('O') == 0)
                .map(s -> s.charAt(0) + "-" + s.substring(1))
                .sorted()
                .forEach(s -> System.out.println(s + " "));

        //this was a simple example for advantages of streams
        System.out.println("------------------------------");

        IntStream.iterate(1, n -> n + 1)
                .limit(100)
                .filter(n -> n % 2 == 0)
                .forEach(s -> System.out.println(s + " "));


        //Print to the console B1-B15, I16-I30, N31-N45, G46-G60, O61-O75 with streams

        //Approach 1
        List<Stream<?>> streams = new ArrayList<>();

        start = 1;
        for (char ch : "BINGO".toCharArray()) {
            var strm = Stream.iterate(start, n -> n + 1)
                    .limit(15)
                    .map(s -> "" + ch + s);

            start += 15;
            streams.add(strm);
        }

        for (var s : streams) {
            s.forEach(x -> System.out.println(x + " "));
            System.out.println();
        }


        //Approach 2

        var strm = Stream.iterate(1, n -> n + 1)
                .limit(15)
                .map(s -> "B" + s);


        Integer[] numbersArray = {16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        var strm2 = Stream.of(numbersArray)
                .map(s -> "I" + s);


        var strm3 = Arrays.stream(new int[]{31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45})
                .boxed()
                .map(s -> "N" + s);

        int str = 46;
        var strm4 = Stream.iterate(str, i -> i <= (str + 15), i -> i + 1)
                .map(s -> "G" + s);


        String[] arr = new String[15];
        Arrays.setAll(arr, i -> "O" + (60 + i));
        var strm5 = Arrays.stream(arr);


        List<Stream<?>> list = new ArrayList<>();
        list.add(strm);
        list.add(strm2);
        list.add(strm3);
        list.add(strm4);
        list.add(strm5);

        /*for(var x : list)
            x.forEach(System.out::println);*/

        //or
        //this part throws an error because a stream can operate only one time
        //Comment out 65-66 before run
        Stream<String> BI = Stream.concat(strm, strm2);
        Stream<String> BIN = Stream.concat(BI, strm3);
        Stream<String> BING = Stream.concat(BIN, strm4);
        Stream<String> BINGO = Stream.concat(BING, strm5);

        BINGO.forEach(System.out::println);

        IntStream numbers = IntStream.rangeClosed(1, 20);

        //It drops elements from the stream as long as the predicate is true,
        //and stops dropping when the predicate becomes false.
        numbers.dropWhile(n -> n < 5)
                .forEach(System.out::println);

        //numbers.takeWhile() takes elements into the stream as long as the predicate is true, and stops taking when the predicate becomes false

    }


}