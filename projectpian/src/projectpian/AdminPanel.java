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
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AdminPanel 
{
    
    private Admin admin;
    
    public AdminPanel(Admin admin)
    {
        this.admin = admin;
    }
    public Parent getView(Main app, Stage primaryStage)
    {
        primaryStage.setTitle("Admin Panel - Course Management");

        // Admin Info
        Label titleLabel = new Label("Admin Panel - Course Management");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Admin Name: " + admin.getName());
        Label idLabel = new Label("Admin ID : " + admin.getAdminId());
        Label emailLabel = new Label("Admin Email: " + admin.getEmail());

        // Fields for course creation
        Label courseSection = new Label("Add Course");
        courseSection.setStyle("-fx-font-weight: bold;");
        TextField courseNameField = new TextField();
        TextField courseCodeField = new TextField();
        ComboBox<Lecturer> lecturerComboBox = new ComboBox();
        lecturerComboBox.getItems().addAll(admin.getAllLecturers());
        lecturerComboBox.setPromptText("Select Lecturer");
        Button addCourseBtn = new Button("Add Course");
        
        // Fields for section creation
        Label sectionSection = new Label("Add Section");
        sectionSection.setStyle("-fx-font-weight: bold;");
        TextField sectionIDField = new TextField();
        ComboBox<Course> courseForSectionComboBox = new ComboBox<>();
        courseForSectionComboBox.getItems().addAll(admin.getAllCourses());
        ComboBox<Lecturer> lecturerForSectionComboBox = new ComboBox<>();
        lecturerForSectionComboBox.getItems().addAll(admin.getAllLecturers());
        Button addSectionBtn = new Button("Add Section");
        
        //Fields for student addition
        Label studentSection = new Label("Add Student");
        studentSection.setStyle("-fx-font-weight: bold;");
        TextField studentNameField = new TextField();
        TextField studentIDField = new TextField();
        TextField studentEmailField = new TextField();
        TextField studentPasswordField = new TextField();
        TextField studentMatricField = new TextField();
        Button addStudentBtn = new Button("Add Student");
        
        //Fields for lecturer addition
        Label lecturerSection = new Label("Add Lecturer");
        lecturerSection.setStyle("-fx-font-weight: bold;");
        TextField lecturerNameField = new TextField();
        TextField lecturerIDField = new TextField();
        TextField lecturerDeptField = new TextField();
        TextField lecturerCourseCode = new TextField();
        TextField lecturerCourseName = new TextField();
        TextField lecturerPassword = new TextField();
        Button addLecturerBtn = new Button("Add Lecturer");
        
        // Fields for course update
        TextField existingCodeField = new TextField();
        TextField newNameField = new TextField();
        TextField newLecturerField = new TextField();
        Button updateButton = new Button("Update");

        // Field for course code change
        TextField changeCodeField = new TextField();
        Button changeButton = new Button("Change Code");
        
        // Fields for adding students to sections
        Label assignLabel = new Label("Assign Student to Section");
        assignLabel.setStyle("-fx-font-weight: bold;");
        ComboBox<Student> studentComboBox = new ComboBox<>();
        studentComboBox.getItems().addAll(admin.getAllStudents());
        ComboBox<Section> assignSectionComboBox = new ComboBox<>();
        assignSectionComboBox.getItems().addAll(admin.getAllSections());
        Button assignButton = new Button("Assign");

        // Output Area
        Label resultArea = new Label();

        // Buttons
        Button logoutButton = new Button("Log out");
        Button courseButton = new Button("Course");

        // Layouts
        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.setPadding(new Insets(20));
        //Course Creation Layout
        form.add(courseSection, 0, 0);
        form.add(new Label("Course Name: "), 0, 1);
        form.add(courseNameField, 1, 1);
        form.add(new Label("Course Code: "), 0, 2);
        form.add(courseCodeField, 1, 2);
        form.add(addCourseBtn, 1, 3);
        //Lecturer Creation Layout
        form.add(lecturerSection, 0, 8);
        form.add(new Label("Lecturer Name: "), 0, 9);
        form.add(lecturerNameField, 1, 9);
        form.add(new Label("Lecturer ID: "), 0, 10);
        form.add(lecturerIDField, 1, 10);
        form.add(new Label("Department: "), 0, 11);
        form.add(lecturerDeptField, 1, 11);
        form.add(new Label("Password: "), 0,12);
        form.add(lecturerPassword, 1, 12);
        form.add(addLecturerBtn, 1, 13);
        //Student Creation Layout
        form.add(studentSection, 4, 0);
        form.add(new Label("Student Name: "), 4, 1);
        form.add(studentNameField, 5, 1);
        form.add(new Label("Student ID: "), 4, 2);
        form.add(studentIDField, 5, 2);
        form.add(new Label("Student Email: "), 4, 3);
        form.add(studentEmailField, 5, 3);
        form.add(new Label("Student Matric: "), 4, 4);
        form.add(studentMatricField, 5, 4);
        form.add(new Label("Password: "), 4, 5);
        form.add(studentPasswordField, 5, 5);
        form.add(addStudentBtn, 5, 6);
        //Section Creation Layout
        form.add(sectionSection, 8, 0);
        form.add(new Label("Section ID: "), 8, 1);
        form.add(sectionIDField, 9, 1);
        form.add(new Label("Course: "), 8, 2);
        form.add(courseForSectionComboBox, 9, 2);
        form.add(new Label("Lecturer: "), 8, 3);
        form.add(lecturerForSectionComboBox, 9, 3);
        form.add(addSectionBtn, 9, 4);
        //Adding Students to Sections Layout
        form.add(assignLabel, 4, 8);
        form.add(new Label("Student: "), 4, 9);
        form.add(studentComboBox, 5, 9);
        form.add(new Label("Section: "), 4, 10);
        form.add(assignSectionComboBox, 5, 10);
        form.add(assignButton, 5, 11);
        
        
        HBox logout = new HBox (logoutButton);
        VBox vbox = new VBox(10, titleLabel, nameLabel, idLabel, emailLabel, form, resultArea, logout);
        vbox.setPadding(new Insets(20));

        
        // Simulate button actions
        addLecturerBtn.setOnAction(e->
        {
            Lecturer newLecturer = new Lecturer
            (
                    lecturerIDField.getText()
                    , lecturerNameField.getText()
                    , lecturerDeptField.getText()
                    , lecturerCourseCode.getText()
                    , lecturerCourseName.getText()
                    , lecturerPassword.getText()
            );
            
            admin.addLecturer(newLecturer);
            lecturerForSectionComboBox.getItems().add(newLecturer);
            resultArea.setText("Lecturer Successfully Added");
            resultArea.setStyle("-fx-text-fill: green;");
        });
        
        addStudentBtn.setOnAction(e->
        {
            Student newStudent = new Student
            (
                    studentIDField.getText()
                    , studentNameField.getText()
                    , studentEmailField.getText()
                    , studentPasswordField.getText()
                    , studentMatricField.getText()
            );
            
            admin.addStudent(newStudent);
            studentComboBox.getItems().add(newStudent);
            resultArea.setText("Student Successfully Added");
            resultArea.setStyle("-fx-text-fill: green;");
        });
        
        addCourseBtn.setOnAction(e->
        {
            String name = courseNameField.getText();
            String code = courseCodeField.getText();
            Course newCourse = new Course(code, name);
            courseForSectionComboBox.getItems().add(newCourse);
            admin.addCourse(newCourse);
            resultArea.setText("Course Successfully Added");
            resultArea.setStyle("-fx-text-fill: green;");  
        });
        
        addSectionBtn.setOnAction(e -> 
        {
            String sectionID = sectionIDField.getText();
            Course selectedCourse = courseForSectionComboBox.getValue();
            Lecturer selectedLecturer = lecturerForSectionComboBox.getValue();
            if (sectionID.isEmpty() || selectedCourse == null || selectedLecturer == null) 
            {
                resultArea.setText("Please fill in all section fields.");
                resultArea.setStyle("-fx-text-fill: red;");
                return;
            }
            Section newSection = new Section(sectionID, selectedCourse, selectedLecturer);
            admin.addSection(newSection);
            selectedLecturer.addSection(newSection);
            assignSectionComboBox.getItems().add(newSection);
            resultArea.setText("Section Successfully Added");
            resultArea.setStyle("-fx-text-fill: green;");
        });
        
        assignButton.setOnAction(e -> 
        {
            Student student = studentComboBox.getValue();
            Section section = assignSectionComboBox.getValue();
            if (student != null && section != null) 
            {
                section.addStudent(student);
                section.getLecturer().addStudentToSection(student, section);
                resultArea.setText("Student assigned to section.");
                resultArea.setStyle("-fx-text-fill: green;");
            } 
            else 
            {
                resultArea.setText("Select both student and section.");
                resultArea.setStyle("-fx-text-fill: red;");
            }
        });
        
        logoutButton.setOnAction(e -> 
        {
            app.setScene(app.getLoginPanelView());
        });
        
        return vbox;
    }
    }