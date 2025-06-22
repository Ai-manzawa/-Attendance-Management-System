/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpian;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.List;

public class Admin
{

    private String adminId;
    private String name;
    private String email;
    private String role;
    private String password;
    
    private List<Lecturer> lecturers;
    private List<Student> students;
    private List<Course> courses;
    private List<Section> sections;
    
    public Admin(String id, String name, String email, String role, String password, List<Lecturer> lecturers, List<Student> students, List<Course> courses, List<Section> sections) 
    {
        this.adminId = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.lecturers = lecturers;
        this.students = students;
        this.courses = courses;
        this.sections = sections;
    }
    
    public void addLecturer(Lecturer lecturer)
    {
        lecturers.add(lecturer);
    }
    
    
    
    public void addStudent(Student student)
    {
        students.add(student);
    }
    
    public void addCourse(Course course)
    {
        courses.add(course);
    }
    
    public void addSection(Section section)
    {
        sections.add(section);
        section.getLecturer().addSection(section);
    }
    
    
    // Methods for course stuff
    public String createCourse(String courseName, String courseCode, Lecturer lecturer) 
    {
        // would save course in real project
        return "Created course: " + courseName + " (" + courseCode + "), Lecturer: " + lecturer;
    }
    public String updateCourse(String code, String newName, String newLecturer) 
    {
        // would do database update here
        return "Updated course " + code + ". New name: " + newName + ", Lecturer: " + newLecturer;
    }
    public String changeClassCode(String oldCode, String newCode) 
    {
        // pretend we updated the code
        return "Changed code from " + oldCode + " to " + newCode;
    }
    // Get methods
    public String getPassword()
    {
        return password;
    }
    public String getAdminId() 
    {
        return adminId;
    }
    public String getName() 
    {
        return name;
    }
    public String getEmail() 
    {
        return email;
    }
    public String getRole() 
    {
        return role;
    }
    
    public List<Lecturer> getAllLecturers() 
    {
        return lecturers;
    }

    public List<Student> getAllStudents() 
    {
        return students;
    }

    public List<Course> getAllCourses() 
    {
        return courses;
    }
    
    public List<Section> getAllSections()
    {
        return sections;
    }
    
    
    public void displayUserInfo() 
    {
        System.out.println("Admin Name: " + name);
        System.out.println("Admin ID: " + adminId);
        System.out.println("Admin Email: " + email);
    }
}