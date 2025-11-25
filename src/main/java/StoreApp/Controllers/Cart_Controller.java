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

    public void setInventory(Inventory_Model inventory) {
        this.inventory = inventory;
    }

    public void setCustomer(Customer_Model customer) {
        this.customer = customer;
        this.cart = customer.getCart();

        cartTable.setItems(cart.getItems());
        updateProductTableDisplay();
        updateAllTotals();
    }
    /*
    @FXML
    public void initialize()
    {
        updateProductBoxDisplay();
        updateAllTotals();
    }

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

    private void updateAllTotals()
    {
        double subTotal = cart.calculateCartSubTotal();
        double vat = subTotal * 0.10; // TODO: modify to include seniority validation
        double total = subTotal + vat;

        subtotalLabel.setText(String.format("₱ %.2f", subTotal));
        vatLabel.setText(String.format("₱ %.2f", vat));
        totalLabel.setText(String.format("₱ %.2f", total));
    }

    /**
     * This method goes to the checkout view scene.
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

    /**
     * This method is delegated the task of adding an item to the cart model.
     * @param product is the product that is to be added to the cart.
     * @param quantity is the number of products desired to be added to cart.
     * @return boolean to signify success/failure.
     */
    public boolean addItem(Product_Model product, int quantity) {
        return customer.getCart().addItem(product, quantity);
    }

    /**
     * This method is delegated the task of removing an item to the cart model.
     * @param productID is the ID of the product being removed.
     * @return boolean indicating success/failure of search.
     */
    public boolean removeItem(int productID) {
        return cart.removeItem(productID);
    }

    /**
     * This method is delegated the task of finding an item in the cart.
     * @param productID is the id of the product that needs to be found.
     * @return Item_Model that is searched.
     */
    public Item_Model findItem(int productID) {
        return cart.findItem(productID);
    }

    /**
     * This method is delegated the task of updating quantity of the item in the cart.
     * @param product id of the product that needs to be updated.
     * @param amount quantity of the product.
     * @return boolean, shows success or failure of the process.
     */
    public boolean updateQuantity(Product_Model product, int amount) {
        return cart.updateQuantity(product, amount);
    }
}
