/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package courseRegister;

import java.util.ArrayList;

/**
 *
 * @author nitiknarang
 */
public class CourseRegister {

    public static void main(String[] args) {
        CourseService courseService = CourseService.getInstance();

        // Add courses
        Course course1 = new Course("CS101", "Introduction to Computer Science", "John Doe", 20, 0);
        Course course2 = new Course("MATH201", "Calculus", "Jane Smith", 30, 0);
        Course course3 = new Course("PHYS202", "Physics", "Michael Johnson", 40, 0);

        courseService.addCourse(course1);
        courseService.addCourse(course2);
        courseService.addCourse(course3);

        // Add students
        Student student1 = new Student(1, "John Doe", "johndoe@example.com", new ArrayList<>());
        Student student2 = new Student(2, "Jane Smith", "janesmith@example.com", new ArrayList<>());
        boolean registered1=courseService.registerCourse(student1,course1);
        boolean registered2=courseService.registerCourse(student2,course2);
        System.out.println("Student 1 registered: " + registered1);
        System.out.println("Student 2 registered: " + registered2);
        System.out.println("Registered courses for Student 1: " + courseService.getRegisteredCourses(student1));
        System.out.println("Registered courses for Student 2: " + courseService.getRegisteredCourses(student2));
        // Search for courses
        System.out.println("Search for courses: " + courseService.searchCourses("Computer Science"));
        // Search for students



    }
}
