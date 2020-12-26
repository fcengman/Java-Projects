/**
 * title: Control.java
 * description: This class is used to manage the game loop.
 * date: December 18, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Game Controller
 *
 * Purpose and Description
 *
 * The class is used to manage the flow of the game. It reads, validates and resolves user input. It manages the object's
 * in the game by moving items in and out of inventories, moving the main player around the map, and resolving the interaction
 * of items with the objects in the world.
 *
 * This program uses the java.io.File, java.io.FileNotFoundException, java.util.ArrayList, java.util.HashMap and
 * java.util.Scanner packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac Control.java
 * Run:        java WonderlandGame
 * Document:   javadoc Control.java
 *
 *
 */

/**
 *
 * Classes
 * --------
 *
 * public class Control
 * This is the primary (public) class for Control.
 *
 *
 * Control Constructors
 * ----------------------------
 *
 * public Control()
 * This is the default constructor for Control. It initializes the game object based on xml text files.
 *
 * Control Methods
 * ----------------------------
 *
 * public void start()
 * This method starts the game by printing the introduction to the terminal.
 *
 * public void readInput()
 * This method reads input from the user and check that it is a valid input.
 *
 * private boolean validateInput(String[] userInput)
 * This method is used to validate the user input.
 *
 * public void resolveInput()
 * This method is used to determine how the commands from the user should be resolved.
 *
 * public void end()
 * This method prints the contents of the end text file to the terminal.
 *
 * private void resolveAction()
 * This method is used to resolve single word commands from the user.
 *
 * private void changeLocation(Exit exitLocation)
 * This method check to see that moving in the given direction is valid and moves alice to a new location.
 *
 * private void resolveActionOnObject()
 * This method resolves the user command when there is more than one command.
 *
 * private void resolveGetItem()
 * This method checks to see if getting the given item is valid and then performs operations on object inventories.
 *
 * private void useItem()
 * This method resolves the use action based on the item's action response identifier.
 *
 * private boolean checkActionResponse()
 * This method checks the action response conditions connected to an item and performs the appropriate action.
 *
 * public boolean getContinueGame()
 * This method is used to get the state of continue game.
 *
 * public void setContinueGame(boolean continueGame)
 * This method is used to set the state of the game.
 *
 * public WinCondition getWinCondition()
 * This method is used to get the winCondtion object.
 *
 * Control Instance Variables
 * ----------------------------
 *
 * private HashMap<String, Action> actionsInGame; - Stores the Actions in the game in a hashmap.
 * private HashMap<String, Location> locationsInGame; - Stores the locations in the game in a hashmap.
 * private HashMap<String, Actor> actorsInGame; - Stores the actors in the game in a hashmap.
 * private HashMap<String, Item> itemsInGame; - Stores the items in the game in a hashmap.
 * private HashMap<String, ActionResponse> actionResponses; - Stores the useCases in the game in a hashmap.
 *
 * private Action usableAction; - Stores the current action from the user.
 * private Item usableItem; - Stores the current item from the user.
 * private Actor usableActor; - Stores the current actor for the user
 *
 * private RandomMessage badInput  = new RandomMessage(); - Stores error messages when user does not input correct instructions.
 * private RandomMessage badUseCase  = new RandomMessage(); - Stores error messages when use case fails.
 *
 * private boolean continueGame = true; - Stores variable that maintains loop of the game.
 * private boolean winCondition = false; - Stores variable based on if the player has met the win condition.
 *
 * private WinCondition winCondition; - Stores the condition of the game that triggers the good ending.
 *
 * private Actor alice; - Stores the player object.
 *
 * -----------------------------------------------------------------------------------------------------------------------------------
 *
 *
 */

/**
 * CODE...
 */

/** Java collection framework packages. */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/** This is the primary (public) class for Control. */
public class Control {

    private HashMap<String, Action> actionsInGame; //Stores the Actions in the game in a hashmap.
    private HashMap<String, Location> locationsInGame; //Stores the locations in the game in a hashmap.
    private HashMap<String, Actor> actorsInGame; //Stores the actors in the game in a hashmap.
    private HashMap<String, Item> itemsInGame; //Stores the items in the game in a hashmap.
    private HashMap<String, ActionResponse> actionResponses; //Stores the useCases in the game in a hashmap.

    private Action usableAction; //Stores the current action from the user.
    private Item usableItem; //Stores the current item from the user.
    private Actor usableActor; //Stores the current actor for the user

