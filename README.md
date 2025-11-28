# CCPROG3-MCO1
This is the repository for CCPROG3's MCO1.

## Project Overview

**The Basket** is a JavaFX-based convenience store management system that simulates a complete retail transaction workflow. The application provides both customer-facing shopping features and employee management capabilities for inventory control and transaction processing.

### Key Features

- **Shopping Interface**: Browse products by category (Food, Beverages, Toiletries, Cleaning Products, Medications) with visual product displays
- **Cart Management**: Add, remove, and adjust item quantities with real-time price calculations
- **Membership System**: Auto-generated membership cards with points tracking for repeat customers
- **Transaction Processing**: 
  - Support for cash and card payment methods
  - Senior citizen discounts (20% on subtotal and VAT exemption for ages 60+)
  - Membership discounts (points-based system)
  - VAT calculation (12% on taxable items)
- **Receipt Generation**: Automated receipt creation with unique receipt numbers (YYYY-MM-DD-XXXX format)
- **Inventory Management**: Employee access to view and manage stock levels, expiration dates, and product information
- **User Authentication**: Role-based access for managers and restocking employees

### Tech Stack

- **Language**: Java
- **GUI Framework**: JavaFX with FXML
- **Architecture**: MVC (Model-View-Controller) pattern
- **IDE**: IntelliJ IDEA
- **Build System**: Standard Java compilation

## Current Known Issues
1. **Email Validation Lacks Comprehensive Checks**: Email validation uses basic regex that may not catch all invalid formats
   - Impact: Could allow invalid email formats through
   - Location: `Transaction_Controller.java` `isValidEmail()` method

2. **Card Payment Processing**: Card payment validation only checks if fields are filled, doesn't validate card number format, CVV length, or expiry date format
   - Impact: No real validation of card details
   - Location: `Transaction_Controller.java` `validatePaymentInputs()` method

3. **Out of Stock Products Purchasable**: Products with 0 stock (e.g., Coffee with 0 quantity) can still be added to cart
   - Impact: Allows purchasing unavailable items
   - Location: Product availability checking logic

5. **Hard-coded Employee Credentials**: Employee login credentials are hard-coded in the initialization
   - Security concern: No external authentication or secure storage
   - Location: `Main.java` `initializeEmployees()` method

6. **No Persistence Layer**: All data is in-memory and lost on application restart
   - Impact: No data persistence between sessions
   - Scope: Entire application

7. **Receipt File Management**: Receipts are saved to local directory without checks for disk space or file write permissions
   - Impact: Potential file I/O errors not handled gracefully
   - Location: Receipt saving logic

## Future Improvements

### Planned Enhancements
1. **UI/UX Enhancements**:
   - Fix UI to responsive design for different screen sizes
   - Search and filter functionality in shopping view
   - Barcode scanning integration

2. **Additional Features**:
   - Customer loyalty program automation
   - Return/refund transaction handling
   - Multi-language support
   - Print receipt functionality
   - Product recommendations based on purchase history

## Getting Started

### Prerequisites
- Java JDK 11 or higher
- JavaFX SDK
- IntelliJ IDEA (recommended) or any Java IDE

### Running the Application
1. Clone the repository
2. Open the project in your IDE
3. Ensure JavaFX libraries are properly configured
4. Run `Main.java` located in `src/main/java/StoreApp/`

### Default Employee Credentials
- **Manager**: john lloyd@gmail.com / abc123
- **Manager**: kristin@gmail.com / xyz456
- **Restocker**: leon@gmail.com / lmn098

## License
This project is developed for academic purposes as part of CCPROG3 coursework at De La Salle University.
