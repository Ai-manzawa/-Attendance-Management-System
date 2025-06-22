/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpian;

/**
 *
 * @author User
 */
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application 
{
    private Stage primaryStage;
    private Scene scene;

    private Admin admin;
    private Student student;
    private Lecturer lecturer;

    private final LoginPanel loginPanelView = new LoginPanel();
    private AdminPanel adminPanelView;
    private StudentPanel studentPanelView;
    private LecturerPanel lecturerPanelView;

    List<Lecturer> lecturers = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<Admin> admins = new ArrayList<>();
    List<Course> courses = new ArrayList<>();
    List<Section> sections = new ArrayList<>();
    
    {
        //Lecturer
        Lecturer L1 = new Lecturer("L001", "Dr. Smith", "CS101", "Computer Science", "Java Programming", "password");
        Lecturer L2 = new Lecturer("L002", "Dr. Myth", "CS102", "Computer Science", "Python Programming", "wordpass");
        lecturers.add(L1);
        lecturers.add(L2);
        
        //Courses
        Course C1 = new Course("CS101", "Java Programming");
        Course C2 = new Course("CS102", "Python Programming");
        courses.add(C1);
        courses.add(C2);
        L1.addCourse(C1);
        L1.addCourse(C2);
        L2.addCourse(C1);
        L2.addCourse(C2);
        
        //Sections
        Section SE1 = new Section ("1", C1, L1);
        Section SE2 = new Section ("2", C1, L2);
        Section SE34 = new Section ("34", C2, L1);
        Section SE29 = new Section ("29", C2, L2);
        sections.add(SE1);
        sections.add(SE2);
        sections.add(SE34);
        sections.add(SE29);
        
        //Students
        Student S1 = new Student("S001", "Alice", "alice@gmail.com", "pass123", "1001");
        Student S2 = new Student("S002", "Bob", "bob@gmail.com", "pass456", "1002"); 
        Student S3 = new Student("S003", "Charlie", "charles@gmail.com", "pass789", "1003");
        Student S4 = new Student("S004", "Diana", "diana@gmail.com", "passabc", "1004");
        Student S5 = new Student("S005", "Edward", "edward@gmail.com", "passdef", "1005");
        Student S6 = new Student("S006", "Fiona", "fiona@gmail.com", "passghi", "1006");
        
        S1.addSection(SE1);
        S1.addSection(SE34);
        S1.setAttendanceForSection(SE1, new AttendanceRecord(S1, 18,2));
        S1.setAttendanceForSection(SE34, new AttendanceRecord(S1, 19,1));
        
        S2.addSection(SE29);
        S2.addSection(SE2);
        S2.setAttendanceForSection(SE29, new AttendanceRecord(S2, 15,3));
        S2.setAttendanceForSection(SE2, new AttendanceRecord(S2, 14,2));     
        
        S3.addSection(SE1);
        S3.addSection(SE29);
        S3.setAttendanceForSection(SE1, new AttendanceRecord(S3, 17,3));
        S3.setAttendanceForSection(SE29, new AttendanceRecord(S3, 16,4));
        
        S4.addSection(SE2);
        S4.addSection(SE29);
        S4.setAttendanceForSection(SE2, new AttendanceRecord(S4, 13, 3));
        S4.setAttendanceForSection(SE29, new AttendanceRecord(S4, 12, 4));
        
        S5.addSection(SE1);
        S5.addSection(SE34);
        S5.setAttendanceForSection(SE1, new AttendanceRecord(S5, 10, 5));
        S5.setAttendanceForSection(SE34, new AttendanceRecord(S5, 15, 2));

        S6.addSection(SE34);
        S6.setAttendanceForSection(SE34, new AttendanceRecord(S6, 20, 0));
        
        students.add(S1);
        students.add(S2);
        students.add(S3);
        students.add(S4);
        students.add(S5);
        students.add(S6);
        
        //Add students to sections
        L1.addStudentToSection(S1, SE1);
        L1.addStudentToSection(S1, SE34);
        L1.addStudentToSection(S3, SE1);
        L1.addStudentToSection(S5, SE1);
        L1.addStudentToSection(S5, SE34);
        L1.addStudentToSection(S6, SE34);

        L2.addStudentToSection(S2, SE2);
        L2.addStudentToSection(S2, SE29);
        L2.addStudentToSection(S3, SE29);
        L2.addStudentToSection(S4, SE2);
        L2.addStudentToSection(S4, SE29);
        
        admins.add(new Admin("A001", "John", "admin@gmail.com", "administrator", "password2", lecturers, students, courses, sections));
    }

    @Override
    public void start(Stage primaryStage) 
    {
        this.primaryStage = primaryStage;
        Parent root = getLoginPanelView();
        scene = new Scene(root, 800, 650);
        primaryStage.setTitle("IIUM Student Attendance Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
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

    public void setScene(Parent view) 
    {
        scene.setRoot(view);
    }

    public Parent getLoginPanelView() 
    {
        return loginPanelView.getView(this, primaryStage, admins, students, lecturers);
    }

    public Parent getAdminPanelView() 
    {
        return adminPanelView.getView(this, primaryStage);
    }
       
    public Parent getLecturerPanelView() 
    {
        return lecturerPanelView.getView(this, primaryStage);
    }

    public Parent getStudentPanelView(Student student)
    {
        this.student = student;
        this.studentPanelView = new StudentPanel(student);
        return studentPanelView.getView(this, primaryStage);
    }

    public void setAdmin(Admin admin) 
    {
        this.admin = admin;
        this.adminPanelView = new AdminPanel(admin);
    }
    
    public void setLecturer(Lecturer lecturer) 
    {
        this.lecturer = lecturer;
        this.lecturerPanelView = new LecturerPanel(lecturer);
    }
    
    public void setStudent(Student student) 
    {
        this.student = student;
        this.studentPanelView = new StudentPanel(student);
    }

    
    public static void main(String[] args) 
    {
        launch(args);
    }
}