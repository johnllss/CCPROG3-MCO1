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
    @FXML private TextField variant_txtbox;
    @FXML private TextField price_txtbox;
    @FXML private TextField qty_txtbox;
    @FXML private DatePicker expirationDate_picker;
    @FXML private TableView<Product_Model> productTable;
    @FXML private TableColumn<Product_Model, Integer> productID;
    @FXML private TableColumn<Product_Model, String> productName;
    @FXML private TableColumn<Product_Model, String> productBrand;
    @FXML private TableColumn<Product_Model, String> productVariant;
    @FXML private TableColumn<Product_Model, Double> productPrice;
    @FXML private TableColumn<Product_Model, Integer> productStock;
    @FXML private Label employeeName;
    @FXML private AnchorPane rightPane;
    @FXML private AnchorPane details;
    @FXML private TextField ID_txtbox;
    @FXML private Button viewExpiredBtn;
    @FXML private Label expiredProductsLabel;


    private Inventory_Model inventory;
    private Stage stage;
    private Employee_Model[] employees;
    private Employee_Model loggedInEmployee;

    private String[] categories = {"Food", "Beverages", "Toiletries", "Cleaning Products", "Medications"};
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
    public void displayEmployeeName(String employee)
    {
        if (loggedInEmployee != null) {
            employeeName.setText(employee + " (" + loggedInEmployee.getRole() + ")!");
        } else {
            employeeName.setText(employee);
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
            Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
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
                updateExpiredProductsLabel();
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
            inventory.updateProductCategory(productID, category);
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
        updateExpiredProductsLabel();
    }


    /**
     * This method handles the update variant action from the UI.
     * @param event is the action event triggered by the update variant button.
     */
    @FXML
    private void updateVariant(ActionEvent event) {
        if (!hasPermission("Manager")) {
            new Alert(Alert.AlertType.ERROR,
                "Access Denied: Only Managers can update variants",
                ButtonType.OK).showAndWait();
            return;
        }

        System.out.println("Update Variant button clicked!");
        Stage popup = new Stage();
        popup.setTitle("Update Variant");
        popup.initModality(Modality.WINDOW_MODAL);
        
        Label productIDLabel = new Label("Product ID");
        TextField productID_txtbox = new TextField();
        Label variantLabel = new Label("New Variant");
        TextField variant_txtbox = new TextField();
        Button submitBtn = new Button("Submit");
        Button cancelBtn = new Button("Cancel");
        
        submitBtn.getStyleClass().add("primary-btn");
        cancelBtn.getStyleClass().add("secondary-btn");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(15, 15, 10, 15));
        vbox.getChildren().addAll(productIDLabel, productID_txtbox, variantLabel, variant_txtbox, submitBtn, cancelBtn);

        submitBtn.setOnAction(e -> {
            try {
                int product_ID = Integer.parseInt(productID_txtbox.getText().trim());
                String newVariant = variant_txtbox.getText().trim();

                if (newVariant.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a variant", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }

                Product_Model product = inventory.findProduct(product_ID);
                if (product == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Product not found!", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }

                boolean success = inventory.updateProductVariant(product_ID, newVariant);
                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Variant updated successfully!", ButtonType.OK);
                    alert.showAndWait();
                    productTable.refresh();
                    popup.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to update variant", ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a valid product ID", ButtonType.OK);
                alert.showAndWait();
            }
        });
        
        cancelBtn.setOnAction(e -> {
            popup.close();
        });

        Scene scene = new Scene(vbox);
        scene.getStylesheets().add(getClass().getResource("/View/Design.css").toExternalForm());
        popup.setScene(scene);
        popup.show();
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
                            updateExpiredProductsLabel();
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
     * This method displays only expired products in the product table.
     * @param event is the action event triggered by the button.
     */
    @FXML
    private void viewExpiredProducts(ActionEvent event) {
        details.setVisible(false);
        ObservableList<Product_Model> allProducts = FXCollections.observableArrayList(productObservableList);

        // show only expired products
        productTable.setItems(FXCollections.observableArrayList(inventory.getExpiredProducts()));

        // create another returning button below for specifically going back to the default product table
        Button back = new Button("Back");
        back.getStyleClass().add("primary-btn");

        // position the button relative to the table
        back.setLayoutX(10);
        back.setLayoutY(productTable.getLayoutY() + productTable.getHeight() + 10);

        // when 
        back.setOnAction(e -> {
            // restore full product list
            productTable.setItems(allProducts);

            // remove the back button from the pane
            rightPane.getChildren().remove(back);

            // show details again
            details.setVisible(true);
        });

        // add the back button to the pane
        if (!rightPane.getChildren().contains(back)) {
            rightPane.getChildren().add(back);
        }
    }

    /**
     * This method applies red font color to all expired products.
     */
    private void colorizeRedFontToExpiredProducts() {
        // apply to Product ID column
        productID.setCellFactory(column -> new TableCell<Product_Model, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item));

                    Product_Model product = getTableView().getItems().get(getIndex());
                    if (product.isExpired()) {
                        setStyle("-fx-text-fill: #d32f2f; -fx-font-weight: bold;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        // apply to Product Name column
        productName.setCellFactory(column -> new TableCell<Product_Model, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    Product_Model product = getTableView().getItems().get(getIndex());
                    if (product.isExpired()) {
                        setStyle("-fx-text-fill: #d32f2f; -fx-font-weight: bold;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        // apply to Brand column
        productBrand.setCellFactory(column -> new TableCell<Product_Model, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    Product_Model product = getTableView().getItems().get(getIndex());
                    if (product.isExpired()) {
                        setStyle("-fx-text-fill: #d32f2f; -fx-font-weight: bold;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        // apply to Variant column
        productVariant.setCellFactory(column -> new TableCell<Product_Model, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    Product_Model product = getTableView().getItems().get(getIndex());
                    if (product.isExpired()) {
                        setStyle("-fx-text-fill: #d32f2f; -fx-font-weight: bold;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        // apply to Price column
        productPrice.setCellFactory(column -> new TableCell<Product_Model, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.format("%.2f", item));

                    Product_Model product = getTableView().getItems().get(getIndex());
                    if (product.isExpired()) {
                        setStyle("-fx-text-fill: #d32f2f; -fx-font-weight: bold;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        // apply to Stock column
        productStock.setCellFactory(column -> new TableCell<Product_Model, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(String.valueOf(item));

                    Product_Model product = getTableView().getItems().get(getIndex());
                    if (product.isExpired()) {
                        setStyle("-fx-text-fill: #d32f2f; -fx-font-weight: bold;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });
    }

    /**
     * This method applies a light red background for all expired products.
     */
    private void colorizeLightRedBGToExpiredProduccts() {
        productTable.setRowFactory(tv -> new TableRow<Product_Model>() {
            @Override
            protected void updateItem(Product_Model product, boolean empty) {
                super.updateItem(product, empty);

                if (empty || product == null) {
                    setStyle("");
                } else if (product.isExpired()) {
                    // light red background for expired products
                    setStyle("-fx-background-color: #ffebee;");
                } else {
                    setStyle("");
                }
            }
        });
    }

    /**
     * This method updates the expired products statistics label.
     */
    private void updateExpiredProductsLabel() {
        if (inventory != null) {
            int expiredCount = inventory.getExpiredProducts().size();

            if (expiredCount > 0) {
                expiredProductsLabel.setText(String.format("%d expired product%s", expiredCount, expiredCount == 1 ? "" : "s"));

                expiredProductsLabel.setVisible(true);
            } else {
                expiredProductsLabel.setText("");
                expiredProductsLabel.setVisible(false);
            }
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
        productVariant.setCellValueFactory(cellData -> {
            String variant = cellData.getValue().getVariant();
            return new javafx.beans.property.SimpleStringProperty(variant != null ? variant : "");
        });
        productPrice.setCellValueFactory(cellData -> new javafx.beans.property.ReadOnlyObjectWrapper<>(cellData.getValue().getProductPrice()));
        productStock.setCellValueFactory(cellData -> new javafx.beans.property.ReadOnlyObjectWrapper<>(cellData.getValue().getProductQuantity()));

        // apply cell factory for red text on expired products
        colorizeRedFontToExpiredProducts();

        // apply row factory for color-coded rows
        colorizeLightRedBGToExpiredProduccts();

        productTable.setItems(productObservableList);
        productObservableList.setAll(inventory.getAllProducts());

        // update expired products statistics label
        updateExpiredProductsLabel();

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