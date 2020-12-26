/**
 * title: GameObject.java
 * description: This class is used to create game objects.
 * date: December 14, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Game Objects
 *
 * Purpose and Description
 *
 * The class is used to create game objects. All game objects have an index, identifier, inventory, name,
 * and description. There are methods get and set these variables. This class includes a set of methods
 * used to manage the object's inventory including, adding and removing items, performing tests on the
 * inventory, and generating a String of all items in the object's inventory.
 *
 *
 * This program uses the Java.util.ArrayList packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac GameObject.java
 * Run:        java WonderlandGame
 * Document:   javadoc GameObject.java
 *
 */

/**
 *
 * Classes
 * --------
 *
 * public class GameObject
 * This class creates string arrays for random error messages.
 *
 * GameObject constructors
 * ----------------------------
 *
 * public GameObject()
 * This is the default constructor for GameObject
 *
 * public GameObject(String[] info)
 * This constructor initializes a gameObject with basic variables and initializes the inventory.
 *
 * GameObject Methods
 * ----------------------------
 *
 * public boolean isItemInInventory(Item usableItem)
 * This method checks to see if the item is in this object's inventory.
 *
 * public String getName()
 * Returns the name of the object.
 *
 * public String getDescription()
 * Returns the description of the object.
 *
 * public String getIdentifier()
 * Returns the single word identifier of the object.
 *
 * public Inventory getInventory()
 * Return the object's inventory.
 *
 * GameObject Instance Variables
 * ----------------------------
 *
 * private ArrayList<String> messages; - This variable stores an array of error messages.
 *
 *     private String identifier; - Stores a single word string to identify object.
 *     private String name; - Stores the name of the object as a string.
 *     private String description; - Store the description of the variable as a string.
 *     private ArrayList<Item> inventory; - Stores items associated with this object in an array.
 *
 * ---------------------------------------------------------------------------------------
 *
 */


/** Java collection framework packages. */
import org.w3c.dom.Element;
/** Primary (public) class for GameObject. */
public class GameObject {

    private String identifier; //Stores a single word string to identify object.
    private String name; //Stores the name of the object as a string.
    private String description; //Store the description of the variable as a string.
    private Inventory inventory; //Stores items associated with this object in an array.
    /******************
     *  Constructors  *
     ******************/

    /** This is the default constructor for GameObject. It initializes the inventory array. */
    public GameObject(){
        this.inventory = new Inventory();
    }

    /**
     * This constructor initializes a gameObject with basic variables and initializes the inventory.
     * @param eElement  This element contains the identifer, name, and description of the object.
     */
    public GameObject(Element eElement){
        this.identifier = eElement.getAttribute("identifier");
        this.name = eElement.getElementsByTagName("name").item(0).getTextContent();
        this.description = eElement.getElementsByTagName("description").item(0).getTextContent();
        this.inventory = new Inventory();
    }

    /**
     * This method checks to see if the item is in this object's inventory.
     * @param usableItem Item to be tested.
     * @return True if item is in inventory.
     * False if item is not in inventory.
     */
    public boolean isItemInInventory(Item usableItem) {
        /* Iterates over this object's inventory */
        for (Item item : this.getInventory().getInventoryList()) {
            /* Tests to see if given item is equal to item at current position. */
            if (item.equals(usableItem)) {
                return true;
            }
        }
        return false;
    }


    /**************************
     *   Getters and Setters  *
     **************************/

    /**
     * Returns the name of the object.
     * @return Object's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the object.
     * @return Object's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the single word identifier of the object.
     * @return Object's identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * This method return's the object's inventory.
     * @return The object's inventory.
     */
    public Inventory getInventory(){
        return inventory;
    }
}
