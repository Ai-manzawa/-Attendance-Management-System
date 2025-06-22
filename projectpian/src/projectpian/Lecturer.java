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
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author User
 */

public class Lecturer 
{
    private String lectID;
    private String name;
    private String password;
    private String department;
    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Section> sections = new ArrayList<>();
    private Map<Course, List<Student>> courseStudents = new HashMap<>();
    private Map<Section, List<Student>> sectionStudents = new HashMap<>();
    
    public Lecturer(String lectID, String name, String courseCode, String department, String subject, String password) 
    {
        this.lectID = lectID;
        this.name = name;
        this.department = department;
        this.password = password;
    }
    
    //Setters and Getters
    public String getPassword()
    {
        return password;
    }
    
    public String getLecturerID()
    {
        return lectID;
    }
    
    public String getLecturerName() 
    {
        return name; 
    }
    
    public void setLectureName(String lecturerName) 
    {
        this.name = lecturerName; 
    }
    
    public String getDepartment()
    {
        return department;
    }
    
    public void addCourse(Course course) 
    {
        if (!courses.contains(course)) 
        {
            courses.add(course);
            courseStudents.put(course, new ArrayList<>());
        }
    }
    
    public void addSection(Section section) 
    {
        if (!sections.contains(section)) 
        {
            sections.add(section);
            sectionStudents.put(section, new ArrayList<>());
        }
    }
    
    public List<Course> getCourses() 
    {
        return courses;
    }
    
public void addStudentToSection(Student student, Section section) 
{
    if (!students.contains(student)) 
    {
        students.add(student);
    }

    if (student.getAttendanceForSection(section) == null) 
    {
        student.setAttendanceForSection(section, new AttendanceRecord(student, 0, 0));
        //If no record is found, a new record is made with 0 days present or absent
    }

    if (!sections.contains(section)) //To avoid duplicate courses
    {
        sections.add(section);
        sectionStudents.put(section, new ArrayList<>());
    }
    
    List<Student> sectionList = sectionStudents.get(section);
    if (!sectionList.contains(student)) 
    {
        sectionList.add(student);
    }
}

    public List<Student> getStudentsInSection(Section section)
    {
        return sectionStudents.getOrDefault(section, new ArrayList<>());
    }
    
public void markAttendance(String studentID, Section section, boolean isPresent) 
{
    for (Student s : students) 
    {
        if (s.getID().equals(studentID)) 
        {
            AttendanceRecord record = s.getAttendanceForSection(section);
            if (record == null) 
            {
                record = new AttendanceRecord(s, 0, 0);
                s.setAttendanceForSection(section, record);
                //If no record is found, a new record is made with 0 days present or absent
            }
            if (isPresent) 
            {
                record.addPresentDays(1);
            } 
            else 
            {
                record.addAbsentDays(1);
            }
            break;
        }
    }
}

public String getAttendanceSummary(String studentID, Section section) 
{
    for (Student s : students) 
    {
        if (s.getID().equals(studentID)) 
        {
            AttendanceRecord r = s.getAttendanceForSection(section);
            if (r == null) 
            {
                return "No attendance record for this course.";
            }
            return "Total: " + r.getTotalDays() +
                   ", Present: " + r.getDaysPresent() +
                   ", Absent: " + r.getDaysAbsent() +
                   ", %: " + String.format("%.2f", r.attendancePercentage()) + "%";
        }
    }
    return "Student not found.";
}
    
    public List<Section> getSections()
    {
        return new ArrayList<>(sectionStudents.keySet());
    }
    
    

    public void displayInfo() 
    {
        System.out.println("Lecturer Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Courses: ");
        for (Course course : courses) 
        {
            System.out.println(" - " + course.getCourseCode() + ": " + course.getCourseName());
        }
    }
    
    @Override
    public String toString()
    {
        return name;
    }
}