/**
 * title: Inventory.java
 * description: This class creates inventory object that object's in the game can use.
 * date: December 18, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Inventory Object
 *
 * Purpose and Description
 *
 * This object is used to manage the items that object's in the game are holding. Each item is stored in an ArrayList
 * in the inventory object. Administrative methods such as adding and removing items from the inventory and checking
 * if a given item is in the inventory are included in this class.
 *
 *
 * This program uses the Java.util.ArrayList, java.util.Hashmap, and org.w3c.dom.Element packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac Inventory.java
 * Run:        java WonderlandGame
 * Document:   javadoc Inventory.java
 *
 */

/**
 *
 * Classes
 * --------
 *
 * public class Inventory
 * This is the primary (public) class for Inventory.
 *
 * GameObject constructors
 * ----------------------------
 *
 * public Inventory()
 * This is the default constructor for Inventory.
 *
 * GameObject Methods
 * ----------------------------
 *
 * public void addImportedItemsToInventory(Element eElement, HashMap<String, Item>itemsInGame)
 * This method adds items to an object's inventory by matching attributes stored in an element to the item hashmap.
 *
 * public void addItemToInventory(Item newItem)
 * This method adds an item to the object's inventory.
 *
 * public void removeItemFromInventory(Item newItem)
 * This method removes an item from the object's inventory.
 *
 * public Item getItemWithInventory()
 * Finds items with their own inventory in this object's inventory.
 *
 * public String printInventory()
 * Generates and returns a string of this object's inventory.
 *
 * public void printAliceInventory()
 * This method print's the items in Alice's inventory to the terminal.
 *
 * public ArrayList<Item> getInventoryList()
 * Returns the list of items in the inventory.
 *
 * GameObject Instance Variables
 * ----------------------------
 *
 * private ArrayList<Item> inventory; - Stores a list of items.
 *
 * ---------------------------------------------------------------------------------------
 */

/**
 * CODE...
 */

/** Java collection framework packages. */
import org.w3c.dom.Element;
import java.util.ArrayList;
import java.util.HashMap;

/** This is the primary (public) class for Inventory. */
public class Inventory {

    private ArrayList<Item> inventory; //Stores a list of items.

    /** This is the default constructor for Inventory. */
    public Inventory() {
        inventory = new ArrayList<Item>();
    }

    /*************************************
     *    Inventory Management Methods   *
     *************************************/

    /**
     * This method adds items to an object's inventory by matching attributes stored in an element to the item hashmap.
     * @param eElement     This element contains the identifier for items.
     * @param itemsInGame  This hashmap contains items in the game.
     */
    public void addImportedItemsToInventory(Element eElement, HashMap<String, Item>itemsInGame) {

        /* Only adds non-empty to the object's inventory. Every object can only be initialized with a maximum of three items. */
        if (!eElement.getElementsByTagName("item1").item(0).getTextContent().equals("empty"))
            this.addItemToInventory(itemsInGame.get(eElement.getElementsByTagName("item1").item(0).getTextContent()));

        if (!eElement.getElementsByTagName("item2").item(0).getTextContent().equals("empty"))
            this.addItemToInventory(itemsInGame.get(eElement.getElementsByTagName("item2").item(0).getTextContent()));

        if (!eElement.getElementsByTagName("item3").item(0).getTextContent().equals("empty"))
            this.addItemToInventory(itemsInGame.get(eElement.getElementsByTagName("item3").item(0).getTextContent()));

        if (!eElement.getElementsByTagName("item4").item(0).getTextContent().equals("empty"))
            this.addItemToInventory(itemsInGame.get(eElement.getElementsByTagName("item4").item(0).getTextContent()));
    }

    /**
     * This method adds an item to the object's inventory.
     * @param newItem Item to be added to inventory.
     */
    public void addItemToInventory(Item newItem) {
        inventory.add(newItem);
    }

    /**
     * This method removes an item from the object's inventory.
     * @param newItem Item to be removed from inventory.
     */
    public void removeItemFromInventory(Item newItem) {
        inventory.remove(newItem);
    }


    /**
     * Finds items with their own inventory in this object's inventory.
     * @return An item with its own inventory.
     */
    public Item getItemWithInventory() {
        Item output = new Item();
        /* Iterates over this object's inventory */
        for (Item item : inventory) {
            /* Tests to see if the item's inventory in this object's inventory is empty. */
            if (!(item.getInventory().getInventoryList().isEmpty()))
                output = item;
        }
        return output;
    }

    /**
     * Generates and returns a string of this object's inventory.
     * @return A string that contains all items in the inventory.
     */
    public String printInventory() {
        String output = "";
        ArrayList<Item> tempInventory = new ArrayList<Item>();

        /* Removes any hidden items from the inventory. */
        for(Item item : this.getInventoryList()){
            if(!item.isHidden())
                tempInventory.add(item);
        }

        /* Iterates over the size of the inventory. */
        for (int i = 0; i < tempInventory.size(); i++) {
            /* Doesn't include items that are hidden from the player. */
            if (!(tempInventory.get(i).isHidden())) {
                /* Tests conditions of items and inventory for proper formatting. */
                if (tempInventory.size() < 2) {
                    output =  output + "a " + tempInventory.get(i).getName();
                } else if (i == tempInventory.size() - 1) {
                    output = output + "and a " + tempInventory.get(i).getName();
                } else {
                    output = output + "a " + tempInventory.get(i).getName() + ", ";
                }
            }
        }
        return output;
    }

    /** This method print's alice's inventory to the terminal. */
    public void printAliceInventory(){
        /* Check to see if inventory is empty. */
        if(!this.getInventoryList().isEmpty()){
            System.out.println("You are carrying: ");
            for(Item item : this.getInventoryList()){
                System.out.println("a " + item.getName());
            }
        } else {
            System.out.println("You aren't carrying anything.");
        }

    }

    /**************************
     *   Getters and Setters  *
     **************************/

    /**
     * Returns the list of items in the inventory.
     * @return Am ArrayList of items.
     */
    public ArrayList<Item> getInventoryList() {
        return inventory;
    }

}