    private RandomMessage badInput  = new RandomMessage(); //Stores error messages when user does not input correct instructions.
    private RandomMessage badActionResponses = new RandomMessage(); //Stores error messages when use case fails.

    private WinCondition winCondition; //Stores the condition of the game that triggers the good ending.

    private boolean continueGame = true; //Stores variable that maintains loop of the game.

    private Actor alice; //Stores the player object.

    /******************
     *  Constructors  *
     ******************/

    /** This is the default constructor for Control. It initializes the game object based on xml text files. */
    public Control(){

        /* Import object elements of the game. */
        actionsInGame = Action.importObjects("actions.xml");
        itemsInGame = Item.importObjects("items.xml");
        Item.addItemsToItemsInGame("items_with_inventory.xml", itemsInGame);
        locationsInGame = Location.importObjects("locations.xml", itemsInGame);
        actorsInGame = Actor.importObjects("actors.xml", locationsInGame, itemsInGame);
        actionResponses = ActionResponse.importObjects("action_responses.xml", itemsInGame, locationsInGame, actorsInGame);

        /* Import error message to the game. */
        badInput.importRandomMessages("bad_input.txt");
        badActionResponses.importRandomMessages("bad_use_cases.txt");

        winCondition = new WinCondition("win_condition.xml", itemsInGame, actorsInGame);

        /* Create a new player character and put them in the starting location. */
        alice = new Actor(locationsInGame.get("start"));
    }

    /***********************
     *  Game Loop Methods  *
     ***********************/

