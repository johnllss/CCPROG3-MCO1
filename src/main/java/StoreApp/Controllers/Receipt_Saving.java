package StoreApp.Controllers;

import StoreApp.Models.Customer_Model;
import StoreApp.Models.MembershipCard_Model;
import StoreApp.Models.Receipt_Model;
import StoreApp.Models.Transaction_Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Receipt_Saving {
    private static final String Receipts_Directory = "receipts/";
    private static final String Receipts_File = "receipts.txt";

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

        // Header
        sb.append("Receipt #: ").append(receipt.getReceiptNumber()).append("\n");
        sb.append("Timestamp: ").append(transaction.getTimestamp()).append("\n");
        sb.append("\n");

        // customer info
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append("Email: ").append(customer.getEmail()).append("\n");
        sb.append("\n");

        // membership info if customer is a member
        if (customer.hasMembership()) {
            MembershipCard_Model card = customer.getMembershipCard();
            sb.append("Membership Card: ").append(card.getCardNumber()).append("\n");
            int pointsEarned = (int)(transaction.getTotal() / 50);
            sb.append("Points Earned: ").append(pointsEarned).append("\n");
            sb.append("\n");
        }

        // transaction summary
        sb.append("Subtotal: ₱ ").append(String.format("%.2f", transaction.getSubtotal())).append("\n");
        sb.append("Discount: ₱ ").append(String.format("%.2f", transaction.getDiscount())).append("\n");
        sb.append("Tax: ₱ ").append(String.format("%.2f", transaction.getTax())).append("\n");
        sb.append("TOTAL: ₱ ").append(String.format("%.2f", transaction.getTotal())).append("\n");
        sb.append("\n");

        // payment info
        sb.append("Payment Method: ").append(transaction.getPaymentMethod()).append("\n");
        sb.append("Amount Received: ₱ ").append(String.format("%.2f", transaction.getAmountReceived())).append("\n");
        sb.append("Change: ₱ ").append(String.format("%.2f", transaction.getChange())).append("\n");

        return sb.toString();
    }
}
