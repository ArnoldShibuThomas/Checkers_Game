/** This class implements the tests that will be run on the Computer Player class
 *
 * @author Arnold Shibu
 * @verison 1.0 Nov 22, 2021.
 *
 */
package test;
import static org.junit.Assert.*;

import core.CheckersLogic;
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

import core.CheckersComputerPlayer;
public class CheckersComputerPlayerTest {

    private static CheckersComputerPlayer game1VsPc, game2VsPc, game3VsPC, game4VsPc, game5VsPc, game6VsPc, game7VsPc;
    private static CheckersLogic board, board2, board3, board4, board5, board6,board7;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{

    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        game1VsPc = null;
        game2VsPc = null;
        game3VsPC = null;
        game4VsPc = null;
        game5VsPc = null;
        game6VsPc = null;
        game7VsPc = null;

        board = null;
        board2 = null;
        board3 = null;
        board4 = null;
        board5 = null;
        board6 = null;
        board7 = null;

    }

    /**
     * SetUp Test fixtures (Called before every test case method)
     */
    @Before
    public void setUp() throws Exception{

        //sets up different game boards with scenarios for the computer player to play in to verify its moves
        board = new CheckersLogic();
        game1VsPc = new CheckersComputerPlayer(board);

        board2 = new CheckersLogic();
        board2.board = new String[][]{{"_", "_", "_", "_", "_", "_", "_", "_"},
                                    {"_", "_", "_", "_", "_", "_", "x", "_"},
                                    {"_", "_", "_", "_", "_", "_", "_", "o"},
                                    {"_", "_", "_", "_", "_", "_", "_", "_"},
                                    {"_", "_", "_", "_", "_", "_", "_", "_"},
                                    {"_", "_", "x", "_", "_", "_", "_", "_"},
                                    {"_", "_", "_", "_", "_", "_", "_", "_"},
                                    {"_", "_", "_", "_", "_", "_", "_", "_"}};
        board2.oCount= 1;
        board2.xCount = 2;
        game2VsPc = new CheckersComputerPlayer(board2);

        board3 = new CheckersLogic();
        board3 = new CheckersLogic();
        board3.board = new String[][]{{"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "x", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "x", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "o"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"}};
        board3.oCount= 1;
        board3.xCount = 2;
        game3VsPC = new CheckersComputerPlayer(board3);


        board4 = new CheckersLogic();
        board4.board = new String[][]{{"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "x", "_", "_", "_", "_", "_", "_"},
                {"o", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "x", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"}};
        board4.oCount= 1;
        board4.xCount = 2;
        game4VsPc = new CheckersComputerPlayer(board4);

        board5 = new CheckersLogic();
        board5.board = new String[][]{{"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "x", "_", "_", "_", "_", "_"},
                {"_", "x", "_", "_", "_", "_", "_", "_"},
                {"o", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "x", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"}};
        board5.oCount= 1;
        board5.xCount = 2;
        game5VsPc = new CheckersComputerPlayer(board5);

        board6 = new CheckersLogic();
        board6.board = new String[][]{{"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "x", "_", "x", "_", "_", "_"},
                {"_", "x", "_", "x", "_", "_", "_", "_"},
                {"_", "_", "o", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "x", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"}};
        board6.oCount= 1;
        board6.xCount = 4;
        game6VsPc = new CheckersComputerPlayer(board6);

        board7 = new CheckersLogic();
        board7.board = new String[][]{{"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"x", "_", "x", "_", "_", "_", "_", "_"},
                {"_", "x", "_", "x", "_", "_", "_", "_"},
                {"_", "_", "o", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"},
                {"_", "_", "x", "_", "_", "_", "_", "_"},
                {"_", "_", "_", "_", "_", "_", "_", "_"}};
        board7.oCount= 1;
        board7.xCount = 4;
        game7VsPc = new CheckersComputerPlayer(board7);
    }
    /**
     * Tear down the Test fixtures (Called after every test case method)
     */
    @After
    public void tearDown() throws Exception{
        //set the objects to null
        game1VsPc = null;
        game2VsPc = null;
        game3VsPC = null;
        game4VsPc = null;
        game5VsPc = null;
        game6VsPc = null;
        game7VsPc = null;

        board = null;
        board2 = null;
        board3 = null;
        board4 = null;
        board5 = null;
        board6 = null;
        board7 = null;
    }

    @Test
    public void testMove(){
        //make move
        assertTrue(game1VsPc.move());
        //check movements that would result in a false (out of bounds)
        assertFalse(game1VsPc.moveLeft(8,8));
        assertFalse(game1VsPc.moveLeft(-1, -1));
        assertFalse(game1VsPc.moveRight(8,8));
        assertFalse(game1VsPc.moveRight(-1, -1));

        //make another valid move
        assertTrue(game1VsPc.move());

        //make invalid left or right move
        assertFalse(game1VsPc.moveLeft(9,9));
        assertFalse(game1VsPc.moveRight(9,9));

        //move from sides
        assertTrue(game2VsPc.move());
        assertFalse(game3VsPC.move());
        assertTrue(game4VsPc.move());
        assertFalse(game5VsPc.move());
        assertTrue(game6VsPc.move());
        assertTrue(game7VsPc.move());


    }
}
