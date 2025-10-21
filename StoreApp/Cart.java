package StoreApp;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    /***
     * Is a method which checks the if the desired product has enough stock/quantity in order to fulfill the customer's request.
     * @param product is the product that wants to be added to the cart
     * @param quantity is the number of products desired to be added to cart
     * @return a boolean to signify succes / failure
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
     * @param product
     * @return boolean indicating success/failure of search
     */
    public boolean removeItem(Product product)
    {
       for(Item i : items)
       {
           if(i.getProduct().equals(product)) {
               items.remove(i);
               return true;
           }
       }
       return false;
    }

    /***
     * Method which iterates through all the items in the cart to find the item being searched
     * @param id
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
     * @param id
     * @param amount
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
     * @return boleaan to show if empty = true or not empty = false
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

    /***
     * iterates through every item and displays product name, product price, product quantity
     */
    public void displayCart()
    {
        for(Item i : items)
        {
            System.out.println(i.getProduct().getProductName());
            System.out.println(i.getProduct().getProductPrice());
            System.out.println(i.getProduct().getProductQuantity());
        }
    }

}

