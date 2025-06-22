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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

public class LoginPanel 
{
    public Parent getView(Main app, Stage primaryStage, List<Admin> admins, List<Student> students, List<Lecturer> lecturers)
    {
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
        
        Image image = new Image("https://upload.wikimedia.org/wikipedia/en/thumb/3/31/International_Islamic_University_Malaysia_emblem.svg/375px-International_Islamic_University_Malaysia_emblem.svg.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        Text title = new Text("                                      IIUM\n STUDENT ATTENDANCE MANAGEMENT SYSTEM");
        title.setFont(Font.font("Courier", FontWeight.BOLD, 20));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        pane.add(title, 0, 1, 2, 1);
        pane.add(imageView, 0, 0, 2, 1);
        GridPane.setHalignment(imageView, HPos.CENTER);

        pane.add(new Label("User Type:"), 0, 2);
        RadioButton adminRB = new RadioButton("Admin");
        RadioButton lecturerRB = new RadioButton("Lecturer");
        RadioButton studentRB = new RadioButton("Student");
        ToggleGroup userGroup = new ToggleGroup();
        adminRB.setToggleGroup(userGroup);
        lecturerRB.setToggleGroup(userGroup);
        studentRB.setToggleGroup(userGroup);
        VBox userBox = new VBox(5, adminRB, lecturerRB, studentRB);
        pane.add(userBox, 0, 3);

        pane.add(new Label("ID: "), 0, 4);
        TextField IDField = new TextField();
        pane.add(IDField, 1, 4);

        pane.add(new Label("Password:"), 0, 5);
        PasswordField passwordField = new PasswordField();
        pane.add(passwordField, 1, 5);

        HBox btnBox = new HBox(10);
        btnBox.setAlignment(Pos.BOTTOM_CENTER);
        Button btnLogin = new Button("Login");
        Button btnExit = new Button("Exit");
        btnBox.getChildren().addAll(btnLogin, btnExit);
        pane.add(btnBox, 0, 6, 2, 1);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        pane.add(errorLabel, 0, 7, 2, 1);

        btnLogin.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                errorLabel.setText("");
                String inputID = IDField.getText();
                String inputPassword = passwordField.getText();

                if (adminRB.isSelected())
                {
                    Admin foundAdmin = null;
                    for(Admin admin : admins)
                    {
                        if (admin.getAdminId().equals(inputID) && admin.getPassword().equals(inputPassword))
                        {
                            foundAdmin = admin;
                            break;
                        }
                    }
                    if (foundAdmin != null)
                    {
                        app.setAdmin(foundAdmin);
                        app.setScene(app.getAdminPanelView());
                    }
                    else
                    {
                        errorLabel.setText("Invalid Admin ID or Password");
                    }
                }
                else if (studentRB.isSelected())
                {
                    Student foundStudent = null;
                    for(Student student : students)
                    {
                        if (student.getUserId().equals(inputID) && student.getPassword().equals(inputPassword))
                        {
                            foundStudent = student;
                            break;
                        }
                    }
                    if (foundStudent != null)
                    {
                        //Demo
                        /* if(foundStudent.getEnrolledCourses().isEmpty()) 
                        {
                            Course demoCourse = new Course("CS101", "Computer Science", "Dr.Smith");
                            foundStudent.addCourse(demoCourse);
                            AttendanceRecord demoRecord = new AttendanceRecord(foundStudent, 18, 2);
                            foundStudent.setAttendanceForCourse(demoCourse, demoRecord);
                        }
                        */
                        app.setScene(app.getStudentPanelView(foundStudent));
                    }
                    else
                    {
                        errorLabel.setText("Invalid Student ID or Password");
                    }
                }
                else if (lecturerRB.isSelected())
                {
                    Lecturer foundLecturer = null;
                    for(Lecturer lecturer : lecturers)
                    {
                        if (lecturer.getLecturerID().equals(inputID) && lecturer.getPassword().equals(inputPassword))
                        {
                            foundLecturer = lecturer;
                            break;
                        }
                    }
                    if (foundLecturer != null)
                    {
                        app.setLecturer(foundLecturer);
                        app.setScene(app.getLecturerPanelView());
                    }
                    else
                    {
                        errorLabel.setText("Invalid Admin ID or Password");
                    }
                }
                else
                {
                    errorLabel.setText("Please select a user type.");
                }
            }
        });

        btnExit.setOnAction(e -> primaryStage.close());

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20");
        layout.getChildren().addAll(pane);
        return layout;
    }
}
