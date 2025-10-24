package StoreApp;

import java.util.Scanner;

/**
 * where the cart is checked out. It handles all the computation and discounts
 */
public class Transaction {
    private Cart cart;
    private Customer customer;
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
    public Transaction(Customer customer, Cart cart)
    {
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
    public double calculateSubtotal()
    {
        subtotal = cart.calculateCartSubTotal();
        return subtotal;
    }

    /**
     * This method calculates the discount to be applied.
     * @return double for the discount.
     */
    public double calculateDiscount()
    {
        Scanner scan = new Scanner(System.in);
        // TODO: find out how to close scan without prematurely closing it here
        
        discount = 0.0; // discount reset to 0.0

        // SENIOR DISCOUNT
        if (customer.isSenior())
        {
            // based on philippine context
            discount += subtotal * 0.20;
        }

        // MEMBERSHIP DISCOUNT
        if (customer.hasMembership())
        {
            MembershipCard card = customer.getMembershipCard();
            int currentPoints = card.getPoints();
            // possible max discount cap with respect to subtotal (prevents over-discounting)
            double maxDiscountCap = subtotal - discount;

            int userDesiredPoints;

            // loop for getting user input for using points until valid input
            do 
            {
                System.out.println("Your current points: " + currentPoints);
                System.out.println("Your max usable points: " + (int)maxDiscountCap);
                System.out.println("Enter the amount of points to use (0 to skip): ");

                userDesiredPoints = scan.nextInt();

                if (userDesiredPoints < 0) 
                {
                    System.out.println("Oops! Points must be positive.");
                } else if (userDesiredPoints > currentPoints)
                {
                    System.out.println("Oops! Not enough points.");
                } else if (userDesiredPoints > maxDiscountCap)
                {
                    System.out.println("Oops! That's above your usable points.");
                }
            } while (userDesiredPoints < 0 || userDesiredPoints > currentPoints || userDesiredPoints > maxDiscountCap);

            discount += userDesiredPoints;
            card.redeemPoints(userDesiredPoints);
        }    

        return discount;
    }

    /**
     * This method calculates the tax to be applied on the subtotal.
     * @return double for the tax.
     */
    public double calculateTax()
    {
        double taxableSubtotal = subtotal - discount;

        // seniors are VAT exempted
        if (customer.isSenior())
        {
            tax = 0.0;
            return tax;
        }

        // based on philippine context
        tax = taxableSubtotal * 0.12;

        return tax;
    }

    /**
     * This method calculates the Cart's total price.
     * @return double for the total.
     */
    public double calculateTotal()
    {
        calculateSubtotal();
        calculateDiscount();
        calculateTax();

        total = subtotal - discount + tax;
        return total;
    }

    /**
     * This method calculates the change to be given to the Customer
     * @return double for the change.
     */
    public double calculateChange()
    {
        if (amountReceived >= total)
        {
            change = amountReceived - total;
            return change;
        } else 
        {
            change = 0.0;
            return change;    
        }
    }

    /**
     * This method calculates the membership points that the Customer earned from a transaction.
     * @return int for the points earned.
     */
    public int calculateMembershipPoints()
    {
        if (!customer.hasMembership())
        {
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
    public boolean isDiscountable(String cardNumber, boolean isSenior)
    {
        // TODO: determine if this method is usable

        return false;
    }

    /**
     * This method generates the time stamp for the processed transaction.
     * @return String for the time stamp.
     */
    public String generateTimeStamp()
    {
        // TODO: research on how to create a timestamp

        return "";
    }



    // GETTERS
    /**
     * This is a getter method to get Transaction's cart.
     * @return Cart is the cart used in the transaction
     */
    public Cart getCart()
    {
        return cart;
    }

    /**
     * This is a getter method to get Transaction's associated Customer.
     * @return Customer that did the transaction
     */
    public Customer getCustomer()
    {
        return customer;
    }

    /**
     * This is a getter method to get Transaction's subtotal.
     * @return double for subtotal.
     */
    public double getSubtotal()
    {
        return subtotal;
    }

    /**
     * This is a getter method to get Transaction's discount.
     * @return double for discount.
     */
    public double getDiscount()
    {
        return discount;
    }

    /**
     * This is a getter method to get Transaction's tax.
     * @return double for tax.
     */
    public double getTax()
    {
        return tax;
    }

    /**
     * This is a getter method to get Transaction's total.
     * @return double for total.
     */
    public double getTotal()
    {
        return total;
    }

    /**
     * This is a getter method to get Transaction's amountReceived.
     * @return double for amountReceived.
     */
    public double getAmountReceived()
    {
        return amountReceived;
    }

    /**
     * This is a getter method to get Transaction's change.
     * @return double for change.
     */
    public double getChange()
    {
        return change;
    }

    /**
     * This is a getter method to get Transaction's paymentMethod.
     * @return double for paymentMethod.
     */
    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    /**
     * This is a getter method to get Transaction's timestamp.
     * @return double for timestamp.
     */
    public String getTimeStamp()
    {
        return timestamp;
    }

    /**
     * This method sets the Transaction's amountReceived.
     * @param amount is the amount to be used to assign to amountRecieved.
     *
     */
    public void setAmountReceived(double amount)
    {
        this.amountReceived = amount;
    }
}
