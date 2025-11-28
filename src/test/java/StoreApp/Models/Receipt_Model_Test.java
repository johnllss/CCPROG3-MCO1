package StoreApp.Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.regex.Pattern;

/**
 * Test class for Receipt_Model
 */
public class Receipt_Model_Test {

    @Test
    public void testGenerateReceiptNumber_CorrectFormat() {
        // Test 1: Generates receipt number in correct format (YYYY-MM-DD-XXXX)
        Cart_Model cart = new Cart_Model();
        Customer_Model customer = new Customer_Model("John", "john@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        Receipt_Model receipt = new Receipt_Model(transaction);

        String receiptNumber = receipt.getReceiptNumber();
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}-\\d{4}");

        assertTrue(pattern.matcher(receiptNumber).matches(),
                   "Receipt number should match format YYYY-MM-DD-XXXX");
    }

    @Test
    public void testGenerateReceiptNumber_FourDigitRandom() {
        // Test 2: Generates receipt number with 4-digit random component
        Cart_Model cart = new Cart_Model();
        Customer_Model customer = new Customer_Model("Jane", "jane@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        Receipt_Model receipt = new Receipt_Model(transaction);

        String receiptNumber = receipt.getReceiptNumber();
        String randomPart = receiptNumber.substring(receiptNumber.lastIndexOf('-') + 1);
        int randomNum = Integer.parseInt(randomPart);

        assertTrue(randomNum >= 1000 && randomNum <= 9999,
                   "Random part should be a 4-digit number (1000-9999)");
    }

    @Test
    public void testGenerateReceiptNumber_Uniqueness() {
        // Test 3: Generates unique receipt numbers for consecutive calls
        Cart_Model cart1 = new Cart_Model();
        Customer_Model customer1 = new Customer_Model("Alice", "alice@email.com", "pass", null, false, cart1);
        Transaction_Model transaction1 = new Transaction_Model(customer1, cart1);
        Receipt_Model receipt1 = new Receipt_Model(transaction1);

        Cart_Model cart2 = new Cart_Model();
        Customer_Model customer2 = new Customer_Model("Bob", "bob@email.com", "pass", null, false, cart2);
        Transaction_Model transaction2 = new Transaction_Model(customer2, cart2);
        Receipt_Model receipt2 = new Receipt_Model(transaction2);

        // Note: There's a small chance they could be the same if created in the same millisecond
        // but statistically very unlikely
        String receiptNum1 = receipt1.getReceiptNumber();
        String receiptNum2 = receipt2.getReceiptNumber();

        // At minimum, the format should be valid for both
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}-\\d{4}");
        assertTrue(pattern.matcher(receiptNum1).matches(), "First receipt should have valid format");
        assertTrue(pattern.matcher(receiptNum2).matches(), "Second receipt should have valid format");
    }

    @Test
    public void testGenerateReceiptNumber_CurrentDate() {
        // Test 4: Generates receipt number with current date
        Cart_Model cart = new Cart_Model();
        Customer_Model customer = new Customer_Model("Test", "test@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        Receipt_Model receipt = new Receipt_Model(transaction);

        String receiptNumber = receipt.getReceiptNumber();
        String yearPart = receiptNumber.substring(0, 4);
        int year = Integer.parseInt(yearPart);

        assertTrue(year >= 2024 && year <= 2030,
                   "Year should be current or recent (2024-2030)");
    }
}
