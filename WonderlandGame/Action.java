/**
 * title: Action.java
 * description: This class holds the actions a player can take.
 * date: December 15, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Player Actions
 *
 * Purpose and Description
 *
 * This class holds the actions a player can take. It is used to match a string value from the user input to
 * an action object.
 *
 *
 * This class uses  org.w3c.dom.* and java.util.HashMap packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac Action.java
 * Run:        java WonderlandGame
 * Document:   javadoc Action.java
 *
 */


/**
 *
 * Class
 * --------
 *
 * public class Action
 * This the primary (public) class for Action
 *
 * Action Constructors
 * ----------------------------
 *
 * Action(String typeOfAction)
 * This is the default constructor for Action.
 *
 *
 * Action Methods
 * ----------------------------
 *
 * public static HashMap<String, Action> importObjects(String fileName)
 * This method imports action objects from attributes stored in a xml file.
 *
 * public String getTypeOfAction()
 * This method is used to get the type of action from an enum Action.
 *
 *
 * Action Instance Variables
 * ----------------------------
 *
 * public String typeOfAction; - This variable stores the string variable to match user input to Action.
 *
 * ---------------------------------------------------------------------------------------
 */

/**
 * CODE...
 */

/** Java collection framework packages. */
import org.w3c.dom.*;
import java.util.HashMap;

/** This the primary (public) class for Action */
public class Action{


    public String typeOfAction; //This variable stores the string variable to match user input to Action.

    /******************
     *  Constructors  *
     ******************/

    /**
     * This is the Default constructor for Action.
     * @param eElement This element contains attributes connected to Action objects.
     */
    public Action(Element eElement){
        this.typeOfAction = eElement.getAttribute("typeOfAction");;
    }

    /**
     * This method imports action objects based on the attributes stored in a xml file.
     * @param fileName   The name of the xml file.
     * @return           A hashmap containing the action objects in the game.
     */
    public static HashMap<String, Action> importObjects(String fileName){
        var actions = new HashMap<String, Action>();

        /* Calls readXMLFile method from toolbox class to populate a nodeList with the value in the xml file. */
        NodeList nList = Toolbox.readXMLFile(fileName, "action");

        /* Creates an actor for each node in the list. */
        for(int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                var tempAction = new Action(eElement); //Creates a new actor object
                actions.put(tempAction.getTypeOfAction(), tempAction); //Adds action to hashmap.
            }
        }
        return actions;
    }


    /**************************
     *   Getters and Setters  *
     **************************/

    /**
     * This method is used to get the type of action from an Action object.
     * @return A string variable that represents the type of action.
     */
    public String getTypeOfAction(){
        return typeOfAction;
    }
}
