/**
 * title: Wonderland.java
 * description: This class execute the WonderlandGame
 * date: December 14, 2020
 * @author Freya Engman
 * @version 1.0
 * @copyright 2001-2020 Freya Engman
 */

/**
 * DOCUMENTATION...
 */

/**
 * Alice in Wonderland Text Adventure Game
 *
 * Purpose and Description
 *
 * This class is used to execute the Alice in Wonderland Text Adventure Game. It initializes an instance of control.
 * The it begins the game loop in which it asks for input from the user. If the input is valid, it resolves the given
 * input. Then it checks to see if resolving the last input has satisfied the win condition.
 *
 *
 * This class does not include any additional packages.
 * It is based on Sun Java JDK 11.0.9
 *
 * Compiling and running instructions
 * Assuming JDK 11.0.9 (or later) and the CLASSPATH are set up properly.
 * Change to the directory containing the source code.
 * Compile:    javac Wonderland.java
 * Run:        java WonderlandGame
 * Document:   javadoc Wonderland.java
 *
 */


/**
 *
 * Classes
 * --------
 *
 * public class WonderlandGame
 * This (public) class creates exit objects.
 *
 * WonderlandGame constructors
 * ----------------------------
 *
 * This class does not have any constructors.
 *
 *
 * WonderlandGame Methods
 * ----------------------------
 *
 * public static void main(String[] args)
 * This method is used to execute the program.
 *
 *
 * Exit Instance Variables
 * ----------------------------
 *
 * This class does not have any instance variables.
 *
 * ---------------------------------------------------------------------------------------
 */

