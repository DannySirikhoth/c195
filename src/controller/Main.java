package controller;

import helper.JDBCDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        //tells which fxml file to load
        Parent root = FXMLLoader.load((getClass().getResource("/view/login.fxml")));
        stage.setScene(new Scene( root,550,290));
        stage.show();
    }
    public static void main(String[] args) throws SQLException {

        //Opens the jdbc connection
        JDBCDAO.openConnection();

        //launches the javafx window
        launch(args);

        //close jdbc connection
        JDBCDAO.closeConnection();




    }

}
