# Controller Tests - Design Rationale

## Why Controller Tests Are Included

You're absolutely right to question whether Controller tests should be included! Here's the reasoning:

### What the Specification Says

> "There is no need to test user-defined methods which are ONLY for screen design (i.e., no computations/processing; just print/println)."

### Key Word: "ONLY"

This means we **exclude** methods that:
- ❌ Only navigate between scenes (e.g., `goToCart()`, `backToMain()`)
- ❌ Only set up UI elements (e.g., `setupNavBar()`, `displayEmployeeName()`)
- ❌ Only display data (e.g., `displayCartItems()`, `populateProductsGrid()`)

But we **include** methods that:
- ✅ Perform validation logic
- ✅ Calculate values
- ✅ Make decisions (if/else logic)
- ✅ Process data

## Controller Tests Breakdown

### 1. Employee_Login_Controller_Test (7 tests)

**What's Tested:** Login authentication logic

**Why It's Tested:**
```java
// This is NOT just UI - it's business logic!
for (Employee_Model employee: employees) {
    if (employee.login(employeeEmail, employeePassword)) {
        foundEmployee = employee;
        isLoggedIn = true;
        break;
    }
}
```

This loop contains:
- Iteration through employees
- Conditional logic (if statement)
- Data validation
- State changes (isLoggedIn flag)

**Not Tested:** The JavaFX scene navigation code (that's UI-only)

---

### 2. Inventory_Controller_Test (9 tests)

**What's Tested:** Permission checking algorithm

**Why It's Tested:**
```java
private boolean hasPermission(String requiredRole) {
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
```

This method contains:
- **Decision logic** (multiple if statements)
- **String comparison** (case-insensitive)
- **Authorization logic** (security-related)
- **Return value computation**

This is **computational logic**, not screen design!

**Not Tested:** The alert popups (UI-only)

---

### 3. Transaction_Controller_Test (22 tests)

**What's Tested:** Input validation rules

**Why It's Tested:**
```java
// Validation logic - NOT just screen design!
if (fullNameText.getText().isEmpty()) {
    return false;  // ← This is a business rule!
}

if (seniorCheckBox.isSelected()) {
    try {
        int age = Integer.parseInt(ageText.getText());
        if (age < 60) {  // ← This is a validation rule!
            return false;
        }
    } catch (NumberFormatException e) {
        return false;
    }
}

// Payment amount validation
if (amountReceived < transaction.getTotal()) {  // ← Computation!
    return false;
}
```

This contains:
- **Validation rules** (age >= 60, non-empty fields)
- **Type conversion** and error handling
- **Numerical comparisons**
- **Business logic** (payment must be >= total)

These are **requirements** that must be tested!

**Not Tested:** The alert popups and scene navigation

---

### 4. Cart_Controller_Test (6 tests)

**What's Tested:** VAT and total calculation logic

**Why It's Tested:**
```java
private void updateAllTotals() {
    double subTotal = cart.calculateCartSubTotal();

    double tax;
    if (customer.isSenior()) {
        tax = 0.0;  // ← Business rule: seniors don't pay VAT
    } else {
        tax = 0.12; // ← Business rule: 12% VAT for non-seniors
    }

    double vat = subTotal * tax;  // ← Computation!
    double total = subTotal + vat; // ← Computation!
}
```

This contains:
- **Conditional logic** (senior vs. non-senior)
- **Mathematical calculations**
- **Business rules** (VAT rates)

This is **not** UI - it's financial calculation logic!

**Not Tested:** The label updates (UI-only)

---

### 5. Shopping_Controller_Test (12 tests)

**What's Tested:** Category navigation algorithm and cart logic

**Why It's Tested:**

**Navigation Logic:**
```java
private void navigateToPreviousCategory() {
    currentCategoryIndex--;

    // wrap around to the last category if index goes < 0
    if (currentCategoryIndex < 0) {  // ← Algorithm logic!
        currentCategoryIndex = categories.length - 1;
    }

    switchCategory(categories[currentCategoryIndex]);
}
```

**Add to Cart Logic:**
```java
private void handleAddToCart(Product_Model product, int quantity) {
    Cart_Model cart = customer.getCart();
    Item_Model item = cart.findItem(product.getProductID());

    if (item != null) {
        // Already in cart - increment
        cart.incrementQuantity(product, quantity);  // ← Processing!
    } else {
        // New item - add
        cart.addItem(product, quantity);  // ← Processing!
    }
}
```

This contains:
- **Array manipulation** (circular navigation)
- **Index calculations** (wrap-around logic)
- **Conditional logic** (item exists vs. new item)
- **Data processing** (cart operations)

**Not Tested:** The UI updates and grid population (UI-only)

---

## Summary: What Makes a Method Testable?

| ✅ **TESTABLE** (Has Logic) | ❌ **NOT TESTABLE** (UI Only) |
|----------------------------|------------------------------|
| Validation rules | Scene navigation |
| Calculations | Setting label text |
| Decision logic (if/else) | Populating UI lists |
| Loops and iterations | Showing/hiding UI elements |
| Data transformations | Alert dialogs |
| Comparisons | Button click handlers (that only navigate) |
| Business rules | Color/style changes |

## Test Coverage Strategy

### Models: 100% method coverage
- All business logic lives here
- Pure computational methods
- No UI dependencies

### Controllers: Selective coverage
- ✅ Test: Validation, calculations, algorithms
- ❌ Skip: Scene navigation, UI updates, displays

This approach follows the specification while ensuring **all computational logic** is tested!

## How This Helps Your Submission

1. **Meets Requirements:** Tests computational methods (per spec)
2. **Demonstrates Understanding:** Shows you can separate logic from UI
3. **Catches Bugs:** Validation logic bugs are common and critical
4. **Complete Coverage:** No computational logic goes untested

---

**Bottom Line:** Controller tests focus on the **"brain"** (logic), not the **"face"** (UI) of your controllers. This is exactly what the specification requires!
