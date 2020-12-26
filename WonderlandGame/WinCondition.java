/**
 * title: WinCondition.java
 * description: This class is used create and store the win condtion of the game.
 * date: December 19, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * WinCondition Object
 *
 * Purpose and Description
 *
 * This class creates a WinCondition object that stores the conditions under which the game can be won.
 *
 * This program uses the org.w3c.dom.NodeList, org.w3c.dom.Element, and java.util.Scanner packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac WinCondition.java
 * Run:        java WonderlandGame
 * Document:   javadoc WinCondition.java
 *
 */

/**
 *
 * Classes
 * --------
 *
 * public class WinCondition
 * This is the primary (public) class for WinCondition.
 *
 *
 * WinCondition Constructors
 * ----------------------------
 *
 * public WinCondition(String fileName, HashMap<String, Item> itemsInGame, HashMap<String, Actor> actorInGame)
 * This constructor creates a WinCondition object based on the attributes in a xml file.
 *
 * WinCondition Methods
 * ----------------------------
 *
 * public boolean checkWinCondition()
 * This method check to see if the win condition has been met.
 *
 * WinCondition Instance Variables
 * --------------------------------
 *
 *  private Inventory winInventory = new Inventory(); //This stores a list of items that trigger the win condition.
 *  private Actor winActor; //This stores the action whose inventory will be tested against winInventory.
 *
 * ---------------------------------------------------------------------------------------
 */

/**
 * CODE...
 */

/** Java collection framework packages. */
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.HashMap;

/** This is the primary (public) class for WinCondition. */
public class WinCondition {

    private Inventory winInventory = new Inventory(); //This stores a list of items that trigger the win condition.
    private Actor winActor; //This stores the action whose inventory will be tested against winInventory.

    /**
     * This constructor creates a WinCondition object based on the attributes in a xml file.
     * @param fileName    This is the filename of the xml file.
     * @param itemsInGame This is a hashmap containing the items in the game used to populate the WinCondition's inventory.
     * @param actorInGame This is a hashmap containing the actors in the game use to initialize the winActor variable.
     */
    public WinCondition(String fileName, HashMap<String, Item> itemsInGame, HashMap<String, Actor> actorInGame){

        NodeList nList = Toolbox.readXMLFile(fileName, "winCondition");
        Element eElement = (Element) nList.item(0);
        this.winInventory.addImportedItemsToInventory(eElement, itemsInGame); //Adds items to the actor's inventory.
        this.winActor = actorInGame.get(eElement.getElementsByTagName("actor").item(0).getTextContent());
    }

    /**
     * This method check to see if the win condition has been met.
     * @return True if win condition has been met.
     */
    public boolean checkWinCondition(){

        /* Checks to see if the rabbit is holding the items in his inventory that satisfies the win condition. */
        return winActor.getInventory().getInventoryList().containsAll(winInventory.getInventoryList());
    }

}
