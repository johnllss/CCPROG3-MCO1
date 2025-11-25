package StoreApp.Controllers;

import StoreApp.Models.Receipt_Model;

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
        sb.append("Receipt #: ").append(receipt.getReceiptNumber()).append("\n");
        sb.append("Timestamp: ").append(receipt.getTransaction().getTimestamp()).append("\n");
        // Add more details as needed...
        return sb.toString();
    }
}
