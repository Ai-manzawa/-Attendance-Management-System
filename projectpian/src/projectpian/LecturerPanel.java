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
import javafx.collections.ObservableList;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.Alert;
/**
 *
 * @author Alfian
 */
public class LecturerPanel 
{
    private Lecturer lecturer;
    
    public LecturerPanel(Lecturer lecturer)
    {
        this.lecturer = lecturer;
    }
    
    public Parent getView(Main app, Stage primaryStage)
    {
        //Title
        primaryStage.setTitle("Lecturer Panel");
        Label titleLabel = new Label("Lecturer Panel");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        //Lecturer Info
        Label nameLabel = new Label("Lecturer Name: " + lecturer.getLecturerName());
        Label idLabel = new Label("Lecturer ID: " + lecturer.getLecturerID());
        Label deptLabel = new Label("Department: " + lecturer.getDepartment());
        
        //Student List
        ListView<Student> studentListView = new ListView<>();
        studentListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        studentListView.setPrefHeight(150);
        
        //DatePicker
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select Date");
        
        //ComboBox for Section
        Label sectionChoice = new Label("Section: ");
        ComboBox<Section> sectionComboBox = new ComboBox<>();
        sectionComboBox.getItems().addAll(lecturer.getSections());
        sectionComboBox.setPromptText("Select Section");
        
        //Attendance Stats
        Label attendanceStats = new Label("Select a student to view attendance.");
        TextArea attendanceLog = new TextArea();
        attendanceLog.setEditable(false);
        attendanceLog.setPrefRowCount(6);   
        
        //Buttons
        Button logoutButton = new Button("Log out");
        Button markPresentButton = new Button("Mark Present");
        Button markAbsentButton = new Button("Mark Absent");
        Button viewAttendanceButton = new Button("View Attendance");
        Button generateReportButton = new Button("Generate Report");
        Button viewAbsencesButton = new Button("View Absences");
        
        // Update stats on selection

        
        //Attendance Button Actions
        markPresentButton.setOnAction(e -> 
        {
            ObservableList<Student> selectedStudents = studentListView.getSelectionModel().getSelectedItems();
            LocalDate selectedDate = datePicker.getValue();
            Section selectedSection = sectionComboBox.getValue();
            if (!selectedStudents.isEmpty() && selectedSection != null && selectedDate != null) 
            {
                for (Student student : selectedStudents) 
                {
                    AttendanceRecord record = student.getAttendanceForSection(selectedSection);
                    if (record == null) 
                    {
                        record = new AttendanceRecord(student, 0, 0);
                        student.setAttendanceForSection(selectedSection, record);
                    }
                    record.markAttendance(selectedDate, true);
                }
                attendanceStats.setText("Marked present for selected students.");
            } 
            else 
            {
                attendanceStats.setText("Please select students, section, and date.");
            } 
        });
        
        markAbsentButton.setOnAction(e -> 
        {
            ObservableList<Student> selectedStudents = studentListView.getSelectionModel().getSelectedItems();
            Section selectedSection = sectionComboBox.getValue();
            LocalDate selectedDate = datePicker.getValue();
            
            if (!selectedStudents.isEmpty() && selectedSection != null && selectedDate != null) 
            {
                for (Student student : selectedStudents) 
                {
                    AttendanceRecord record = student.getAttendanceForSection(selectedSection);
                    if (record == null) 
                    {
                        record = new AttendanceRecord(student, 0, 0);
                        student.setAttendanceForSection(selectedSection, record);
                    }
                    record.markAttendance(selectedDate, false);
                }
                attendanceStats.setText("Marked absent for selected students.");
            } 
            else 
            {
                attendanceStats.setText("Please select students, section, and date.");
            } 
        });
        
        viewAbsencesButton.setOnAction(e -> 
        {
            Student selectedStudent = studentListView.getSelectionModel().getSelectedItem();
            Section selectedSection = sectionComboBox.getValue();
            if (selectedStudent != null && selectedSection != null) 
            {
                AttendanceRecord record = selectedStudent.getAttendanceForSection(selectedSection);
                if (record != null) 
                {
                    List<LocalDate> absentDates = record.getAbsentDates();
                    if (!absentDates.isEmpty()) 
                    {
                        StringBuilder msg = new StringBuilder("Absent Dates:\n");
                        for (LocalDate date : absentDates) 
                        {
                            msg.append(date.toString()).append("\n");
                        }
                        showAlert("Absent Dates", msg.toString());
                    } 
                    else 
                    {
                        showAlert("Absent Dates", "This student has no absences.");
                    }
                } 
                else 
                {
                    showAlert("No Record", "No attendance record found for this student.");
                }
            }
            else 
            {
                showAlert("Selection Required", "Please select both a student and section.");
            }
        });
        
        sectionComboBox.setOnAction(e -> 
        {
            Section selectedSection = sectionComboBox.getValue();
            if (selectedSection != null) 
            {
                studentListView.getItems().setAll(lecturer.getStudentsInSection(selectedSection));
            }
        });
        
        //Update Stats
        studentListView.getSelectionModel().selectedItemProperty().addListener((observed, oldVal, newVal) -> 
        {
            Section selectedSection = sectionComboBox.getValue();
            if (newVal != null && selectedSection != null) 
            {
                updateStats(attendanceStats, attendanceLog, newVal, selectedSection);
            }
        });
        
        //Logout Button Action
        logoutButton.setOnAction(e -> 
        {
            app.setScene(app.getLoginPanelView());
        });
        
        //Layout
        VBox lecturerInfo = new VBox(5, nameLabel, idLabel, deptLabel, sectionChoice, sectionComboBox);
        VBox buttons = new VBox(5, markPresentButton, markAbsentButton);
        VBox dateBox = new VBox(5, new Label("Pick Date"), datePicker);
        VBox checkAbsence = new VBox(5, new Label("Check Absences", viewAbsencesButton));
        HBox attendanceControl = new HBox(20, dateBox, buttons, attendanceStats, checkAbsence);
        VBox layout = new VBox(15, titleLabel, lecturerInfo, studentListView, attendanceControl, logoutButton);
        layout.setPadding(new Insets(20));
        return layout;
        
    }
    
    private void updateStats(Label label, TextArea log, Student student, Section section) {
        AttendanceRecord record = student.getAttendanceForSection(section);
        if (record == null)
        {
            label.setText("No attendance record found fo this section");
            return;
        }
        String stats = String.format("Attendance for %s: %d present, %d absent, %.2f%%",
                student.getName(),
                record.getDaysPresent(),
                record.getDaysAbsent(),
                record.attendancePercentage()
        );
        label.setText(stats);
    }    
    
    private void showAlert(String title, String content) 
    {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
    }
}
 