package StoreApp.Models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

/**
 * Test class for Product_Model
 */
public class Product_Model_Test {

    // ========== reduceStock Tests ==========

    @Test
    public void testReduceStock_LessThanQuantity_ReturnsTrue() {
        // Test 1: Reduces stock when amount is less than current quantity
        Product_Model product = new Product_Model("Chips", 50.0, 10, "Food", "Brand A");

        assertTrue(product.reduceStock(5), "Should successfully reduce stock by 5");
        assertEquals(5, product.getProductQuantity(), "Quantity should be 5 after reduction");
    }

    @Test
    public void testReduceStock_EqualsQuantity_ReturnsTrue() {
        // Test 2: Reduces stock when amount equals current quantity
        Product_Model product = new Product_Model("Chips", 50.0, 10, "Food", "Brand A");

        assertTrue(product.reduceStock(10), "Should successfully reduce stock to 0");
        assertEquals(0, product.getProductQuantity(), "Quantity should be 0 after reduction");
    }

    @Test
    public void testReduceStock_ExceedsQuantity_ReturnsFalse() {
        // Test 3: Fails to reduce stock when amount exceeds current quantity
        Product_Model product = new Product_Model("Chips", 50.0, 10, "Food", "Brand A");

        assertFalse(product.reduceStock(15), "Should fail to reduce stock by 15");
        assertEquals(10, product.getProductQuantity(), "Quantity should remain unchanged");
    }

    @Test
    public void testReduceStock_EdgeCaseOneToZero_ReturnsTrue() {
        // Test 4: Handles edge case of reducing by 1 from stock of 1
        Product_Model product = new Product_Model("Chips", 50.0, 1, "Food", "Brand A");

        assertTrue(product.reduceStock(1), "Should successfully reduce stock from 1 to 0");
        assertEquals(0, product.getProductQuantity(), "Quantity should be 0");
    }

    // ========== updateStock Tests ==========

    @Test
    public void testUpdateStock_ValidPositive_ReturnsTrue() {
        // Test 1: Successfully updates stock with valid positive quantity
        Product_Model product = new Product_Model("Chips", 50.0, 10, "Food", "Brand A");

        assertTrue(product.updateStock(5), "Should successfully update stock");
        assertEquals(15, product.getProductQuantity(), "Quantity should be 15 after update");
    }

    @Test
    public void testUpdateStock_Zero_ReturnsTrue() {
        // Test 2: Successfully updates stock with zero quantity
        Product_Model product = new Product_Model("Chips", 50.0, 10, "Food", "Brand A");

        assertTrue(product.updateStock(0), "Should successfully update with 0");
        assertEquals(10, product.getProductQuantity(), "Quantity should remain 10");
    }

    @Test
    public void testUpdateStock_Negative_ReturnsFalse() {
        // Test 3: Fails to update stock with negative quantity
        Product_Model product = new Product_Model("Chips", 50.0, 10, "Food", "Brand A");

        assertFalse(product.updateStock(-5), "Should fail to update with negative value");
        assertEquals(10, product.getProductQuantity(), "Quantity should remain unchanged");
    }

    @Test
    public void testUpdateStock_FromZero_ReturnsTrue() {
        // Test 4: Successfully updates stock from zero to positive quantity
        Product_Model product = new Product_Model("Chips", 50.0, 0, "Food", "Brand A");

        assertTrue(product.updateStock(20), "Should successfully restock from 0 to 20");
        assertEquals(20, product.getProductQuantity(), "Quantity should be 20");
    }

    // ========== isExpired Tests ==========

    @Test
    public void testIsExpired_PastDate_ReturnsTrue() {
        // Test 1: Determines that perishable product with past expiration date is expired
        Product_Model product = new Product_Model("Milk", 60.0, 10, "Beverages", "Brand B",
                                                   "", LocalDate.of(2024, 1, 1), "");

        assertTrue(product.isExpired(), "Product with past expiration date should be expired");
    }

    @Test
    public void testIsExpired_FutureDate_ReturnsFalse() {
        // Test 2: Determines that perishable product with future expiration date is not expired
        Product_Model product = new Product_Model("Milk", 60.0, 10, "Beverages", "Brand B",
                                                   "", LocalDate.of(2026, 12, 31), "");

        assertFalse(product.isExpired(), "Product with future expiration date should not be expired");
    }

    @Test
    public void testIsExpired_NonPerishable_ReturnsFalse() {
        // Test 3: Determines that non-perishable product (null expiration) is not expired
        Product_Model product = new Product_Model("Shampoo", 100.0, 10, "Toiletries", "Brand C");

        assertFalse(product.isExpired(), "Non-perishable product should not be expired");
    }