    /** This method starts the game by printing the introduction to the terminal. */
    public void start(){
        /* Tries to read start text file. */
        try(Scanner scan = new Scanner( new File( "start.txt"))) {

            /* Prints introduction to the terminal. */
            while(scan.hasNextLine()){
                System.out.println(Toolbox.wrapText(scan.nextLine()));
            }
            /* Prints the description of the initial location to the terminal. */
            System.out.println();
            System.out.println(Toolbox.wrapText(alice.getCurrentLocation().generateDescription()));
            alice.getCurrentLocation().locationFound();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /** This method reads input from the user and check that it is a valid input. */
    public void readInput(){

        boolean validInput = false;  //Ensures loop will continue until user enters valid input.
        String[] userInput; //Holds user input in an array.
        Scanner scan = new Scanner(System.in);

        /* Continues to check user input until valid. */
        while(!validInput){
            try{

                String input = scan.nextLine().toLowerCase().trim(); //Store user input and converts to lower case.
                userInput = input.split(" "); //Converts user input to string array.

                /* Reset game action variables. */
                usableAction = actionsInGame.get("none");
                usableItem = itemsInGame.get("none");
                usableActor = actorsInGame.get("none");

                /* Checks that input is valid. */
                 if( validateInput(userInput) ){
                    badInput.printRandomMessage();
                    System.out.print("> ");
                } else {
                    validInput = true; //Breaks loop if user inputs valid input.
                }
            } catch (Exception e) {
                System.out.println("Try again.");
            }
            scan.reset(); //Resets scanner for next attempt.
        }
    }

    /**
     * This method is used to validate the user input.
     * @param userInput  This string array holds the input from the player.
     * @return           Returns true if valid commands are found in the string array.
     */
    private boolean validateInput(String[] userInput){

        /* Checks if the user input contains valid action word. */
        for(String s : userInput) {
            if(actionsInGame.containsKey(s)){
                usableAction = actionsInGame.get(s);
                break;
            }
        }

        /* Checks if the user input contains valid item word. */
        for(String s : userInput) {
            if(itemsInGame.containsKey(s)){
                usableItem = itemsInGame.get(s);
                break;
            }
        }
        /* Checks if the user input contains valid actor words. */
        for(String s : userInput){
            if(actorsInGame.containsKey(s)){
                usableActor = actorsInGame.get(s);
                break;
            }
        }

        return usableAction.getTypeOfAction().equals("none") ? true : false; //Returns true if action word is found.
    }

    /** This method is used to determine how the commands from the user should be resolved. */
    public void resolveInput(){

        /* Checks if user input was equal to the quit command. */
        if(usableAction.equals(actionsInGame.get("q")) | usableAction.equals(actionsInGame.get("quit"))){
            this.continueGame = false;
            System.out.println("Thank you for playing.");

        /* Checks if user input contains a single action word. */
        } else if(usableActor.getIdentifier().equals("none") & usableItem.getIdentifier().equals("none")){
            this.resolveAction();
       /* Otherwise user input contains one action word and at least one item or actor word. */
        } else {
            this.resolveActionOnObject();
        }
    }

    /** This method prints the contents of the end text file to the terminal. */
    public void end(){
        /* Tries to read the end text file. */
        try(Scanner scan = new Scanner( new File( "end.txt"))) {
            System.out.println(Toolbox.wrapText(scan.nextLine()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /************************
     * Action Word Methods  *
     ************************/

    /** This method is used to resolve single word commands from the user. */
    private void resolveAction() {

        /* Initiates switch statement for action index stored in usableAction. */
        switch (usableAction.getTypeOfAction()) {
            case "north":
            case "n":
                changeLocation(alice.getCurrentLocation().getNorth()); //Passes the location identifier stored in north field of the location that alice is currently in.
                break;
            case "east":
            case "e":
                changeLocation(alice.getCurrentLocation().getEast());
                break;
            case "south":
            case "s":
                changeLocation(alice.getCurrentLocation().getSouth());
                break;
            case "west":
            case "w":
                changeLocation(alice.getCurrentLocation().getWest());
                break;
            case "look":
                alice.getCurrentLocation().look();
                break;
            case "inventory":
            case "inven":
            case "i":
                alice.getInventory().printAliceInventory();
                break;
            default:
                badInput.printRandomMessage(); //Prints error message in default case.
        }
    }

    /**
     * This method check to see that moving in the given direction is valid and moves alice to a new location.
     * @param exitLocation  This is the direction that alice is attempting to move in.
     */
    private void changeLocation(Exit exitLocation) {

        Location newLocation = locationsInGame.get(exitLocation.getLocation()); // Stores the location that the given exit is pointing to in a temporary variable.

        /* Checks to see if there is an exit in this direction. */
        if (exitLocation.getLocation().equals("none")) {
            System.out.println("You can't go that way!");

            /* Checks to see if the door is locked in this direction. */
        } else if (exitLocation.isLocked()) {
            System.out.println("The door in this direction is locked!");

        /* Checks to see if entering this location triggers an ending. */
        } else if (newLocation.isLoseGame()) {
                newLocation.descriptionWhenChangedLocation();
                this.continueGame = false;

        /* If there is a valid direction in the current location's direction field and it is unlocked, alice moves to that location. */
        } else {
            alice.setCurrentLocation(newLocation); //Moves alice to the new location.
            newLocation.descriptionWhenChangedLocation();
        }

    }

    /*******************************
     *   Action On Object Methods  *
     *******************************/

    /** This method resolves the user command when there is more than one command. */
    private void resolveActionOnObject() {

        /* Initiates switch statement for action index stored in usableAction for actions that require an item or actor */
        switch (usableAction.getTypeOfAction()) {

            case "examine":
                if(usableItem.equals(itemsInGame.get("none"))){
                    usableActor.examineActor(alice.getCurrentLocation());
                } else {
                    usableItem.examineItem(alice, alice.getCurrentLocation());
                }
                break;

            case "talk":
                alice.talkToActor(usableActor);
                break;

            case "get":
                resolveGetItem();
                if(alice.isItemInInventory(usableItem))
                    this.checkActionResponse();  //Checks to see if getting this item triggers an action response.
                break;

            case "drop":
                alice.dropItem(usableItem);
                if(alice.getCurrentLocation().isItemInLocation(usableItem))
                    this.checkActionResponse(); //Checks to see if dropping the item triggers an action response.
                break;

            case "give":
                alice.giveItem(usableItem, usableActor);
                if(usableActor.isItemInInventory(usableItem))
                    this.checkActionResponse(); //Checks to see if giving the item to this actor triggers an action response.
                break;

            case "use":
                useItem();
                break;

                default:
                badInput.printRandomMessage();
        }
    }

    /** This method checks to see if getting the given item is valid and then performs operations on object inventories. */
    private void resolveGetItem(){
        Location currentLocation = alice.getCurrentLocation();
        Item itemWithInventory = currentLocation.getInventory().getItemWithInventory();

        /* Checks to see if the item can be picked up. */
        if( !usableItem.isPickupable() ){
            System.out.println("You can't pick that up!");

        /* Checks to see if the item is hidden */
        } else if(usableItem.isHidden()){
            badInput.printRandomMessage();

        } else if( !currentLocation.isItemInLocation(usableItem) ) {

            /* Checks to see if the character is in the same location as alice and has the item. */
            if(!( usableActor.getCurrentLocation().equals(currentLocation) & usableActor.isItemInInventory(usableItem) ) ){


                if( !itemWithInventory.isItemInInventory(usableItem) ){
                    if(alice.isItemInInventory(usableItem)){
                        System.out.println("You are already carrying that item.");
                    } else {
                        System.out.println("You can't get that!");
                    }
                /* The item is added to Alice's inventory, and removed from the item's inventory */
                } else{
                    usableItem.swapInventoryToAlice(alice, itemWithInventory);
                }

            /* The item is added to Alice's inventory, and removed from the actor's inventory. */
            } else {
                usableItem.swapInventoryToAlice(alice, usableActor);
            }

        /* The item is added to Alice's inventory, and removed from the inventory in the room. */
        } else {
            usableItem.swapInventoryToAlice(alice, currentLocation);
        }
    }

    /** This method resolves the use action based on the item's action response identifier. */
    private void useItem(){
        /* Checks that the item has an action response. */
        if(usableItem.getActionResponseIdentifier().equals("none")){
            badActionResponses.printRandomMessage();

        /* Checks to see if the given item is in alice's inventory or a item with an inventory is in the current location. */
        } else if( alice.isItemInInventory(usableItem) |
                ( alice.getCurrentLocation().isItemInLocation(usableItem) & !usableItem.getInventory().getInventoryList().isEmpty() ) ){

            /* Checks to see if the valid action for the given item has already been done. */
            if(actionResponses.get(usableItem.getActionResponseIdentifier()).isActionComplete()) {
               System.out.println("You've already used this item in the correct way.");

          /* Checks the action response conditions of the given item. */
           } else if(alice.isItemInInventory(usableItem) & !checkActionResponse() ){
               badActionResponses.printRandomMessage();
           }

        /* Checks to see if given item is in alice's inventory. */
        }else if(!alice.isItemInInventory(usableItem)){
            System.out.println("You are not carrying that item.");
        }

    }

    /**
     * This method checks the action response conditions connected to an item and performs the appropriate action.
     * @return True, if the action is successful.
     *         False. if the action is unsuccessful.
     */
    private boolean checkActionResponse(){

        ActionResponse currentActionResponse = actionResponses.get( usableItem.getActionResponseIdentifier() ); //Stores an action response in a temporary variable based on the actionResponseIndex stored in the given item.

        /* If given item is in the current location, in the current actor's inventory, or in alice's inventory,
           and the current location and action trigger words match that of the current action response, and the
           action has not already been performed. Then proceed with resolving the action response.
         */
        if(currentActionResponse.getLocationUsed().equals( alice.getCurrentLocation() ) &
                currentActionResponse.getTrigger().equals( usableAction.getTypeOfAction() ) &
                !currentActionResponse.isActionComplete()  ) {

            switch (currentActionResponse.getActionResponseIdentifier()) {
                /* Unlocks a locked exit in the current location. */
                case "unlock":
                    currentActionResponse.unlockLocation(alice, badActionResponses);
                    return true;

                /* Unhides the response item of this action response. */
                case "unhide":
                    currentActionResponse.unhideItem();
                    return true;

               /* Prints lose condition description and sets continue to false to end game loop. */
                case "lose_game":
                    currentActionResponse.loseGame();
                    this.setContinueGame(false); //Sets continue game to false to end game.
                    return true;

                /* Begins puzzle mini game that requires a word to unlock the given item. */
                case "word_puzzle":
                    currentActionResponse.wordLock(usableItem, badActionResponses);
                    return true;

                /* Moves item into alice's inventory. */
                case "add_item_to_player_inventory":
                    currentActionResponse.addItemActionResponse(alice, usableItem, usableActor);
                    return true;

                default:
                    badActionResponses.printRandomMessage();
            }
        }
        return false; //Returns false if resolving the action response was unsuccessful.
    }

    /**************************
     *   Getters and Setters  *
     **************************/

    /**
     * This method is used to get the state of continue game.
     * @return  Returns a boolean value representing the state of the game.
     */
    public boolean getContinueGame() {
        return continueGame;
    }

    /**
     * This method is used to set the state of the game.
     * @param continueGame A boolean value that represents if the game should continue.
     */
    public void setContinueGame(boolean continueGame) {
        this.continueGame = continueGame;
    }

    /**
     * This method is used to get the winCondtion object.
     * @return A winCondition object.
     */
    public WinCondition getWinCondition() {
        return winCondition;
    }
}

