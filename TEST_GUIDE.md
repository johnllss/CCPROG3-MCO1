# CCPROG3 MCO1 - Complete Testing Guide

## Overview

This guide provides step-by-step instructions for running the comprehensive JUnit test suite for the Convenience Store Simulation project.

## What's Been Created

### Test Files Created (9 Model Test Classes)

1. **Customer_Model_Test.java** - Tests membership verification
2. **Employee_Model_Test.java** - Tests login functionality
3. **Product_Model_Test.java** - Tests stock management, expiration, perishability
4. **Item_Model_Test.java** - Tests subtotal calculations
5. **Cart_Model_Test.java** - Tests cart operations (add, remove, update, calculate)
6. **MembershipCard_Model_Test.java** - Tests points and card number generation
7. **Inventory_Model_Test.java** - Tests inventory management operations
8. **Transaction_Model_Test.java** - Tests transaction calculations (discounts, tax, totals)
9. **Receipt_Model_Test.java** - Tests receipt number generation

### Helper Files

- **AllModelsTestSuite.java** - Runs all model tests together
- **run-tests.bat** - Automated test runner script
- **run-single-test.bat** - Run individual test classes
- **README.md** - Test documentation

## Prerequisites

### Required: JUnit 5 Library

You need JUnit 5 (Jupiter) to run the tests. Choose one of these methods:

#### Method 1: Download JUnit JAR (Recommended for this project)

1. Download JUnit Platform Console Standalone JAR:
   - Visit: https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/
   - Download: `junit-platform-console-standalone-1.10.0.jar`

2. Place the JAR file in your `lib` folder:
   ```
   CCPROG3-MCO1/
   └── lib/
       └── junit-platform-console-standalone-1.10.0.jar
   ```

#### Method 2: Using Maven (If you have a pom.xml)

Add to `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Running the Tests

### Option 1: Using the Batch Script (Easiest)

1. **Run All Tests:**
   - Double-click `run-tests.bat`
   - This will compile everything and run all tests
   - Results will be shown in the console
   - Detailed reports saved to `test-reports/` folder

2. **Run a Single Test Class:**
   ```batch
   run-single-test.bat Product_Model_Test
   ```

### Option 2: Using VS Code

1. **Install Extension:**
   - Open VS Code
   - Install "Test Runner for Java" extension
   - Install "Extension Pack for Java"

2. **Run Tests:**
   - Open any test file (e.g., `Product_Model_Test.java`)
   - Click the ▶️ icon next to the test class or individual test methods
   - View results in the Test Explorer panel

### Option 3: Using IntelliJ IDEA

1. **Open Project:**
   - Open the project in IntelliJ IDEA
   - Right-click on `src/test/java/StoreApp/Models`

2. **Run Tests:**
   - Select "Run 'Tests in StoreApp.Models'"
   - Or right-click individual test file → "Run 'ClassName'"

### Option 4: Command Line (Manual)

```bash
# Navigate to project root
cd "e:\PERSONAL-Jigs\DLSU BSCS-ST\4th Term 1\CCPROG3\CCPROG3-MCO1"

