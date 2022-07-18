package controller;

import helper.Appointment;
import helper.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AddAppointment {

    public Button saveButton;
    public Button CancelButton;
    public TextField TitleText;
    public TextField DescriptionText;
    public TextField LocationText;
    public TextField TypeText;
    public TextField StartText;
    public TextField EndText;
    public TextField CustomerIDText;
    public TextField UserIDText;
    public TextField DivisionIDText;



    public void saveButtonClick(ActionEvent actionEvent) throws SQLException, IOException {
        String title = TitleText.getText();
        String description = DescriptionText.getText();
        String location = LocationText.getText();
        String  type = TypeText.getText();
        Timestamp start = Timestamp.valueOf(StartText.getText());
        Timestamp end = Timestamp.valueOf(EndText.getText());

        int customer_id = Integer.parseInt(CustomerIDText.getText());
        int user_id = Integer.parseInt(UserIDText.getText());
        int division_id = Integer.parseInt(DivisionIDText.getText());




        int rowsAffected = Appointment.addNewAppointment(title,description,location,type,start,end,customer_id,user_id,division_id);
        if(rowsAffected > 0)
        {
            System.out.println("INSERT Appointment successful");
        }
        else {
            System.out.println("INSERT Appointment FAILED");
        }
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointment.fxml"));
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void CancelButtonClicked(ActionEvent actionEvent) throws IOException {
        //Returns to the Home Screen
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointment.fxml"));
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
