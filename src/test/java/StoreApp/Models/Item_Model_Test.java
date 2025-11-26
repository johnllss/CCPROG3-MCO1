package StoreApp.Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Item_Model
 */
public class Item_Model_Test {

    @Test
    public void testCalculateItemSubtotal_WholeNumberPrice_ReturnsCorrectSubtotal() {
        // Test 1: Calculates subtotal for item with whole number price and quantity
        Product_Model product = new Product_Model("Chips", 50.0, 10, "Food", "Brand A");
        Item_Model item = new Item_Model(product, 3);

        assertEquals(150.0, item.calculateItemSubtotal(), 0.01,
                     "Subtotal should be 150.00 (50.00 * 3)");
    }

    @Test
    public void testCalculateItemSubtotal_DecimalPrice_ReturnsCorrectSubtotal() {
        // Test 2: Calculates subtotal for item with decimal price
        Product_Model product = new Product_Model("Candy", 25.50, 10, "Food", "Brand B");
        Item_Model item = new Item_Model(product, 2);

        assertEquals(51.0, item.calculateItemSubtotal(), 0.01,
                     "Subtotal should be 51.00 (25.50 * 2)");
    }

    @Test
    public void testCalculateItemSubtotal_SingleQuantity_ReturnsPrice() {
        // Test 3: Calculates subtotal for single quantity item
        Product_Model product = new Product_Model("Premium Item", 100.0, 5, "Food", "Brand C");
        Item_Model item = new Item_Model(product, 1);

        assertEquals(100.0, item.calculateItemSubtotal(), 0.01,
                     "Subtotal should be 100.00 (100.00 * 1)");
    }

    @Test
    public void testCalculateItemSubtotal_FractionalPrice_ReturnsCorrectSubtotal() {
        // Test 4: Calculates subtotal for item with fractional price
        Product_Model product = new Product_Model("Special", 33.33, 10, "Food", "Brand D");
        Item_Model item = new Item_Model(product, 3);

        assertEquals(99.99, item.calculateItemSubtotal(), 0.01,
                     "Subtotal should be 99.99 (33.33 * 3)");
    }

    @Test
    public void testCalculateItemSubtotal_ZeroQuantity_ReturnsZero() {
        // Test 5: Calculates subtotal for zero quantity (edge case)
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand E");
        Item_Model item = new Item_Model(product, 0);

        assertEquals(0.0, item.calculateItemSubtotal(), 0.01,
                     "Subtotal should be 0.00 (50.00 * 0)");
    }
}
