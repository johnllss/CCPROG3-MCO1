package StoreApp;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * Master Test Suite to run ALL tests (Models + Controllers)
 */
@Suite
@SelectPackages({"StoreApp.Models", "StoreApp.Controllers"})
public class AllTestsSuite {
    // This class remains empty, it is used only as a holder for the above annotations
}
