package StoreApp;
import java.util.ArrayList;

/**
 * Where all items picked by customer is placed.
 */
public class Cart {
    private ArrayList<Item> items;

    /**
     * Default constructor for class Cart
     */
    public Cart() {
        this.items = new ArrayList<>();
    }

    /***
     * Is a method which checks if the desired product has enough stock/quantity in order to fulfill the customer's request.
     * @param product is the product that wants to be added to the cart
     * @param quantity is the number of products desired to be added to cart
     * @return a boolean to signify success / failure
     */
    public boolean addItem(Product product, int quantity)
    {
        if(product.getProductQuantity() < quantity)
        {
            return false;
        }
        else
        {
            items.add(new Item(product, quantity));
            return true;
        }
    }

    /***
     * Method which iterates through all items in cart and checks if product that wants to be removed is part of the cart
     * @param productID is the ID of the product being removed
     * @return boolean indicating success/failure of search
     */
    public boolean removeItem(int productID)
    {
       for(Item i: items)
       {
           if(i.getProduct().getProductID() == productID) {
               items.remove(i);
               return true;
           }
       }
       return false;
    }

    /***
     * Method which iterates through all the items in the cart to find the item being searched
     * @param id is the id of the product that wants to be found
     * @return Item that is searched
     */
    public Item findItem(int id)
    {
        for(Item i : items) {
            if (i.getProduct().getProductID() == id) {
                return i;
            }
        }
        return null;
    }

    /***
     * getter for all the items in the array list
     * @return ArrayList of Items
     */
    public ArrayList<Item> getItems()
    {
        return items;
    }

    /***
     * Updates the quantity of the product in cart
     * @param id id of the product that wants to be updated
     * @param amount quantity of the product
     * @return boolean, shows success or failure of the process
     */
    public boolean updateQuantity(int id, int amount){
        for(Item i : items){
            if(i.getProduct().getProductID() == id && i.getProduct().getProductQuantity() >= amount) {
                i.setQuantity(amount);
                return true;
            }
        }
        return false;
    }

    /***
     * Iterates and calculates the total price for every item in the cart.
     * @return double (total price)
     */
    public double calculateCartSubTotal(){
        double subTotal = 0;
        for(Item i : items){
            subTotal += i.calculateItemSubtotal();
        }
        return subTotal;
    }

    /***
     * method that checks if the cart is empty
     * @return boolean to show if empty = true or not empty = false
     */
    public boolean isEmpty()
    {
        if(items.isEmpty())
        {
            return true;
        }
        return false;
    }

    /***
     * method that removes every item in the cart
     */
    public void clearCart()
    {
        items.clear();
    }


}

