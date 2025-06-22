/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpian;

/**
 *
 * @author User
 */
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class AttendanceRecord {

    private Map<LocalDate, Boolean> attendanceByDate = new HashMap<>();
    private Student student;
    private int totalDays;
    private int presentDays;
    private int absentDays;

    public AttendanceRecord(Student s, int present, int absent) 
    {
        this.student = s;
        this.presentDays = present;
        this.absentDays = absent;
        this.totalDays = present + absent;
        this.attendanceByDate = new HashMap<>();
    }
    
    public AttendanceRecord() 
    {
        this.student = student;
        this.attendanceByDate = new HashMap<>();
        this.presentDays = 0;
        this.absentDays = 0;
        this.totalDays = 0;
    }
    
    public AttendanceRecord(Student student) 
    {
    this.student = student;
    this.presentDays = 0;
    this.absentDays = 0;
    this.totalDays = 0;
    this.attendanceByDate = new HashMap<>();
    }

    public Map<LocalDate, Boolean> getAttendanceMap()
    {
        return attendanceByDate;
    }
    public void addPresentDays(int days) {
        presentDays += days;
        totalDays += days;
    }

    public void addAbsentDays(int days) {
        absentDays += days;
        totalDays += days;
    }

    public int getDaysPresent() {
        return presentDays;
    }

    public int getDaysAbsent() {
        return absentDays;
    }

     public double attendancePercentage() {
    if (totalDays <= 0) 
    {
        throw new IllegalArgumentException("Total days must be greater than zero.");
    }
    if (presentDays < 0) 
    {
        throw new IllegalArgumentException("Days present cannot be negative.");
    }
    if (presentDays > totalDays) 
    {
        throw new IllegalArgumentException("Days present cannot exceed total days.");
    }

    return (presentDays * 100.0) / totalDays;
}

    public Student getStudent() 
    {
        return student;
    }

    public int getTotalDays() 
    {
        return totalDays;
    }

    public void setTotalDays(int total) 
    {
        totalDays = total;
    }
    
    public void markAttendance(LocalDate date, boolean isPresent)
    {
        attendanceByDate.put(date, isPresent);
        if (isPresent) 
        {
            presentDays++;
        } else 
        {
        absentDays++;
        }
    totalDays++;
    }
    
    public List<LocalDate> getAbsentDates()
    {
        List<LocalDate> absents = new ArrayList<>();
        for (Map.Entry<LocalDate, Boolean> entry : attendanceByDate.entrySet())
        {
            if (!entry.getValue())
            {
                absents.add(entry.getKey());
            }
        }
        return absents;
    }
    
}