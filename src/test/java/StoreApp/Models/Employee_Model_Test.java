package StoreApp.Models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Employee_Model
 */
public class Employee_Model_Test {
    private Employee_Model employee;

    @BeforeEach
    public void setUp() {
        employee = new Employee_Model("John Smith", "john@store.com", "pass123", "Manager");
    }

    @Test
    public void testLogin_WithCorrectCredentials_ReturnsTrue() {
        // Test 1: Validates successful login with correct email and password
        assertTrue(employee.login("john@store.com", "pass123"),
                   "Login should succeed with correct credentials");
    }

    @Test
    public void testLogin_WithIncorrectEmail_ReturnsFalse() {
        // Test 2: Validates failed login with incorrect email
        assertFalse(employee.login("wrong@store.com", "pass123"),
                    "Login should fail with incorrect email");
    }

    @Test
    public void testLogin_WithIncorrectPassword_ReturnsFalse() {
        // Test 3: Validates failed login with incorrect password
        assertFalse(employee.login("john@store.com", "wrongpass"),
                    "Login should fail with incorrect password");
    }

    @Test
    public void testLogin_WithBothIncorrect_ReturnsFalse() {
        // Test 4: Validates failed login with both incorrect email and password
        assertFalse(employee.login("wrong@store.com", "wrongpass"),
                    "Login should fail with both incorrect email and password");
    }

    @Test
    public void testLogin_WithEmptyEmail_ReturnsFalse() {
        // Test 5: Validates failed login with empty email
        assertFalse(employee.login("", "pass123"),
                    "Login should fail with empty email");
    }
}
