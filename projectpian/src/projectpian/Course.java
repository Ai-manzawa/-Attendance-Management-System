/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpian;

/**
 *
 * @author User
 */

import java.util.List;
import java.util.ArrayList;

public class Course 
{
    private String courseName;
    private String courseCode;
    private Lecturer lecturer;
    private List<Student> enrolledStudents = new ArrayList<>();
    
    public Course (String courseName, String courseCode)
    {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }
    
    public void enrollStudent(Student student)
    {
        enrolledStudents.add(student);
    }
    
    public List<Student> getEnrolledStudents()
    {
        return enrolledStudents;
    }
 
    // Getters and setters
    public String getCourseName() 
    {
        return courseName;
    }

    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseCode() 
    {
        return courseCode;
    }

    public void setCourseCode(String courseCode) 
    {
        this.courseCode = courseCode;
    }



     public String toString() 
     {
        return "Course: " + courseName + ", Code: " + courseCode;
    }
}