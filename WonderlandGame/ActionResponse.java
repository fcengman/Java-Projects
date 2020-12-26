/**
 * title: ActionResponse.java
 * description: This class creates ActionResponse objects that define the action responses in the game.
 * date: December 15, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * ActionResponse Objects
 *
 * Purpose and Description
 *
 *
 * This program uses the Java.util.ArrayList and org.w3c.dom.* packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac ActionResponse.java
 * Run:        java WonderlandGame
 * Document:   javadoc ActionResponse.java
 *
 */

/**
 *
 * Classes
 * --------
 *
 * public class ActionResponse
 * This is the primary (public) class for ActionResponse.
 *
 *
 * ActionResponse  Constructors
 * ----------------------------
 *
 * public ActionResponse(Element eElement, HashMap<String, Item> itemsInGame, HashMap<String, Location> locationsInGame, HashMap<String, Actor> actorsInGame)
 * This constructor create ActionResponse objects based on the attributes stored in a string array.
 *
 * ActionResponse  Methods
 * ----------------------------
 *
 * public static HashMap<String, ActionResponse> importObjects(String fileName, HashMap<String, Item> itemsInGame, HashMap<String, Location> locationsInGame, HashMap<String, Actor> actorsInGame)
 * This method is used to generate ActionResponse objects based on an xml file.
 *
 * public void unlockLocation(Actor alice, RandomMessage badUseCase)
 * This method is used to unlock an exit in a location when an action response is successful.
 *
 * public void unhideItem()
 * This method makes an item visible when an action response is successful.
 *
 * public void loseGame()
 * This method ends the game when a lose game action is successful.
 *
 * public void wordLock(Item usableItem, RandomMessage badUseCase)
 * This method begins a puzzle mini game that requires a string answer from the user.
 *
 * public void addItemActionResponse(Actor alice, Item usableItem, Actor usableActor)
 * This method adds an item to alice's inventory if the action response is successful.
 *
 * public String getIdentifier()
 * This method get the unique identifier connected to the ActionResponse object.
 *
 * public Item getResponseItem()
 * This method is used to get the item to be operated on.
 *
 * public Location getLocationUsed()
 * This method is used to get the location used to trigger an action response.
 *
 * public Location getResponseLocation()
 * This method is used to get the location to be operated on.
 *
 * public String getDescription()
 * This method is used to get a description of the action response.
 *
 * public String getPuzzleAnswer()
 * This method is used to get the answer to a puzzle.
 *
 * public String getActionResponseIdentifier()
 * This method is used to get the reference to the appropriate response to the use case.
 *
 * public boolean isActionComplete()
 * This method is used to check if the use case has been completed.
 *
 * public void actionComplete()
 * This method is used to set the actionComplete to true.
 *
 * public String getTrigger()
 * This method is used to get the action word that triggers the use case.
 *
 * UseCase Instance Variables
 * ----------------------------
 *
 * private String trigger; - Stores single word string trigger value.
 * private Item itemUsed; //Stores the item used.
 * private Item responseItem; - Stores the item to be operated on.
 * private Location locationUsed; - Stores the location used to trigger a use case.
 * private Location responseLocation; - Stores the location to be operated on.
 * private String description; - Stores a description of the response.
 * private String puzzleAnswer; - Stores a answer variable for puzzles.
 * private int useCaseResponse; - Stores the a variable that references an appropriate response to the action.
 * private boolean actionComplete; - Stores a variables that represents if the action has already occurred.
 *
 * ---------------------------------------------------------------------------------------
 */

/**
 * CODE...
 */

/** Java collection framework packages. */
import org.w3c.dom.*;
import java.util.HashMap;
import java.util.Scanner;

/** Primary (public) class for UseCase. */
public class ActionResponse {

    private String trigger; //Stores single word string trigger value.
    private Item itemUsed; //Stores the item used.
    private Item responseItem; //Stores the item to be operated on.
    private Location locationUsed; //Stores the location used to trigger a use case.
    private Location responseLocation; //Stores the location to be operated on.
    private String description; //Stores a description of the response.
    private String puzzleAnswer; //Stores a answer variable for puzzles.
    private String actionResponseIdentifier; //Stores the a variable that references an appropriate response to the action.
    private boolean actionComplete; //Stores a variables that represents if the action has already occurred.
    private String identifier; //Stores a unique string to identify this object.


