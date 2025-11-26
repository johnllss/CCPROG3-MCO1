package StoreApp.Models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Customer_Model
 */
public class Customer_Model_Test {

    @Test
    public void testHasMembership_WithMembershipCard_ReturnsTrue() {
        // Test 1: Determines that customer with membership card has membership
        MembershipCard_Model membershipCard = new MembershipCard_Model("12345678-123456");
        Customer_Model customer = new Customer_Model("John Doe", "john@email.com", "pass123", membershipCard, false, new Cart_Model());

        assertTrue(customer.hasMembership(), "Customer with membership card should have membership");
    }

    @Test
    public void testHasMembership_WithoutMembershipCard_ReturnsFalse() {
        // Test 2: Determines that customer without membership card does not have membership
        Customer_Model customer = new Customer_Model("John Doe", "john@email.com", "pass123", null, false, new Cart_Model());

        assertFalse(customer.hasMembership(), "Customer without membership card should not have membership");
    }

    @Test
    public void testHasMembership_WithNewlyCreatedCard_ReturnsTrue() {
        // Test 3: Determines that customer with newly created membership card has membership
        MembershipCard_Model newCard = new MembershipCard_Model();
        Customer_Model customer = new Customer_Model("Jane Doe", "jane@email.com", "pass456", newCard, false, new Cart_Model());

        assertTrue(customer.hasMembership(), "Customer with newly created membership card should have membership");
    }
}
