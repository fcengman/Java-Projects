/**
 * title: Item.java
 * description: This class is used to create and manage items.
 * date: December 15, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Item Objects
 *
 * Purpose and Description
 *
 * The class is used to create Items. All game objects have an index, identifier, inventory, name,
 * and description. All items have a useCase and boolean values for pickupable, hidden and locked. There are
 * methods to get and set these variables. This class includes methods used to import and create items based
 * on the attributes stored in an xml file.
 *
 *
 * This program uses the Java.util.Hashmap and org.w3c.dom.* packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac Item.java
 * Run:        java WonderlandGame
 * Document:   javadoc Item.java
 *
 */

/**
 *
 * Classes
 * --------
 *
 * public class Items extends GameObject
 * This is the primary (public) class for Items. It extends the parent class GameObject
 *
 *
 * Items Constructors
 * ----------------------------
 *
 * public Item()
 * This is the default constructor for Item.
 *
 * public Item(Element eElement)
 * This constructor creates Item that have no items in their inventory objects based on
 * the attributes stored in element.
 *
 * public Item(Element eElement, HashMap<String, Item> itemsInGame)
 * This constructor creates Item objects that have items in their inventory based on the
 * attributes stored in an element.
 *
 * Items Methods
 * ----------------------------
 *
 * public static Hashmap<String, Item> importObjects(String fileName)
 * This method is used to create items that do not have items in their inventory
 * and populate an item array based on a xml file.
 *
 * public static void addItemsToItemsInGame(String fileName, Hashmap<String, Item> itemsInGame)
 * This method is used to create items that do have items in their inventory and adds them to an item
 * array based on a xml file.
 *
 * public String generateDescription()
 * This method generates a string that includes the description of the item and any items that the
 * given item may be holding.
 *
 * public void swapInventoryToAlice(Actor alice, Actor usableActor)
 * This method adds an item to alice's inventory, and removes the item from an actor's inventory.
 * This method is overloaded.
 *
 * public void swapInventoryToAlice(Actor alice, Item itemWithInventory)
 * This method adds an item to alice's inventory, and removes the item from an item's inventory.
 * This method is overloaded.
 *
 * public void swapInventoryToAlice(Actor alice, Location currentLocation)
 * This method adds an item to alice's inventory, and removes the item from a location's inventory.
 * This method is overloaded.
 *
 * public void swapInventoryFromAlice(Actor alice)
 * This method removes an item from alice's inventory, and adds the item to a location's inventory.
 *
 * public void examineItem(Actor alice, Location currentLocation)
 * This method checks to see if the given item can be examined and returns a description of the item.
 *
 * public void unHide()
 * This method sets the item's hidden variable to false.
 *
 * public void unlock()
 * This method sets the item's locked variable to false.
 *
 * public void setPickupable(int pickupable)
 * This method is used to set the item's pickupable variable based on an integer value.
 *
 * public boolean isPickupable()
 * This method is used to check if an item is pickupable.
 *
 * public void setHidden(int hidden)
 * This method is used to set the item's hidden variable based on an integer value.
 *
 * public boolean isHidden()
 * This method is used to check if an item is visible.
 *
 * public void setLock(int locked)
 * This method is used to set the item's locked variable based on an integer value.
 *
 * public boolean isLocked()
 * This method is used to check if an item is locked.
 *
 * public int getUseCase()
 * This method is used to get the item's useCase.
 *
 * Items Instance Variables
 * ----------------------------
 *
 * private boolean pickupable; - This variable determines if the item can be picked up.
 * private boolean hidden; - This variable determines if the item is hidden from the player.
 * private boolean locked; - This variable determines if the item is locked.
 * private String actionResponseIdentifier; - This variable determines which use cases the item is connected to.
 *
 * ---------------------------------------------------------------------------------------
 */

/**
 * CODE...
 */

/** Java collection framework packages. */
import org.w3c.dom.*;
import java.util.HashMap;

/** Primary (public) class for Item. It extends the parent class GameObject. */
public class Item extends GameObject{

    private boolean pickupable; //This variable determines if the item can be picked up.
    private boolean hidden; //This variable determines if the item is hidden from the player.
    private boolean locked; //This variable determines if the item is locked.
    private String actionResponseIdentifier; //This variable determines which use cases the item is connected to.


