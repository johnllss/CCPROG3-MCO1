package StoreApp.Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.regex.Pattern;

/**
 * Test class for MembershipCard_Model
 */
public class MembershipCard_Model_Test {

    // ========== addPoints Tests ==========

    @Test
    public void testAddPoints_ZeroInitialPoints_ReturnsTrue() {
        // Test 1: Adds points to membership card with zero initial points
        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");

        assertTrue(card.addPoints(50), "Should successfully add points");
        assertEquals(50, card.getPoints(), "Points should be 50");
    }

    @Test
    public void testAddPoints_ExistingPoints_ReturnsTrue() {
        // Test 2: Adds points to membership card with existing points
        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(100);

        assertTrue(card.addPoints(30), "Should successfully add points");
        assertEquals(130, card.getPoints(), "Points should be 130 (100 + 30)");
    }

    @Test
    public void testAddPoints_LargeNumber_ReturnsTrue() {
        // Test 3: Adds large number of points
        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(50);

        assertTrue(card.addPoints(1000), "Should successfully add 1000 points");
        assertEquals(1050, card.getPoints(), "Points should be 1050 (50 + 1000)");
    }

    // ========== redeemPoints Tests ==========

    @Test
    public void testRedeemPoints_LessThanAvailable_ReturnsTrue() {
        // Test 1: Successfully redeems points when amount is less than available
        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(100);

        assertTrue(card.redeemPoints(50), "Should successfully redeem 50 points");
        assertEquals(50, card.getPoints(), "Points should be 50 (100 - 50)");
    }

    @Test
    public void testRedeemPoints_EqualsAvailable_ReturnsFalse() {
        // Test 2: Fails to redeem points when amount equals available points
        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(100);

        assertFalse(card.redeemPoints(100), "Should fail to redeem when amount equals available");
        assertEquals(100, card.getPoints(), "Points should remain 100");
    }

    @Test
    public void testRedeemPoints_ExceedsAvailable_ReturnsFalse() {
        // Test 3: Fails to redeem points when amount exceeds available points
        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");
        card.addPoints(100);

        assertFalse(card.redeemPoints(150), "Should fail to redeem when amount exceeds available");
        assertEquals(100, card.getPoints(), "Points should remain 100");
    }

    @Test
    public void testRedeemPoints_ZeroPoints_ReturnsFalse() {
        // Test 4: Fails to redeem from card with zero points
        MembershipCard_Model card = new MembershipCard_Model("12345678-123456");

        assertFalse(card.redeemPoints(10), "Should fail to redeem from card with zero points");
        assertEquals(0, card.getPoints(), "Points should remain 0");
    }

    // ========== generateCardNumber Tests ==========

    @Test
    public void testGenerateCardNumber_CorrectFormat() {
        // Test 1: Generates card number in correct format (YYYYMMDD-HHMMSS)
        MembershipCard_Model card = new MembershipCard_Model();
        String cardNumber = card.getCardNumber();

        Pattern pattern = Pattern.compile("\\d{8}-\\d{6}");
        assertTrue(pattern.matcher(cardNumber).matches(),
                   "Card number should match format YYYYMMDD-HHMMSS");
    }

    @Test
    public void testGenerateCardNumber_Uniqueness() {
        // Test 2: Generates unique card numbers for consecutive calls
        MembershipCard_Model card1 = new MembershipCard_Model();
        // Small delay to ensure different timestamps
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MembershipCard_Model card2 = new MembershipCard_Model();

        assertNotEquals(card1.getCardNumber(), card2.getCardNumber(),
                        "Consecutive card numbers should be unique");
    }

    @Test
    public void testGenerateCardNumber_ValidDateComponents() {
        // Test 3: Generates card number with valid date components
        MembershipCard_Model card = new MembershipCard_Model();
        String cardNumber = card.getCardNumber();

        String yearPart = cardNumber.substring(0, 4);
        int year = Integer.parseInt(yearPart);

        assertTrue(year >= 2024 && year <= 2030,
                   "Year should be current or recent (2024-2030)");
    }
}
