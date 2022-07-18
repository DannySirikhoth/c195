package controller;

import helper.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddCustomer implements Initializable {
    private static int id = 100;
    public Label addCustomerIDlabel;
    public TextField addCustomerNametxt;
    public TextField addCustomerAddresstxt;
    public TextField addCustomerZipcodext;
    Stage stage;
    Parent scene;






    @FXML
   public void onActionSave(ActionEvent event) throws IOException,NumberFormatException {
        try
        {

            String name = addCustomerNametxt.getText();
            String address = addCustomerAddresstxt.getText();
            int zip = Integer.parseInt(addCustomerZipcodext.getText());


            //Customer.addNewCustomer(name,address,zip);
            int rowsAffected = Customer.addNewCustomer(name,address,zip);
            if(rowsAffected > 0)
            {
                System.out.println("INSERT successful");
            }
            else {
                System.out.println("INSERT FAILED");
            }

                //Returns to the Home Screen
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please input valid values for all fields");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**Returns to the main screen when the user clicks cancel
     *
    //@param event, when the cancel button is clicked
    //@throws IOException
     */
    @FXML
    public void onActionCancel(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all values, do you wish to continue?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}