    /******************
     *  Constructors  *
     ******************/

    /** This is the default constructor for Item. */
    public Item(){
        //Empty constructor.
    }

    /**
     * This constructor creates Item that have no items in their inventory objects based on
     * the attributes stored in element.
     * @param eElement This is the element of a node that contains the attributes of an item.
     */
    public Item(Element eElement){

        super(eElement); //Calls the parent classes constructor to set identifier, name, and description variables.

        setPickupable( Integer.parseInt( eElement.getElementsByTagName("pickupable").item(0).getTextContent() ) );
        setHidden( Integer.parseInt( eElement.getElementsByTagName("hidden").item(0).getTextContent() ) );

        this.actionResponseIdentifier = eElement.getElementsByTagName("itemUse").item(0).getTextContent();
        this.locked = false; //All items that do not have items in their inventory are unlocked.

    }

    /**
     * This constructor creates Item objects that have items in their inventory based on the
     * attributes stored in an element.
     * @param eElement     This is the element of a node that contains the attributes of an item.
     * @param itemsInGame  A hashmap is used to fill the item's inventory based the values in string array.
     */
    public Item(Element eElement, HashMap<String, Item> itemsInGame){

        super(eElement); //Calls the parent classes constructor to set identifier, name, and description variables.

        this.pickupable = false; //All items that have items in their inventory cannot be picked up.
        this.hidden = false; //All items that have items in their inventory are visible to the player.
        this.setLock( Integer.parseInt( eElement.getElementsByTagName("locked").item(0).getTextContent() ) );
        this.actionResponseIdentifier = eElement.getElementsByTagName("itemUse").item(0).getTextContent();

        super.getInventory().addImportedItemsToInventory(eElement, itemsInGame); //Adds items to this item's inventory.

    }

    /*************
     *  Methods  *
     *************/

