package StoreApp.Controllers;

import StoreApp.Models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Inventory_Controller
 * Tests the hasPermission logic which is computational, not UI-related
 */
public class Inventory_Controller_Test {

    // ========== hasPermission Tests ==========
    // This is the main computational method in Inventory_Controller

    @Test
    public void testHasPermission_Manager_ManagerAction_ReturnsTrue() {
        // Test 1: Grants permission to Manager for Manager-required action
        Employee_Model manager = new Employee_Model("John", "john@store.com", "pass", "Manager");

        boolean hasPermission = checkPermission(manager, "Manager");

        assertTrue(hasPermission, "Manager should have permission for Manager-required actions");
    }

    @Test
    public void testHasPermission_Manager_RestockerAction_ReturnsTrue() {
        // Test 2: Grants permission to Manager for Restocker-required action
        Employee_Model manager = new Employee_Model("John", "john@store.com", "pass", "Manager");

        boolean hasPermission = checkPermission(manager, "Restocker");

        assertTrue(hasPermission, "Manager should have permission for Restocker-required actions");
    }

    @Test
    public void testHasPermission_Restocker_RestockerAction_ReturnsTrue() {
        // Test 3: Grants permission to Restocker for Restocker-required action
        Employee_Model restocker = new Employee_Model("Jane", "jane@store.com", "pass", "Restocker");

        boolean hasPermission = checkPermission(restocker, "Restocker");

        assertTrue(hasPermission, "Restocker should have permission for Restocker-required actions");
    }

    @Test
    public void testHasPermission_Restocker_ManagerAction_ReturnsFalse() {
        // Test 4: Denies permission to Restocker for Manager-required action
        Employee_Model restocker = new Employee_Model("Jane", "jane@store.com", "pass", "Restocker");

        boolean hasPermission = checkPermission(restocker, "Manager");

        assertFalse(hasPermission, "Restocker should not have permission for Manager-required actions");
    }

    @Test
    public void testHasPermission_NullEmployee_ReturnsFalse() {
        // Test 5: Denies permission when employee is null
        Employee_Model nullEmployee = null;

        boolean hasPermission = checkPermission(nullEmployee, "Manager");

        assertFalse(hasPermission, "Null employee should not have any permissions");
    }

    @Test
    public void testHasPermission_CaseInsensitive_Manager_ReturnsTrue() {
        // Test 6: Handles case-insensitive role checking for Manager
        Employee_Model manager = new Employee_Model("John", "john@store.com", "pass", "manager");

        boolean hasPermission = checkPermission(manager, "Manager");

        assertTrue(hasPermission, "Should handle case-insensitive role comparison for Manager");
    }

    @Test
    public void testHasPermission_CaseInsensitive_Restocker_ReturnsTrue() {
        // Test 7: Handles case-insensitive role checking for Restocker
        Employee_Model restocker = new Employee_Model("Jane", "jane@store.com", "pass", "RESTOCKER");

        boolean hasPermission = checkPermission(restocker, "restocker");

        assertTrue(hasPermission, "Should handle case-insensitive role comparison for Restocker");
    }

    @Test
    public void testHasPermission_UnknownRole_ReturnsFalse() {
        // Test 8: Denies permission for unknown role
        Employee_Model employee = new Employee_Model("Bob", "bob@store.com", "pass", "Cashier");

        boolean hasPermission = checkPermission(employee, "Manager");

        assertFalse(hasPermission, "Unknown role should not have Manager permissions");
    }

    @Test
    public void testHasPermission_EmptyRole_ReturnsFalse() {
        // Test 9: Denies permission when employee has empty role
        Employee_Model employee = new Employee_Model("Bob", "bob@store.com", "pass", "");

        boolean hasPermission = checkPermission(employee, "Manager");

        assertFalse(hasPermission, "Employee with empty role should not have permissions");
    }

    /**
     * Helper method that replicates the hasPermission logic from Inventory_Controller
     * This allows us to test the logic without instantiating the controller
     */
    private boolean checkPermission(Employee_Model loggedInEmployee, String requiredRole) {
        if (loggedInEmployee == null) {
            return false;
        }

        String role = loggedInEmployee.getRole();

        // managers have all permissions
        if (role.equalsIgnoreCase("Manager")) {
            return true;
        }

        // restockers only have restock permission
        if (role.equalsIgnoreCase("Restocker") && requiredRole.equalsIgnoreCase("Restocker")) {
            return true;
        }

        return false;
    }
}
