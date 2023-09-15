package dev.lpa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainTask {

    public static void main(String[] args) {
        Course pymc= new Course("PYMC", "Python Masterclass",50);
        Course jmc= new Course("JMC", "Java Masterclass",100);

        Course jgames= new Course("CGJ", "Creating Games in Java");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc,jmc,jgames))
                .limit(5000)
                .toList();

        Double sum = students.stream()
                .map(s -> s.getEngagementMap().get("JMC").getPercentComplete())
                .reduce(Double::sum).get();

        Double threeQuarter = ((sum / 5000) * 1.25);

        /*List<Student> std = students.stream()
                .filter(s -> s.getEngagementMap().get("JMC").getPercentComplete() > threeQuarter)
                .toList();
        std.forEach(System.out::println);*/


        // a different solving approach of above



        List<Student> students2 = IntStream
                .rangeClosed(1, 5000)
                .mapToObj(s -> Student.getRandomStudent(jmc, pymc))
                .toList();

        double totalPercent = students.stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .reduce(0, Double::sum);

        double avePercent = totalPercent / students.size();
        System.out.printf("Average Percentage Complete = %.2f%% %n", avePercent);

        int topPercent = (int) (1.25 * avePercent);
        System.out.printf("Best Percentage Complete = %d%% %n", topPercent);

        Comparator<Student> longTermStudent
                = Comparator.comparing(Student::getYearEnrolled);

        List<Student> hardWorkers = students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .toList();

        hardWorkers.forEach(s -> {
            s.addCourse(jgames);
            System.out.print(s.getStudentId() + " ");
        });

        System.out.println();

        Comparator<Student> uniqueSorted = longTermStudent.thenComparing(
                Student::getStudentId);

        students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
//                .toList()
//                .collect(Collectors.toList())
//                .collect(Collectors.toSet())
//                .collect(() -> new TreeSet<>(uniqueSorted),
//                    TreeSet::add, TreeSet::addAll)
                .forEach(s -> {
                    s.addCourse(jgames);
                    System.out.print(s.getStudentId() + " ");
                });

    }



}
