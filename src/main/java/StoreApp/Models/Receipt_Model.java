package StoreApp.Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Receipt_Model {
    private Transaction_Model transaction;
    private String receiptNumber;

    /**
     * Class Receipt parameterized constructor
     * @param transaction is the referenced transaction.
     */
    public Receipt_Model(Transaction_Model transaction)
    {
        this.transaction = transaction;
        this.receiptNumber = generateReceiptNumber();
    }

    /**
     * This method generates a random receipt number following YYYY-MM-DD-XXXX (XXXX is a random 4-digit number)
     * @return String for receiptNumber.
     */
    public String generateReceiptNumber()
    {
        LocalDateTime dateNow = LocalDateTime.now();
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String datePart = dateNow.format(formattedDate);

        Random random = new Random();

        // generate 0 to 8999 and add 1000 to follow 4-digit rule
        int randomNum = 1000 + random.nextInt(9000); // generates 1000-9999

        return datePart + "-" + randomNum;
    }

    /**
     * This method returns the transaction details of the receipt.
     * @return Transaction_Model is the class that contains the transaction details.
     */
    public Transaction_Model getTransaction() {
        return transaction;
    }

    /**
     * This method returns the receipt number of the receipt.
     * @return String is the receipt number.
     */
    public String getReceiptNumber() {
        return receiptNumber;
    }
}
