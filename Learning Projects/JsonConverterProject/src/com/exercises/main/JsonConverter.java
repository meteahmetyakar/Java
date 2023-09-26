package com.exercises.main;

import com.exercises.school.course.Course;
import com.exercises.school.student.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonConverter {


//  Task: Create random 1000 students and save they in JSON format as students.json
//        student package can be changed.

    public static void main(String[] args) {

        String delimiter = "," + System.lineSeparator();
        Course jmc = new Course("JMC", "Java Masterclass");
        Course pymc = new Course("PYC", "Python Masterclass");
        String students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .map(Student::toJSON)
                .collect(Collectors.joining(delimiter, "[", "]"));

        try {
            Files.writeString(Path.of("students.json"), students);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
