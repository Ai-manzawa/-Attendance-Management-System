/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpian;

/**
 *
 * @author User
 */
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Student extends User 
{
    private String matricNumber;
    private Map<Section, AttendanceRecord> sectionAttendance = new HashMap<>();
    
    public Student(String userId, String name, String email, String password, String matricNumber) 
    {
        super(userId, name, email, password);
        this.matricNumber = matricNumber;
    }

    public String getMatricNumber() 
    { 
        return matricNumber; 
    }
    
    public String getID()
    {
        return userId;
    }
    
public void addSection(Section section) 
{
    sectionAttendance.putIfAbsent(section, new AttendanceRecord(this, 0, 0));
}

    
    public void displayStudentInfo() {
        displayUserInfo();
        System.out.println("Matric Number: " + matricNumber);
    }

    @Override
    public void displayUserInfo() 
    {
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
    
    public AttendanceRecord getAttendanceForSection(Section section) 
    {
    return sectionAttendance.get(section);
    }
    
    public void setAttendanceForSection(Section section, AttendanceRecord record) 
    {
    sectionAttendance.put(section, record);
    }
    
    public Map<Section, AttendanceRecord> getAllAttendanceRecords() {
    return sectionAttendance;
}
    
    public List<Section> getEnrolledSections() 
    {
    return new ArrayList<>(sectionAttendance.keySet());
    }
    
    public String toString()
    {
        return name + " (" + matricNumber + ")";
    }
    
}