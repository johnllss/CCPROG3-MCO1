package StoreApp.Models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Cart_Model
 */
public class Cart_Model_Test {

    // ========== isEmpty Tests ==========

    @Test
    public void testIsEmpty_NewCart_ReturnsTrue() {
        // Test 1: Determines that newly created cart is empty
        Cart_Model cart = new Cart_Model();

        assertTrue(cart.isEmpty(), "Newly created cart should be empty");
    }

    @Test
    public void testIsEmpty_WithItems_ReturnsFalse() {
        // Test 2: Determines that cart with items is not empty
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand A");
        cart.addItem(product, 1);

        assertFalse(cart.isEmpty(), "Cart with items should not be empty");
    }

    @Test
    public void testIsEmpty_AfterClearing_ReturnsTrue() {
        // Test 3: Determines that cart after clearing all items is empty
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand A");
        cart.addItem(product, 1);
        cart.clearCart();

        assertTrue(cart.isEmpty(), "Cart should be empty after clearing");
    }

    // ========== addItem Tests ==========

    @Test
    public void testAddItem_NewProductValidQuantity_ReturnsTrue() {
        // Test 1: Successfully adds new product to empty cart with valid quantity
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand B");

        assertTrue(cart.addItem(product, 3), "Should successfully add new product with valid quantity");
        assertEquals(1, cart.getItems().size(), "Cart should have 1 item");
    }

    @Test
    public void testAddItem_ExceedsStock_ReturnsFalse() {
        // Test 2: Fails to add product when requested quantity exceeds available stock
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand C");

        assertFalse(cart.addItem(product, 15), "Should fail to add product when quantity exceeds stock");
        assertTrue(cart.isEmpty(), "Cart should remain empty");
    }

    @Test
    public void testAddItem_ProductAlreadyExists_UpdatesQuantity() {
        // Test 3: Updates quantity when product already exists in cart
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand D");
        cart.addItem(product, 2);

        assertTrue(cart.addItem(product, 5), "Should update quantity when product already exists");
        assertEquals(5, cart.findItem(product.getProductID()).getQuantity(),
                     "Quantity should be updated to 5");
    }

    @Test
    public void testAddItem_ExactlyAvailableStock_ReturnsTrue() {
        // Test 4: Successfully adds product with exactly available stock quantity
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand E");

        assertTrue(cart.addItem(product, 10), "Should add product with exactly available stock");
        assertEquals(10, cart.findItem(product.getProductID()).getQuantity(),
                     "Quantity should be 10");
    }

    // ========== removeItem Tests ==========

    @Test
    public void testRemoveItem_ExistingItem_ReturnsTrue() {
        // Test 1: Successfully removes existing item from cart
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand F");
        cart.addItem(product, 3);

        assertTrue(cart.removeItem(product.getProductID()), "Should successfully remove existing item");
        assertTrue(cart.isEmpty(), "Cart should be empty after removal");
    }

    @Test
    public void testRemoveItem_NonExistentItem_ReturnsFalse() {
        // Test 2: Fails to remove non-existent item from cart
        Cart_Model cart = new Cart_Model();

        assertFalse(cart.removeItem(999), "Should fail to remove non-existent item");
    }

    @Test
    public void testRemoveItem_MultipleItems_ReturnsTrue() {
        // Test 3: Successfully removes item from cart with multiple items
        Cart_Model cart = new Cart_Model();
        Product_Model p1 = new Product_Model("Item1", 50.0, 10, "Food", "Brand G");
        Product_Model p2 = new Product_Model("Item2", 60.0, 10, "Food", "Brand G");
        cart.addItem(p1, 2);
        cart.addItem(p2, 3);

        assertTrue(cart.removeItem(p2.getProductID()), "Should remove item from multi-item cart");
        assertEquals(1, cart.getItems().size(), "Cart should have 1 item remaining");
    }

    // ========== findItem Tests ==========

    @Test
    public void testFindItem_ExistingItem_ReturnsItem() {
        // Test 1: Finds existing item in cart by product ID
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand H");
        cart.addItem(product, 3);

        Item_Model foundItem = cart.findItem(product.getProductID());
        assertNotNull(foundItem, "Should find existing item");
        assertEquals(product.getProductID(), foundItem.getProduct().getProductID(),
                     "Found item should have correct product ID");
    }

    @Test
    public void testFindItem_NonExistentItem_ReturnsNull() {
        // Test 2: Returns null for non-existent item in cart
        Cart_Model cart = new Cart_Model();

        assertNull(cart.findItem(999), "Should return null for non-existent item");
    }

