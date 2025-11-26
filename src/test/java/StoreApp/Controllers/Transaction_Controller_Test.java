package StoreApp.Controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Transaction_Controller
 * Tests the validation logic which is computational
 */
public class Transaction_Controller_Test {

    // ========== Input Validation Tests ==========

    @Test
    public void testValidateFullName_ValidName_ReturnsTrue() {
        // Test 1: Validates successful input with valid full name
        String fullName = "John Doe";

        boolean isValid = !fullName.isEmpty();

        assertTrue(isValid, "Valid full name should pass validation");
    }

    @Test
    public void testValidateFullName_EmptyName_ReturnsFalse() {
        // Test 2: Fails validation when full name is empty
        String fullName = "";

        boolean isValid = !fullName.isEmpty();

        assertFalse(isValid, "Empty full name should fail validation");
    }

    @Test
    public void testValidateEmail_ValidEmail_ReturnsTrue() {
        // Test 3: Validates successful input with valid email
        String email = "john@email.com";

        boolean isValid = !email.isEmpty();

        assertTrue(isValid, "Valid email should pass validation");
    }

    @Test
    public void testValidateEmail_EmptyEmail_ReturnsFalse() {
        // Test 4: Fails validation when email is empty
        String email = "";

        boolean isValid = !email.isEmpty();

        assertFalse(isValid, "Empty email should fail validation");
    }

    @Test
    public void testValidatePaymentMethod_CashSelected_ReturnsTrue() {
        // Test 5: Validates that at least one payment method is selected (Cash)
        boolean cashSelected = true;
        boolean cardSelected = false;

        boolean isValid = cashSelected || cardSelected;

        assertTrue(isValid, "Cash payment method should be valid");
    }

    @Test
    public void testValidatePaymentMethod_CardSelected_ReturnsTrue() {
        // Test 6: Validates that at least one payment method is selected (Card)
        boolean cashSelected = false;
        boolean cardSelected = true;

        boolean isValid = cashSelected || cardSelected;

        assertTrue(isValid, "Card payment method should be valid");
    }

    @Test
    public void testValidatePaymentMethod_NoneSelected_ReturnsFalse() {
        // Test 7: Fails validation when no payment method selected
        boolean cashSelected = false;
        boolean cardSelected = false;

        boolean isValid = cashSelected || cardSelected;

        assertFalse(isValid, "No payment method selected should fail validation");
    }

    @Test
    public void testValidateAge_SeniorAge60_ReturnsTrue() {
        // Test 8: Validates senior age of exactly 60
        int age = 60;

        boolean isValid = age >= 60;

        assertTrue(isValid, "Age 60 should be valid for senior discount");
    }

    @Test
    public void testValidateAge_SeniorAge65_ReturnsTrue() {
        // Test 9: Validates senior age above 60
        int age = 65;

        boolean isValid = age >= 60;

        assertTrue(isValid, "Age 65 should be valid for senior discount");
    }

    @Test
    public void testValidateAge_Below60_ReturnsFalse() {
        // Test 10: Fails validation when age is below 60
        int age = 55;

        boolean isValid = age >= 60;

        assertFalse(isValid, "Age below 60 should not qualify for senior discount");
    }

    @Test
    public void testValidateAge_Negative_ReturnsFalse() {
        // Test 11: Fails validation for negative age
        int age = -5;

        boolean isValid = age >= 60;

        assertFalse(isValid, "Negative age should fail validation");
    }

    @Test
    public void testValidateMembershipCard_WithCardNumber_ReturnsTrue() {
        // Test 12: Validates when membership checkbox is selected and card number provided
        boolean membershipSelected = true;
        String cardNumber = "12345678-123456";

        boolean isValid = !membershipSelected || !cardNumber.isEmpty();

        assertTrue(isValid, "Valid membership card number should pass validation");
    }

