/**
 * title: Actor.java
 * description: This class is used to create and manage actors.
 * date: December 15, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Actor Objects
 *
 * Purpose and Description
 *
 * The class is used to create actors. All game objects have an index, identifier, inventory, name,
 * and description. All actors have a current location and dialogue. There are methods get and set
 * these variables. This class includes methods used to import variables associated with actors
 * from an xml file.
 *
 *
 * This program uses the java.util.HashMap and org.w3c.dom.* packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac Actor.java
 * Run:        java WonderlandGame
 * Document:   javadoc Actor.java
 *
 *
 */

/**
 *
 * Classes
 * --------
 *
 * public class Actor
 * This is the primary (public) class for Actor. It extends the parent class GameObject.
 *
 *
 * Actor Constructors
 * ----------------------------
 *
 * public Actor(Location location)
 * This is the default constructor for this class.
 *
 * public Actor(Element eElement, HashMap<String, Location> locations, HashMap<String, Item> itemsInGame)
 * This constructor is used to create actor objects using the attributes stored in an element.
 *
 * Actor Methods
 * ----------------------------
 *
 * public static HashMap<String, Actor> importObjects(String fileName, HashMap<String, Location> locationsInGame, HashMap<String, Item> itemsInGame)
 * This method is used to create actor objects from attributes stored in an xml file. It returns an actor array list to
 * the control class.
 *
 * public Location getCurrentLocation()
 * This method returns the actor's current location.
 *
 * public void setCurrentLocation(Location newLocation)
 * This method sets the actor's current location.
 *
 * public String getDialogue()
 * This method returns the actor's dialogue.
 *
 * public String generateDescription()
 * This method generates a description of the actor with it's description and any item that are in it's inventory.
 *
 * public void talkToActor(Actor usableActor)
 * This method prints the given actor's dialogue to the terminal if the actor is in the same location as alice.
 *
 * public void examineActor(Location currentLocation)
 * This method prints the description of the actor to the terminal if they are in the same location as alice.
 *
 * public void dropItem(Item usableItem)
 * This method moves the given item from alice's inventory into the current location's inventory.
 *
 * public void giveItem(Item usableItem, Actor usableActor)
 * This method moves the given item from alice's inventory to the usableActor's inventory if the action is valid.
 *
 *
 * Actor Instance Variables
 * ----------------------------
 *
 * private Location currentLocation; - This variable holds the actor's location.
 * private String dialogue; - This variable holds the actor's dialogue.
 *
 * ---------------------------------------------------------------------------------------
 *
 */

/**
 * CODE...
 */

/** Java collection framework packages. */
import org.w3c.dom.*;
import java.util.HashMap;

/** This is the primary (public) class for GameObject. It extends the parent class GameObject. */
public class Actor extends GameObject{

    private Location currentLocation; //This variable holds the actor's location.
    private String dialogue; //This variable holds the actor's dialogue.


    /******************
     *  Constructors  *
     ******************/

    /**
     * This is the default constructor for Actor.
     * @param location This is the actor's current location.
     */
    public Actor(Location location){
        super(); //Calls parent classes constructor to initialize inventory.
        this.currentLocation = location;
    }

    /**
     * This constructor is used to create actor objects using the attributes stored in an element.
     * @param eElement    This is the element that contains the actor's attributes.
     * @param locations   This array is used to initialize actor's location given the value in string array.
     * @param itemsInGame This array is used to populate the actor's invenetory given values in the string array.
     */
    public Actor(Element eElement, HashMap<String, Location> locations, HashMap<String, Item> itemsInGame){

        super(eElement); //Calls parent constructor to set actor's identifier, name, description and inventory.

        this.dialogue = eElement.getElementsByTagName("dialogue").item(0).getTextContent();
        currentLocation = locations.get( eElement.getElementsByTagName("currentLocation").item(0).getTextContent() );

        super.getInventory().addImportedItemsToInventory(eElement, itemsInGame); //Adds items to the actor's inventory.

    }

    /*************
     *  Methods  *
     *************/


