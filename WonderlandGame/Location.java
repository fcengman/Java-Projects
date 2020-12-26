/**
 * title: Location.java
 * description: This class is used to create and manage Location.
 * date: December 15, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Location Objects
 *
 * Purpose and Description
 *
 * The class is used to create Items. All game objects have an index, identifier, inventory, name,
 * and description. All locations have four exits (north, east, south, and west), and a boolean value
 * that represents if the locations has been visited already.This class includes methods used to import
 * and create items based on the attributes stored in an xml file. There is also a method that generates
 * a description of the location when entered for the first time or the the look method is used in the
 * control class.
 *
 *
 * This program uses the Java.util.Hashmap and org.w3c.dom.* packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac Location.java
 * Run:        java WonderlandGame
 * Document:   javadoc Location.java
 *
 */

/**
 *
 * Classes
 * --------
 *
 * public class Location extends GameObject
 * This is the primary (public) class for Items.
 *
 *
 * Location Constructors
 * ----------------------------
 *
 * public Location(String[] info, ArrayList<Item> itemsInGame)
 * This constructor creates a location based on the attributes stored in a xml file
 *
 * Location Methods
 * ----------------------------
 *
 * public static ArrayList<Location> importObjects(String fileName, ArrayList<Item> itemsInGame)
 * This method is used to create locations based on the attributes stored in a xml file.
 *
 * public boolean isItemInLocation(Item usableItem)
 * This method check if a given item is in the location's inventory.
 *
 * public String generateDescription()
 * This method is used to generate a description of the location including the location's description, the location exit's
 * descriptions, and the location's inventory.
 *
 * public String checkResponseLocation(ActionResponse currentActionResponse)
 * This method checks to see which direction the action response should resolve.
 *
 * public void look()
 * This method resolves the look command.
 *
 * private void setLoseGame(int loseGame)
 * This method sets the lose game condition.
 *
 * public Exit getNorth()
 * This method is used to get the exit in the north direction.
 *
 * public Exit getEast()
 * This method is used to get the exit in the east direction.
 *
 * public Exit getSouth()
 * This method is used to get the exit in the south direction.
 *
 * public Exit getWest()
 * This method is used to get the exit in the west direction.
 *
 * public boolean isLocationFound()
 * This method is used to check if the location has been visited.
 *
 * public void locationFound()
 * This method is used to set the location to found.
 *
 * public boolean isLoseGame()
 * This method gets the loseGame variable.
 *
 * private void setLoseGame(int loseGame)
 * This method sets the lose game condition.
 *
 * Location Instance Variables
 * ----------------------------
 *
 * private Exit north; - This variables stores the exit in the north direction of the location.
 * private Exit east; - This variables stores the exit in the north direction of the location.
 * private Exit south; - This variables stores the exit in the north direction of the location.
 * private Exit west; - This variables stores the exit in the north direction of the location.
 * private boolean locationFound; - This variable indicates if the player has visited the location.
 * private boolean loseGame; //Determines if going to this location triggers an ending.
 *
 * ---------------------------------------------------------------------------------------
 */

/**
 * CODE...
 */

/** Java collection framework packages. */
import org.w3c.dom.*;
import java.util.HashMap;

/** Primary (public) class for RandomMessages. */
public class Location extends GameObject{

    private Exit north; //This variables stores the exit in the north direction of the location.
    private Exit east; //This variables stores the exit in the north direction of the location.
    private Exit south; //This variables stores the exit in the north direction of the location.
    private Exit west; //This variables stores the exit in the north direction of the location.
    private boolean locationFound; //This variable indicates if the player has visited the location.
    private boolean loseGame; //Determines if going to this location triggers an ending.

    /******************
     *  Constructors  *
     ******************/

    /**
     * This constructor creates a location based on the attributes stored in a xml file.
     * @param eElement     This element contains attributes of the location object.
     * @param itemsInGame  This array is used to fill the location's array based on attributes in the xml file.
     */
    public Location(Element eElement, HashMap<String, Item> itemsInGame){
        super(eElement); //Calls the parent classes constructor to set identifier, name, and description variables.

        /* Creates a new exit object for each of the location's directions. */
        this.north = new Exit(eElement.getElementsByTagName("north").item(0).getTextContent(),
                Integer.parseInt(eElement.getElementsByTagName("northLocked").item(0).getTextContent()),
                eElement.getElementsByTagName("northDescription").item(0).getTextContent());
        this.east = new Exit(eElement.getElementsByTagName("east").item(0).getTextContent(),
                Integer.parseInt(eElement.getElementsByTagName("eastLocked").item(0).getTextContent()),
                eElement.getElementsByTagName("eastDescription").item(0).getTextContent());
        this.south = new Exit(eElement.getElementsByTagName("south").item(0).getTextContent(),
                Integer.parseInt(eElement.getElementsByTagName("southLocked").item(0).getTextContent()),
                eElement.getElementsByTagName("southDescription").item(0).getTextContent());
        this.west = new Exit(eElement.getElementsByTagName("west").item(0).getTextContent(),
                Integer.parseInt(eElement.getElementsByTagName("westLocked").item(0).getTextContent()),
                eElement.getElementsByTagName("westDescription").item(0).getTextContent());

        super.getInventory().addImportedItemsToInventory(eElement, itemsInGame); //Adds items to location's inventory.

        this.setLoseGame(Integer.parseInt(eElement.getElementsByTagName("loseGame").item(0).getTextContent()));

        locationFound = false; //All locations have not been found at the beginning of the game.

    }

