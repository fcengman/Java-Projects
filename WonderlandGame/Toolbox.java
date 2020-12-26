
/**
 * title: Toolbox.java
 * description: This class is used to contain administrative methods for the game.
 * date: December 15, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Toolbox
 *
 * Purpose and Description
 *
 * The class contains administrative methods for the game.
 *
 * This program uses the org.w3c.dom.Document, org.w3c.dom.NodeList, javax.xml.parsers.DocumentBuilder,
 * javax.xml.parsers.DocumentBuilderFactory,java.io.File java.util.ArrayList, and java.util.Scanner packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac Toolbox.java
 * Run:        java WonderlandGame
 * Document:   javadoc Toolbox.java
 *
 */

/**
 *
 * Classes
 * --------
 *
 * public class Toolbox
 * This is the primary (public) class for Toolbox.
 *
 *
 * Toolbox Constructors
 * ----------------------------
 *
 * This class does not have any constructors.
 *
 * Toolbox Methods
 * ----------------------------
 *
 * public static String wrapText(String text)
 * This method returns a formatted string that wraps the text based on a set width.
 *
 * public static NodeList readXMLFile(String fileName, String objectType)
 * This method reads a xml file and returns a NodeList for further operation.
 *
 * Toolbox Instance Variables
 * ----------------------------
 *
 * This class does not have any instance variables.
 *
 * ---------------------------------------------------------------------------------------
 */

/**
 * CODE...
 */

/** Java collection framework packages. */
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

/** Primary (public) class for Toolbox. */
public class Toolbox {

    /**
     * This method returns a formatted string that wraps the text based on a set
     * width.
     * 
     * @param text This is the string to be formatted.
     * @return A formatted string.
     */
    public static String wrapText(String text) {
        String output = "";
        try (Scanner scan = new Scanner(text)) {
            /* Continues loop as long as there are more words in the input string. */
            while (scan.hasNext()) {
                /* Loops over sets of 15 words, then inserts a carriage return. */
                for (int i = 0; i < 15; i++) {
                    if (scan.hasNext()) {
                        if (i == 0) {
                            output = output + scan.next();
                        } else {
                            output = output + " " + scan.next(); // Inserts a space at the end of each word.
                        }
                    }
                }
                /* Inserts a carriage return for each loop iteration. */
                if (scan.hasNext())
                    output = output + "\n";
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return output;
    }

    /**
     * This method reads a xml file and returns a NodeList for further operation.
     * 
     * @param fileName   This is the name of the xml file.
     * @param objectType This is the type of object stored in the xml file. It
     *                   should match the name of the element.
     * @return A NodeList containing the elements from the xml file.
     */
    public static NodeList readXMLFile(String fileName, String objectType) {
        NodeList nList = null;

        /* Tries to read given xml file name. */
        try {
            File xmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            nList = doc.getElementsByTagName(objectType); // Creates a node list of all objects in the xml file.

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nList;
    }

}