    /**
     * This method is used to generate actor objects based on an xml file.
     * @param fileName         This is the name of the xml file.
     * @param locationsInGame  This hashmap is used to determine actor's current location based on integer values in the xml file.
     * @param itemsInGame      This hashmap is used to populate the actor's inventory based on integer values in the xml file.
     * @return                 An hashmap of actors held in the control class.
     */
    public static HashMap<String, Actor> importObjects(String fileName,
                                                       HashMap<String, Location> locationsInGame,
                                                       HashMap<String, Item> itemsInGame){
        var actors = new HashMap<String, Actor>();

        /* Calls readXMLFile method from toolbox class to populate a nodeList with the value in the xml file. */
        NodeList nList = Toolbox.readXMLFile(fileName, "actor");

        /* Creates an actor for each node in the list. */
        for(int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                var tempActor = new Actor(eElement, locationsInGame, itemsInGame); //Creates new actor object.
                actors.put(tempActor.getIdentifier(), tempActor); //Adds new actor object to hashmap.
            }
        }
        return actors;
    }

    /**
     * This method generates a description of the actor with it's description and any item that are in it's inventory.
     * @return A string with the full description of the actor.
     */
    public String generateDescription() {

        String output = this.getDescription() + " ";
        String inventoryDescription = this.getInventory().printInventory();
        return inventoryDescription.equals("") ? output : output + "This character is carrying " + inventoryDescription + ".";
    }


    /**
     * This method prints the given actor's dialogue to the terminal if the actor is in the same location as alice.
     * @param usableActor This is the actor alice will talk to.
     * */
    public void talkToActor(Actor usableActor){
        if( usableActor.getCurrentLocation().equals(this.getCurrentLocation()) ){
            System.out.println (Toolbox.wrapText( usableActor.getDialogue() ) );
        } else {
            System.out.println("That character isn't here!");
        }
    }

    /**
     * This method prints the description of the actor to the terminal if they are in the same location as alice.
     * @param currentLocation Alice's current location.
     */
    public void examineActor(Location currentLocation){
        if(this.getCurrentLocation().equals(currentLocation)){
            System.out.println(Toolbox.wrapText(this.generateDescription())); //Prints a description of the actor to the terminal.

        } else {
            System.out.println("That character isn't in this location.");
        }
    }

    /**
     * This method moves the given item from alice's inventory into the current location's inventory.
     * @param usableItem This is the item to be dropped.
     * */
    public void dropItem(Item usableItem){

        /* Checks that given item is in alice's inventory. */
        if( this.isItemInInventory(usableItem) ){
            usableItem.swapInventoryFromAlice(this); //Removes item from alice's inventory and adds it to the current location's inventory.
        } else {
            System.out.println("You don't have that item to drop!");
        }
    }

    /**
     * This method moves the given item from alice's inventory to the usableActor's inventory if the action is valid.
     * @param usableItem  This is the item to be given.
     * @param usableActor This is the actor alice is giving the item to.
     * */
    public void giveItem(Item usableItem, Actor usableActor){

        /* Checks to see if the given item is in alice's inventory. */
        if (this.isItemInInventory(usableItem)) {

            /* Checks to see if the given actor is in the same location as alice. */
            if (usableActor.getCurrentLocation().equals(this.getCurrentLocation())) {

                this.getInventory().removeItemFromInventory(usableItem);
                usableActor.getInventory().addItemToInventory(usableItem);
                System.out.println("You have given the " + usableItem.getName() + " to " + usableActor.getName() + ".");

            } else {
                System.out.println("That character is not present in this location."); //Returns message of actor is not in current location.
            }

        } else {
            System.out.println("You don't have that item to give."); //Returns message if item is not in alice's inventory.
        }

    }

    /**************************
     *   Getters and Setters  *
     **************************/

    /**
     * This method gets the actor's location.
     * @return Actor's current location.
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * This method sets the actor's current location when Alice changes location.
     * @param newLocation The location that the actor moves to.
     */
    public void setCurrentLocation(Location newLocation) {
        this.currentLocation = newLocation;
    }

    /**
     * This method gets an actor's dialogue.
     * @return A string containing the actor's dialogue.
     */
    public String getDialogue() {
        return dialogue;
    }

}
