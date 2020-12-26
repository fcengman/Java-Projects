/**
 * title: Exit.java
 * description: This class creates objects that represent a locations exits.
 * date: December 14, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Location Exits
 *
 * Purpose and Description
 *
 * This class creates objects that represent a locations exits. Each location has four possible exits.
 * An exit object holds a locked value, a location value and a description.
 *
 *
 * This class does not include any additional packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac Exit.java
 * Run:        java WonderlandGame
 * Document:   javadoc Exit.java
 *
 */


/**
 *
 * Classes
 * --------
 *
 * public class Exit
 * This (public) class creates exit objects.
 *
 * Exit Constructors
 * ----------------------------
 *
 * public Exit(int location, int isLocked, String description)
 * This is the default constructor for this class.
 *
 *
 * Exit Methods
 * ----------------------------
 *
 * public String getLocation()
 * This method returns the string identifier of a location that this exit is connected to.
 *
 * public void unlock(ActionResponse currentActionResponse)
 * This method unlocks the exit and sets the current action response to complete.
 *
 * public void setLock(int locked)
 * This method stores a lock state in the locked variable.
 *
 * public String getDescription()
 * This method returns the description of the exit.
 *
 * public int isLocked()
 * This method returns the lock state of the exit.
 *
 *
 * Exit Instance Variables
 * ----------------------------
 *
 * private String location; - Stores a reference string identifier to the location connected to this exit.
 * private boolean locked; - Determines whether the exit is locked or not.
 * private String description; - Stores the description of the exit as a string.
 *
 * ---------------------------------------------------------------------------------------
 */

/** This is the primary (public) class for Control. */
public class Exit {

    private String location; //Stores a reference string identifier to the location connected to this exit.
    private boolean locked; //Determines whether the exit is locked or not.
    private String description; //Stores the description of the exit as a string.

    /******************
     *  Constructors  *
     ******************/


    /**
     * This default constructor creates an Exit object.
     * @param location    This is the location this exit is connected to.
     * @param isLocked    This determines if the exit is locked or not.
     *                    1 represented locked.
     *                    0 represents unlocked.
     * @param description This is the description of the exit.
     */
    public Exit(String location, int isLocked, String description){
        this.location = location;
        setLock(isLocked); //Checks the integer value and sets the variable to true if isLocked is 1, and false if isLocked is 0.
        this.description = description;
    }

    /********************
     *   Exit Methods   *
     ********************/

    /**
     * This method unlocks the exit and sets the current action response to complete.
     * @param currentActionResponse This is the action response connected to this exit.
     * */
    public void unlock(ActionResponse currentActionResponse){
        this.locked = false;
        currentActionResponse.actionComplete();
        System.out.println(Toolbox.wrapText(currentActionResponse.getDescription()));
    }

    /**************************
     *   Getters and Setters  *
     **************************/

    /**
     * This method returns the string identifier of a location that this exit is connected to.
     * @return A location string identifier.
     */
    public String getLocation(){
        return location;
    }

    /**
     * This method sets the lock variable.
     * @param locked This determines if the exit is locked or not.
     *               1 represented locked.
     *               0 represents unlocked.
     */
    public void setLock(int locked){
        this.locked = locked == 1 ? true : false;
    }

    /**
     * This method returns the locked variable.
     * @return A boolean value representing the exit's lock.
     */
    public boolean isLocked(){
        return locked;
    }

    /**
     * This method returns the description of the exit.
     * @return A description of the exit.
     */
    public String getDescription(){
        return description;
    }
}
