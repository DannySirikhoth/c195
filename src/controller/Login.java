package controller;


import helper.JDBCDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;


public class Login implements Initializable {

    public Button LoginButton;
    public Button ResetButton;
    public Label TimeLabel;
    public Label titleLabel;
    public javafx.scene.control.MenuButton MenuButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField userNameField;


    //Connection con;

    Alert a = new Alert(Alert.AlertType.ERROR);
    ResourceBundle resources = ResourceBundle.getBundle("helper/nat_fr");

    public void loginButtonClick(ActionEvent actionEvent) throws IOException, SQLException
    {
        System.out.println("pressed login ");

        if(userNameField.getText().isBlank())
        {
            a.setContentText(" Please enter a username");
            a.show();
        }

        else if(passwordField.getText().isBlank())
        {
            a.setContentText(" Please enter a Password!");
            a.show();
        }

        else
        {
            if (JDBCDAO.checkCredentials(userNameField.getText(), passwordField.getText()) == true)
            {
                System.out.println("Login verified");

                //Opens new scene
                Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
                Stage stage = (Stage) LoginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();



            } else {
                //Alert that tells the user they set invalid credentials
                System.out.println("Please Enter Valid Credentials");
                a.setContentText(" you have entered invalid credentials");
                a.show();
                

            }
        }
    }

    public void resetButtonClick(ActionEvent actionEvent) {
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TimeLabel.setText("Time Zone: " + ZoneId.systemDefault().toString());

        //ResourceBundle rb = ResourceBundle.getBundle("/helper/nat_fr.properties");
        //System.out.println(rb.getString("titleLabel"));


    }
}
