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

public class Section 
{
    private String sectionID;
    private Course course;
    private Lecturer lecturer;
    private List<Student> students = new ArrayList<>();
    
    public Section(String sectionID, Course course, Lecturer lecturer)
    {
        this.sectionID = sectionID;
        this.course = course;
        this.lecturer = lecturer;
    }
    
    public String getSectionID()
            {
                return sectionID;
            }
    
    public Course getCourse()
    {
        return course;
    }
    
    public Lecturer getLecturer()
    {
        return lecturer;
    }
    
    public List<Student> getStudents()
    {
        return students;
    }
    
    public void addStudent(Student student)
    {
        if (!students.contains(student))
        {
            students.add(student);
        }
    }
    
    @Override
    public String toString()
    {
        return course.getCourseCode() + " - Section " + sectionID + " (" + lecturer.getLecturerName() + ")";
    }
}