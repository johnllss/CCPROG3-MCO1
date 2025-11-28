# CCPROG3 MCO1 - Test Suite

This directory contains comprehensive JUnit 5 test cases for the Convenience Store Simulation project.

## Test Structure

```
test/
└── java/
    └── StoreApp/
        ├── Models/
        │   ├── Customer_Model_Test.java
        │   ├── Employee_Model_Test.java
        │   ├── Product_Model_Test.java
        │   ├── Item_Model_Test.java
        │   ├── Cart_Model_Test.java
        │   ├── MembershipCard_Model_Test.java
        │   ├── Inventory_Model_Test.java
        │   ├── Transaction_Model_Test.java
        │   ├── Receipt_Model_Test.java
        │   └── AllModelsTestSuite.java
        └── Controllers/
            (Controller tests if applicable)
```

## Prerequisites

Make sure you have JUnit 5 (Jupiter) added to your project.

### For Maven Projects

Add to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-suite</artifactId>
        <version>1.10.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### For Manual Setup (Using JAR files)

Download and add to your classpath:
- junit-jupiter-api-5.10.0.jar
- junit-jupiter-engine-5.10.0.jar
- junit-platform-console-standalone-1.10.0.jar

## Running Tests

### Option 1: Run All Tests from Command Line

```bash
# Navigate to project root
cd "e:\PERSONAL-Jigs\DLSU BSCS-ST\4th Term 1\CCPROG3\CCPROG3-MCO1"

# Compile tests (adjust classpath as needed)
javac -cp "lib/*;src/main/java" -d bin/test src/test/java/StoreApp/Models/*Test.java

# Run all tests
java -cp "lib/*;bin/main;bin/test" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

### Option 2: Run Individual Test Classes

```bash
# Run a specific test class
java -cp "lib/*;bin/main;bin/test" org.junit.platform.console.ConsoleLauncher --select-class StoreApp.Models.Product_Model_Test
```

### Option 3: Using VS Code

1. Install the "Test Runner for Java" extension
2. Open the test file
3. Click the "Run Test" or "Debug Test" button above each test method

### Option 4: Using IntelliJ IDEA

1. Right-click on the test file or test package
2. Select "Run 'ClassName'" or "Run 'PackageName'"

### Option 5: Using Eclipse

1. Right-click on the test file
2. Select "Run As" > "JUnit Test"

## Test Coverage

### Model Classes (100% Method Coverage)

Each model class has comprehensive test coverage including:

1. **Customer_Model** (3 tests)
   - Membership verification tests

2. **Employee_Model** (5 tests)
   - Login validation with various credential combinations

3. **Product_Model** (24 tests)
   - Stock management (reduce/update)
   - Expiration tracking
   - Perishability checks
   - Low stock detection
   - Quantity validation

4. **Item_Model** (5 tests)
   - Subtotal calculations with various price/quantity combinations

5. **Cart_Model** (20 tests)
   - Empty cart detection
   - Add/remove/find items
   - Quantity updates and increments
   - Subtotal calculations

6. **MembershipCard_Model** (10 tests)
   - Points addition/redemption
   - Card number generation

7. **Inventory_Model** (30+ tests)
   - Category-based product retrieval
   - Low stock and expired product detection
   - Product CRUD operations
   - Stock verification and cart processing

8. **Transaction_Model** (25+ tests)
   - Subtotal, discount, tax, and total calculations
   - Senior citizen and membership discounts
   - Payment and change calculations
   - Membership points calculation

9. **Receipt_Model** (4 tests)
   - Receipt number generation and format validation

### Controller Classes

Controller tests focus on computational logic only (excluding UI-only methods):
- Employee login validation
- Permission checking
- Payment input validation
- Cart total calculations
- Navigation logic

## Test Naming Convention

All tests follow the pattern:
```
test[MethodName]_[Condition]_[ExpectedResult]
```

Example: `testReduceStock_LessThanQuantity_ReturnsTrue`

## Understanding Test Results

- **PASS (P)**: Test executed successfully and assertions passed
- **FAIL (F)**: Test executed but assertions failed
- **ERROR**: Test encountered an exception during execution

## Writing Additional Tests

To add new tests:

1. Create a new test class in the appropriate package
2. Use `@Test` annotation for each test method
3. Use `@BeforeEach` for setup code that runs before each test
4. Use JUnit assertions: `assertEquals`, `assertTrue`, `assertFalse`, `assertNull`, etc.

Example:

```java
@Test
public void testMethodName_Condition_ExpectedResult() {
    // Arrange
    ModelClass obj = new ModelClass(...);

    // Act
    boolean result = obj.method(params);

    // Assert
    assertTrue(result, "Description of what should happen");
}
```

## Notes

- Tests are independent and can run in any order
- Each test creates its own objects (no shared state)
- Test data is defined within each test method
- Floating-point comparisons use delta values (e.g., `0.01`) for precision
- Some tests may require JavaFX runtime if testing Controllers (use `@TestFX` extension)

## Troubleshooting

### "Class not found" errors
- Ensure JUnit JARs are in classpath
- Verify test classes are compiled to correct output directory

### "Method not found" errors
- Check that you're using JUnit 5 (`org.junit.jupiter.api.Test`)
- Not JUnit 4 (`org.junit.Test`)

### JavaFX errors in Controller tests
- Add JavaFX JARs to classpath
- Use TestFX library for UI component testing
- Or run tests with JavaFX runtime enabled

## Contact

For issues or questions about the tests, refer to the project documentation or course instructor.
