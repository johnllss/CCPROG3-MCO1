package StoreApp.Models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Transaction_Model
 */
public class Transaction_Model_Test {

    // ========== calculateSubtotal Tests ==========

    @Test
    public void testCalculateSubtotal_SingleItem_ReturnsCorrectValue() {
        // Test 1: Calculates subtotal for cart with single item
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand A");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("John", "john@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);

        assertEquals(100.0, transaction.calculateSubtotal(), 0.01,
                     "Subtotal should be 100.00");
    }

    @Test
    public void testCalculateSubtotal_MultipleItems_ReturnsCorrectValue() {
        // Test 2: Calculates subtotal for cart with multiple items
        Cart_Model cart = new Cart_Model();
        Product_Model p1 = new Product_Model("Item1", 50.0, 10, "Food", "Brand B");
        Product_Model p2 = new Product_Model("Item2", 75.25, 10, "Food", "Brand B");
        cart.addItem(p1, 2);
        cart.addItem(p2, 3);

        Customer_Model customer = new Customer_Model("John", "john@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);

        assertEquals(325.75, transaction.calculateSubtotal(), 0.01,
                     "Subtotal should be 325.75 (100 + 225.75)");
    }

    @Test
    public void testCalculateSubtotal_EmptyCart_ReturnsZero() {
        // Test 3: Calculates subtotal for empty cart
        Cart_Model cart = new Cart_Model();
        Customer_Model customer = new Customer_Model("John", "john@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);

        assertEquals(0.0, transaction.calculateSubtotal(), 0.01,
                     "Subtotal should be 0.00 for empty cart");
    }

    // ========== calculateDiscount Tests ==========

    @Test
    public void testCalculateDiscount_SeniorOnly_Returns20Percent() {
        // Test 1: Calculates senior discount (20%) without membership
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand C");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Senior", "senior@email.com", "pass", null, true, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateSubtotal();

        assertEquals(20.0, transaction.calculateDiscount(0), 0.01,
                     "Senior discount should be 20.00 (20% of 100)");
    }

    @Test
    public void testCalculateDiscount_MembershipPointsOnly() {
        // Test 2: Calculates membership points discount only
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand D");
        cart.addItem(product, 2);

        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(100);
        Customer_Model customer = new Customer_Model("Member", "member@email.com", "pass", card, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateSubtotal();

        assertEquals(50.0, transaction.calculateDiscount(50), 0.01,
                     "Discount should be 50.00 from points redemption");
    }

    @Test
    public void testCalculateDiscount_Combined() {
        // Test 3: Calculates combined senior and membership discount
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand E");
        cart.addItem(product, 2);

        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(100);
        Customer_Model customer = new Customer_Model("Senior Member", "sm@email.com", "pass", card, true, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateSubtotal();

        double discount = transaction.calculateDiscount(30);
        assertEquals(50.0, discount, 0.01,
                     "Total discount should be 50.00 (20 senior + 30 points)");
    }

    // ========== getMaxUsablePoints Tests ==========

    @Test
    public void testGetMaxUsablePoints_NoMembership_ReturnsZero() {
        // Test 1: Returns zero when customer has no membership
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand F");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Guest", "guest@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateSubtotal();

        assertEquals(0, transaction.getMaxUsablePoints(),
                     "Max usable points should be 0 without membership");
    }

    @Test
    public void testGetMaxUsablePoints_PointsLessThanCap() {
        // Test 2: Returns available points when less than discounted subtotal
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand G");
        cart.addItem(product, 2);

        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(50);
        Customer_Model customer = new Customer_Model("Member", "member@email.com", "pass", card, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateSubtotal();

        assertEquals(50, transaction.getMaxUsablePoints(),
                     "Max usable points should be 50");
    }

    @Test
    public void testGetMaxUsablePoints_PointsExceedCap() {
        // Test 3: Returns max discount cap when points exceed it
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand H");
        cart.addItem(product, 2);

        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(150);
        Customer_Model customer = new Customer_Model("Member", "member@email.com", "pass", card, true, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateSubtotal();
        transaction.calculateDiscount(0); // Apply senior discount first

        int maxPoints = transaction.getMaxUsablePoints();
        assertTrue(maxPoints <= 80,
                   "Max usable points should not exceed discounted subtotal");
    }

    // ========== calculateTax Tests ==========

    @Test
    public void testCalculateTax_NonSenior_Returns12Percent() {
        // Test 1: Calculates 12% VAT for non-senior customer
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand I");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Regular", "regular@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateSubtotal();

        assertEquals(12.0, transaction.calculateTax(), 0.01,
                     "Tax should be 12.00 (12% of 100)");
    }

    @Test
    public void testCalculateTax_Senior_ReturnsZero() {
        // Test 2: Calculates zero VAT for senior citizen
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand J");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Senior", "senior@email.com", "pass", null, true, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateSubtotal();

        assertEquals(0.0, transaction.calculateTax(), 0.01,
                     "Tax should be 0.00 for senior citizen");
    }

    @Test
    public void testCalculateTax_WithDiscount() {
        // Test 3: Calculates VAT on discounted amount (non-senior)
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand K");
        cart.addItem(product, 2);

        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(100);
        Customer_Model customer = new Customer_Model("Member", "member@email.com", "pass", card, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateSubtotal();
        transaction.calculateDiscount(20);

        assertEquals(9.6, transaction.calculateTax(), 0.01,
                     "Tax should be 9.60 (12% of 80)");
    }

    // ========== calculateTotal Tests ==========

    @Test
    public void testCalculateTotal_NoDiscount_NoSenior() {
        // Test 1: Calculates total with no discount, no senior (subtotal + tax)
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand L");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Regular", "regular@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);

        assertEquals(112.0, transaction.calculateTotal(), 0.01,
                     "Total should be 112.00 (100 + 12)");
    }

    @Test
    public void testCalculateTotal_WithSeniorDiscount() {
        // Test 2: Calculates total with senior discount (subtotal - 20% + 0 tax)
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand M");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Senior", "senior@email.com", "pass", null, true, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateDiscount(0);

        assertEquals(80.0, transaction.calculateTotal(), 0.01,
                     "Total should be 80.00 (100 - 20 + 0)");
    }

    @Test
    public void testCalculateTotal_WithMembershipDiscount() {
        // Test 3: Calculates total with membership discount
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand N");
        cart.addItem(product, 2);

        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(100);
        Customer_Model customer = new Customer_Model("Member", "member@email.com", "pass", card, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateDiscount(50);

        double total = transaction.calculateTotal();
        assertEquals(56.0, total, 0.01,
                     "Total should be 56.00 (100 - 50 + 6)");
    }

    // ========== calculateChange Tests ==========

    @Test
    public void testCalculateChange_PaymentExceedsTotal() {
        // Test 1: Calculates correct change when payment exceeds total
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand O");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Regular", "regular@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateTotal();
        transaction.setAmountReceived(150.0);

        assertEquals(38.0, transaction.calculateChange(), 0.01,
                     "Change should be 38.00 (150 - 112)");
    }

    @Test
    public void testCalculateChange_PaymentEqualsTotal() {
        // Test 2: Calculates zero change when payment equals total
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand P");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Regular", "regular@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        double total = transaction.calculateTotal();
        transaction.setAmountReceived(total);

        assertEquals(0.0, transaction.calculateChange(), 0.01,
                     "Change should be 0.00 when payment equals total");
    }

    @Test
    public void testCalculateChange_PaymentLessThanTotal() {
        // Test 3: Calculates zero change when payment is insufficient
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand Q");
        cart.addItem(product, 2);

        Customer_Model customer = new Customer_Model("Regular", "regular@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateTotal();
        transaction.setAmountReceived(80.0);

        assertEquals(0.0, transaction.calculateChange(), 0.01,
                     "Change should be 0.00 when payment is insufficient");
    }

    // ========== calculateMembershipPoints Tests ==========

    @Test
    public void testCalculateMembershipPoints_WithMembership() {
        // Test 1: Calculates points earned for membership customer (1 point per 50 pesos)
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand R");
        cart.addItem(product, 5);

        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        Customer_Model customer = new Customer_Model("Member", "member@email.com", "pass", card, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateTotal();

        int pointsEarned = transaction.calculateMembershipPoints();
        assertTrue(pointsEarned >= 5, "Should earn at least 5 points for 250+ total");
    }

    @Test
    public void testCalculateMembershipPoints_NoMembership() {
        // Test 2: Returns zero points for non-membership customer
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 50.0, 10, "Food", "Brand S");
        cart.addItem(product, 5);

        Customer_Model customer = new Customer_Model("Guest", "guest@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateTotal();

        assertEquals(0, transaction.calculateMembershipPoints(),
                     "Should return 0 points for non-membership customer");
    }

    @Test
    public void testCalculateMembershipPoints_LessThan50() {
        // Test 4: Calculates zero points when total is less than 50
        Cart_Model cart = new Cart_Model();
        Product_Model product = new Product_Model("Item", 10.0, 10, "Food", "Brand U");
        cart.addItem(product, 4);

        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        Customer_Model customer = new Customer_Model("Member", "member@email.com", "pass", card, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);
        transaction.calculateTotal();

        int pointsEarned = transaction.calculateMembershipPoints();
        assertTrue(pointsEarned <= 1, "Should earn 0 or 1 point for total less than 50");
    }

    // ========== isDiscountable Tests ==========

    @Test
    public void testIsDiscountable_WithMembershipCard() {
        // Test 1: Returns true when customer has membership card
        Cart_Model cart = new Cart_Model();
        Customer_Model customer = new Customer_Model("Member", "member@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);

        assertTrue(transaction.isDiscountable("12345678-123456", false),
                   "Should be discountable with membership card");
    }

    @Test
    public void testIsDiscountable_WithSeniorStatus() {
        // Test 2: Returns true when customer is senior citizen
        Cart_Model cart = new Cart_Model();
        Customer_Model customer = new Customer_Model("Senior", "senior@email.com", "pass", null, true, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);

        assertTrue(transaction.isDiscountable(null, true),
                   "Should be discountable for senior citizen");
    }

    @Test
    public void testIsDiscountable_Both() {
        // Test 3: Returns true when customer has both membership and senior status
        Cart_Model cart = new Cart_Model();
        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        Customer_Model customer = new Customer_Model("Senior Member", "sm@email.com", "pass", card, true, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);

        assertTrue(transaction.isDiscountable("12345678-123456", true),
                   "Should be discountable with both membership and senior status");
    }

    @Test
    public void testIsDiscountable_Neither() {
        // Test 4: Returns false when no membership and not senior
        Cart_Model cart = new Cart_Model();
        Customer_Model customer = new Customer_Model("Regular", "regular@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);

        assertFalse(transaction.isDiscountable(null, false),
                    "Should not be discountable without membership or senior status");
    }

    @Test
    public void testIsDiscountable_EmptyCardNumber() {
        // Test 5: Returns false when card number is empty string
        Cart_Model cart = new Cart_Model();
        Customer_Model customer = new Customer_Model("Regular", "regular@email.com", "pass", null, false, cart);
        Transaction_Model transaction = new Transaction_Model(customer, cart);

        assertFalse(transaction.isDiscountable("", false),
                    "Should not be discountable with empty card number");
    }
}
