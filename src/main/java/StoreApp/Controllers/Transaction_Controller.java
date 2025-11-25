package StoreApp.Controllers;

import java.text.NumberFormat;

import StoreApp.Models.Cart_Model;
import StoreApp.Models.Customer_Model;
import StoreApp.Models.Inventory_Model;
import StoreApp.Models.MembershipCard_Model;
import StoreApp.Models.Receipt_Model;
import StoreApp.Controllers.Receipt_Controller;
import StoreApp.Models.Transaction_Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

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

    /**
     * This method handles the cash payment method selection.
     */
    @FXML
    private void selectCashPayment() {
        if (cashCheckBox.isSelected()) {
            // make amount field of cash inputtable
            amountText.setDisable(false);
            
            // unselect card checkbox
            cardCheckBox.setSelected(false);
            
            // disable inputting in card fields and clear previous inputs
            accountNumberText.setDisable(true);
            cvvText.setDisable(true);;
            expiryDateText.setDisable(true);
            accountNumberText.clear();
            cvvText.clear();
            expiryDateText.clear();
        } else { 
            // if unselected, disable and clear amount field
            amountText.setDisable(true);
            amountText.clear();
        }
    }

    /**
     * This method handles the card payment method selection.
     */
    @FXML
    private void selectCardPayment() {
        if (cardCheckBox.isSelected()) {
            // enable all card fields
            accountNumberText.setDisable(false);
            cvvText.setDisable(false);
            expiryDateText.setDisable(false);
            
            // unselect cash checkbox
            cashCheckBox.setSelected(false);

            // disable inputting in cash's amount field and clear previous inputs
            amountText.setDisable(true);
            amountText.clear();
        } else {
            // if unselected, disable and clear all card fields
            accountNumberText.setDisable(true);
            cvvText.setDisable(true);
            expiryDateText.setDisable(true);
            accountNumberText.clear();
            cvvText.clear();
            expiryDateText.clear();
        }
    }

    /**
     * This method updates the transaction summary display: subtotal, tax (VAT), discount, and total.
     */
    private void updateTransactionSummary() {
        if (transaction == null) {
            return;
        }

        double subtotal = transaction.calculateSubtotal();

        int pointsToUse = 0;

        if (membershipCheckBox.isSelected() && !membershipNumberText.getText().isEmpty()) {
            customer.setMembershipCard(new MembershipCard_Model(membershipNumberText.getText()));

            /* TODO should have a scenario where the user is a new member. generateCardNumber() might need to be inside the MembershipCard_Model controller to auto-generate the card number.

            TODO also, there needs to be a validation of the card to determine if the customer is already a member. Or, perhaps, just put checkboxes to ask to user if they are a current or new member.
            */
        }

        if (seniorCheckBox.isSelected() && !ageText.getText().isEmpty()) {
            try {
                int age = Integer.parseInt(ageText.getText());
                customer.setSenior(age >= 60);
            } catch (NumberFormatException e) {
                customer.setSenior(false);
            }
        } else {
            customer.setSenior(false);
        }

        double discount = transaction.calculateDiscount(pointsToUse);
        double tax = transaction.calculateTax();
        double total = transaction.calculateTotal();

        subtotalLabel.setText(String.format("₱ %.2f", subtotal));
        discountLabel.setText(String.format("₱ %.2f", discount));
        taxLabel.setText(String.format("₱ %.2f", tax));
        totalLabel.setText(String.format("₱ %.2f", total));
    }

    /**
     * This method processes the payment and completes the transaction.
     * @param event is the action event triggered by the button.
     */
    @FXML
    private void processPayment(ActionEvent event) {
        if (!validatePaymentInputs()) {
            return;
        }

        // if cash payment, process cash info
        if (cashCheckBox.isSelected()) {
            try {
                double amountReceived = Double.parseDouble(amountText.getText());
                transaction.setAmountReceived(amountReceived);
                transaction.setPaymentMethod("Cash");

                if (amountReceived < transaction.getTotal()) {
                    popupAlert(Alert.AlertType.ERROR, "Insufficient Amount Received", "Amount receieved is less than the total price of your transaction. Please enter a valid amount.");

                    return;
                }

                // payment success, get change
                double change = transaction.calculateChange();

                popupAlert(Alert.AlertType.INFORMATION, "Payment Successful!", String.format("Your change: ₱ %.2f", change));
            } catch (NumberFormatException e) {
                popupAlert(Alert.AlertType.ERROR, "Invalid Amount", "Please enter a valid amount.");

                return;
            }
        } else if (cardCheckBox.isSelected()) {
            // if card payment, process
            transaction.setAmountReceived(transaction.getTotal()); // amount received is the total price since it's a card payment
            transaction.setPaymentMethod("Card");

            popupAlert(Alert.AlertType.INFORMATION, "Payment Successful!", "Your card payment was processed succesfully.");
        }

        // if customer has membership, show points earned
        if (customer.hasMembership()) {
            int pointsEarned = transaction.calculateMembershipPoints();

            popupAlert(Alert.AlertType.INFORMATION, "Points Earned", String.format("You earned %d membership points from this transaction!", pointsEarned));
        }

        // validation of individual items in the cart for stock in inventory
        if (!inventory.operateCartPurchase(cart)) {
            popupAlert(Alert.AlertType.ERROR, "Transaction Failed", "Your transaction failed to process. Please try again.");

            return;
        }

        goToReceipt(event);
    }


    /**
     * This method validates all user inputs before processing payment.
     * @return boolean indicating if all inputs are valid.
     */
    private boolean validatePaymentInputs() {
        if (fullNameText.getText().isEmpty()) {
            popupAlert(Alert.AlertType.ERROR, "Missing Information", "Please enter your full name.");

            return false;
        }

        if (emailText.getText().isEmpty()) {
            popupAlert(Alert.AlertType.ERROR, "Missing Information", "Please enter your email.");

            return false;
        }

        if (membershipCheckBox.isSelected() && membershipNumberText.getText().isEmpty()) {
            popupAlert(Alert.AlertType.ERROR, "Missing Information", "Please enter your membership number.");

            return false;
        }

        if (seniorCheckBox.isScaleShape()) {
            if (ageText.getText().isEmpty()) {
                popupAlert(Alert.AlertType.ERROR, "Missing Information", "Please enter your age.");

                return false;
            }

            try {
                int age = Integer.parseInt(ageText.getText());

                if (age < 60) {
                    popupAlert(Alert.AlertType.ERROR, "Invalid Age Input", "Senior citizen discount requires 60 or above.");

                    return false;
                }
            } catch (NumberFormatException e) {
                    popupAlert(Alert.AlertType.ERROR, "Invalid Age Input", "Please enter a valid age.");

                    return false;
            }
        }

        if (!cashCheckBox.isSelected() && !cardCheckBox.isSelected()) {
            popupAlert(Alert.AlertType.ERROR, "Missing Payment Method", "Please select a payment method.");

            return false;
        }

        if (cardCheckBox.isSelected()) {
            if (accountNumberText.getText().isEmpty() || cvvText.getText().isEmpty() || expiryDateText.getText().isEmpty()) {
                popupAlert(Alert.AlertType.ERROR, "Missing Card Information", "Please fill in all card details.");

                return false;
            }
        }

        return true;
    }

    /**
     * This method displays an alert dialog to the user for errors.
     * @param alert is the type of alert.
     * @param title is the title of the alert.
     * @param message is the message to display.
     */
    private void popupAlert(Alert.AlertType alert, String title, String message) {
        Alert actualAlert = new Alert(alert);
        actualAlert.setTitle(title);
        actualAlert.setHeaderText(null);
        actualAlert.setContentText(message);
        actualAlert.showAndWait();
    }

    /**
     * This method cancels the transaction and returns to the cart view.
     * @param event is the action event triggered by the button.
     */
    @FXML
    private void cancelTransaction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Transaction");
        alert.setHeaderText("Are you sure you want to cancel this transaction?");
        alert.setContentText("This will return you to the cart.");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Cart_View.fxml"));
                Parent root = loader.load();

                Cart_Controller cartController = loader.getController();
                cartController.setInventory(inventory);
                cartController.setCustomer(customer);

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void goToReceipt(ActionEvent event) {
        try {
            Receipt_Model receipt = new Receipt_Model(transaction);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Receipt_View.fxml"));
            Parent root = loader.load();

            Receipt_Controller receiptController = loader.getController();
            receiptController.setReceipt(receipt);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            popupAlert(Alert.AlertType.ERROR, "Navigation Error", "Unable to load receipt. Error: " + e.getMessage());
        }
    }
}