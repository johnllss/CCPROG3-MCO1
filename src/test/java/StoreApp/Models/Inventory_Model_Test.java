package StoreApp.Models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Test class for Inventory_Model
 */
public class Inventory_Model_Test {
    private Inventory_Model inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory_Model();
        // Add some test products
        inventory.addProduct(new Product_Model("Chips", 50.0, 10, "Food", "Brand A"));
        inventory.addProduct(new Product_Model("Soda", 35.0, 2, "Beverages", "Brand B"));
        inventory.addProduct(new Product_Model("Shampoo", 120.0, 15, "Toiletries", "Brand C"));
        inventory.addProduct(new Product_Model("Milk", 60.0, 5, "Beverages", "Brand D",
                                                "", LocalDate.of(2024, 1, 1), ""));
        inventory.addProduct(new Product_Model("Bread", 40.0, 1, "Food", "Brand E",
                                                "", LocalDate.of(2026, 12, 31), ""));
    }

    // ========== getProductsByCategory Tests ==========

    @Test
    public void testGetProductsByCategory_ExistingCategory_ReturnsProducts() {
        // Test 1: Retrieves all products from existing category
        ArrayList<Product_Model> foodProducts = inventory.getProductsByCategory("Food");

        assertFalse(foodProducts.isEmpty(), "Food category should have products");
        assertTrue(foodProducts.size() >= 2, "Food category should have at least 2 products");
    }

    @Test
    public void testGetProductsByCategory_NonExistent_ReturnsEmptyList() {
        // Test 2: Returns empty list for non-existent category
        ArrayList<Product_Model> products = inventory.getProductsByCategory("InvalidCategory");

        assertTrue(products.isEmpty(), "Non-existent category should return empty list");
    }

    @Test
    public void testGetProductsByCategory_Null_ReturnsEmptyList() {
        // Test 3: Returns empty list for null category
        ArrayList<Product_Model> products = inventory.getProductsByCategory(null);

        assertTrue(products.isEmpty(), "Null category should return empty list");
    }

    @Test
    public void testGetProductsByCategory_Beverages_ReturnsMultipleItems() {
        // Test 4: Retrieves products from category with multiple items
        ArrayList<Product_Model> beverages = inventory.getProductsByCategory("Beverages");

        assertTrue(beverages.size() >= 2, "Beverages category should have at least 2 products");
    }

    // ========== getLowStockProducts Tests ==========

    @Test
    public void testGetLowStockProducts_BelowThreshold_ReturnsProducts() {
        // Test 1: Retrieves products below specified quantity threshold
        ArrayList<Product_Model> lowStock = inventory.getLowStockProducts(3);

        assertFalse(lowStock.isEmpty(), "Should have low stock products");
        for (Product_Model p : lowStock) {
            assertTrue(p.getProductQuantity() < 3,
                       "All returned products should have quantity < 3");
        }
    }

    @Test
    public void testGetLowStockProducts_ZeroThreshold_ReturnsEmptyList() {
        // Test 2: Returns empty list when no products are low stock
        ArrayList<Product_Model> lowStock = inventory.getLowStockProducts(0);

        assertTrue(lowStock.isEmpty(), "Should return empty list for threshold 0");
    }

    @Test
    public void testGetLowStockProducts_HighThreshold_ReturnsMultipleProducts() {
        // Test 3: Retrieves low stock products across all categories
        ArrayList<Product_Model> lowStock = inventory.getLowStockProducts(5);

        assertFalse(lowStock.isEmpty(), "Should have products below threshold 5");
    }

    @Test
    public void testGetLowStockProducts_VeryLowThreshold_ReturnsEmptyOrFew() {
        // Test 4: Returns empty list when all products have high stock
        // This assumes all products added have qty >= 1
        ArrayList<Product_Model> lowStock = inventory.getLowStockProducts(1);

        // Should only return products with qty < 1 (i.e., qty = 0)
        for (Product_Model p : lowStock) {
            assertTrue(p.getProductQuantity() < 1,
                       "All returned products should have quantity < 1");
        }
    }

    // ========== getExpiredProducts Tests ==========

    @Test
    public void testGetExpiredProducts_HasExpired_ReturnsProducts() {
        // Test 1: Retrieves all expired products across categories
        ArrayList<Product_Model> expired = inventory.getExpiredProducts();

        assertFalse(expired.isEmpty(), "Should have at least one expired product");
        for (Product_Model p : expired) {
            assertTrue(p.isExpired(), "All returned products should be expired");
        }
    }

    @Test
    public void testGetExpiredProducts_OnlyPerishable() {
        // Test 3: Excludes non-perishable products from expired list
        ArrayList<Product_Model> expired = inventory.getExpiredProducts();

        for (Product_Model p : expired) {
            assertTrue(p.isPerishable(), "All expired products should be perishable");
        }
    }

    // ========== findProduct Tests ==========

    @Test
    public void testFindProduct_ExistingID_ReturnsProduct() {
        // Test 1: Finds existing product by valid ID
        ArrayList<Product_Model> allProducts = inventory.getAllProducts();
        if (!allProducts.isEmpty()) {
            int productID = allProducts.get(0).getProductID();
            Product_Model found = inventory.findProduct(productID);

            assertNotNull(found, "Should find existing product");
            assertEquals(productID, found.getProductID(), "Product ID should match");
        }
    }

    @Test
    public void testFindProduct_NonExistent_ReturnsNull() {
        // Test 2: Returns null for non-existent product ID
        Product_Model found = inventory.findProduct(99999);

        assertNull(found, "Should return null for non-existent product ID");
    }

    @Test
    public void testFindProduct_AcrossDifferentShelves() {
        // Test 3: Finds product across different shelves
        ArrayList<Product_Model> beverages = inventory.getProductsByCategory("Beverages");
        if (!beverages.isEmpty()) {
            int productID = beverages.get(0).getProductID();
            Product_Model found = inventory.findProduct(productID);

            assertNotNull(found, "Should find product from specific category");
            assertEquals("Beverages", found.getProductCategory(),
                         "Found product should be from Beverages category");
        }
    }

    // ========== addProduct Tests ==========

    @Test
    public void testAddProduct_ValidProduct_ReturnsTrue() {
        // Test 1: Successfully adds product to appropriate shelf
        Product_Model newProduct = new Product_Model("Cookies", 75.0, 20, "Food", "Brand F");

        assertTrue(inventory.addProduct(newProduct), "Should successfully add valid product");
        assertNotNull(inventory.findProduct(newProduct.getProductID()),
                      "Product should be findable after adding");
    }

    @Test
    public void testAddProduct_Null_ReturnsFalse() {
        // Test 2: Fails to add null product
        assertFalse(inventory.addProduct(null), "Should fail to add null product");
    }

    @Test
    public void testAddProduct_InvalidCategory_ReturnsFalse() {
        // Test 3: Fails to add product with invalid category
        Product_Model invalidProduct = new Product_Model("Item", 50.0, 10, "InvalidCategory", "Brand G");

        assertFalse(inventory.addProduct(invalidProduct),
                    "Should fail to add product with invalid category");
    }

    // ========== restockProduct Tests ==========

    @Test
    public void testRestockProduct_ValidAmount_ReturnsTrue() {
        // Test 1: Successfully restocks existing product with positive amount
        ArrayList<Product_Model> allProducts = inventory.getAllProducts();
        if (!allProducts.isEmpty()) {
            int productID = allProducts.get(0).getProductID();
            int originalQty = allProducts.get(0).getProductQuantity();

            assertTrue(inventory.restockProduct(productID, 10),
                       "Should successfully restock product");
            assertEquals(originalQty + 10,
                         inventory.findProduct(productID).getProductQuantity(),
                         "Quantity should increase by 10");
        }
    }

    @Test
    public void testRestockProduct_ZeroAmount_ReturnsFalse() {
        // Test 2: Fails to restock with zero amount
        ArrayList<Product_Model> allProducts = inventory.getAllProducts();
        if (!allProducts.isEmpty()) {
            int productID = allProducts.get(0).getProductID();

            assertFalse(inventory.restockProduct(productID, 0),
                        "Should fail to restock with zero amount");
        }
    }

    @Test
    public void testRestockProduct_NegativeAmount_ReturnsFalse() {
        // Test 3: Fails to restock with negative amount
        ArrayList<Product_Model> allProducts = inventory.getAllProducts();
        if (!allProducts.isEmpty()) {
            int productID = allProducts.get(0).getProductID();

            assertFalse(inventory.restockProduct(productID, -5),
                        "Should fail to restock with negative amount");
        }
    }

    @Test
    public void testRestockProduct_NonExistent_ReturnsFalse() {
        // Test 4: Fails to restock non-existent product
        assertFalse(inventory.restockProduct(99999, 10),
                    "Should fail to restock non-existent product");
    }

    // ========== removeProduct Tests ==========

    @Test
    public void testRemoveProduct_ExistingProduct_ReturnsTrue() {
        // Test 1: Successfully removes existing product
        ArrayList<Product_Model> allProducts = inventory.getAllProducts();
        if (!allProducts.isEmpty()) {
            int productID = allProducts.get(0).getProductID();

            assertTrue(inventory.removeProduct(productID),
                       "Should successfully remove existing product");
            assertNull(inventory.findProduct(productID),
                       "Product should not be findable after removal");
        }
    }

    @Test
    public void testRemoveProduct_NonExistent_ReturnsFalse() {
        // Test 2: Fails to remove non-existent product
        assertFalse(inventory.removeProduct(99999),
                    "Should fail to remove non-existent product");
    }

    // ========== updateProductName Tests ==========

    @Test
    public void testUpdateProductName_ValidName_ReturnsTrue() {
        // Test 1: Successfully updates name for existing product
        ArrayList<Product_Model> allProducts = inventory.getAllProducts();
        if (!allProducts.isEmpty()) {
            int productID = allProducts.get(0).getProductID();

            assertTrue(inventory.updateProductName(productID, "NewName"),
                       "Should successfully update product name");
            assertEquals("NewName", inventory.findProduct(productID).getProductName(),
                         "Product name should be updated");
        }
    }

    @Test
    public void testUpdateProductName_NonExistent_ReturnsFalse() {
        // Test 2: Fails to update name for non-existent product
        assertFalse(inventory.updateProductName(99999, "NewName"),
                    "Should fail to update non-existent product");
    }

    @Test
    public void testUpdateProductName_NullName_ReturnsFalse() {
        // Test 3: Fails to update with null name
        ArrayList<Product_Model> allProducts = inventory.getAllProducts();
        if (!allProducts.isEmpty()) {
            int productID = allProducts.get(0).getProductID();

            assertFalse(inventory.updateProductName(productID, null),
                        "Should fail to update with null name");
        }
    }

    // ========== updateProductPrice Tests ==========

    @Test
    public void testUpdateProductPrice_ValidPrice_ReturnsTrue() {
        // Test 1: Successfully updates price with valid positive value
        ArrayList<Product_Model> allProducts = inventory.getAllProducts();
        if (!allProducts.isEmpty()) {
            int productID = allProducts.get(0).getProductID();

            assertTrue(inventory.updateProductPrice(productID, 75.0),
                       "Should successfully update product price");
            assertEquals(75.0, inventory.findProduct(productID).getProductPrice(), 0.01,
                         "Product price should be updated");
        }
    }

    @Test
    public void testUpdateProductPrice_Zero_ReturnsTrue() {
        // Test 2: Successfully updates price to zero
        ArrayList<Product_Model> allProducts = inventory.getAllProducts();
        if (!allProducts.isEmpty()) {
            int productID = allProducts.get(0).getProductID();

            assertTrue(inventory.updateProductPrice(productID, 0.0),
                       "Should successfully update price to zero");
            assertEquals(0.0, inventory.findProduct(productID).getProductPrice(), 0.01,
                         "Product price should be 0");
        }
    }

    @Test
    public void testUpdateProductPrice_Negative_ReturnsFalse() {
        // Test 3: Fails to update price with negative value
        ArrayList<Product_Model> allProducts = inventory.getAllProducts();
        if (!allProducts.isEmpty()) {
            int productID = allProducts.get(0).getProductID();

            assertFalse(inventory.updateProductPrice(productID, -50.0),
                        "Should fail to update with negative price");
        }
    }

    @Test
    public void testUpdateProductPrice_NonExistent_ReturnsFalse() {
        // Test 4: Fails to update price for non-existent product
        assertFalse(inventory.updateProductPrice(99999, 50.0),
                    "Should fail to update non-existent product");
    }

    // ========== verifyCartStock Tests ==========

    @Test
    public void testVerifyCartStock_SufficientStock_ReturnsTrue() {
        // Test 1: Verifies cart with sufficient stock for all items
        Cart_Model cart = new Cart_Model();
        ArrayList<Product_Model> products = inventory.getAllProducts();
        if (!products.isEmpty()) {
            Product_Model product = products.get(0);
            cart.addItem(product, 1);

            assertTrue(inventory.verifyCartStock(cart),
                       "Should verify cart with sufficient stock");
        }
    }

    @Test
    public void testVerifyCartStock_NullCart_ReturnsFalse() {
        // Test 3: Fails verification for null cart
        assertFalse(inventory.verifyCartStock(null),
                    "Should fail to verify null cart");
    }

    @Test
    public void testVerifyCartStock_EmptyCart_ReturnsFalse() {
        // Test 4: Fails verification for empty cart
        Cart_Model emptyCart = new Cart_Model();

        assertFalse(inventory.verifyCartStock(emptyCart),
                    "Should fail to verify empty cart");
    }

    // ========== operateCartPurchase Tests ==========

    @Test
    public void testOperateCartPurchase_ValidCart_ReturnsTrue() {
        // Test 1: Successfully processes purchase for valid cart
        Cart_Model cart = new Cart_Model();
        ArrayList<Product_Model> products = inventory.getAllProducts();
        if (!products.isEmpty()) {
            Product_Model product = products.get(0);
            int originalQty = product.getProductQuantity();
            cart.addItem(product, 2);

            assertTrue(inventory.operateCartPurchase(cart),
                       "Should successfully process purchase");
            assertTrue(inventory.findProduct(product.getProductID()).getProductQuantity() < originalQty,
                       "Product quantity should decrease after purchase");
        }
    }

    @Test
    public void testOperateCartPurchase_NullCart_ReturnsFalse() {
        // Test 2: Fails to process null cart
        assertFalse(inventory.operateCartPurchase(null),
                    "Should fail to process null cart");
    }

    @Test
    public void testOperateCartPurchase_EmptyCart_ReturnsFalse() {
        // Test 3: Fails to process empty cart
        Cart_Model emptyCart = new Cart_Model();

        assertFalse(inventory.operateCartPurchase(emptyCart),
                    "Should fail to process empty cart");
    }
}