/**
 *
 * Test Plan
 *
 * 1. Run the program from the command line.
 * I compiled and ran the program using the Windows command prompt.
 *
 * EXPECTED:
 *  The program begin the game by printing the text located in the start document and a description
 *  of the starting room. Then it should leave a line for user input.
 *
 *  text = "This text adventure game takes place in the word of Alice in Wonderland. You play"
 *  text = "the character of Alice."
 *  text = "-----------------------------------------------------------------------------------------"
 *  text = "You can use the text commands: north, east, south, west, get, examine, give, use, look,"
 *  text = "talk, and drop to interact with the world. See you what item you are carrying by using the
 *  text = "commands inventory, inven, or i."
 *
 *  text = "Try combining these commands with object in the world to see what happens. You can"
 *  text = "quit the game at anytime by entering: Q or Quit"
 *  text = "----------------------------------------------------------------------------------------"
 *  text = "You were considering your own mind, when a rabbit with pink eyes ran close by."
 *  text = ""Oh dear! Oh dear! I shall be late!" said the rabbit, taking out a pocket watch"
 *  text = "and looking at it. The rabbit flashed out of sight, you run across the field"
 *  text = "after it just in time to see it pop down a rabbit hole. In another"
 *  text = "moment you are right down after it. Oh! Alice! You've fallen down the rabbit hole!"
 *  text = "This location will act as your 'safe room'. You can gather object and give them"
 *  text = "to the white rabbit to hold for you. Talk to the white rabbit, he will help you find"
 *  text = "a way home."
 *
 *  text = "You find yourself in a long, low hall, which is lit up by a row"
 *  text = "of lamps hanging from the roof. The White rabbit stand near one of the walls.
 *  text = "North of you there is a crack in the wall. You could probably fit through.
 *  text = "To the east looks like a large wooden door. You notice a glass table in the area."
 *
 * ACTUAL:
 *      Results displayed as expected. (Confirmed by running the program.)
 *
 *
 * 2. Good Data Cases:
 *    Good data cases include any commands given to the game by the user that the game can interpret
 *    and respond to in some way. There are several elements included in this game. Alice can win or
 *    lose the game. She can interact with items, characters and locations in the world. Restart the
 *    game between each of the following tests.
 *
 * a) Testing the Win Condition
 *     Testing the win condition requires a series of commands from the user. Once the white rabbit
 *     holds the four missing pieces of his watch, the end of the game should automatically trigger.
 *
 * EXPECTED:
 *     Run the following commands:
 *     get bottle          -> output should be "You took the small bottle from the glass table."
 *     north               -> output should be "You come across a neat little house. Inside is a tidy little room. North of
 *                                              you there is a bushy path into the woods. To the east looks like a
 *                                              gravel path with a castle in the distance. The southern route leads to a dark,
 *                                              mysterious path. To the west you see a garden path. Nearby there is a small chest
 *                                              that you can interact with.
 *     use chest          -> output should be "Enter the word to unlock the small chest:"
 *     aeiou              -> output should be "It worked! The chest is unlocked."
 *     get key            -> output should be "You took the rusty key from the small chest."
 *     get wheel          -> output should be "You took the crown wheel from the small chest."
 *     WEST               -> output should be "You walk into a garden filled with colorful flowers. A large mushroom stands near you,
 *                                             your eyes immediately meet those of a large blue caterpillar. North of you there is
 *                                             a wooded path. To the east looks like a garden path. Nearby there is a piece
 *                                             of a mushroom, and a murky pond in the area."
 *     use bottle         -> output should be "You use the bottle to retrieve an oyster from the bottom of the pond.
 *                                             The plump oyster has been added to your inventory."
 *     get mushroom       -> output should be "You picked up the piece of a mushroom."
 *     go n               -> output should be "You come across a house with chimneys shaped like ears, and a roof thatched with
 *                                             fur. There is a table set out under a tree in front of the house,
 *                                             and the March Hare and the Mad Hatter are having tea at it. To the
 *                                             east looks like a gravel path with a castle in the distance. The southern route
 *                                             leads to a wooded path. To the west you see a bushy path into the
 *                                             woods. Nearby there is a tea cup you can interact with."
 *     drop mushroom      -> output should be "You dropped the piece of a mushroom.
 *                                             A mouse sneaks out from underneath a tea cup. He runs up to you and
 *                                             snatches the piece of mushroom you left on the table, and scurries away. The overturned
 *                                             tea cup reveals the missing minute hand!"
 *     get hand           -> output should be "You picked up the minute hand."
 *     EAST               -> output should be "You arrive before a grand castle. Large rose trees line the entrance of the castle.
 *                                             The roses growing on it are white, but there are three playing card gardeners at
 *                                             it, busily painting them red. The grounds have been set up to play a croquet
 *                                             game. The Queen of Hearts stands nearby. North of you there is a wooded path.
 *                                             To the west you see a gravel path into the woods. Nearby there is a paint
 *                                             brush, and a flamingo mallet you can interact with."
 *     get mallet         -> output should be "You picked up the flamingo mallet."
 *     use mallet         -> output should be "You use the flamingo mallet to hit the hedgehog ball. He rolls into a bush.
 *                                             You go to retrieve the hedgehog and see something shimmering in the light, its the
 *                                             missing winding spring!"
 *     get spring         -> output should be "You picked up the winding spring."
 *     wEsT               -> output should be "You are at the White Rabbit's cottage again."
 *     s                  -> output should be "You are at the great hall again."
 *     use key            -> output should be "You unlocked the wooden door!"
 *     east               -> output should be "You walk onto a beach. The sea was wet as wet could be, the sands
 *                                             were dry as dry. You could not see a cloud, because no cloud was in
 *                                             the sky. A walrus sits in the sand digging. The southern route leads to the
 *                                             endless sea. To the west you see a large wooden door.
 *     give walrus oyster -> output should be "Yum Yum!" exclaims the Walrus. "For your trouble..." The walrus offers you the White Rabbit's
 *                                              missing balance screw."
 *     get screw walrus   -> output should be "You took the balance screw from the jolly Walrus."
 *     w                  -> output should be "You are at the great hall again."
 *     give rabbit wheel  -> output should be "You have given the crown wheel to the White Rabbit."
 *     rabbit give spring -> output should be "You have given the winding spring to the White Rabbit."
 *     screw rabbit give  -> output should be "You have given the balance screw to the White Rabbit."
 *     give hand rabbit   -> output should be "You have given the minute hand to the White Rabbit.
 *                                             Yes! Yes!" Cries the rabbit. "My watch is working again. I will take you home
 *                                             now." The White Rabbit takes you home. Congratulations you have won the game!"
 *
 * ACTUAL:
 *      Results displayed as expected. (Confirmed by entering commands).
 *
 *
 * b) Testing the Lose Condition
 *     There are certain actions that cause the player to lose the game if performed in a certain
 *     location or under certain circumstances. To test one of those condtions run the following
 *     commands:
 *
 * EXPECTED:
 *     north -> output should be "You come across a neat little house. Inside is a tidy little room. North of
 *                                you there is a bushy path into the woods. To the east looks like a
 *                                gravel path with a castle in the distance. The southern route leads to a dark,
 *                                mysterious path. To the west you see a garden path. Nearby there is a small chest
 *                                you can interact with.
 *      e -> output should be "You arrive before a grand castle. Large rose trees line the entrance of the castle.
 *                             The roses growing on it are white, but there are three playing card gardeners at
 *                             it, busily painting them red. The grounds have been set up to play a croquet
 *                             game. The Queen of Hearts stands nearby. Behind you leads to a gravel path toward
 *                             a garden. Nearby there is a paint brush, and a flamingo mallet you can interact with."
 *      get brush -> output should be "You picked up the paint brush.
 *                                     The Queen of Hearts marches up to you. "Who has been painting my roses red?!"
 *                                     Bellows the queen. "Off with her head!" A playing card gaurd grabs your arm and
 *                                     takes you to the dungeon.
 *
 *                                     Sorry, you've lost the game. Please try again."
 *
 * ACTUAL:
 *      Results displayed as expected. (Confirmed by entering commands).
 *
 *
 * c) Testing Good Movement Through the Map
 *     Alice can move between rooms. Each room can have up to 4 exits, some exits are locked and some
 *     don't exist. Alice can use the full word or the first letter of a direction to move. When alice
 *     enters a new location a description of that location is printed to the terminal, if it is a
 *     location she has visited before, a short message will be printed to the terminal. Alice can use
 *     the command 'look' to get a description of the location again. This is useful if the location has
 *     changed, ie. if an item has become visible. Run the following commands to test movement in the game:
 *
 * EXPECTED:
 *     north -> output should be "You come across a neat little house. Inside is a tidy little room. North of
 *                                you there is a bushy path into the woods. To the east looks like a
 *                                gravel path with a castle in the distance. The southern route leads to a dark,
 *                                mysterious path. To the west you see a garden path. Nearby there is a small chest
 *                                you can interact with."
 *     SOUTH -> output should be "You are at the great hall again."
 *     look -> output should be "You find yourself in a long, low hall, which is lit up by a row
 *                               of lamps hanging from the roof. The White Rabbit stands near one of the walls.
 *                               North of you there is a crack in the wall, you could probably fit through. To the
 *                               east looks like a large wooden door. Nearby there is a glass table you can
 *                               interact with."
 *     go NoRtH -> output should be "You are at the White Rabbit's cottage again."
 *
 * ACTUAL:
 *      Results displayed as expected. (Confirmed by entering commands).
 *
 *
 * d) Testing Good Item Interaction
 *     Any object in the game can have items in their inventory. Alice can examine, pick up, drop, give, and use items.
 *     If get, drop, or give actions are used the item is moved between object inventories and the description
 *     of that object is changes. There are also items that alice cannot pickup or items that are locked. Some
 *     items are not visible to alice until certain actions are taken. Alice can check what she is carrying at any
 *     time by using the inventory, inven, or i commands. Run the following commands to test item
 *     interaction in the game:
 *
 * EXPECTED:
 *     examine table -> output should be "A three-legged table, all made of solid glass. The glass table contains a small bottle."
 *     get                bottle -> output should be "You took the small bottle from the glass table."
 *     table examine -> output should be "A three-legged table, all made of solid glass."
 *     inven -> output should be "You are carrying:
 *                                a small bottle"
 *     drop bottle -> output should be "You dropped the small bottle."
 *     i -> output should be "You aren't carrying anything."
 *
 *
 * e) Testing Good Character Interaction
 *      Alice can interact with characters in the world. She can talk, examine, and give items to them. Some characters
 *      will trigger an action response if given an item in a location. The description of a character changes depending
 *      on what items they are holding. Run the following commands to test good character interaction:
 *
 * EXPECTED:
 *      examine rabbit -> output should be "He is a very distinguished rabbit, always wearing gloves, a waistcoat, and a pocket watch."
 *      talk rabbit -> output should be ""I'm late! I'm late!" Exclaims the rabbit, "And my watch broke on the way down
 *                                       here. I'll never make it on time with my watch in this condition. Bring me
 *                                       the four missing pieces of my watch, the minute hand, the balance screw, the crown
 *                                       wheel, and the winding spring. Then I will take you home. I know I left
 *                                       at least once piece at my cottage."
 *      get the bottle -> output should be "You picked up the small bottle."
 *      give bottle to white rabbit -> output should be "You have given the small bottle to the White Rabbit."
 *      examine the rabbit -> output should be "He is a very distinguished rabbit, always wearing gloves, a waistcoat, and a pocket watch. This
 *                                   character is carrying a small bottle."
 *
 * ACTUAL:
 *      Results displayed as expected. (Confirmed by entering commands).
 *
 *
 * 3. Bad Data Cases:
 *      Bad data cases include any commands given to the game that the game can either not interpret or that is invalid
 *      because the game's conditions are not met. If alice tries to pick up an item that isn't in the room for example.
 *      In each of these cases a random message will be printed to the terminal. Since these messages are randomized you
 *      might get a different output when you run the bad data cases each time. Restart the game by using the command 'q'
 *      to quite the game between each of the following tests.
 *
 * a) Testing Bad User Input
 *      The game only understands commands that include either a valid action word or a combination of a valid action word
 *      and valid item/actor word. Single action words like get, use, drop, talk, and examine without a second item/actor
 *      word will result in an error message. Run the following commands to test bad user input:
 *
 * EXPECTED:
 *      <ENTER> -> output should be "Random Bad Input Error Message"
 *      0000000000000000000000000000 -> output should be "Random Bad Input Error Message"
 *      get -> output should be "Random Bad Input Error Message"
 *      -0.3453 -> output should be "Random Bad Input Error Message"
 *      move rabbit -> output should be "Random Bad Input Error Message"
 *      pick up bottle -> output should be "Random Bad Input Error Message"
 *      " " -> output should be "Random Bad Input Error Message"
 *      bottle -> output should be "Random Bad Input Error Message"
 *
 *
 * ACTUAL:
 *      Results displayed as expected. (Confirmed by entering commands).
 *      >
 *      I don't understand that.
 *      > 0000000000000000000000000000
 *      What?
 *      > get
 *      I don't know how to apply that word here.
 *      > -0.3453
 *      What?
 *      > move rabbit
 *      What?
 *      > pick up bottle
 *      I don't know how to apply that word here.
 *      >
 *      I don't understand that.
 *      > bottle
 *      I don't understand that.
 *
 *
 * b) Testing Bad Movement Commands
 *     When alice tries to move in an invalid direction an error message will be printed to the terminal.
 *     If a direction is locked, alice will be told as muchRun the following commands to test bad movement commands:
 *
 * EXPECTED:
 *     s -> output should be "You can't go that way!"
 *     east -> output should be "The door in this direction is locked!"
 *
 * ACTUAL:
 *      Results displayed as expected. (Confirmed by entering commands).
 *
 *
 * c) Testing Bad Item Commands
 *     Bad item commands consist of an invalid action with an item, either because the item isn't available, or the
 *     use of that item is not appropriate in the location alice is in. In the former a warning will be printed to
 *     the terminal, in the latter case a random bad use case message will be printed to the terminal. Run the following
 *     commands to test bad item commands:
 *
 * EXPECTED:
 *     get bottle -> output should be "You took the small bottle from the glass table."
 *     get bottle -> output should be "You are already carrying that item."
 *     use bottle -> output should be Random Bad Use Case Message
 *     get table -> output should be "You can't get that."
 *     drop bottle -> output should be "You dropped the small bottle."
 *     use bottle -> output should be "You are not carrying that item."
 *     use table -> output should be Random Bad Use Case Message
 *     use key -> output should be "You are not carrying that item."
 *     use rabbit -> output should be Random Bad Use Case Message
 *     use hall -> output should be Random Bad Input Message
 *
 *
 *
 * ACTUAL:
 *      Results displayed as expected. (Confirmed by entering commands).
 *      > get bottle
 *      You took the small bottle from the glass table.
 *      > get bottle
 *      You are already carrying that item.
 *      > use bottle
 *      That can't be used in this way.
 *      > get table
 *      You can't pick that up!
 *      > drop bottle
 *      You dropped the small bottle.
 *      > use bottle
 *      You are not carrying that item.
 *      > use table
 *      Hmm....nothing happened.
 *      > use key
 *      You are not carrying that item.
 *      > use hall
 *      I don't understand that.
 *
 *
 *
 * d) Testing Bad Character Commands
 *      Alice can interact with characters that are in the same location as alice, but the player
 *      is given an error message if
 *
 * EXPECTED:
 *      talk to mad hatter -> output should be "That character isn't here!"
 *      talk to -> output should be Random Bad Input Error Message
 *      give bottle to rabbit -> output should be "You don't have that item to give."
 *      examine mad hatter -> output should be "You can't examine that!"
 *      get bottle -> output should be "You took the small bottle from the glass table."
 *      give bottle to mad hatter -> output should be "That character is not present in this location."
 *      use rabbit -> output should be Random Bad Use Case Error Message
 *
 *
 * ACTUAL:
 *       Results displayed as expected. (Confirmed by running test cases.)
 *
 *       > talk to mad hatter
 *       That character isn't here!
 *       > talk to
 *       I don't understand that.
 *       > give bottle to rabbit
 *       You don't have that item to give.
 *       > examine mad hatter
 *       That character isn't in this location.
 *       > get bottle
 *       You took the small bottle from the glass table.
 *       > give bottle to mad hatter
 *       That character is not present in this location.
 *       > use rabbit
 *       Well....that didn't work.
 *
 */

/**
 * CODE...
 */

/** Primary (public) class for WonderlandGame. */
public class WonderlandGame {

    /** This is main method used to execute the program. */
    public static void main(String[] args){


        var newGame = new Control(); //Initialize instance of the game.
        newGame.start(); //Print start message to terminal.

        /* Continues game loop while the continueGame variable is true. */
        while(newGame.getContinueGame()){
            System.out.print("> "); //Included for nicer formatting.

            /* Reads input from user. */
            newGame.readInput();
            /* Resolves valid input from user. */
            newGame.resolveInput();

            /* Checks to see if resolveInput has satisfied win condition. */
            if(newGame.getWinCondition().checkWinCondition()){
                newGame.setContinueGame(false); //Breaks out of game loop.
                newGame.end(); //Prints end message to terminal.
            }
        }
    }
}
