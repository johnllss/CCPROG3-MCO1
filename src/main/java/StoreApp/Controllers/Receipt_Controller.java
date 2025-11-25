package StoreApp.Controllers;

import StoreApp.Models.Cart_Model;
import StoreApp.Models.Customer_Model;
import StoreApp.Models.Item_Model;
import StoreApp.Models.Product_Model;
import StoreApp.Models.Receipt_Model;
import StoreApp.Models.Transaction_Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Receipt_Controller {
    @FXML private Label receiptNumberLabel;
    @FXML private Label timestampLabel;
    @FXML private Label customerNameLabel;
    @FXML private Label customerEmailLabel;
    @FXML private VBox itemsVBox;
    @FXML private Label subtotalLabel;
    @FXML private Label discountLabel;
    @FXML private Label taxLabel;
    @FXML private Label totalLabel;
    @FXML private Label paymentMethodLabel;
    @FXML private Label amountReceivedLabel;
    @FXML private Label changeLabel;
    @FXML private Label pointsEarnedLabel;
    @FXML private AnchorPane membershipPane;
    @FXML private Button newTransactionButton;

    private Receipt_Model receipt;

    /**
     * This method sets the receipt model and populates the receipt view.
     * @param receipt is the receipt model containing transaction details.
     */
    public void setReceipt(Receipt_Model receipt) {
        this.receipt = receipt;
        displayReceiptDetails();
    }

    /**
     * This method displays all the receipt details in the scene.
     */
    private void displayReceiptDetails() {
        if (receipt == null) {
            return;
        }

        Transaction_Model transaction = receipt.getTransaction();
        Customer_Model customer = transaction.getCustomer();
        Cart_Model cart = transaction.getCart();

        receiptNumberLabel.setText(receipt.getReceiptNumber());
        timestampLabel.setText(transaction.getTimestamp());

        displayCartItems(cart);

        subtotalLabel.setText(String.format("₱ %.2f", transaction.getSubtotal()));
        discountLabel.setText(String.format("₱ %.2f", transaction.getDiscount()));
        taxLabel.setText(String.format("₱ %.2f", transaction.getTax()));
        totalLabel.setText(String.format("₱ %.2f", transaction.getTotal()));
        
        totalLabel.setText(transaction.getPaymentMethod());
        amountReceivedLabel.setText(String.format("₱ %.2f", transaction.getAmountReceived()));
        changeLabel.setText(String.format("₱ %.2f", transaction.getChange()));

        if (customer.hasMembership()) {
            // 1 point per ₱50 spent
            int pointsEarned = (int)(transaction.getTotal() / 50);
            pointsEarnedLabel.setText(pointsEarned + " points");
            membershipPane.setVisible(true);
        } else {
            membershipPane.setVisible(false);
        }
    }

    /**
     * This method displays all items in the cart on the receipt.
     * @param cart is the cart containing the purchased items.
     */
    private void displayCartItems(Cart_Model cart) {
        itemsVBox.getChildren().clear();

        for (Item_Model item: cart.getItems()) {
            // extract necessary details to display
            Product_Model product = item.getProduct();
            int quantity = item.getQuantity();
            double price = product.getProductPrice();
            double itemTotalPrice = price * quantity;

            HBox itemRow = new HBox(10);
            itemRow.setPadding(new Insets(5, 0, 5, 0));

            Label quantityLabel = new Label(quantity + "x");
            quantityLabel.setPrefWidth(40);

            Label nameLabel = new Label(product.getProductName());
            nameLabel.setPrefWidth(250);

            Label priceLabel = new Label(String.format("₱ %.2f", price));
            priceLabel.setPrefWidth(80);

            Label totalLabel = new Label(String.format("₱ %.2f", itemTotalPrice));
            totalLabel.setPrefWidth(80);
            totalLabel.setStyle("-fx-font-weight: bold");

            itemRow.getChildren().addAll(quantityLabel, nameLabel, priceLabel, totalLabel);
            itemsVBox.getChildren().add(itemRow);
        }
    }

    /**
     * This method starts a new transaction by returning to the main menu.
     * @param event is the action event triggered by the button.
     */
    @FXML
    private void startNewTransaction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainMenu_View.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
