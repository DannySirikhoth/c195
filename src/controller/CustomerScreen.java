/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import helper.Customer;
import helper.JDBCDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class CustomerScreen implements Initializable {

    public Button AddButton;
    public Button UpdateButton;
    public Button DeleteButton;
    public Button viewAppointmentsButton;
    @FXML
    public TableColumn<Customer, String> custID;
    @FXML
    public TableColumn<Customer, String> custTableName;
    @FXML
    public TableColumn<Customer, String> custAddress;
    @FXML
    public TableColumn<Customer, String>custPostalCode;
    @FXML
    public TableColumn<Customer, String>custPhone;
    @FXML
    public TableView <Customer> custTable;
    public TextField NameText;
    public TextField AddressText;
    public TextField PostalText;
    public TextField PhoneText;
    public TextField DivisionIdText;

    @FXML /* port information over to the form */
    public void showCustomerDetails(Customer selectedCustomer) {

        NameText.setText(selectedCustomer.getName());
        AddressText.setText(selectedCustomer.getAddress());
        PostalText.setText(selectedCustomer.getZip());
        PhoneText.setText(selectedCustomer.getPhone());
        DivisionIdText.setText(selectedCustomer.getId());

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         custID.setCellValueFactory(new PropertyValueFactory<>("id"));
         custTableName.setCellValueFactory(new PropertyValueFactory<>("name"));

         custTable.getItems().setAll(parseCustomerList()); /* takes the list from the parseCustomerList()
         method, and adds the rows to the TableView */
        custTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->showCustomerDetails(newValue));

    }
    private List<Customer> parseCustomerList() {
      
        String tId;
        String tName;
        String tAddress;
        String tZip;
        String tPhone;
        ArrayList<Customer> custList = new ArrayList();
        try(
            
            
        PreparedStatement statement = JDBCDAO.getConn().prepareStatement("select  customer_Id, customer_name, address, postal_code, phone from customers;");
            ResultSet rs = statement.executeQuery()){
            System.out.println("first query on Home screen successful");
            
            while (rs.next()) {
                tId = rs.getString("customer_ID");

                tName = rs.getString("customer_Name");

                tAddress = rs.getString("address");
                
                tZip = rs.getString("postal_Code");

                tPhone = rs.getString("phone");

                custList.add(new Customer(tId, tName, tAddress, tZip,tPhone));

            }
          
        } catch (SQLException sqe) {
            System.out.println("Check your SQL in Home Screen");
        } catch (Exception e) {
            System.out.println("Something besides the SQL went wrong In Home screen.");
        }

        System.out.println("Second Query on Home screen  successful");
        return custList;

    }
    public void AddCustomerButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void updateButtonClicked(ActionEvent actionEvent) throws IOException, SQLException {

        //makes sure a selection is picked
        if (custTable.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: Choose a Customer to update");
            alert.showAndWait();
        } else
        {
            //This takes the selected Id to make as a parameter to send to update query
            Customer selectedCustomer =custTable.getSelectionModel().getSelectedItem();
            int selectedId = Integer.parseInt(selectedCustomer.getId());
            System.out.println("the selected ID is " + selectedId);

            //--------Takes the input values in the form
           String name = NameText.getText();
           String address  =AddressText.getText();
           String postcode = PostalText.getText();
           String phone = PhoneText.getText();
           int divId = Integer.parseInt(DivisionIdText.getText());

           if(Customer.updateCustomer(selectedId,name,address,postcode,phone,divId) ==1)
           {
               System.out.println("Update Customer query executed");
           }
            custTable.refresh();
        }
    }
    public void deleteButtonClicked(ActionEvent actionEvent) throws SQLException, IOException {
        //makes sure a selection is picked
        if (custTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: Choose a Customer to delete");
            alert.showAndWait();
        } else {
             int customer_Id = Integer.parseInt(custTable.getSelectionModel().getSelectedItem().getId());
             System.out.println(" customer ID to be deleted is " + customer_Id);
             Customer.deleteCustomer(customer_Id);
            custTable.getItems().setAll(parseCustomerList());
            custTable.refresh();

        }
    }
    public void viewAppointmentButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointment.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
