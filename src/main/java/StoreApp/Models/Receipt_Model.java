package StoreApp.Models;

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
     * This method returns the transaction details of the receipt.
     * @return Transaction_Model is the class that contains the transaction details.
     */
    public Transaction_Model getTransaction() {
        return transaction;
    }

    /**
     * This method sets the current receipt's transaction details to a new given transaction details.
     * @param transaction is the new given transaction details
     */
    public void setTransaction(Transaction_Model transaction) {
        this.transaction = transaction;
    }

    /**
     * This method returns the receipt number of the receipt.
     * @return String is the receipt number.
     */
    public String getReceiptNumber() {
        return receiptNumber;
    }

    /**
     * This method sets the receipt number of the receipt to a new given receipt number.
     * @param receiptNumber is the new receipt number.
     */
    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
}
