package StoreApp.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import StoreApp.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Inventory_Controller implements Initializable {

    @FXML private ChoiceBox<String> category_choiceBox;
    @FXML private ComboBox<String> filterCombo;
    @FXML private Button logout_btn;
    @FXML private AnchorPane scenePane;
    @FXML private TextField name_txtbox;
    @FXML private TextField brand_txtbox;
    @FXML private TextField price_txtbox;
    @FXML private TextField qty_txtbox;
    @FXML private DatePicker expirationDate_picker;
    @FXML private TableView<Product_Model> productTable;
    @FXML private TableColumn<Product_Model, Integer> productID;
    @FXML private TableColumn<Product_Model, String> productName;
    @FXML private TableColumn<Product_Model, String> productBrand;
    @FXML private TableColumn<Product_Model, Double> productPrice;
    @FXML private TableColumn<Product_Model, Integer> productStock;
    @FXML private Label employeeName;
    @FXML private AnchorPane rightPane;
    @FXML private AnchorPane details;
    @FXML private TextField ID_txtbox;


    private Inventory_Model inventory;
    private Stage stage;
    private Employee_Model[] employees;
    private Employee_Model loggedInEmployee;

    private String[] categories = {"Food", "Beverage", "Toiletries", "Cleaning Products", "Medications"};
    private String[] filterCategories = {"All","Food", "Beverage", "Toiletries", "Cleaning Products", "Medications"};
    private ObservableList<Product_Model> productObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        category_choiceBox.getItems().addAll(categories);
        filterCombo.getItems().addAll(filterCategories);
        filterCombo.setValue("All");

    }

    /**
     * This method displays the employee name on the view.
     * @param employee is the name of the employee to display.
     */
    public void displayEmployeeName(String employeeName)
    {
        if (loggedInEmployee != null) {
            employeeName.setText(employeeName + " (" + loggedInEmployee.getRole() + ")");
        } else {
            employeeName.setText(employeeName);
        }
    }

    /**
     * This method handles the logout action.
     * @param event is the action event triggered by the logout button.
     */
    @FXML
    public void onLogOut(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Employee_Login_View.fxml"));
            Parent root = loader.load();


            Employee_Login_Controller employeeLoginController= loader.getController();
            employeeLoginController.setInventory(inventory);
            employeeLoginController.setEmployees(employees);


            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method filters the products displayed by category.
     * @param event is the action event triggered by the filter selection.
     */
    @FXML
    private void FilterByCategory(ActionEvent event)
    {
        String choice = filterCombo.getValue();
        if (choice == null || choice.equals("All")) {
            productObservableList.setAll(inventory.getAllProducts());
        } else {
            var filtered = inventory.getProductsByCategory(choice);
            productObservableList.setAll(filtered);
        }

        productTable.refresh();
    }

    /**
     * This method handles the add product action from the UI.
     * @param event is the action event triggered by the add product button.
     */
    @FXML
    private void addProduct(ActionEvent event) {
        if (!hasPermission("Manager")) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                "Access Denied: Only Managers can add products",
                ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // For now, just debug
        System.out.println("Add Product button clicked!");
        try{
            String name = name_txtbox.getText().trim();
            String category = category_choiceBox.getValue();
            String brand = brand_txtbox.getText().trim();
            double price = Double.parseDouble(price_txtbox.getText().trim());
            int qty = Integer.parseInt(qty_txtbox.getText().trim());
            LocalDate expirationDate = expirationDate_picker.getValue();

            if(name.isEmpty() || category.isEmpty() || brand.isEmpty() || price < 0 || qty < 0){
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all the fields", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            Product_Model product;
            if (expirationDate != null) {
                // use the full constructor with expiration date
                product = new Product_Model(name, price, qty, category, brand, "", expirationDate, "");
            } else {
                // use the constructor without expiration date for non-perishable items
                product = new Product_Model(name, price, qty, category, brand);
            }

            boolean success = inventory.addProduct(product);
            if (success) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION, "Product added", ButtonType.OK);
                alert.showAndWait();

                // clear fields after successful adding of product
                name_txtbox.clear();
                brand_txtbox.clear();
                price_txtbox.clear();
                qty_txtbox.clear();
                category_choiceBox.setValue(null);
                expirationDate_picker.setValue(null);
                productObservableList.setAll(inventory.getAllProducts());
            }
            else{
               Alert alert =  new Alert(Alert.AlertType.WARNING, "Product already exists", ButtonType.OK);
                alert.showAndWait();
            }

        }catch(NumberFormatException e){
            new Alert(Alert.AlertType.WARNING, "Please enter a valid number", ButtonType.OK).showAndWait();
        }

    }

    /**
     * This method handles the restock product action from the UI.
     * @param event is the action event triggered by the restock button.
     */
    @FXML
    private void restockProduct(ActionEvent event)
    {
        System.out.println("Restock Button Pressed");
        Stage popup = new  Stage();
        popup.setTitle("Restock");
        popup.initModality(Modality.WINDOW_MODAL);
        Label qty = new Label("Quantity");
        TextField qty_txtbox = new TextField();
        Label productID = new Label("Product ID");
        TextField productID_txtbox = new TextField();
        Button submitbtn = new Button("Submit");
        Button cancelbtn = new Button("Cancel");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15,15,10,15));
        vbox.getChildren().addAll(qty, qty_txtbox, productID, productID_txtbox, submitbtn, cancelbtn);

        submitbtn.setOnAction(e -> {
            try {
                int quantity = Integer.parseInt(qty_txtbox.getText());
                int product_ID = Integer.parseInt(productID_txtbox.getText());

                inventory.restockProduct(product_ID, quantity);
                popup.close();
                productTable.refresh();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid number", ButtonType.OK);
                alert.showAndWait();
            }
        });
        cancelbtn.setOnAction(e -> {
            popup.close();
        });

        Scene scene = new Scene(vbox);
        popup.setScene(scene);
        popup.show();
        System.out.println("inventory = " + inventory);
    }

    /**
     * This method handles the update product action from the UI.
     * @param event is the action event triggered by the update button.
     */
    @FXML
    private void updateProduct(ActionEvent event) {
        if (!hasPermission("Manager")) {
            new Alert(Alert.AlertType.ERROR,
                "Access Denied: Only Managers can update products",
                ButtonType.OK).showAndWait();
            return;
        }

        String name = name_txtbox.getText().trim();
        String category = category_choiceBox.getValue();
        String brand = brand_txtbox.getText().trim();
        int productID;
        Double price = null;
        LocalDate expirationDate = expirationDate_picker.getValue();


        try {
            productID = Integer.parseInt(ID_txtbox.getText().trim());

            String priceText = price_txtbox.getText().trim();
            if (!priceText.isEmpty()) {
                price = Double.parseDouble(priceText);
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid number format!", ButtonType.OK).showAndWait();
            return;
        }

        Product_Model product = inventory.findProduct(productID);
        if (product == null) {
            new Alert(Alert.AlertType.ERROR, "Product not found!", ButtonType.OK).showAndWait();
            return;
        }

        boolean updated = false;

        if (!name.isEmpty() && !name.equals(product.getProductName())) {
            inventory.updateProductName(productID, name);
            updated = true;
        }
        if (category != null && !category.equals(product.getProductCategory())) {
            inventory.updateProductVariant(productID, category);
            updated = true;
        }
        if (!brand.isEmpty() && !brand.equals(product.getProductBrand())) {
            inventory.updateProductBrand(productID, brand);
            updated = true;
        }
        if (price != null && price != product.getProductPrice()) {
            inventory.updateProductPrice(productID, price);
            updated = true;
        }
        if (expirationDate != null && !expirationDate.equals(product.getExpirationDate())) {
            inventory.updateProductExpirationDate(productID, expirationDate);
            updated = true;
        }

        String message = updated ? "Product updated successfully!" : "No changes detected";
        new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK).showAndWait();
        productTable.refresh();
    }


    /**
     * This method handles the remove product action from the UI.
     * @param event is the action event triggered by the remove button.
     */
    @FXML
    private void removeProduct(ActionEvent event)
    {
        if (!hasPermission("Manager")) {
            new Alert(Alert.AlertType.ERROR,
                "Access Denied: Only Managers can remove products",
                ButtonType.OK).showAndWait();
            return;
        }

        System.out.println("Delete Product button clicked!");
        Stage popup = new  Stage();
        popup.setTitle("Remove item");
        popup.initModality(Modality.WINDOW_MODAL);
        Label productID = new Label("Product ID");
        TextField productID_txtbox = new TextField();
        Button submitbtn = new Button("Submit");
        Button cancelbtn = new Button("Cancel");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15,15,10,15));
        vbox.getChildren().addAll(productID, productID_txtbox, submitbtn, cancelbtn);

        submitbtn.setOnAction(e -> {
            try {
                int product_ID = Integer.parseInt(productID_txtbox.getText());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove?", ButtonType.OK,  ButtonType.CANCEL);
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        boolean success = inventory.removeProduct(product_ID);
                        if(success){
                            productObservableList.setAll(inventory.getAllProducts());
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Product removed", ButtonType.OK);
                            alert1.showAndWait();
                            popup.close();
                            productTable.refresh();
                        }
                        else{
                            Alert alert2 = new Alert(Alert.AlertType.WARNING, "Product not removed", ButtonType.OK);
                            alert2.showAndWait();
                        }
                    }
                    else{
                        popup.close();
                    }
                });


            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid number", ButtonType.OK);
                alert.showAndWait();
            }
        });
        cancelbtn.setOnAction(e -> {
            popup.close();
        });

        Scene scene = new Scene(vbox);
        popup.setScene(scene);
        popup.show();
        System.out.println("inventory = " + inventory);
    }

    /**
     * This method displays only low stock products in the table view.
     * @param event is the action event triggered by the view low stock button.
     */
    @FXML
    private void viewLowStock(ActionEvent event) {
        details.setVisible(false);
        ObservableList<Product_Model> allProducts = FXCollections.observableArrayList(productObservableList);

        // Show only low-stock products
        productTable.setItems(FXCollections.observableArrayList(
                inventory.getLowStockProducts(3)
        ));


        Button back = new Button("Back");
        back.setStyle("-fx-background-color: #006937; -fx-text-fill: white;");

        // Position the button relative to the table
        back.setLayoutX(10);
        back.setLayoutY(productTable.getLayoutY() + productTable.getHeight() + 10);

        back.setOnAction(e -> {
            // Restore full product list
            productTable.setItems(allProducts);

            // Remove the back button from the pane
            rightPane.getChildren().remove(back);

            // Show details again
            details.setVisible(true);
        });

        // Add the back button to the pane containing the table
        if (!rightPane.getChildren().contains(back)) {
            rightPane.getChildren().add(back);
        }
    }

    /**
     * This method asks for confirmation from the employee for logging out. If confirmed, the employee will be logged out.
     * @param event is the event listener.
     */
    public void logout(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout.");
        alert.setContentText("Do you want to save before exiting?");

        if (alert.showAndWait().get() == ButtonType.OK)
        {
            stage = (Stage)scenePane.getScene().getWindow();
            System.out.println("You successfully logged out!");
            stage.close();
        }
    }

    /**
     * This method sets the inventory for the controller and initializes the table view.
     * @param inv is the Inventory_Model to be set.
     */
    public void setInventory(Inventory_Model inv)
    {
        this.inventory = inv;
        productID.setCellValueFactory(cellData -> new javafx.beans.property.ReadOnlyObjectWrapper<>(cellData.getValue().getProductID()));
        productName.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getProductName()));
        productBrand.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getProductBrand()));
        productPrice.setCellValueFactory(cellData -> new javafx.beans.property.ReadOnlyObjectWrapper<>(cellData.getValue().getProductPrice()));
        productStock.setCellValueFactory(cellData -> new javafx.beans.property.ReadOnlyObjectWrapper<>(cellData.getValue().getProductQuantity()));
        productTable.setItems(productObservableList);
        productObservableList.setAll(inventory.getAllProducts());

    }

    /**
     * This sets the employees array for the controller.
     * @param employees is the array of Employee_Model to be set.
     */
    public void setEmployees(Employee_Model[] employees){
        this.employees = employees;
    }

    /**
     * This sets the current logged-in employee.
     * @param employee is the Employee_Model who logged in.
     */
    public void setLoggedInEmployee(Employee_Model employee) {
        this.loggedInEmployee = employee;
    }

    /**
     * This method checks if the current logged in employee has permission for an action.
     * @param requiredRole is the minimum role required ("Manager" or "Restocker").
     * @return boolean indicating if employee has permission.
     */
    private boolean hasPermission(String requiredRole) {
        if (loggedInEmployee == null) {
            return false;
        }

        String role = loggedInEmployee.getRole();

        // managers have all permissions
        if (role.equalsIgnoreCase("Manager")) {
            return true;
        }

        // restockers only have restock permission
        if (role.equalsIgnoreCase("Restocker") && requiredRole.equalsIgnoreCase("Restocker")) {
            return true;
        }

        return false;
    }
}