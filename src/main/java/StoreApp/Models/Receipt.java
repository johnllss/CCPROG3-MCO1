package StoreApp.Models;

/***
 * Displays the whole summary of transaction made by the customer
 */
public class Receipt {
    private Transaction transaction;
    private String receiptNumber;

    /**
     * Class Receipt parameterized constructor
     * @param transaction is the referenced transaction.
     */
    public Receipt(Transaction transaction)
    {
            this.transaction = transaction;
            this.receiptNumber = generateReceiptNumber();
    }

    /**
     * This method generates a random receipt number.
     * @return String for receiptNumber.
     */
    public String generateReceiptNumber()
    {
        // TODO: research on how to create a receipt number programming-wise
        // research on standard receipt number formats too

        return "";
    }


    /**
     * This method saves the Receipt details to a file.
     * @param fileName is the file name to be used.
     * @return boolean for success/failure.
     */
    public boolean saveToFile(String fileName)
    {
        //TODO: file I/O self discovery learning ? then research about file I/O

        return false;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
