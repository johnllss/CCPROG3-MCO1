package StoreApp;

public class Receipt {
    private Transaction transaction;
    private String receiptNumber;

    public Receipt(Transaction transaction)
    {
            this.transaction = transaction;
            this.receiptNumber = generateReceiptNumber();
    }

    public String generateReceiptNumber()
    {

    }

    public void displayReceiptDetails()
    {

    }

    public boolean saveToFile(String fileName)
    {
        
    }
}
