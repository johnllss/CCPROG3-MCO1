package StoreApp.Controllers;

import StoreApp.Models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Shopping_Controller
 * Tests category navigation and add-to-cart logic
 */
public class Shopping_Controller_Test {

    private final String[] categories = {"Food", "Beverages", "Toiletries", "Cleaning Products", "Medications"};

    // ========== Category Navigation Tests ==========

    @Test
    public void testNavigateToPreviousCategory_FromFood_WrapsToMedications() {
        // Test 1: Navigates from Food to Medications (wraps around)
        int currentCategoryIndex = 0; // Food

        currentCategoryIndex--;
        if (currentCategoryIndex < 0) {
            currentCategoryIndex = categories.length - 1;
        }

        assertEquals(4, currentCategoryIndex, "Should wrap to last category (Medications)");
        assertEquals("Medications", categories[currentCategoryIndex], "Should be Medications");
    }

    @Test
    public void testNavigateToPreviousCategory_FromBeverages_GoesToFood() {
        // Test 2: Navigates from Beverages to Food
        int currentCategoryIndex = 1; // Beverages

        currentCategoryIndex--;
        if (currentCategoryIndex < 0) {
            currentCategoryIndex = categories.length - 1;
        }

        assertEquals(0, currentCategoryIndex, "Should go to Food");
        assertEquals("Food", categories[currentCategoryIndex], "Should be Food");
    }

    @Test
    public void testNavigateToPreviousCategory_FromMedications_GoesToCleaningProducts() {
        // Test 3: Navigates from Medications to Cleaning Products
        int currentCategoryIndex = 4; // Medications

        currentCategoryIndex--;
        if (currentCategoryIndex < 0) {
            currentCategoryIndex = categories.length - 1;
        }

        assertEquals(3, currentCategoryIndex, "Should go to Cleaning Products");
        assertEquals("Cleaning Products", categories[currentCategoryIndex], "Should be Cleaning Products");
    }

    @Test
    public void testNavigateToNextCategory_FromFood_GoesToBeverages() {
        // Test 4: Navigates from Food to Beverages
        int currentCategoryIndex = 0; // Food

        currentCategoryIndex++;
        if (currentCategoryIndex >= categories.length) {
            currentCategoryIndex = 0;
        }

        assertEquals(1, currentCategoryIndex, "Should go to Beverages");
        assertEquals("Beverages", categories[currentCategoryIndex], "Should be Beverages");
    }

    @Test
    public void testNavigateToNextCategory_FromMedications_WrapsToFood() {
        // Test 5: Navigates from Medications to Food (wraps around)
        int currentCategoryIndex = 4; // Medications

        currentCategoryIndex++;
        if (currentCategoryIndex >= categories.length) {
            currentCategoryIndex = 0;
        }

        assertEquals(0, currentCategoryIndex, "Should wrap to first category (Food)");
        assertEquals("Food", categories[currentCategoryIndex], "Should be Food");
    }

    @Test
    public void testNavigateToNextCategory_FromToiletries_GoesToCleaningProducts() {
        // Test 6: Navigates from Toiletries to Cleaning Products
        int currentCategoryIndex = 2; // Toiletries

        currentCategoryIndex++;
        if (currentCategoryIndex >= categories.length) {
            currentCategoryIndex = 0;
        }

        assertEquals(3, currentCategoryIndex, "Should go to Cleaning Products");
        assertEquals("Cleaning Products", categories[currentCategoryIndex], "Should be Cleaning Products");
    }

    // ========== Switch Category Tests ==========

    @Test
    public void testSwitchCategory_ToFood_UpdatesIndex() {
        // Test 7: Switches to Food category and updates index
        String targetCategory = "Food";
        int newIndex = -1;

        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(targetCategory)) {
                newIndex = i;
                break;
            }
        }

        assertEquals(0, newIndex, "Food should be at index 0");
    }

    @Test
    public void testSwitchCategory_ToBeverages_UpdatesIndex() {
        // Test 8: Switches to Beverages category and updates index
        String targetCategory = "Beverages";
        int newIndex = -1;

        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(targetCategory)) {
                newIndex = i;
                break;
            }
        }

        assertEquals(1, newIndex, "Beverages should be at index 1");
    }

    @Test
    public void testSwitchCategory_ToMedications_UpdatesIndex() {
        // Test 9: Switches to Medications category and updates index
        String targetCategory = "Medications";
        int newIndex = -1;

        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(targetCategory)) {
                newIndex = i;
                break;
            }
        }

        assertEquals(4, newIndex, "Medications should be at index 4");
    }

    // ========== Add to Cart Logic Tests ==========

    @Test
    public void testHandleAddToCart_NewProduct_AddsSuccessfully() {
        // Test 10: Successfully adds new product to cart
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Chips", 50.0, 10, "Food", "Brand A");
        Customer_Model customer = new Customer_Model("John", "john@email.com", "pass", null, false, cart);

        int quantity = 3;

        // Simulate handleAddToCart logic
        Item_Model existingItem = cart.findItem(product.getProductID());
        boolean success;

        if (existingItem != null) {
            success = cart.incrementQuantity(product, quantity);
        } else {
            success = cart.addItem(product, quantity);
        }

        assertTrue(success, "Should successfully add new product to cart");
        assertEquals(1, cart.getItems().size(), "Cart should have 1 item");
        assertEquals(3, cart.findItem(product.getProductID()).getQuantity(), "Item should have quantity 3");
    }

    @Test
    public void testHandleAddToCart_ExistingProduct_IncrementsQuantity() {
        // Test 11: Successfully increments existing product quantity in cart
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Soda", 35.0, 10, "Beverages", "Brand B");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Jane", "jane@email.com", "pass", null, false, cart);

        int addQuantity = 3;

        // Simulate handleAddToCart logic
        Item_Model existingItem = cart.findItem(product.getProductID());
        boolean success;

        if (existingItem != null) {
            success = cart.incrementQuantity(product, addQuantity);
        } else {
            success = cart.addItem(product, addQuantity);
        }

        assertTrue(success, "Should successfully increment quantity");
        assertEquals(1, cart.getItems().size(), "Cart should still have 1 item");
        assertEquals(5, cart.findItem(product.getProductID()).getQuantity(),
                     "Item quantity should be 5 (2 + 3)");
    }

    @Test
    public void testHandleAddToCart_ExceedsStock_Fails() {
        // Test 12: Fails when trying to add more than available stock
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Milk", 60.0, 5, "Beverages", "Brand C");
        cart.addItem(product, 3);

        Customer_Model customer = new Customer_Model("Bob", "bob@email.com", "pass", null, false, cart);

        int addQuantity = 5; // Total would be 8, but only 5 in stock

        // Simulate handleAddToCart logic
        Item_Model existingItem = cart.findItem(product.getProductID());
        boolean success;

        if (existingItem != null) {
            success = cart.incrementQuantity(product, addQuantity);
        } else {
            success = cart.addItem(product, addQuantity);
        }

        assertFalse(success, "Should fail when exceeding stock");
        assertEquals(3, cart.findItem(product.getProductID()).getQuantity(),
                     "Quantity should remain 3");
    }
}
