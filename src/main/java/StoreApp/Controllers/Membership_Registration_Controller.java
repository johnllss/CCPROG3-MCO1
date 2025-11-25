package StoreApp.Controllers;

import StoreApp.Models.Customer_Model;
import StoreApp.Models.MembershipCard_Model;
import StoreApp.Models.MembershipDataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * Controller for the Membership Registration Dialog.
 * Handles new member registration with validation and duplicate detection.
 */
public class Membership_Registration_Controller {
    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField cardNumberField;
    @FXML private Button registerBtn;
    @FXML private Button cancelBtn;

    private MembershipDataManager dataManager;
    private String generatedCardNumber;

    /**
     * Sets initial data for the form (pre-populated from Transaction_View)
     * @param name the customer's full name
     * @param email the customer's email
     * @param manager the membership data manager instance
     */
    public void setData(String name, String email, MembershipDataManager manager) {
        this.dataManager = manager;
        fullNameField.setText(name);
        emailField.setText(email);

        // Generate card number immediately for preview
        this.generatedCardNumber = dataManager.generateUniqueCardNumber();
        cardNumberField.setText(generatedCardNumber);
    }

    /**
     * Handles member registration
     * @param event the action event triggered by the register button
     */
    @FXML
    private void registerMember(ActionEvent event) {
        String name = fullNameField.getText().trim();
        String email = emailField.getText().trim();

        // Validation
        if (name.isEmpty() || email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing Information",
                "Please fill in all fields.");
            return;
        }

        // Email format validation (basic)
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Email",
                "Please enter a valid email address.");
            return;
        }

        // Double-check for duplicates
        Customer_Model existingMember = dataManager.findByNameAndEmail(name, email);
        if (existingMember != null) {
            showAlert(Alert.AlertType.WARNING, "Already Registered",
                "This name/email combination is already registered.\nCard: " +
                existingMember.getMembershipCard().getCardNumber());
            return;
        }

        try {
            // Create new customer with membership
            MembershipCard_Model newCard = new MembershipCard_Model(generatedCardNumber, LocalDate.now());
            Customer_Model newMember = new Customer_Model(
                name,
                email,
                "", // password not used in this context
                newCard,
                false, // not senior
                null  // cart not needed for registration
            );

            // Save to file
            boolean success = dataManager.addMember(newMember);

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success!",
                    "Registration successful!\nYour card number is: " + generatedCardNumber);
                closeModal(event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Registration Failed",
                    "Unable to save membership. Please try again.");
                generatedCardNumber = null; // Clear on failure
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error",
                "An error occurred during registration: " + e.getMessage());
            generatedCardNumber = null;
        }
    }

    /**
     * Cancels registration and closes modal
     * @param event the action event triggered by the cancel button
     */
    @FXML
    private void cancelRegistration(ActionEvent event) {
        generatedCardNumber = null; // Signal cancellation
        closeModal(event);
    }

    /**
     * Gets the generated card number (null if cancelled)
     * @return the generated card number or null
     */
    public String getGeneratedCardNumber() {
        return generatedCardNumber;
    }

    /**
     * Shows alert dialog
     * @param type the alert type
     * @param title the alert title
     * @param message the alert message
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Closes the modal window
     * @param event the action event
     */
    private void closeModal(ActionEvent event) {
        Stage stage = (Stage) registerBtn.getScene().getWindow();
        stage.close();
    }
}
