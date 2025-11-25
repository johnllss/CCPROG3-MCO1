package StoreApp.Controllers;

import java.lang.classfile.Label;

import StoreApp.Models.Cart_Model;
import StoreApp.Models.Customer_Model;
import StoreApp.Models.Inventory_Model;
import StoreApp.Models.Transaction_Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Transaction_Controller {
    @FXML private TextField fullNameText;
    @FXML private TextField emailText;
    @FXML private CheckBox membershipCheckBox;
    @FXML private TextField membershipNumberText;
    @FXML private CheckBox seniorCheckBox;
    @FXML private TextField ageText;
    @FXML private CheckBox cashCheckBox;
    @FXML private CheckBox cardCheckBox;
    @FXML private TextField amountText;
    @FXML private TextField accountNumberText;
    @FXML private TextField cvvText;
    @FXML private TextField expiryDateText;
    @FXML private Label subtotalLabel;
    @FXML private Label taxLabel;
    @FXML private Label discountLabel;
    @FXML private Label totalLabel;
    @FXML private Button processPaymentBtn;
    @FXML private Button cancelBtn;

    private Customer_Model customer;
    private Cart_Model cart;
    private Inventory_Model inventory;
    private Transaction_Model transaction;

    /**
     * This method initializes the controller after FXML elements are loaded, setting up the initial state of UI elements.
     */
    @FXML
    public void initialize() {
        membershipNumberText.setDisable(true);
        ageText.setDisable(true);
        accountNumberText.setDisable(true);
        cvvText.setDisable(true);
        expiryDateText.setDisable(true);
    }

    /**
     * This method sets the customer, cart, and inventory from the previous scene.
     * @param customer is the customer making the purchase.
     * @param inventory is the store's inventory.
     */
    public void setData(Customer_Model customer, Inventory_Model inventory) {
        this.customer = customer;
        this.cart = customer.getCart();
        this.inventory = inventory;
        this.transaction = new Transaction_Model(customer, cart);

        updateTransactionSummary();
    }

    /**
     * This method handles the membership checkbox toggle.
     */
    @FXML
    private void toggleMembership() {
        membershipNumberText.setDisable(!membershipCheckBox.isSelected());

        if (!membershipCheckBox.isSelected()) {
            membershipNumberText.clear();
        }

        updateTransactionSummary();
    }

    /**
     * This method handles the senior citizen checkbox toggle.
     */
    @FXML
    private void toggleSenior() {
        ageText.setDisable(!seniorCheckBox.isSelected());

        if (!seniorCheckBox.isSelected()) {
            ageText.clear();
        }

        updateTransactionSummary();
    }
}