    /**
     * This constructor create ActionResponse objects based on the attributes stored in a string array.
     * @param eElement        This element contains the attribute of the ActionResponse object.
     * @param itemsInGame     This hashmap is used to initialize the item variables in a UseCase object.
     * @param locationsInGame This hashmap is used to initialize the location variables in a UseCase object.
     * @param actorsInGame    This hashmap is used to initialize the actor variables in a UseCase object.
     */
    public ActionResponse(Element eElement,
                          HashMap<String, Item> itemsInGame,
                          HashMap<String, Location> locationsInGame,
                          HashMap<String, Actor> actorsInGame){

        this.trigger = eElement.getAttribute("trigger");
        this.itemUsed = itemsInGame.get(eElement.getElementsByTagName("itemUsed").item(0).getTextContent());
        this.responseItem = itemsInGame.get(eElement.getElementsByTagName("responseItem").item(0).getTextContent());
        this.locationUsed = locationsInGame.get(eElement.getElementsByTagName("LocationUsed").item(0).getTextContent());
        this.responseLocation = locationsInGame.get(eElement.getElementsByTagName("responseLocation").item(0).getTextContent());
        this.actionResponseIdentifier = eElement.getElementsByTagName("useCaseResponse").item(0).getTextContent();
        this.description = eElement.getElementsByTagName("description").item(0).getTextContent();
        this.puzzleAnswer = eElement.getElementsByTagName("puzzleAnswer").item(0).getTextContent();
        this.identifier = eElement.getElementsByTagName("identifier").item(0).getTextContent();

        this.actionComplete = false; //Sets every ActionResponse object action complete variable to false.
    }

    /*************
     *  Methods  *
     *************/