    @Test
    public void testIsExpired_Today_ReturnsFalse() {
        // Test 4: Determines that perishable product expiring today is not expired
        Product_Model product = new Product_Model("Bread", 40.0, 10, "Food", "Brand D",
                                                   "", LocalDate.now(), "");

        assertFalse(product.isExpired(), "Product expiring today should not be considered expired");
    }

    // ========== isPerishable Tests ==========

    @Test
    public void testIsPerishable_WithExpirationDate_ReturnsTrue() {
        // Test 1: Determines that product with expiration date is perishable
        Product_Model product = new Product_Model("Yogurt", 45.0, 10, "Food", "Brand E",
                                                   "", LocalDate.of(2025, 12, 31), "");

        assertTrue(product.isPerishable(), "Product with expiration date should be perishable");
    }

    @Test
    public void testIsPerishable_WithoutExpirationDate_ReturnsFalse() {
        // Test 2: Determines that product without expiration date is not perishable
        Product_Model product = new Product_Model("Soap", 35.0, 10, "Toiletries", "Brand F");

        assertFalse(product.isPerishable(), "Product without expiration date should not be perishable");
    }

    @Test
    public void testIsPerishable_WithPastDate_ReturnsTrue() {
        // Test 3: Determines that product with past expiration date is still perishable
        Product_Model product = new Product_Model("Expired Milk", 60.0, 10, "Beverages", "Brand G",
                                                   "", LocalDate.of(2020, 1, 1), "");

        assertTrue(product.isPerishable(), "Product with past expiration date should still be perishable");
    }

    // ========== isProductLowStock Tests ==========

    @Test
    public void testIsProductLowStock_LessThan3_ReturnsTrue() {
        // Test 1: Determines that product with quantity less than 3 is low stock
        Product_Model product = new Product_Model("Item", 50.0, 2, "Food", "Brand H");

        assertTrue(product.isProductLowStock(), "Product with quantity 2 should be low stock");
    }

    @Test
    public void testIsProductLowStock_Equals3_ReturnsFalse() {
        // Test 2: Determines that product with quantity of 3 or more is not low stock
        Product_Model product = new Product_Model("Item", 50.0, 3, "Food", "Brand H");

        assertFalse(product.isProductLowStock(), "Product with quantity 3 should not be low stock");
    }

    @Test
    public void testIsProductLowStock_Zero_ReturnsTrue() {
        // Test 3: Determines that product with zero quantity is low stock
        Product_Model product = new Product_Model("Item", 50.0, 0, "Food", "Brand H");

        assertTrue(product.isProductLowStock(), "Product with quantity 0 should be low stock");
    }

    @Test
    public void testIsProductLowStock_One_ReturnsTrue() {
        // Test 4: Determines that product with exactly 1 quantity is low stock
        Product_Model product = new Product_Model("Item", 50.0, 1, "Food", "Brand H");

        assertTrue(product.isProductLowStock(), "Product with quantity 1 should be low stock");
    }

    @Test
    public void testIsProductLowStock_HighQuantity_ReturnsFalse() {
        // Test 5: Determines that product with high quantity is not low stock
        Product_Model product = new Product_Model("Item", 50.0, 100, "Food", "Brand H");

        assertFalse(product.isProductLowStock(), "Product with quantity 100 should not be low stock");
    }

    // ========== ProductQuantity Tests ==========

    @Test
    public void testProductQuantity_LessThanStock_ReturnsTrue() {
        // Test 1: Validates that requested quantity is available when less than stock
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand I");

        assertTrue(product.ProductQuantity(5), "Requested quantity 5 should be available when stock is 10");
    }

    @Test
    public void testProductQuantity_ExceedsStock_ReturnsFalse() {
        // Test 2: Validates that requested quantity is not available when exceeds stock
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand I");

        assertFalse(product.ProductQuantity(15), "Requested quantity 15 should not be available when stock is 10");
    }

    @Test
    public void testProductQuantity_EqualsStock_ReturnsTrue() {
        // Test 3: Validates that requested quantity is available when equals stock
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand I");

        assertTrue(product.ProductQuantity(10), "Requested quantity 10 should be available when stock is 10");
    }

    @Test
    public void testProductQuantity_Zero_ReturnsTrue() {
        // Test 4: Validates zero quantity request
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand I");

        assertTrue(product.ProductQuantity(0), "Zero quantity request should be valid");
    }
}