    @Test
    public void testFindItem_MultipleItems_ReturnsCorrectItem() {
        // Test 3: Finds correct item in cart with multiple items
        Cart_Model cart = new Cart_Model();
        Product_Model p1 = new Product_Model("Item1", 50.0, 10, "Food", "Brand I");
        Product_Model p2 = new Product_Model("Item2", 60.0, 10, "Food", "Brand I");
        Product_Model p3 = new Product_Model("Item3", 70.0, 10, "Food", "Brand I");
        cart.addItem(p1, 1);
        cart.addItem(p2, 2);
        cart.addItem(p3, 3);

        Item_Model foundItem = cart.findItem(p3.getProductID());
        assertNotNull(foundItem, "Should find item in multi-item cart");
        assertEquals(3, foundItem.getQuantity(), "Found item should have correct quantity");
    }

    // ========== updateQuantity Tests ==========

    @Test
    public void testUpdateQuantity_ValidAmount_ReturnsTrue() {
        // Test 1: Successfully updates quantity of existing item with valid amount
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand J");
        cart.addItem(product, 2);

        assertTrue(cart.updateQuantity(product, 5), "Should update quantity to 5");
        assertEquals(5, cart.findItem(product.getProductID()).getQuantity(),
                     "Quantity should be updated to 5");
    }

    @Test
    public void testUpdateQuantity_ExceedsStock_ReturnsFalse() {
        // Test 2: Fails to update quantity when amount exceeds available stock
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand K");
        cart.addItem(product, 2);

        assertFalse(cart.updateQuantity(product, 15), "Should fail when new quantity exceeds stock");
        assertEquals(2, cart.findItem(product.getProductID()).getQuantity(),
                     "Quantity should remain unchanged");
    }

    @Test
    public void testUpdateQuantity_NonExistentProduct_ReturnsFalse() {
        // Test 3: Fails to update quantity for non-existent product
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand L");

        assertFalse(cart.updateQuantity(product, 5), "Should fail for non-existent product");
    }

    @Test
    public void testUpdateQuantity_MaximumStock_ReturnsTrue() {
        // Test 4: Successfully updates quantity to maximum available stock
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand M");
        cart.addItem(product, 2);

        assertTrue(cart.updateQuantity(product, 10), "Should update to maximum stock");
        assertEquals(10, cart.findItem(product.getProductID()).getQuantity(),
                     "Quantity should be 10");
    }

    // ========== calculateCartSubTotal Tests ==========

    @Test
    public void testCalculateCartSubTotal_SingleItem_ReturnsCorrectTotal() {
        // Test 1: Calculates subtotal for cart with single item
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand R");
        cart.addItem(product, 2);

        assertEquals(100.0, cart.calculateCartSubTotal(), 0.01,
                     "Subtotal should be 100.00 (50.00 * 2)");
    }

    @Test
    public void testCalculateCartSubTotal_MultipleItems_ReturnsCorrectTotal() {
        // Test 2: Calculates subtotal for cart with multiple items
        Cart_Model cart = new Cart_Model();
        Product_Model p1 = new Product_Model("Item1", 50.0, 10, "Food", "Brand S");
        Product_Model p2 = new Product_Model("Item2", 30.0, 10, "Food", "Brand S");
        Product_Model p3 = new Product_Model("Item3", 20.0, 10, "Food", "Brand S");
        cart.addItem(p1, 2); // 100
        cart.addItem(p2, 1); // 30
        cart.addItem(p3, 3); // 60

        assertEquals(190.0, cart.calculateCartSubTotal(), 0.01,
                     "Subtotal should be 190.00 (100 + 30 + 60)");
    }

    @Test
    public void testCalculateCartSubTotal_EmptyCart_ReturnsZero() {
        // Test 3: Calculates subtotal for empty cart
        Cart_Model cart = new Cart_Model();

        assertEquals(0.0, cart.calculateCartSubTotal(), 0.01,
                     "Subtotal should be 0.00 for empty cart");
    }

    @Test
    public void testCalculateCartSubTotal_DecimalPrices_ReturnsCorrectTotal() {
        // Test 4: Calculates subtotal with decimal prices
        Cart_Model cart = new Cart_Model();
        Product_Model p1 = new Product_Model("Item1", 25.50, 10, "Food", "Brand T");
        Product_Model p2 = new Product_Model("Item2", 33.75, 10, "Food", "Brand T");
        cart.addItem(p1, 2); // 51.00
        cart.addItem(p2, 3); // 101.25

        assertEquals(152.25, cart.calculateCartSubTotal(), 0.01,
                     "Subtotal should be 152.25 (51.00 + 101.25)");
    }
}
