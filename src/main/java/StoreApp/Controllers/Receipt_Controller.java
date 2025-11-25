package StoreApp.Controllers;

import java.lang.classfile.Label;

import StoreApp.Models.Receipt_Model;
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
}
