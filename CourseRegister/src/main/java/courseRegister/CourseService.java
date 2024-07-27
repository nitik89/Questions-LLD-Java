package courseRegister;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class CourseService {
    private static CourseService instance;
    private final Map<String,Course>courses;
    private final Map<Integer,Student>students;
    private final List<Registeration>registeration;

    private CourseService() {
        this.courses = new ConcurrentHashMap<>();
        this.students = new ConcurrentHashMap<>();
        this.registeration = new CopyOnWriteArrayList<>();
    }

    public static synchronized CourseService getInstance() {
        if (instance == null) {
            instance = new CourseService();
        }
        return instance;
    }

    public void addCourse(Course course){
        courses.put(course.getCode(), course);
    }

    public void addStudent(Student student){
        students.put(student.getId(), student);
    }

    public List<Course> searchCourses(String query){
        List<Course> result = new ArrayList<>();
        for (Course course : this.courses.values()) {
            if (course.getCode().contains(query) || course.getName().contains(query)) {
                result.add(course);
            }
        }
        return result;
    }

    public synchronized boolean registerCourse(Student student, Course course) {
        if (course.getEnrolledStudents() < course.getMaxCapacity()) {
            Registeration registration = new Registeration(student, course, new Timestamp(System.currentTimeMillis()));
            registeration.add(registration);
            student.getRegisteredCourses().add(course);
            course.setEnrolledStudents(course.getEnrolledStudents() + 1);
            return true;
        }
        return false;
    }

    public List<Course> getRegisteredCourses(Student student) {
        return student.getRegisteredCourses();
    }
}
