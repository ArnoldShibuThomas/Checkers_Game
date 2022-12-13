/** This class implements the tests that will be run on the CheckerLogic Class
 *
 * @author Arnold Shibu
 * @verison 1.0 Nov 22, 2021.
 *
 */
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;

import core.*;


public class CheckerLogicTest {
    /**
     * This is a test case class used in testing the CheckersLogic class.
     */

    private static CheckersLogic game1, game2, game3, game4, game5, game6,game7, game8, game9;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{

    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception{

    }

    /**
     * SetUp Test fixtures (Called before every test case method)
     */
    @Before
    public void setUp() throws Exception{
        game1 = new CheckersLogic();
        game2 = new CheckersLogic();
        game3 = new CheckersLogic();
        game4 = new CheckersLogic();
        game5 = new CheckersLogic();
        game6 = new CheckersLogic();
        game7 = new CheckersLogic();
        game8 = new CheckersLogic();
        game9 = new CheckersLogic();

        //game with default board player should only be allowed to move diagonally from both sides
        game1.board = new String[][]{{"_", "x", "_", "x", "_", "x", "_", "x"},
                                    {"x", "_", "x", "_", "x", "_", "x", "_"},
                                    {"_", "x", "_", "x", "_", "x", "_", "x"},
                                    {"_", "_", "_", "_", "_", "_", "_", "_"},
                                    {"_", "_", "_", "_", "_", "_", "_", "_"},
                                    {"o", "_", "o", "_", "o", "_", "o", "_"},
                                    {"_", "o", "_", "o", "_", "o", "_", "o"},
                                    {"o", "_", "o", "_", "o", "_", "o", "_"}};

        //ability to capture with pieces in tha way
        game2.board = new String[][]{{"_", "x", "_", "x", "_", "x", "_", "x"},
                                    {"x", "_", "x", "_", "x", "_", "x", "_"},
                                    {"_", "_", "_", "x", "_", "x", "_", "_"},
                                    {"x", "_", "o", "_", "o", "_", "_", "x"},
                                    {"o", "_", "_", "x", "_", "x", "_", "o"},
                                    {"_", "_", "o", "_", "o", "_", "_", "_"},
                                    {"_", "o", "_", "o", "_", "o", "_", "o"},
                                    {"o", "_", "o", "_", "o", "_", "o", "_"}};

        //edge cases of being stuck to, bottom, left or right
        game3.board = new String[][]{{"_", "o", "_", "x", "_", "x", "_", "x"},
                                    {"x", "_", "x", "_", "x", "_", "x", "_"},
                                    {"_", "_", "_", "_", "_", "x", "_", "_"},
                                    {"x", "_", "_", "x", "_", "_", "_", "x"},
                                    {"o", "_", "_", "_", "o", "_", "_", "o"},
                                    {"_", "_", "o", "_", "_", "_", "_", "_"},
                                    {"_", "o", "_", "o", "_", "o", "_", "o"},
                                    {"o", "_", "x", "_", "o", "_", "o", "_"}};
        //clean copy of board
        game4.board = new String[][]{{"_", "x", "_", "x", "_", "x", "_", "x"},
                {"x", "_", "x", "_", "x", "_", "x", "_"},
                {"_", "x", "_", "x", "_", "x", "_", "x"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"o", "_", "o", "_", "o", "_", "o", "_"},
                {"_", "o", "_", "o", "_", "o", "_", "o"},
                {"o", "_", "o", "_", "o", "_", "o", "_"}};

        //x wins
        game5.board = new String[][]{{"_", "x", "_", "_", "o", "_", "o", "_"},
                                     {"o", "_", "_", "_", "_", "o", "_", "o"},
                                    {"_", "_", "_", "_", "_", "_", "_", "_"},
                                     {"_", "_", "_", "_", "_", "_", "_", "_"},
                                    {"_", "_", "_", "_", "x", "_", "_", "_"},
                                    {"_", "_", "x", "_", "_", "_", "_", "_"},
                                    {"_", "_", "_", "_", "_", "_", "_", "_"},
                                    {"_", "x", "_", "_", "_", "_", "_", "_"}};
        game5.oCount = 5;
        game5.xCount = 4;

        //o wins
        game7.board = new String[][]{{"_", "_", "_", "_", "_", "_", "o", "_"},
                                     {"_", "_", "_", "_", "_", "_", "_", "_"},
                                     {"_", "_", "o", "_", "_", "_", "_", "_"},
                                     {"_", "_", "_", "_", "_", "_", "_", "_"},
                                     {"_", "_", "_", "_", "o", "_", "_", "_"},
                                     {"_", "_", "_", "_", "_", "_", "_", "_"},
                                     {"x", "_", "x", "_", "_", "_", "_", "x"},
                                     {"_", "x", "_", "o", "_", "_", "o", "_"}};
        game5.oCount = 5;
        game5.xCount = 4;

        game8.board = new String[][]{{"x", "_", "_", "_", "_", "_", "_", "x"},
                                    {"_", "_", "_", "_", "_", "x", "_", "_"},
                                    {"_", "x", "_", "_", "_", "_", "x", "_"},
                                    {"o", "_", "_", "_", "_", "_", "_", "o"},
                                    {"_", "_", "_", "_", "_", "_", "_", "_"},
                                    {"_", "_", "x", "_", "_", "_", "_", "_"},
                                    {"_", "x", "_", "_", "_", "_", "x", "_"},
                                    {"o", "_", "_", "_", "_", "_", "_", "o"}};;


        //game9.board = new String[][];
    }
    /**
     * Tear down the Test fixtures (Called after every test case method)
     */
    @After
    public void tearDown() throws Exception{
        //set the objects to null
        game1 = null;
        game2 = null;
        game3 = null;
        game4 = null;
        game5 = null;
        game6 = null;
        game7 = null;
        game8 = null;
        game9 = null;
    }

    @Test
    public void testIsValid(){

        //validate moves for X

        //we attempt to "move" x to where it is currently
        assertTrue(game1.isValid("3e-3e",1) == false);
        //move x forward
        assertTrue(game1.isValid("2d-3d",1) == false);
        //move x backward
        assertTrue(game1.isValid("2d-1d",1) == false);
        //move x left
        assertTrue(game1.isValid("2f-2e",1) == false);
        //move x right
        assertTrue(game1.isValid("2f-2g",1) == false);
        //move backwards right
        assertTrue(game3.isValid("4e-3f",1) == false);
        //move backwards left
        assertTrue(game3.isValid("4e-3d",1) == false);
        //move X forward but there is an x piece in the way
        assertTrue(game1.isValid("2b-3a",1) == false);
        //move X forward, but there is an O piece in the way (no jump available)
        assertTrue(game2.isValid("3e-4d", 1) == false);
        //move X forward, but there is an O piece in the way(jump is available)
        assertTrue(game2.isValid("3e-4f", 1) == true);
        //moving right from far right (out of bound to the right)
        assertTrue(game3.isValid("4h-5i",1) == false);
        //moving left from far right
        assertTrue(game3.isValid("4h-5g",1) == true);
        //moving right from far left
        assertTrue(game3.isValid("4a-5b",1) == true);
        //moving left from the far left (out of bounds to the left)
        assertTrue(game3.isValid("4a-5z",1) == false);
        //moving a stuck piece left and right (out of bounds top threshold)
        assertTrue(game3.isValid("8f-9e",1) == false);
        assertTrue(game3.isValid("8f-9g",1) == false);
        //moving an enemy piece
        assertTrue(game1.isValid("6d-5c",1) == false);
        //making a valid move
        assertTrue(game1.isValid("3a-4b",1) == true);
        assertTrue(game1.isValid("4b-5a",1)== true);


        // validate moves for o
        //we attempt to "move" o to where it is currently
        assertTrue(game1.isValid("6b-6b",0) == false);
        //move o forward
        assertTrue(game1.isValid("6d-5d",0) == false);
        //move o backwards
        assertTrue(game1.isValid("6b-7b", 0)== false);
        //move o left
        assertTrue(game1.isValid("6d-6c", 0)== false);
        //move o right
        assertTrue(game1.isValid("6d-6e", 0)== false);
        //move backwards left and right
        assertTrue(game3.isValid("5d-6c",0) == false);
        assertTrue(game3.isValid("5d-6e",0) == false);
        //forward, but piece in the way
        assertTrue(game2.isValid("6d-5e",0) == true);
        //forward, but piece is not in the way
        assertTrue(game2.isValid("6d-5c",0) == false);
        //move right and left from far right
        assertTrue(game3.isValid("5h-4i",0) == false);
        assertTrue(game3.isValid("5h-4g",0) == true);
        //move right and left from far left
        assertTrue(game3.isValid("5a-4b",0) == true);
        assertTrue(game3.isValid("5a-4h",0) == false);
        //move left and right when you are stuck
        assertTrue(game3.isValid("1g-8f", 0) == false);
        assertTrue(game3.isValid("1g-8h",0) == false);
        // move an enemy piece
        assertTrue(game1.isValid("3c-4d", 0) == false);
        // make a valid game move
        assertTrue(game1.isValid("6h-5g",0) == true);
        assertTrue(game1.isValid("5g-4h",0)== true);
        //move to the right
        assertTrue(game4.isValid("6e-5f",0)== false);
        assertTrue(game4.isValid("6b-5a",0)== true);

        //check if the 0 can move  or capture from sides
        assertTrue(game8.moveORight(3,0));
        assertFalse(game8.moveOLeft(3,7));
        assertFalse(game8.moveORight(7,0));
        assertTrue(game8.moveOLeft(7,7));
        assertFalse(game8.moveOLeft(20,20));
        assertFalse(game8.moveORight(20,20));
        //move x
        assertTrue(game8.moveXLeft(0,7));
        assertFalse(game8.moveXRight(0,7));
        assertFalse(game8.moveXLeft(0,0));
        assertTrue(game8.moveXRight(0,0));
        assertFalse(game8.moveXRight(20,20));
        assertFalse(game8.moveXLeft(20,20));





    }

    @Test
    public void testGetBoard(){
        //test to see if the get gameboard returns the board
        final int size = 8;
        String[][] test = game1.getBoard();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                assertTrue(game1.board[i][j].equals(test[i][j]) == true);
            }
        }
    }

    @Test
    public void testWin(){

        //checks if no one has won
        assertTrue(game1.gameStatus(1) == false && game1.gameStatus(0 ) == false);
        //check for tie
        game1.xCount = 0;
        game1.oCount = 0;

        assertTrue(game1.gameStatus(0) == true && game1.gameStatus(1) == true);
        assertTrue(game5.gameStatus(0) == false && game5.gameStatus(1) == true);
        //checks where one team or the other wins
        assertFalse(game6.gameStatus(0) == true && game6.gameStatus(1) == false);
    }

    @Test
    public void testSize(){
        //return the board size
        assertTrue(game1.size() == 8);

    }
    @Test
    public void testCaptureX(){
        //tests to see if the xCount went down
        game1.captureX();
        assertTrue(game1.xCount == 11);
    }
    @Test
    public void testSetFigure(){
        //tests if the figure got place in and out of bounds
        game1.setFigure(3,3,"x");
        assertTrue(game1.board[3][3].equals("x"));
        try{
        game1.setFigure(9,9,"o");
        }
        catch (Exception e)
        {
            assertTrue(true);
        }
    }

    @Test
    public void testToString(){
        //checks if the toString retrieves a value
        game1.toString();
        assertTrue(game1 != null);
    }

    @Test
    public void testFigure(){
        //if the piece is out of bounds
        assertTrue((game1.figure(9,9)).equals("N") == true);
        //x is in bounds
        assertTrue((game1.figure(0,7)).equals("x") == true);
        //o is in bounds
        assertTrue((game1.figure(5,0)).equals("o") == true);
        //if the space is empty
        assertTrue((game1.figure(0,0)).equals("n") == false);
    }

}
