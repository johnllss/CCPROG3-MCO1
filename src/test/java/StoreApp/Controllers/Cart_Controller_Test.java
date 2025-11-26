package StoreApp.Controllers;

import StoreApp.Models.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Cart_Controller
 * Tests the updateAllTotals calculation logic
 */
public class Cart_Controller_Test {

    // ========== updateAllTotals Calculation Tests ==========

    @Test
    public void testUpdateAllTotals_NonSenior_CalculatesCorrectly() {
        // Test 1: Calculates correct totals for non-senior customer
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand A");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("John", "john@email.com", "pass", null, false, cart);

        // Calculate totals
        double subtotal = cart.calculateCartSubTotal();
        double tax = customer.isSenior() ? 0.0 : 0.12;
        double vat = subtotal * tax;
        double total = subtotal + vat;

        assertEquals(100.0, subtotal, 0.01, "Subtotal should be 100.00");
        assertEquals(12.0, vat, 0.01, "VAT should be 12.00 (12% of 100)");
        assertEquals(112.0, total, 0.01, "Total should be 112.00");
    }

    @Test
    public void testUpdateAllTotals_Senior_ZeroVAT() {
        // Test 2: Calculates correct totals for senior customer (0% VAT)
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand B");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Senior", "senior@email.com", "pass", null, true, cart);

        // Calculate totals
        double subtotal = cart.calculateCartSubTotal();
        double tax = customer.isSenior() ? 0.0 : 0.12;
        double vat = subtotal * tax;
        double total = subtotal + vat;

        assertEquals(100.0, subtotal, 0.01, "Subtotal should be 100.00");
        assertEquals(0.0, vat, 0.01, "VAT should be 0.00 for senior");
        assertEquals(100.0, total, 0.01, "Total should be 100.00 for senior");
    }

    @Test
    public void testUpdateAllTotals_EmptyCart_AllZero() {
        // Test 3: Calculates correct totals for empty cart
        Cart_Model cart = new Cart_Model();
        Customer_Model customer = new Customer_Model("John", "john@email.com", "pass", null, false, cart);

        // Calculate totals
        double subtotal = cart.calculateCartSubTotal();
        double tax = customer.isSenior() ? 0.0 : 0.12;
        double vat = subtotal * tax;
        double total = subtotal + vat;

        assertEquals(0.0, subtotal, 0.01, "Subtotal should be 0.00");
        assertEquals(0.0, vat, 0.01, "VAT should be 0.00");
        assertEquals(0.0, total, 0.01, "Total should be 0.00");
    }

    @Test
    public void testUpdateAllTotals_DecimalAmounts_NonSenior() {
        // Test 4: Calculates correct totals with decimal amounts
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 43.75, 10, "Food", "Brand C");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("John", "john@email.com", "pass", null, false, cart);

        // Calculate totals
        double subtotal = cart.calculateCartSubTotal();
        double tax = customer.isSenior() ? 0.0 : 0.12;
        double vat = subtotal * tax;
        double total = subtotal + vat;

        assertEquals(87.50, subtotal, 0.01, "Subtotal should be 87.50");
        assertEquals(10.50, vat, 0.01, "VAT should be 10.50 (12% of 87.50)");
        assertEquals(98.0, total, 0.01, "Total should be 98.00");
    }

    @Test
    public void testUpdateAllTotals_MultipleItems_NonSenior() {
        // Test 5: Calculates correct totals with multiple items
        Cart_Model cart = new Cart_Model();
        Product_Model p1 = new Product_Model("Item1", 50.0, 10, "Food", "Brand D");
        Product_Model p2 = new Product_Model("Item2", 30.0, 10, "Food", "Brand D");
        cart.addItem(p1, 2);
        cart.addItem(p2, 3);

        Customer_Model customer = new Customer_Model("John", "john@email.com", "pass", null, false, cart);

        // Calculate totals
        double subtotal = cart.calculateCartSubTotal();
        double tax = customer.isSenior() ? 0.0 : 0.12;
        double vat = subtotal * tax;
        double total = subtotal + vat;

        assertEquals(190.0, subtotal, 0.01, "Subtotal should be 190.00 (100 + 90)");
        assertEquals(22.80, vat, 0.01, "VAT should be 22.80 (12% of 190)");
        assertEquals(212.80, total, 0.01, "Total should be 212.80");
    }

    @Test
    public void testUpdateAllTotals_MultipleItems_Senior() {
        // Test 6: Calculates correct totals with multiple items for senior
        Cart_Model cart = new Cart_Model();
        Product_Model p1 = new Product_Model("Item1", 50.0, 10, "Food", "Brand E");
        Product_Model p2 = new Product_Model("Item2", 30.0, 10, "Food", "Brand E");
        cart.addItem(p1, 2);
        cart.addItem(p2, 3);

        Customer_Model customer = new Customer_Model("Senior", "senior@email.com", "pass", null, true, cart);

        // Calculate totals
        double subtotal = cart.calculateCartSubTotal();
        double tax = customer.isSenior() ? 0.0 : 0.12;
        double vat = subtotal * tax;
        double total = subtotal + vat;

        assertEquals(190.0, subtotal, 0.01, "Subtotal should be 190.00");
        assertEquals(0.0, vat, 0.01, "VAT should be 0.00 for senior");
        assertEquals(190.0, total, 0.01, "Total should be 190.00 for senior");
    }
}
