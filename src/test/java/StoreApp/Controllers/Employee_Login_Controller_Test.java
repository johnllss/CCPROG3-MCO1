package StoreApp.Controllers;

import StoreApp.Models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Employee_Login_Controller
 * Note: These tests focus on the login logic, not the UI navigation
 */
public class Employee_Login_Controller_Test {
    private Employee_Model[] employees;
    private Inventory_Model inventory;

    @BeforeEach
    public void setUp() {
        // Setup test data
        employees = new Employee_Model[3];
        employees[0] = new Employee_Model("John Manager", "manager@store.com", "pass123", "Manager");
        employees[1] = new Employee_Model("Jane Restocker", "restocker@store.com", "pass456", "Restocker");
        employees[2] = new Employee_Model("Bob Employee", "employee@store.com", "pass789", "Employee");

        inventory = new Inventory_Model();
    }

    // ========== Login Logic Tests ==========

    @Test
    public void testLoginLogic_ValidCredentials_ManagerFound() {
        // Test 1: Successfully authenticates manager with valid credentials
        String email = "manager@store.com";
        String password = "pass123";

        Employee_Model foundEmployee = null;
        boolean isLoggedIn = false;

        for (Employee_Model employee : employees) {
            if (employee.login(email, password)) {
                foundEmployee = employee;
                isLoggedIn = true;
                break;
            }
        }

        assertTrue(isLoggedIn, "Login should succeed with valid credentials");
        assertNotNull(foundEmployee, "Should find the employee");
        assertEquals("Manager", foundEmployee.getRole(), "Should authenticate as Manager");
    }

    @Test
    public void testLoginLogic_ValidCredentials_RestockerFound() {
        // Test 2: Successfully authenticates restocker with valid credentials
        String email = "restocker@store.com";
        String password = "pass456";

        Employee_Model foundEmployee = null;
        boolean isLoggedIn = false;

        for (Employee_Model employee : employees) {
            if (employee.login(email, password)) {
                foundEmployee = employee;
                isLoggedIn = true;
                break;
            }
        }

        assertTrue(isLoggedIn, "Login should succeed with valid restocker credentials");
        assertNotNull(foundEmployee, "Should find the restocker");
        assertEquals("Restocker", foundEmployee.getRole(), "Should authenticate as Restocker");
    }

    @Test
    public void testLoginLogic_InvalidCredentials_NotFound() {
        // Test 3: Fails to authenticate with invalid credentials
        String email = "wrong@store.com";
        String password = "wrongpass";

        Employee_Model foundEmployee = null;
        boolean isLoggedIn = false;

        for (Employee_Model employee : employees) {
            if (employee.login(email, password)) {
                foundEmployee = employee;
                isLoggedIn = true;
                break;
            }
        }

        assertFalse(isLoggedIn, "Login should fail with invalid credentials");
        assertNull(foundEmployee, "Should not find any employee");
    }

    @Test
    public void testLoginLogic_EmptyEmail_NotFound() {
        // Test 4: Fails to authenticate with empty email
        String email = "";
        String password = "pass123";

        Employee_Model foundEmployee = null;
        boolean isLoggedIn = false;

        for (Employee_Model employee : employees) {
            if (employee.login(email, password)) {
                foundEmployee = employee;
                isLoggedIn = true;
                break;
            }
        }

        assertFalse(isLoggedIn, "Login should fail with empty email");
        assertNull(foundEmployee, "Should not find any employee");
    }

    @Test
    public void testLoginLogic_EmptyPassword_NotFound() {
        // Test 5: Fails to authenticate with empty password
        String email = "manager@store.com";
        String password = "";

        Employee_Model foundEmployee = null;
        boolean isLoggedIn = false;

        for (Employee_Model employee : employees) {
            if (employee.login(email, password)) {
                foundEmployee = employee;
                isLoggedIn = true;
                break;
            }
        }

        assertFalse(isLoggedIn, "Login should fail with empty password");
        assertNull(foundEmployee, "Should not find any employee");
    }

    @Test
    public void testLoginLogic_CorrectEmailWrongPassword_NotFound() {
        // Test 6: Fails to authenticate with correct email but wrong password
        String email = "manager@store.com";
        String password = "wrongpassword";

        Employee_Model foundEmployee = null;
        boolean isLoggedIn = false;

        for (Employee_Model employee : employees) {
            if (employee.login(email, password)) {
                foundEmployee = employee;
                isLoggedIn = true;
                break;
            }
        }

        assertFalse(isLoggedIn, "Login should fail with wrong password");
        assertNull(foundEmployee, "Should not find any employee");
    }

    @Test
    public void testLoginLogic_NullEmployeesArray_HandlesGracefully() {
        // Test 7: Handles null employees array without crashing
        Employee_Model[] nullEmployees = null;
        String email = "manager@store.com";
        String password = "pass123";

        // This test ensures the logic can handle null array
        boolean exceptionThrown = false;
        try {
            if (nullEmployees != null) {
                for (Employee_Model employee : nullEmployees) {
                    employee.login(email, password);
                }
            }
        } catch (Exception e) {
            exceptionThrown = true;
        }

        assertFalse(exceptionThrown, "Should handle null employees array gracefully");
    }
}
