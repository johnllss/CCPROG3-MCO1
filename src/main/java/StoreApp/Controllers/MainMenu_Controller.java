package StoreApp.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu_Controller {
    private Stage primaryStage;
    private Scene scene;
    private Parent root;

    public void CustomerView(ActionEvent e) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("/View/Shopping_View.fxml"));
        primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void EmployeeView(ActionEvent e) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("/View/Employee_Login_View.fxml"));
        primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void Exit(ActionEvent e)
    {
        System.out.println("Exit");
    }
}
