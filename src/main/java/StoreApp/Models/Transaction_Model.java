package StoreApp.Models;

public class Transaction_Model {
    private Cart_Model cart;
    private Customer_Model customer;
    private double subtotal;
    private double discount;
    private double tax;
    private double total;
    private double amountReceived;
    private double change;
    private String paymentMethod;
    private String timestamp;

    /**
     * Class Transaction parameterized constructor.
     * @param customer is the referenced customer.
     * @param cart is the referenced cart.
     */
    public Transaction_Model(Customer_Model customer, Cart_Model cart)
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
    private String generateTimeStamp()
    {
        return "''";
    }
    public Cart_Model getCart() {
        return cart;
    }
    public void setCart(Cart_Model cart) {
        this.cart = cart;
    }
    public Customer_Model getCustomer() {
        return customer;
    }
    public void setCustomer(Customer_Model customer) {
        this.customer = customer;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getTax() {
        return tax;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public double getAmountReceived() {
        return amountReceived;
    }
    public void setAmountReceived(double amountReceived) {
        this.amountReceived = amountReceived;
    }
    public double getChange() {
        return change;
    }
    public void setChange(double change) {
        this.change = change;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }

}
