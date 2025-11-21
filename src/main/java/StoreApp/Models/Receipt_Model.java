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
    public String generateReceiptNumber()
    {
        // TODO: research on how to create a receipt number programming-wise
        // research on standard receipt number formats too

        return "";
    }
    public Transaction_Model getTransaction() {
        return transaction;
    }
    public void setTransaction(Transaction_Model transaction) {
        this.transaction = transaction;
    }
    public String getReceiptNumber() {
        return receiptNumber;
    }
    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
}