    /**
     * This method is used to generate ActionResponse objects based on an xml file.
     * @param fileName        This is the name of the xml file.
     * @param itemsInGame     This hashmap is used to initialize ActionResponse's item variables based on integer value in xml file.
     * @param locationsInGame This hashmap is used to initialize ActionResponse's item variables based on integer value in xml file.
     * @param actorsInGame    This hashmap is used to initialize ActionResponse's item variables based on integer value in xml file.
     * @return                An arraylist of UseCases held in the control class.
     */
    public static HashMap<String, ActionResponse> importObjects(String fileName,
                                                                HashMap<String, Item> itemsInGame,
                                                                HashMap<String, Location> locationsInGame,
                                                                HashMap<String, Actor> actorsInGame){

        var actionResponses = new HashMap<String, ActionResponse>();

        /* Calls readXMLFile method from toolbox class to populate a nodeList with the value in the xml file. */
        NodeList nList = Toolbox.readXMLFile(fileName, "useCase");

        /* Creates an actor for each node in the list. */
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                var tempActionResponse = new ActionResponse(eElement, itemsInGame, locationsInGame, actorsInGame); //Creates a new action response object.
                actionResponses.put(tempActionResponse.getIdentifier(), tempActionResponse); //Add new actionResponse to hashmap.

            }
        }
        return actionResponses;
    }

    /**
     * This method is used to unlock an exit in a location when an action response is successful.
     * @param alice      This is the game player.
     * @param badUseCase This is an array of messages to return to the terminal when an action response is unsuccessful.
     */
    public void unlockLocation(Actor alice, RandomMessage badUseCase){

        /* Begins switch statement to check which exit to unlock. */
        switch(alice.getCurrentLocation().checkResponseLocation(this)){
            case "north":
                alice.getCurrentLocation().getNorth().unlock(this);
                break;
            case "east":
                alice.getCurrentLocation().getEast().unlock(this);
                break;
            case "south":
                alice.getCurrentLocation().getSouth().unlock(this);
                break;
            case "west":
                alice.getCurrentLocation().getWest().unlock(this);
                break;
            default:
                badUseCase.printRandomMessage();
        }

    }
    /** This method makes an item visible when an action response is successful. */
    public void unhideItem(){

        System.out.println(Toolbox.wrapText(this.getDescription())); //Gets a description of the successful action response.
        this.getResponseItem().unHide(); //Makes the response item visible.
        this.actionComplete(); //Sets action to complete.
    }

    /** This method ends the game when a lose game action is successful. */
    public void loseGame(){
        System.out.println(Toolbox.wrapText(this.getDescription())); //Gets a description of the successful action response.
        System.out.println();
        System.out.println("Sorry, you've lost the game. Please try again."); //Prints final message to the terminal.
    }

    /**
     * This method begins a puzzle mini game that requires a string answer from the user.
     * @param usableItem This is the item that initites the action response.
     * @param badUseCase This is an array of messages to return to the terminal when an action response is unsuccessful.
     */
    public void wordLock(Item usableItem, RandomMessage badUseCase){

        /* Asks user for input. */
        System.out.println("Enter the word to unlock the " + usableItem.getName() + ":");
        System.out.print("> ");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine().toLowerCase().trim(); //Reads user input.

        /* Checks the user's response against the puzzle answer stored in the ActionResponse object. */
        if(input.equals(this.getPuzzleAnswer())) {

            System.out.println(Toolbox.wrapText(this.getDescription())); //Gets a description of the successful action response.
            usableItem.unlock(); //Unlocks the given item.
            this.actionComplete(); //Sets this action to complete.

            /* Makes any items in the given item's inventory visible. */
            for(int i = 0; i < usableItem.getInventory().getInventoryList().size(); i++){
                usableItem.getInventory().getInventoryList().get(i).unHide();
            }

        } else{
            badUseCase.printRandomMessage(); //Prints error message to terminal if action response is unsuccessful.
        }
    }

    /**
     * This method adds an item to alice's inventory if the action response is successful.
     * @param alice       This is the game player.
     * @param usableItem  This is the given item in the user input.
     * @param usableActor This is the given actor in the user input.
     */
    public void addItemActionResponse(Actor alice, Item usableItem, Actor usableActor){

        System.out.println(Toolbox.wrapText(this.getDescription())); //Gets a description of the successful action response.
        this.getResponseItem().unHide(); //Makes the response item visible.

        /* If the response item is in the current location, then that item is removed from the location. */
        if(alice.getCurrentLocation().isItemInLocation(this.getResponseItem())) {
            alice.getCurrentLocation().getInventory().removeItemFromInventory(this.getResponseItem());

        /* If the response item is in the given item's inventory, then that item is removed from the given item's inventory. */
        } else if(usableItem.isItemInInventory(this.getResponseItem())){
            usableItem.getInventory().removeItemFromInventory(this.getResponseItem());

        /* If the response item is in the given itemUsed's inventory, then that item is removed from the given itemUsed's inventory. */
        } else if(itemUsed.isItemInInventory(this.getResponseItem())){
            itemUsed.getInventory().removeItemFromInventory(this.getResponseItem());

        /* If given item is in the given actor's inventory, then that item is removed from their inventory. */
        } else if( usableActor.isItemInInventory(this.getResponseItem()) ){
            usableActor.getInventory().removeItemFromInventory(this.getResponseItem());
        }

        alice.getInventory().addItemToInventory(this.getResponseItem()); //Adds response item to alice's inventory.
        this.actionComplete(); //Sets the ActionResponse to complete.
        System.out.println ("The " + this.getResponseItem().getName() + " has been added to your inventory.");
    }


    /**************************
     *   Getters and Setters  *
     **************************/


    /**
     * This method get the unique identifier connected to the ActionResponse object.
     * @return The ActionResponse object's unique identifier.
     */
    public String getIdentifier(){
        return identifier;
    }

    /**
     * This method is used to get the item to be operated on.
     * @return The item to be operated on.
     */
    public Item getResponseItem() {
        return responseItem;
    }

    /**
     * This method is used to get the location used to trigger an action response.
     * @return The location that triggers an action response.
     */
    public Location getLocationUsed() {
        return locationUsed;
    }

    /**
     * This method is used to get the location to be operated on.
     * @return The location to be operated on.
     */
    public Location getResponseLocation() {
        return responseLocation;
    }

    /**
     * This method is used to get a description of the action response.
     * @return A description of the action response.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method is used to get the answer to a puzzle.
     * @return A string answer to a puzzle.
     */
    public String getPuzzleAnswer() {
        return puzzleAnswer;
    }

    /**
     * This method is used to get the reference to the appropriate response to the use case.
     * @return An integer value that represents the response to the use case.
     */
    public String getActionResponseIdentifier() {
        return actionResponseIdentifier;
    }

    /** This method is used to check if the use case has been completed. */
    public boolean isActionComplete() {
        return actionComplete;
    }

    /** This method is used to set the actionComplete to true. */
    public void actionComplete() {
        this.actionComplete = true;
    }

    /**
     * This method is used to get the action word that triggers the use case.
     * @return A string action word.
     */
    public String getTrigger() {
        return trigger;
    }
}
