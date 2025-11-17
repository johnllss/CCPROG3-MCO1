package StoreApp.Models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu_View.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("StoreApp");
            stage.show();

            stage.setOnCloseRequest(event -> {
                event.consume(); // prevents from closing the program when pressing the X button
                logout(stage);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout(Stage stage)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout!");
        alert.setContentText("Do you want to save before exiting?: ");

        if (alert.showAndWait().get() == ButtonType.OK)
        {
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
