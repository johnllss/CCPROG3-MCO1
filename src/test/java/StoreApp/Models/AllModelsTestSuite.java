package StoreApp.Models;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test Suite to run all Model tests together
 */
@Suite
@SelectClasses({
    Customer_Model_Test.class,
    Employee_Model_Test.class,
    Product_Model_Test.class,
    Item_Model_Test.class,
    Cart_Model_Test.class,
    MembershipCard_Model_Test.class,
    Inventory_Model_Test.class,
    Transaction_Model_Test.class,
    Receipt_Model_Test.class
})
public class AllModelsTestSuite {
    // This class remains empty, it is used only as a holder for the above annotations
}
