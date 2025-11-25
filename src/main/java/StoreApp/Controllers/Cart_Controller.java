package StoreApp.Controllers;

import StoreApp.Models.Cart_Model;
import StoreApp.Models.Customer_Model;
import StoreApp.Models.Inventory_Model;
import StoreApp.Models.Item_Model;
import StoreApp.Models.Product_Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cart_Controller {
    @FXML private VBox productsBox;
    @FXML private Label subtotalLabel;
    @FXML private Label vatLabel;
    @FXML private Label totalLabel;
    @FXML private TableView<Item_Model> cartTable;
    @FXML private TableColumn<Item_Model, Integer> qty;
    @FXML private TableColumn<Item_Model, String> ProductName;
    @FXML private TableColumn<Item_Model, String> total;

    private Customer_Model customer;
    private Cart_Model cart;
    private Inventory_Model inventory;

    /**
     * This sets the inventory for the controller.
     * @param inventory is the Inventory_Model to be set.
     */
    public void setInventory(Inventory_Model inventory) {
        this.inventory = inventory;
    }

    /**
     * This sets the customer and initializes the cart display.
     * @param customer is the Customer_Model to be set.
     */
    public void setCustomer(Customer_Model customer) {
        this.customer = customer;
        this.cart = customer.getCart();

        cartTable.setItems(cart.getItems());
        updateProductTableDisplay();
        updateAllTotals();
    }

    /**
     * This method updates the product table display with cart items.
     */
    private void updateProductTableDisplay() {
        productsBox.getChildren().clear();

        cartTable.setEditable(true);

        ProductName.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getProduct().getProductName()
                )
        );

        total.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(
                        String.format("₱ %.2f", cellData.getValue().getProduct().getProductPrice() * cellData.getValue().getQuantity())
                )
        );
        qty.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject()
        );

        qty.setCellFactory(column -> new TableCell<Item_Model, Integer>() {
            private HBox buttonBox = new HBox(10);
            private Button minusBtn = new Button("-");
            private Button plusBtn = new Button("+");
            private Label qty = new Label();

            {
                minusBtn.getStyleClass().add("circular-btn");
                plusBtn.getStyleClass().add("circular-btn");
                minusBtn.setStyle("-fx-min-width: 30; -fx-min-height: 30;");
                plusBtn.setStyle("-fx-min-width: 30; -fx-min-height: 30;");
                qty.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");


                buttonBox.getChildren().addAll(minusBtn, qty, plusBtn);


                minusBtn.setOnAction(event -> {
                    Item_Model item = getTableView().getItems().get(getIndex());
                    updateTableColumnQty(item, false); // decrease
                });

                plusBtn.setOnAction(event -> {
                    Item_Model item = getTableView().getItems().get(getIndex());
                    updateTableColumnQty(item, true); // increase
                });
            }

            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null); // clear cell if empty
                } else {
                    qty.setText(String.valueOf(item)); // update label
                    setGraphic(buttonBox); // show HBox
                }
            }
        });


    }

    /**
     * This method updates the quantity of an item in the cart table.
     * @param item is the item to update.
     * @param sign is true to increase quantity, false to decrease.
     */
    private void updateTableColumnQty(Item_Model item, boolean sign) {
        int newQty = item.getQuantity();
        if(sign){
            newQty = newQty + 1;

        }
        else if(!sign){
            newQty = newQty - 1;
        }

        // if quantity becomes 0 or negative, remove the item from cart
        if(newQty <= 0) {
            cart.removeItem(item.getProduct().getProductID());
        } else {
            cart.updateQuantity(item.getProduct(), newQty);
        }

        cartTable.refresh();
        updateAllTotals();
    }

    /**
     * This method calculates and updates all total labels (subtotal, VAT, total).
     */
    private void updateAllTotals()
    {
        double subTotal = cart.calculateCartSubTotal();

        double tax;
        double vat;
        double total;

        if (customer.isSenior()) {
            tax = 0.0;
        } else {
            tax = 0.12;
        }

        vat = subTotal * tax;
        total = subTotal + vat;

        subtotalLabel.setText(String.format("₱ %.2f", subTotal));
        vatLabel.setText(String.format("₱ %.2f", vat));
        totalLabel.setText(String.format("₱ %.2f", total));
    }

    /**
     * This method goes to the checkout view scene.
     * @param event is the action event triggered by the checkout button.
     */
    @FXML
    private void goToCheckout(ActionEvent event)
    {
        System.out.println("Checkout");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Transaction_View.fxml"));
            Parent root = loader.load();

            Transaction_Controller transactionController = loader.getController();
            transactionController.setData(customer, inventory);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns to the shopping view scene to view all products for sale.
     * @param event is the action event triggered by the button.
     */
    @FXML
    private void returnToProducts(ActionEvent event)
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Shopping_View.fxml"));
            Parent root = loader.load();

            // state management so the next scene knows which customer is shopping and what the inventory's state is
            Shopping_Controller shoppingController = loader.getController();
            shoppingController.setInventory(inventory);
            shoppingController.setCustomer(customer);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