    /*************
     *  Methods  *
     *************/

    /**
     * This method is used to create locations based on the attributes stored in a xml file.
     * @param fileName     This is the name of the xml file.
     * @param itemsInGame  This array is used to pupulate a location's inventory.
     * @return             An arraylist of items held in the control class.
     */
    public static HashMap<String, Location> importObjects(String fileName, HashMap<String, Item> itemsInGame){
        var locations = new HashMap<String, Location>();

        /* Calls readXMLFile method from toolbox class to populate a nodeList with the value in the xml file. */
        NodeList nList = Toolbox.readXMLFile(fileName, "location");

        /* Creates an item for each node in the list. */
        for(int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                var tempLocation = new Location(eElement, itemsInGame); // Creates new location object.
                locations.put(tempLocation.getIdentifier(), tempLocation); // Adds new location to location hashmap.
            }
        }
        return locations;
    }

    /**
     * This method check if a given item is in the location's inventory.
     * @param usableItem Item used to check location's inventory.
     * @return           Returns true if usableItem is in the location's inventory.
     */
    public boolean isItemInLocation(Item usableItem){
        return this.getInventory().getInventoryList().contains(usableItem) ? true : false;
    }

    /**
     * This method is used to generate a description of the location including the location's description, the location exit's
     * descriptions, and the location's inventory.
     * @return A string of the full description of the location.
     */
    public String generateDescription() {

        String output = this.getDescription() + " ";
        output = !this.getNorth().getLocation().equals("none") ? output + "North of you there is " + this.getNorth().getDescription() + ". " : output;
        output = !this.getEast().getLocation().equals("none") ? output + "To the east looks like " + this.getEast().getDescription() + ". " : output;
        output = !this.getSouth().getLocation().equals("none") ? output + "The southern route leads to " + this.getSouth().getDescription() + ". " : output;
        output = !this.getWest().getLocation().equals("none") ? output + "To the west you see " + this.getWest().getDescription() + ". " : output;
        output = !this.getInventory().getInventoryList().isEmpty() ? output + "Nearby there is " + this.getInventory().printInventory() + " you can interact with." : output;

        return output;
    }

    /** This method generates a description when alice moves between rooms. */
    public void descriptionWhenChangedLocation() {

        /* Checks to see if alice has been in this location before. */
        if (!(this.isLocationFound())) {
            this.locationFound(); //Changes locationFound variable to true if this is Alice's first time in the location.
            System.out.println( Toolbox.wrapText( this.generateDescription() ) ); //Prints description of the location.

            /* When alice has already been in a locations, prints a different message. */
        } else {
            System.out.println("You are at the " + this.getName() + " again.");
        }
    }

    /**
     * This method checks to see which direction the action response should resolve.
     * @param currentActionResponse This is teh current action response being resolved.
     * @return                      A string representing the direction.
     */
    public String checkResponseLocation(ActionResponse currentActionResponse) {
        String output = "";
        String actionResponseLocation = currentActionResponse.getResponseLocation().getIdentifier();

        if(this.getNorth().getLocation().equals(actionResponseLocation)){
            output = "north";
        } else if(this.getEast().getLocation().equals(actionResponseLocation)){
            output = "east";
        } else if(this.getSouth().getLocation().equals(actionResponseLocation)){
            output = "east";
        } else if(this.getWest().getLocation().equals(actionResponseLocation)){
            output = "east";
        }
        return output;
    }

    /** This method resolves the look command. */
    public void look() {
        System.out.println( Toolbox.wrapText(this.generateDescription() ) ); //Prints the description of the location that alice is currently in.
    }

    /**************************
     *   Getters and Setters  *
     **************************/

    /**
     * This method is used to get the exit in the north direction.
     * @return The location's north exit.
     */
    public Exit getNorth() {
        return north;
    }

    /**
     * This method is used to get the exit in the east direction.
     * @return The location's east exit.
     */
    public Exit getEast() {
        return east;
    }

    /**
     * This method is used to get the exit in the south direction.
     * @return The location's south exit.
     */
    public Exit getSouth() {
        return south;
    }

    /**
     * This method is used to get the exit in the west direction.
     * @return The location's west exit.
     */
    public Exit getWest() {
        return west;
    }

    /**
     * This method is used to check if the location has been visited.
     * @return A boolean value that represents if the location has been visited.
     */
    public boolean isLocationFound() {
        return locationFound;
    }

    /** This method is used to set the location to found. */
    public void locationFound() {
        this.locationFound = true;
    }

    /**
     * This method gets the loseGame variable.
     * @return A boolean that represents if the location triggers an ending.
     */
    public boolean isLoseGame() {
        return loseGame;
    }

    /**
     * This method sets the lose game condition.
     * @param loseGame This integer represents if entering this location triggers an ending.
     *                 If 1, the location triggers an ending.
     *                 If 0, the location does not triggr an ending.
     */
    private void setLoseGame(int loseGame) {
        this.loseGame= loseGame == 1 ? true : false;
    }
}
