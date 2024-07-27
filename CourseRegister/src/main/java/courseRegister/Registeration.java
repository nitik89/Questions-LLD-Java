package courseRegister;

import java.sql.Timestamp;

public class Registeration {
    private final Student student;
    private final Course course;
    private final Timestamp timestamp;

    public Registeration(Student student, Course course, Timestamp timestamp2) {
        this.student = student;
        this.course = course;
        this.timestamp = timestamp2;
    }

}