# Compile main source
javac -cp "lib/*" -d bin/main src/main/java/StoreApp/Models/*.java src/main/java/StoreApp/Controllers/*.java

# Compile tests
javac -cp "lib/*;bin/main" -d bin/test src/test/java/StoreApp/Models/*Test.java

# Run all tests
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path "bin/main;bin/test;lib/*" --scan-class-path

# Run specific test
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path "bin/main;bin/test;lib/*" --select-class StoreApp.Models.Product_Model_Test
```

## Understanding Test Results

### Console Output

```
Test run finished after 1234 ms
[        10 containers found      ]
[         0 containers skipped    ]
[        10 containers started    ]
[         0 containers aborted    ]
[        10 containers successful ]
[         0 containers failed     ]
[       120 tests found           ]
[         0 tests skipped         ]
[       120 tests started         ]
[         0 tests aborted         ]
[       120 tests successful      ]  ← All tests passed!
[         0 tests failed          ]
```

### Interpreting Results

- ✅ **Green/Successful**: Test passed - functionality works as expected
- ❌ **Red/Failed**: Test failed - there's a bug to fix
- ⚠️ **Yellow/Skipped**: Test was skipped (usually marked with `@Disabled`)

### Common Test Failures

If a test fails, you'll see:

```
testReduceStock_LessThanQuantity_ReturnsTrue() FAILED
  Expected: true
  Actual: false
  at Product_Model_Test.java:45
```

This means:
- The test expected `true` but got `false`
- Check the `reduceStock` method in `Product_Model.java`
- Look at line 45 of the test to see what was being tested

## Test Coverage Summary

### Total Test Count: 120+ tests

| Model Class | Test Count | Key Areas Tested |
|-------------|------------|------------------|
| Customer_Model | 3 | Membership verification |
| Employee_Model | 5 | Login validation |
| Product_Model | 24 | Stock, expiration, quantity checks |
| Item_Model | 5 | Subtotal calculations |
| Cart_Model | 20 | Add, remove, update, calculate |
| MembershipCard_Model | 10 | Points, redemption, card generation |
| Inventory_Model | 30+ | Product CRUD, stock verification |
| Transaction_Model | 25+ | Discounts, tax, totals, payments |
| Receipt_Model | 4 | Receipt number generation |

## Example: Running and Interpreting a Test

### Step 1: Run a Single Test

```batch
run-single-test.bat Product_Model_Test
```

### Step 2: Read the Output

```
├─ Product_Model_Test ✔
   ├─ testReduceStock_LessThanQuantity_ReturnsTrue() ✔
   ├─ testReduceStock_EqualsQuantity_ReturnsTrue() ✔
   ├─ testReduceStock_ExceedsQuantity_ReturnsFalse() ✔
   ├─ testIsExpired_PastDate_ReturnsTrue() ✔
   └─ ... (20 more tests) ✔

Test run finished: 24 tests, 24 successful, 0 failed
```

### Step 3: If a Test Fails

```
├─ Product_Model_Test ✘
   ├─ testReduceStock_LessThanQuantity_ReturnsTrue() ✘
       Expected: 5
       Actual: 10
       at Product_Model_Test.java:35
```

**What to do:**
1. Open `Product_Model_Test.java` and look at line 35
2. Open `Product_Model.java` and check the `reduceStock` method
3. Fix the bug in the implementation
4. Re-run the test

## Viewing Detailed Reports

After running `run-tests.bat`, check the `test-reports/` folder:

```
test-reports/
├── index.html         ← Open this in a browser
├── TEST-junit-jupiter.xml
└── ...
```

The HTML report shows:
- Visual test results
- Execution times
- Stack traces for failures
- Test organization by class

## Tips for Success

### 1. Run Tests Frequently
- Run tests after making changes
- Catch bugs early
- Ensure new code doesn't break existing functionality

### 2. Fix One Test at a Time
- Don't try to fix all failures at once
- Focus on one test class at a time
- Understand why each test failed

### 3. Use Test Names as Documentation
- Test names describe what should happen
- Example: `testReduceStock_LessThanQuantity_ReturnsTrue`
  - Method: `reduceStock`
  - Condition: amount less than current quantity
  - Expected: should return `true`

### 4. Add Your Own Tests
- If you find a bug, write a test for it first
- Then fix the bug
- The test ensures the bug doesn't come back

## Troubleshooting

### Problem: "javac is not recognized"

**Solution:** Install Java JDK and add to PATH
1. Download JDK from Oracle or OpenJDK
2. Add to PATH: `C:\Program Files\Java\jdk-XX\bin`

### Problem: "Class not found: org.junit.jupiter"

**Solution:** Ensure JUnit JAR is in `lib/` folder
- Download `junit-platform-console-standalone-1.10.0.jar`
- Place in `CCPROG3-MCO1/lib/`

### Problem: Tests compile but don't run

**Solution:** Check classpath
- Ensure `bin/main` contains compiled `.class` files
- Ensure `bin/test` contains compiled test `.class` files
- Run `run-tests.bat` to automate compilation

### Problem: JavaFX errors when running Controller tests

**Solution:** Add JavaFX to classpath
- Download JavaFX SDK
- Add JavaFX JARs to `lib/` folder
- Or skip Controller tests for now (focus on Models)

## Next Steps

1. **Run all tests** to establish a baseline
2. **Review failed tests** (if any) and fix bugs
3. **Fill in the test script table** from the original document with P/F results
4. **Add more tests** if you find edge cases not covered
5. **Keep tests passing** as you continue development

## For Submission

The test script document you created earlier can be filled with:
- **Expected Output**: Already provided in the table
- **Actual Output**: Get from running these tests
- **P/F**: Mark based on whether test passed or failed

Example:
```
Method: reduceStock
Test #1: Reduces stock when amount is less than current quantity
Input: productQuantity = 10, amount = 5
Expected Output: true (new quantity = 5)
Actual Output: true (new quantity = 5)  ← From running test
P/F: P                                   ← Test passed
```

## Questions?

If you encounter issues:
1. Check the error message carefully
2. Review the test code to understand what's being tested
3. Review the implementation code
4. Check the README in `src/test/`
5. Consult course materials or instructor

---

**Good luck with your testing!** 🚀
