package StoreApp.Controllers;

import StoreApp.Models.Cart_Model;
import StoreApp.Models.Customer_Model;
import StoreApp.Models.Item_Model;
import StoreApp.Models.MembershipCard_Model;
import StoreApp.Models.Product_Model;
import StoreApp.Models.Receipt_Model;
import StoreApp.Models.Transaction_Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Receipt_Saving {
    private static final String Receipts_Directory = "receipts/";

    public static void saveReceipt(Receipt_Model receipt) throws IOException {
        File directory = new File(Receipts_Directory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filename = Receipts_Directory + receipt.getReceiptNumber() + ".txt";

        try(FileWriter fw = new FileWriter(filename)) {
            fw.write(formatReceipt(receipt));
        }
    }

    private static String formatReceipt(Receipt_Model receipt) {
        StringBuilder sb = new StringBuilder();
        Transaction_Model transaction = receipt.getTransaction();
        Customer_Model customer = transaction.getCustomer();
        Cart_Model cart = transaction.getCart();

        // Header
        sb.append("                    RECEIPT                    \n");
        sb.append("Receipt #: ").append(receipt.getReceiptNumber()).append("\n");
        sb.append("Timestamp: ").append(transaction.getTimestamp()).append("\n");
        sb.append("\n");

        // customer info
        sb.append("CUSTOMER INFORMATION\n");
        sb.append("Name: ").append(customer.getName()).append("\n");
        sb.append("Email: ").append(customer.getEmail()).append("\n");

        // membership info if customer is a member
        if (customer.hasMembership()) {
            MembershipCard_Model card = customer.getMembershipCard();
            sb.append("Membership Card: ").append(card.getCardNumber()).append("\n");
        }
        sb.append("\n");

        // item-by-item breakdown
        sb.append("ITEMS PURCHASED\n");
        sb.append(String.format("%-4s %-30s %10s %12s\n", "Qty", "Product Name", "Price", "Total"));

        for (Item_Model item : cart.getItems()) {
            Product_Model product = item.getProduct();
            int quantity = item.getQuantity();
            double price = product.getProductPrice();
            double itemTotal = item.calculateItemSubtotal();

            sb.append(String.format("%-4d %-30s ₱%9.2f ₱%10.2f\n", quantity, product.getProductName(), price, itemTotal));
        }
        sb.append("\n");

        // transaction summary
        sb.append("TRANSACTION SUMMARY\n");
        sb.append(String.format("%-40s ₱%10.2f\n", "Subtotal:", transaction.getSubtotal()));
        sb.append(String.format("%-40s ₱%10.2f\n", "Discount:", transaction.getDiscount()));
        sb.append(String.format("%-40s ₱%10.2f\n", "Tax (12%):", transaction.getTax()));
        sb.append(String.format("%-40s ₱%10.2f\n", "TOTAL:", transaction.getTotal()));
        sb.append("\n");

        // payment info
        sb.append("PAYMENT DETAILS\n");
        sb.append(String.format("%-40s %s\n", "Payment Method:", transaction.getPaymentMethod()));
        sb.append(String.format("%-40s ₱%10.2f\n", "Amount Received:", transaction.getAmountReceived()));
        sb.append(String.format("%-40s ₱%10.2f\n", "Change:", transaction.getChange()));
        sb.append("\n");

        // membership points if applicable
        if (customer.hasMembership()) {
            int pointsEarned = (int)(transaction.getTotal() / 50);
            sb.append("MEMBERSHIP REWARDS\n");
            sb.append(String.format("%-40s %d points\n", "Points Earned:", pointsEarned));
            sb.append("\n");
        }

        sb.append("           Thank you for shopping with us!           \n");

        return sb.toString();
    }
}
