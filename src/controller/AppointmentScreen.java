package controller;

import helper.Appointment;
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

import java.sql.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentScreen implements Initializable{

    @FXML
    public TableView AppointmentTable;
    @FXML
    public TableColumn <Appointment, String> AppId;
    @FXML
    public TableColumn <Appointment, String> TitleColumn;
    public TableColumn <Appointment,String> descriptionColumn;
    @FXML
    public TableColumn <Appointment, String> locationColumn;
    @FXML
    public TableColumn <Appointment, String> typeColumn;
    @FXML
    public TableColumn <Appointment, String> startColumn;
    public TableColumn <Appointment, String> endColumn;
    public TableColumn <Appointment, String>customerIdColumn;
    public TableColumn <Appointment, String>UserIdColumn;
    public TableColumn <Appointment, String>contactIdColumn;


    public Label idLabel;
    public Label typeLabel;
    public Label locationLabel;
    public Label startLabel;


    public TextField AppidText;
    public TextField titleText;
    public TextField descriptionText;
    public TextField locationText;
    public TextField typeText;
    public TextField startText;
    public TextField endText;
    public TextField customer_idtext;
    public TextField user_idtext;
    public TextField contactText;


    public Button AddButton;
    public Button UpdateButton;
    public Button DeleteButton;
    public Button viewCustomersButton;



    public void AddCustomerButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage) AddButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void updateButtonClicked(ActionEvent actionEvent) throws IOException, SQLException {
        //makes sure a selection is picked
        if (AppointmentTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: Choose an Appointment to update");
            alert.showAndWait();
        } else {

            Appointment selectedAppointment = (Appointment) AppointmentTable.getSelectionModel().getSelectedItem();
            int selectedId = Integer.parseInt(selectedAppointment.getAppID());
            System.out.println("the selected Appointment ID is " + selectedId);

            String title = titleText.getText();
            String description = descriptionText.getText();
            String location = locationText.getText();
            String type = typeText.getText();

            Timestamp start = Timestamp.valueOf(startText.getText());
            Timestamp end = Timestamp.valueOf(endText.getText());
            int customerId = Integer.parseInt(customer_idtext.getText());
            int userId = Integer.parseInt(user_idtext.getText());
            int contactId = Integer.parseInt(contactText.getText());

           if(Appointment.updateAppointment(selectedId,title,description,location,type,start,end,customerId,userId,contactId)==1)
           {
               System.out.println(" Update Appointment query executed");
           }
        }
    }

    public void deleteButtonClicked(ActionEvent actionEvent) throws SQLException {
        //makes sure a selection is picked
        if (AppointmentTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("ERROR: Choose an appointment to delete");
            alert.showAndWait();
        } else {
            Appointment selectedAppointment = (Appointment) AppointmentTable.getSelectionModel().getSelectedItem();
            int selectedId = Integer.parseInt(selectedAppointment.getAppID());
            System.out.println("the selected Appointment ID is " + selectedId);

            if(Appointment.deleteAppointment(selectedId) ==1)
            {
                System.out.println(selectedId + " was successfully deleted from the database");
                AppointmentTable.refresh();
            }
        }
    }

    @FXML /* port information over to the form */
    private void showAppointmentDetails(Appointment selectedAppointment) {

        AppidText.setText(selectedAppointment.getAppID());
        titleText.setText(selectedAppointment.getTitle());
        descriptionText.setText(selectedAppointment.getDescription());
        locationText.setText(selectedAppointment.getLocation());
        typeText.setText(selectedAppointment.getType());

        startText.setText(selectedAppointment.getStart());
        endText.setText(selectedAppointment.getEnd());
        customer_idtext.setText(selectedAppointment.getCustomer_id());
        user_idtext.setText(selectedAppointment.getUser_id());
        contactText.setText(selectedAppointment.getContact());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AppId.setCellValueFactory(new PropertyValueFactory<>("AppID"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        UserIdColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        contactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));

        AppointmentTable.getItems().setAll(parseAppointmentList()); /* takes the list from the parseCustomerList()
         method, and adds the rows to the TableView */
        AppointmentTable.getSelectionModel().selectedItemProperty().addListener
                ((observable, oldValue, newValue)-> showAppointmentDetails((Appointment) newValue));
    }

    private List<Appointment> parseAppointmentList() {

        //first 5
        String tAppId;
        String tTitle;
        String tDescription;
        String tLocation;
        String tType;

        //second 5
        String tStart;
        String tEnd;
        String tCustomer_id;
        String tUser_id;
        String tContact_id;


        ArrayList<Appointment> appList = new ArrayList();
        try(
                PreparedStatement statement = JDBCDAO.getConn().prepareStatement("select  Appointment_ID,Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID from appointments;");
                ResultSet rs = statement.executeQuery()){
            System.out.println("first query on Appointment screen successful");

            while (rs.next()) {
                tAppId = rs.getString("Appointment_ID");
                tTitle = rs.getString("Title");
                tDescription = rs.getString("Description");
                tLocation = rs.getString("Location");
                tType = rs.getString("Type");
                tStart = rs.getString("Start");
                tEnd = rs.getString("End");
                tCustomer_id = rs.getString("Customer_ID");
                tUser_id = rs.getString("User_ID");
                tContact_id = rs.getString("Contact_ID");

                appList.add(new Appointment(tAppId, tTitle,tDescription,tLocation, tType, tStart,tEnd,tCustomer_id,tUser_id,tContact_id));
            }

        } catch (SQLException sqe) {
            System.out.println("Check your SQL in AppointmentScreen");
        } catch (Exception e) {
            System.out.println("Something besides the SQL went wrong In Appointment screen.");
        }

        System.out.println("Second Query on Appointment screen successful");

        return appList;

    }

    public void viewCustomersButtonClick(ActionEvent actionEvent) throws IOException {
        //Returns to the Home Screen
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerScreen.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
