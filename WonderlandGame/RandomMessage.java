/**
 * title: RandomMessage.java
 * description: This class is used to generate error messages for WonderLandGame.
 * date: December 14, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Random Message Generator
 *
 * Purpose and Description
 *
 * The class allows the WonderlandGame application to read a text file and store each line
 * of the text file as an entry in a string array. Then the game can call the printRandomMessage
 * method to print a random string from the array to the terminal.
 *
 *
 * This program uses the Java.util.ArrayList, java.util.Scanner, java.io.FileNotFoundException,
 * and Java.io.File packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac RandomMessage.java
 * Run:        java WonderlandGame
 * Document:   javadoc OneLineFileReader.java
 *
 */


/**
 *
 * Classes
 * --------
 *
 * public class RandomMessage
 * This class creates string arrays for random error messages.
 *
 * RandomMessage constructors
 * ----------------------------
 *
 * public RandomMessage()
 * This is the default constructor for this class.
 *
 *
 * RandomMessage Methods
 * ----------------------------
 *
 * public void importRandomMessage(String fileName)
 * This method reads a text file and creates a RandomMessage object populated with the lines
 * text from the text file.
 *
 * public void printRandomMessage()
 * This method prints a random error message contained in a RandomMessage object.
 *
 *
 * RandomMessage Instance Variables
 * ----------------------------
 *
 * private ArrayList<String> messages; - This variable stores an array of error messages.
 *
 * ---------------------------------------------------------------------------------------
 */

/** Java collection framework packages. */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/** Primary (public) class for RandomMessages. */
public class RandomMessage {

    private ArrayList<String> messages; //This variable stores an array of error messages.

    /******************
     *  Constructors  *
     ******************/

    /** This is the default constructor for RandomMessage. */
    public RandomMessage(){
        this.messages = new ArrayList<String>();
    }

    /**
     * This method populates the string array of a RandomMessage object from a text file.
     * @param fileName This is the name of the text file to import from.
     */
    public void importRandomMessages(String fileName){

        /* Tries to read text file */
        try( var reader = new Scanner( new File(fileName) ) ){
            while(reader.hasNextLine()){
                this.messages.add(reader.nextLine()); //Adds error message to string array.
            }
        } catch (FileNotFoundException e){
            System.out.println("There is no text file with that name.");
        }
    }


    /** This method prints a random message from the string array to the terminal. */
    public void printRandomMessage(){
        int randOutput = (int) Math.floor(Math.random()*messages.size()); //Get random index value based on size of string array.
        System.out.println( messages.get(randOutput) );
    }
}
