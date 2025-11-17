package StoreApp.Views;

public class Receipt_View {
    /**
     * This method displays the Receipt details.
     *
     */
    public void displayReceiptDetails(Transaction transaction, String receiptNumber)
    {
        System.out.println(">>> YOUR RECEIPT <<<");
        System.out.println("Receipt #: " + receiptNumber);
        System.out.println("Date: " +transaction.getTimeStamp());
        System.out.print("\n\n");

        // display each item and their details
        System.out.println("Your Orders:");
        Cart cart = transaction.getCart();
        for (Item item: cart.getItems())
        {
            Product productItem = item.getProduct();
            String productName = productItem.getProductName();
            int productQty = item.getQuantity();
            double productPrice = productItem.getProductPrice();
            double itemTotalPrice = item.calculateItemSubtotal();

            System.out.printf("%-20s x%-2d @PHP%6.2f = PHP%7.2f\n", productName, productQty, productPrice, itemTotalPrice);
        }

        // display transaction details
        System.out.print("\n\n");
        System.out.printf("Subtotal:        PHP%10.2f\n", transaction.calculateSubtotal());
        System.out.printf("Discount:        PHP%10.2f\n", transaction.calculateDiscount());
        System.out.printf("Tax (VAT 12%%):  PHP%10.2f\n", transaction.calculateTax());
        System.out.printf("__________");
        System.out.printf("TOTAL:           PHP%10.2f\n", transaction.calculateTotal());
        System.out.printf("Amount Received: PHP%10.2f\n", transaction.getAmountReceived());
        System.out.printf("Change:          PHP%10.2f\n", transaction.calculateChange());

        // display customer's points if hasMembership() = true
        Customer customer = transaction.getCustomer();
        if (customer.hasMembership())
        {
            int pointsEarnedFromTXN = transaction.calculateMembershipPoints();

            System.out.println("Points Earned From Transaction: " +pointsEarnedFromTXN);
        }

        System.out.println("\n\nTHANK YOU FOR PURCHASING HERE!\n\n");
    }
}