    /**
     * This method is used to create items that do not have items in their inventory
     * and populate an item array based on a xml file.
     * @param fileName   This is the name of the xml file.
     * @return           A hashmap of items held in the control class.
     */
    public static HashMap<String, Item> importObjects(String fileName) {

        var items = new HashMap<String, Item>();
        /* Calls readXMLFile method from toolbox class to populate a nodeList with the value in the xml file. */
        NodeList nList = Toolbox.readXMLFile(fileName, "item");

        /* Creates an item for each node in the list. */
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i); //Store single item's attributes in a node.

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode; //Stores the node as an eElement to be passed to constructor.
                var tempItem = new Item(eElement); //Creates a new item object
                items.put(tempItem.getIdentifier(), tempItem); //Adds item to hashmap, using identifier as the key.
            }
        }
        return items;
    }

    /**
     * This method is used to create items that do have items in their inventory and adds them to an item
     * array based on a xml file.
     * @param fileName     This is the name of the xmlFile.
     * @param itemsInGame  This is the hashmap that items are added to.
     */
    public static void addItemsToItemsInGame(String fileName, HashMap<String, Item> itemsInGame) {

        /* Calls readXMLFile method from toolbox class to populate a nodeList with the value in the xml file. */
        NodeList nList = Toolbox.readXMLFile(fileName, "item");

        /* Creates an item for each node in the list. */
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i); //Store single item's attributes in a node.

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode; //Stores the node as an eElement to be passed to constructor.
                var tempItem = new Item(eElement, itemsInGame); //Creates item with inventory object.
                itemsInGame.put(tempItem.getIdentifier(), tempItem); //Adds new item to itemsInGame hashmap.
            }
        }
    }

    /**
     * This method generates a string that includes the description of the item and any items that the
     * given item may be holding.
     * @return A string with the full description of the item.
     */
    public String generateDescription() {

        String output = this.getDescription();
        String inventory = this.getInventory().printInventory();
        /* Prints the item's inventory to the terminal if it has any items and it is unlocked. */
        if(!inventory.equals("") & !this.isLocked()){
            output = output + " The " + this.getName() + " contains " + inventory + ".";
        }
        return output;
    }

    /**
     * This method adds an item to alice's inventory, and removes the item from an actor's inventory.
     * This method is overloaded.
     * @param alice        Actor has item added to inventory.
     * @param usableActor  Actor has item removed from inventory.
     */
    public void swapInventoryToAlice(Actor alice, Actor usableActor){
        System.out.println("You took the " + this.getName() + " from " + usableActor.getName() + ".");
        alice.getInventory().addItemToInventory(this);
        usableActor.getInventory().removeItemFromInventory(this);
    }

    /**
     * This method adds an item to alice's inventory, and removes the item from an item's inventory.
     * This method is overloaded.
     * @param alice             Actor has item added to inventory.
     * @param itemWithInventory Item has item removed from inventory.
     */
    public void swapInventoryToAlice(Actor alice, Item itemWithInventory){
        System.out.println("You took the " + this.getName() + " from the " + itemWithInventory.getName() + ".");
        alice.getInventory().addItemToInventory(this);
        itemWithInventory.getInventory().removeItemFromInventory(this);
    }

    /**
     * This method adds an item to alice's inventory, and removes the item from a location's inventory.
     * This method is overloaded.
     * @param alice             Actor has item added to inventory.
     * @param currentLocation   Location has item removed from inventory.
     */
    public void swapInventoryToAlice(Actor alice, Location currentLocation){
        System.out.println("You picked up the " + this.getName() + ".");
        alice.getInventory().addItemToInventory(this);
        currentLocation.getInventory().removeItemFromInventory(this);
    }
    /**
     * This method removes an item from alice's inventory, and adds the item to a location's inventory.
     * @param alice  Actor has item removed from inventory.
     */
    public void swapInventoryFromAlice(Actor alice){
        System.out.println("You dropped the " + this.getName() + ".");
        alice.getInventory().removeItemFromInventory(this);
        alice.getCurrentLocation().getInventory().addItemToInventory(this);
    }

    /**
     * This method checks to see if the given item can be examined and returns a description of the item.
     * @param alice            This is the game player.
     * @param currentLocation  This is alice's current location.
     */
    public void examineItem(Actor alice, Location currentLocation){
        Item itemWithInventory = currentLocation.getInventory().getItemWithInventory(); //Stores a temporary Item when there is an item with an inventory in the current location.

        /*
         * Checks to see if the given item is in alice's inventory,
         * in the current location, or on/in an item in the current location.
         * */
        if(alice.isItemInInventory(this) |
                currentLocation.isItemInLocation(this) |
                itemWithInventory.isItemInInventory(this) ) {

            System.out.println(Toolbox.wrapText(this.generateDescription())); //Print's the item's description to the terminal.
        } else{
            System.out.println("You can't examine that!");
        }
    }

    /** This method sets the item's hidden variable to false. */
    public void unHide(){
        this.hidden = false;
    }

    /** This method sets the item's locked variable to false. */
    public void unlock(){
        this.locked = false;
    }

    /**************************
     *   Getters and Setters  *
     **************************/

    /**
     * This method is used to set the item's pickupable variable based on an integer value.
     * @param pickupable This integer represents the locked state of an item.
     *                   If 1, the item can be picked up.
     *                   If 0, the item cannot be picked up.
     */
    public void setPickupable(int pickupable){
        this.pickupable = pickupable == 1 ? true : false;
    }

    /**
     * This method is used to check if an item is pickupable.
     * @return A boolean value that represented if the item can be picked up.
     */
    public boolean isPickupable(){
        return pickupable;
    }

    /** This method is used to set the item's hidden variable based on an integer value.
     * @param hidden This integer represents the visibility of an item.
     *                If 1, the item is hidden.
     *                If 0, the item is visible.
     */
    public void setHidden(int hidden){
        this.hidden= hidden == 1 ? true : false;
    }

    /**
     * This method is used to check if an item is visible.
     * @return A boolean value that represents if the item is visible.
     */
    public boolean isHidden(){
        return hidden;
    }

    /**
     * This method is used to set the item's locked variable based on an integer value.
     * @param locked This integer represents the locked state of an item.
     *               If 1, the item is locked.
     *               If 0, the item is unlocked.
     */
    public void setLock(int locked){
        this.locked = locked == 1 ? true : false;
    }

    /**
     * This method is used to check if an item is locked.
     * @return A boolean value that represents if the item is locked.
     */
    public boolean isLocked(){
        return locked;
    }

    /**
     * This method is used to get the item's useCase.
     * @return The identifier of an action response that is connected to this item.
     */
    public String getActionResponseIdentifier() {
        return actionResponseIdentifier;
    }


}
