package StoreApp.Controllers;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test Suite to run all Controller tests together
 */
@Suite
@SelectClasses({
    Employee_Login_Controller_Test.class,
    Inventory_Controller_Test.class,
    Transaction_Controller_Test.class,
    Cart_Controller_Test.class,
    Shopping_Controller_Test.class
})
public class AllControllersTestSuite {
    // This class remains empty, it is used only as a holder for the above annotations
}
