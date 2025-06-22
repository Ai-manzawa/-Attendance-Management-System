/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpian;

/**
 *
 * @author User
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StudentPanel
{
    private Student student;

    public StudentPanel(Student student)
    {
        this.student = student;
    }

    public Parent getView(Main app,Stage primaryStage)
    {

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);
        pane.setHgap(10);

        //Title
        Label title = new Label("Attendance Overview:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        pane.add(title, 0, 0, 2, 1);

        //Student Details
        pane.add(new Label("Name:"), 0, 1);
        pane.add(new Label(student.getName()), 1, 1);
        
        pane.add(new Label("Student ID: "), 0, 2);
        pane.add(new Label(student.getID()), 1, 2);
        
        pane.add(new Label("Email:"), 0, 3);
        pane.add(new Label(student.getEmail()), 1, 3);
        
        pane.add(new Label("Matric No:"), 0, 4);
        pane.add(new Label(student.getMatricNumber()), 1, 4);

        //Placeholder image or student picture
        ImageView pic = new ImageView(new Image("https://via.placeholder.com/100"));
        pic.setFitHeight(100);
        pic.setFitWidth(100);
        pane.add(pic, 0, 3, 1, 4);

        // Course Selection
        ComboBox<Section> sectionComboBox = new ComboBox<>();
        sectionComboBox.getItems().addAll(student.getEnrolledSections());
        sectionComboBox.setPromptText("Select Section");
        pane.add(new Label("Select Section:"), 1, 5);
        pane.add(sectionComboBox, 2, 5);
        
        //Attendance Labels
        Label presentLabel = new Label("Days Present:");
        Label presentValue = new Label("-");
        Label absentLabel = new Label("Days Absent:");
        Label absentValue = new Label("-");
        Label percentLabel = new Label("Attendance Percentage:");
        Label percentValue = new Label("-");
        Label warningLabel = new Label("Warning: You have 3 unexcused absences in this section! 1 more unexcused absence will lead to barring from attending the Final Examination!");
        Label barringLabel = new Label("Warning: You have 3+ unexcused absences in this section! You have been barred from attending the Final Examination.\nPlease meet your section lecturer if this is a mistake.");        
        warningLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        warningLabel.setVisible(false);
        barringLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        barringLabel.setVisible(false);
        
        pane.add(presentLabel, 1, 6);
        pane.add(presentValue, 2, 6);
        pane.add(absentLabel, 1, 7);
        pane.add(absentValue, 2, 7);
        pane.add(percentLabel, 1, 8);
        pane.add(percentValue, 2, 8);
        
        //Warning and Barring
        VBox warnBox = new VBox(10);
        warnBox.getChildren().add(warningLabel);
        pane.add(warnBox, 0, 10, 3, 1);
        VBox barBox = new VBox(10);
        barBox.getChildren().add(barringLabel);
        pane.add(barBox, 0, 10, 3, 1);

        //Logout Button
        HBox btnBox = new HBox(10);
        Button btnLogout = new Button("Log Out");
        btnBox.getChildren().add(btnLogout);
        pane.add(btnBox, 0, 11, 3, 1);
        
        sectionComboBox.setOnAction(e ->
        {
            Section selectedSection = sectionComboBox.getValue();
            if (selectedSection != null)
            {
                AttendanceRecord record = student.getAttendanceForSection(selectedSection);
                if (record != null)
                {
                    presentValue.setText(String.valueOf(record.getDaysPresent()));
                    absentValue.setText(String.valueOf(record.getDaysAbsent()));
                    percentValue.setText(String.format("%.2f%%", record.attendancePercentage()));
                    warningLabel.setVisible(record.getDaysAbsent() == 3);
                    barringLabel.setVisible(record.getDaysAbsent() > 3);

                }
                else
                {
                    presentValue.setText("-");
                    absentValue.setText("-");
                    percentValue.setText("-");
                }
            }
        });
        
        btnLogout.setOnAction(e -> {
            app.setScene(app.getLoginPanelView());
        });

        VBox layout = new VBox(10, pane);
        layout.setPadding(new Insets(20));

        return layout;
    }
}