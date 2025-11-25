package StoreApp.Controllers;

import javafx.scene.control.Label;

import StoreApp.Models.Cart_Model;
import StoreApp.Models.Customer_Model;
import StoreApp.Models.Receipt_Model;
import StoreApp.Models.Transaction_Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    
}
