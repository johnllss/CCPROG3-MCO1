package StoreApp;

import java.util.Scanner;

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

    public double calculateSubtotal()
    {
        subtotal = cart.calculateCartSubTotal();
        return subtotal;
    }

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

    public double calculateTotal()
    {
        calculateSubtotal();
        calculateDiscount();
        calculateTax();

        total = subtotal - discount + tax;
        return total;
    }

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

    public boolean isDiscountable(String cardNumber, boolean isSenior)
    {
        // TODO: determine if this method is usable

        return false;
    }

    public String generateTimeStamp()
    {
        // TODO: research on how to create a timestamp

        return "";
    }



    // GETTERS
    public Cart getCart()
    {
        return cart;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public double getSubtotal()
    {
        return subtotal;
    }

    public double getDiscount()
    {
        return discount;
    }

    public double getTax()
    {
        return tax;
    }

    public double getTotal()
    {
        return total;
    }

    public double getAmountReceived()
    {
        return amountReceived;
    }

    public double getChange()
    {
        return change;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    public String getTimeStamp()
    {
        return timestamp;
    }

    public void setAmountReceived(double amount)
    {
        this.amountReceived = amount;
    }
}
