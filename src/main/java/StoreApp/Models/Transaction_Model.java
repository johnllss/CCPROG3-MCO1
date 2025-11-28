package StoreApp.Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction_Model {
    private Cart_Model cart;
    private Customer_Model customer;
    private double subtotal;
    private double discount;
    private double tax;
    private double total;
    private double amountReceived;
    private double change;
    private String paymentMethod;
    private String timestamp;

    /**
     * Class Transaction parameterized constructor.
     * @param customer is the referenced customer.
     * @param cart is the referenced cart.
     */
    public Transaction_Model(Customer_Model customer, Cart_Model cart) {
        this.cart = cart;
        this.customer = customer;
        this.subtotal = 0.0;
        this.discount = 0.0;
        this.tax = 0.0;
        this.total = 0.0;
        this.amountReceived = 0.0;
        this.change = 0.0;
        this.paymentMethod = "";
        this.timestamp = generateTimeStamp();
    }

    /**
     * This method calculates the whole Cart's subtotal.
     * @return double for the subtotal.
     */
    public double calculateSubtotal() {
        subtotal = cart.calculateCartSubTotal();
        return subtotal;
    }

    /**
     * This method calculates the discount to be applied.
     * @param pointsToRedeem is the number of points that the customer wants to redeem.
     * @return double for the discount.
     */
    public double calculateDiscount(int pointsToRedeem) {
        discount = 0.0;

        if (customer.isSenior()) {
            discount += subtotal * 0.20;
        }

        if (customer.hasMembership() && pointsToRedeem > 0) {
            MembershipCard_Model card = customer.getMembershipCard();

            if (pointsToRedeem <= card.getPoints() && pointsToRedeem <= (subtotal - discount)) {
                discount += pointsToRedeem;
                card.redeemPoints(pointsToRedeem);
            }
        }

        return discount;
    }

    /**
     * This is a helper function to get the max-usable points of the customer (for Controller usage).
     * @return int for the max number of usable points.
     */
    public int getMaxUsablePoints() {
        if (!customer.hasMembership()) {
            return 0;
        }

        int currentPoints = customer.getMembershipCard().getPoints();
        double maxDiscountCap = subtotal - discount;

        return Math.min(currentPoints, (int)maxDiscountCap);
    }

    /**
     * This method calculates the tax to be applied on the subtotal.
     * @return double for the tax.
     */
    public double calculateTax() {
        double taxableSubtotal = subtotal - discount;

        if (customer.isSenior()) {
            tax = 0.0;
            return tax;
        }

        tax = taxableSubtotal * 0.12;

        return tax;
    }

    /**
     * This method calculates the Cart's total price.
     * @return double for the total.
     */
    public double calculateTotal() {
        calculateSubtotal();
        calculateTax();

        total = subtotal - discount + tax;
        return total;
    }

    /**
     * This method calculates the change to be given to the Customer
     * @return double for the change.
     */
    public double calculateChange() {
        if (amountReceived >= total) {
            change = amountReceived - total;
            return change;
        } else {
            change = 0.0;
            return change;
        }
    }

    /**
     * This method calculates the membership points that the Customer earned from a transaction.
     * @return int for the points earned.
     */
    public int calculateMembershipPoints() {
        if (!customer.hasMembership()) {
            return 0;
        }

        int pointsEarnedFromTXN = (int)(total / 50);

        customer.getMembershipCard().addPoints(pointsEarnedFromTXN);

        return pointsEarnedFromTXN;
    }

    /**
     * This method checks if the purchase is discountable based on both the membership card and seniority condition.
     * @param cardNumber is the Customer's membership card number.
     * @param isSenior is the Customer's status on whether they are a senior or not.
     * @return boolean for success/failure.
     */
    public boolean isDiscountable(String cardNumber, boolean isSenior) {

        return (cardNumber != null && !cardNumber.isEmpty()) || isSenior;
    }

    /**
     * This method generates the time stamp for the processed transaction folowing MMM-DD-YYYY HH:MM:SS.
     * @return String for the time stamp.
     */
    private String generateTimeStamp() {
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm:ss");

        return dateNow.format(formattedDate);
    }

    /**
     * This is a getter method to get Transaction's cart.
     * @return Cart is the cart used in the transaction
     */
    public Cart_Model getCart() {
        return cart;
    }

    /**
     * This is a getter method to get Transaction's associated Customer.
     * @return Customer that did the transaction
     */
    public Customer_Model getCustomer() {
        return customer;
    }

    /**
     * This is a getter method to get Transaction's subtotal.
     * @return double for subtotal.
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * This is a getter method to get Transaction's discount.
     * @return double for discount.
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * This is a getter method to get Transaction's tax.
     * @return double for tax.
     */
    public double getTax() {
        return tax;
    }

    /**
     * This is a getter method to get Transaction's total.
     * @return double for total.
     */
    public double getTotal() {
        return total;
    }

    /**
     * This is a getter method to get Transaction's amountReceived.
     * @return double for amountReceived.
     */
    public double getAmountReceived() {
        return amountReceived;
    }
    
    /**
     * This method sets the Transaction's amountReceived.
     * @param amountReceived is the amount to be used to assign to amountReceived.
     *
     */
    public void setAmountReceived(double amountReceived) {
        this.amountReceived = amountReceived;
    }
    
    /**
     * This is a getter method to get Transaction's change.
     * @return double for change.
     */
    public double getChange() {
        return change;
    }
    
    /**
     * This is a getter method to get Transaction's paymentMethod.
     * @return String for paymentMethod.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * This method sets the Transaction's paymentMethod.
     * @param paymentMethod is the payment method used (Cash/Card).
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * This is a getter method to get Transaction's timestamp.
     * @return String for timestamp.
     */
    public String getTimestamp() {
        return timestamp;
    }
}