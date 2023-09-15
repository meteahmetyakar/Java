package student;

import java.util.StringJoiner;

public record Course(String courseCode, String title) {

    public int getLectureCount() {
        return 15;
    }

    @Override
    public String toString() {
        return "%s %s".formatted(courseCode, title);
    }


    //this is a kind of toString method which uses template StringJoiner
    public String toJSON() {
        return new StringJoiner(", ", "{", "}")
                .add("\"courseCode\":\"" + courseCode + "\"")
                .add("\"title\":\"" + title + "\"")
                .toString();
    }

}
