package StoreApp.Models;

import java.util.ArrayList;

public class Inventory_Model {
    private ArrayList<Shelf> shelves;

    /**
     * Default Constructor for inventory
     */
    public Inventory_Model()
    {
        this.shelves = new ArrayList<Shelf>();
        initializeShelves();
    }
    public void initializeShelves()
    {
        String[] categories = {"Food", "Beverages", "Toiletries", "Cleaning Products", "Medications"};

        for (String c: categories)
        {
            shelves.add(new Shelf(c, 20));
        }
    }

    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

}
