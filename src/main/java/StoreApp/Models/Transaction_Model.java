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
    public Transaction_Model(Customer_Model customer, Cart_Model cart) {
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

    /**
     * This method generates the time stamp for the processed transaction.
     * @return String for the time stamp.
     */
    private String generateTimeStamp() {
        return "''";
    }

    /**
     * This is a getter method to get Transaction's cart.
     * @return Cart is the cart used in the transaction
     */
    public Cart_Model getCart() {
        return cart;
    }

    /**
     * This method sets the Transaction's cart to a given cart.
     * @param cart is the new given cart that will be assigned to this Transaction's cart.
     */
    public void setCart(Cart_Model cart) {
        this.cart = cart;
    }

    /**
     * This is a getter method to get Transaction's associated Customer.
     * @return Customer that did the transaction
     */
    public Customer_Model getCustomer() {
        return customer;
    }

    /**
     * This method sets the Transaction's customer to a given customer.
     * @param customer is the new given customer that will be assigned to this Transaction's customer.
     */
    public void setCustomer(Customer_Model customer) {
        this.customer = customer;
    }

    /**
     * This is a getter method to get Transaction's subtotal.
     * @return double for subtotal.
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * This method sets the Transaction's subtotal to a new given subtotal.
     * @param discount is the new passed subtotal that will be assigned to this Transaction's subtotal.
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * This is a getter method to get Transaction's discount.
     * @return double for discount.
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * This method sets the Transaction's discount to a new given discount.
     * @param discount is the passed-in new discount that will be assigned to this Transaction's discount.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * This is a getter method to get Transaction's tax.
     * @return double for tax.
     */
    public double getTax() {
        return tax;
    }

    /**
     * This method sets the Transaction's tax to a new given tax.
     * @param tax is the passed-in new tax that will be assigned to this Transaction's tax.
     */
    public void setTax(double tax) {
        this.tax = tax;
    }

    /**
     * This is a getter method to get Transaction's total.
     * @return double for total.
     */
    public double getTotal() {
        return total;
    }

    /**
     * This method sets the Transaction's total to a new given total.
     * @param total is the passed-in new total that will be assigned to this Transaction's total.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * This is a getter method to get Transaction's amountReceived.
     * @return double for amountReceived.
     */
    public double getAmountReceived() {
        return amountReceived;
    }
    
    /**
     * This method sets the Transaction's amountReceived.
     * @param amount is the amount to be used to assign to amountRecieved.
     *
     */
    public void setAmountReceived(double amountReceived) {
        this.amountReceived = amountReceived;
    }
    
    /**
     * This is a getter method to get Transaction's change.
     * @return double for change.
     */
    public double getChange() {
        return change;
    }
    
    /**
     * This method sets the Transaction's change to a new given change.
     * @param change is the passed-in new change that will be assigned to this Transaction's change.
     */
    public void setChange(double change) {
        this.change = change;
    }
    
    /**
     * This is a getter method to get Transaction's paymentMethod.
     * @return double for paymentMethod.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }
}