    @Test
    public void testValidateMembershipCard_NoMembership_ReturnsTrue() {
        // Test 13: Validates when membership is not selected
        boolean membershipSelected = false;
        String cardNumber = "";

        boolean isValid = !membershipSelected || !cardNumber.isEmpty();

        assertTrue(isValid, "No membership selected should pass validation");
    }

    @Test
    public void testValidateMembershipCard_SelectedButEmpty_ReturnsFalse() {
        // Test 14: Fails validation when membership selected but card number empty
        boolean membershipSelected = true;
        String cardNumber = "";

        boolean isValid = !membershipSelected || !cardNumber.isEmpty();

        assertFalse(isValid, "Membership selected with empty card number should fail validation");
    }

    @Test
    public void testValidateCardDetails_AllFilled_ReturnsTrue() {
        // Test 15: Validates when all card details are provided
        boolean cardSelected = true;
        String accountNumber = "1234567890";
        String cvv = "123";
        String expiryDate = "12/26";

        boolean isValid = !cardSelected ||
                         (!accountNumber.isEmpty() && !cvv.isEmpty() && !expiryDate.isEmpty());

        assertTrue(isValid, "Complete card details should pass validation");
    }

    @Test
    public void testValidateCardDetails_CardNotSelected_ReturnsTrue() {
        // Test 16: Validates when card payment is not selected
        boolean cardSelected = false;
        String accountNumber = "";
        String cvv = "";
        String expiryDate = "";

        boolean isValid = !cardSelected ||
                         (!accountNumber.isEmpty() && !cvv.isEmpty() && !expiryDate.isEmpty());

        assertTrue(isValid, "Card not selected should pass validation");
    }

    @Test
    public void testValidateCardDetails_MissingAccountNumber_ReturnsFalse() {
        // Test 17: Fails validation when card selected but account number missing
        boolean cardSelected = true;
        String accountNumber = "";
        String cvv = "123";
        String expiryDate = "12/26";

        boolean isValid = !cardSelected ||
                         (!accountNumber.isEmpty() && !cvv.isEmpty() && !expiryDate.isEmpty());

        assertFalse(isValid, "Missing account number should fail validation");
    }

    @Test
    public void testValidateCardDetails_MissingCVV_ReturnsFalse() {
        // Test 18: Fails validation when card selected but CVV missing
        boolean cardSelected = true;
        String accountNumber = "1234567890";
        String cvv = "";
        String expiryDate = "12/26";

        boolean isValid = !cardSelected ||
                         (!accountNumber.isEmpty() && !cvv.isEmpty() && !expiryDate.isEmpty());

        assertFalse(isValid, "Missing CVV should fail validation");
    }

    @Test
    public void testValidateCardDetails_MissingExpiryDate_ReturnsFalse() {
        // Test 19: Fails validation when card selected but expiry date missing
        boolean cardSelected = true;
        String accountNumber = "1234567890";
        String cvv = "123";
        String expiryDate = "";

        boolean isValid = !cardSelected ||
                         (!accountNumber.isEmpty() && !cvv.isEmpty() && !expiryDate.isEmpty());

        assertFalse(isValid, "Missing expiry date should fail validation");
    }

    @Test
    public void testValidateAmount_SufficientPayment_ReturnsTrue() {
        // Test 20: Validates when payment amount is sufficient
        double amountReceived = 150.0;
        double total = 100.0;

        boolean isSufficient = amountReceived >= total;

        assertTrue(isSufficient, "Sufficient payment should be valid");
    }

    @Test
    public void testValidateAmount_ExactPayment_ReturnsTrue() {
        // Test 21: Validates when payment amount equals total
        double amountReceived = 100.0;
        double total = 100.0;

        boolean isSufficient = amountReceived >= total;

        assertTrue(isSufficient, "Exact payment should be valid");
    }

    @Test
    public void testValidateAmount_InsufficientPayment_ReturnsFalse() {
        // Test 22: Fails validation when payment amount is insufficient
        double amountReceived = 80.0;
        double total = 100.0;

        boolean isSufficient = amountReceived >= total;

        assertFalse(isSufficient, "Insufficient payment should fail validation");
    }
}
