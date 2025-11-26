# Quick Start Guide - Running Tests

## What You Have

✅ **180+ JUnit test cases** covering all Model and Controller classes
✅ **14 test classes** with comprehensive coverage (9 Models + 5 Controllers)
✅ **Automated test runner** batch scripts
✅ **Complete test documentation**

## Setup (One-Time Only)

### Step 1: Download JUnit
1. Go to: https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.0/
2. Download: `junit-platform-console-standalone-1.10.0.jar`
3. Place in: `CCPROG3-MCO1/lib/` folder

### Step 2: Verify Folder Structure
```
CCPROG3-MCO1/
├── lib/
│   └── junit-platform-console-standalone-1.10.0.jar  ← Must have this
├── src/
│   ├── main/java/StoreApp/Models/...
│   └── test/java/StoreApp/Models/...Test.java  ← Your test files
├── run-tests.bat  ← Double-click to run all tests
└── run-single-test.bat
```

## Running Tests (Choose One Method)

### Method 1: Easiest - Use Batch Script

```batch
# Double-click this file:
run-tests.bat
```

**That's it!** The script will:
- Compile your main code
- Compile your tests
- Run all tests
- Show results in console

### Method 2: Run Single Test Class

```batch
run-single-test.bat Product_Model_Test
```

Replace `Product_Model_Test` with any test class name:
- `Customer_Model_Test`
- `Employee_Model_Test`
- `Cart_Model_Test`
- `Transaction_Model_Test`
- etc.

### Method 3: Use VS Code (If Installed)

1. Install "Test Runner for Java" extension
2. Open any test file
3. Click ▶️ icon next to test methods
4. View results in Test Explorer

## Understanding Results

### ✅ All Tests Pass
```
[       180 tests successful      ]
[         0 tests failed          ]
```
**Great!** All functionality works correctly.

### ❌ Some Tests Fail
```
testReduceStock_LessThanQuantity_ReturnsTrue() FAILED
  Expected: true
  Actual: false
```

**Action needed:**
1. Note which test failed
2. Open the corresponding Model class
3. Fix the bug in that method
4. Re-run the test

## Test Files Explained

### Model Tests (9 classes)
| Test File | What It Tests | Test Count |
|-----------|---------------|------------|
| `Customer_Model_Test` | Membership checking | 3 |
| `Employee_Model_Test` | Login validation | 5 |
| `Product_Model_Test` | Stock, expiration, low stock | 24 |
| `Item_Model_Test` | Price calculations | 5 |
| `Cart_Model_Test` | Add/remove items, totals | 20 |
| `MembershipCard_Model_Test` | Points, card numbers | 10 |
| `Inventory_Model_Test` | Product management | 30+ |
| `Transaction_Model_Test` | Discounts, tax, payments | 25+ |
| `Receipt_Model_Test` | Receipt generation | 4 |

### Controller Tests (5 classes)
| Test File | What It Tests | Test Count |
|-----------|---------------|------------|
| `Employee_Login_Controller_Test` | Login authentication logic | 7 |
| `Inventory_Controller_Test` | Permission checking logic | 9 |
| `Transaction_Controller_Test` | Input validation logic | 22 |
| `Cart_Controller_Test` | Total calculation logic | 6 |
| `Shopping_Controller_Test` | Navigation & cart logic | 12 |

## Common Issues & Fixes

### ❌ "javac is not recognized"
**Fix:** Install Java JDK and add to PATH

### ❌ "junit class not found"
**Fix:** Download JUnit JAR to `lib/` folder (see Setup Step 1)

### ❌ "Cannot find symbol" errors
**Fix:** Make sure your main code compiles first:
```batch
javac -d bin/main src/main/java/StoreApp/Models/*.java
```

### ❌ Tests fail unexpectedly
**Fix:** Read the error message - it tells you exactly what's wrong:
- **Expected vs Actual** - shows what value was wrong
- **Line number** - shows where in your code the problem is

## For Your Test Script Document

Use the test results to fill in your Google Docs table:

1. **Run tests** using `run-tests.bat`
2. **Note the output** for each test
3. **Fill in "Actual Output"** column with what you got
4. **Mark P or F** based on whether test passed

Example:
```
Method: reduceStock
Test 1: Reduces stock when amount is less than current quantity
Sample Input: productQuantity = 10, amount = 5
Expected Output: true (new quantity = 5)
Actual Output: true (new quantity = 5)    ← From test results
P/F: P                                     ← Test passed
```

## Files Reference

- **TEST_GUIDE.md** - Detailed guide with everything
- **src/test/README.md** - Technical documentation
- **run-tests.bat** - Automated test runner
- **run-single-test.bat** - Run one test class

## Need Help?

1. Check error messages (they're very specific!)
2. Read **TEST_GUIDE.md** for detailed troubleshooting
3. Look at the test code to see what it's checking
4. Review your Model implementation

## Quick Command Reference

```batch
# Run all tests
run-tests.bat

# Run specific test
run-single-test.bat Product_Model_Test

# Compile manually
javac -cp "lib/*" -d bin/main src/main/java/StoreApp/Models/*.java
javac -cp "lib/*;bin/main" -d bin/test src/test/java/StoreApp/Models/*Test.java

# Run manually
java -jar lib/junit-platform-console-standalone-1.10.0.jar ^
  --class-path "bin/main;bin/test;lib/*" --scan-class-path
```

---

**You're all set!** Just run `run-tests.bat` and see your results. 🎉
