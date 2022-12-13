package ui;

import core.CheckersComputerPlayer;
import core.CheckersLogic;
import javafx.application.Application;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import javafx.scene.layout.*;

import javafx.stage.Stage;

import java.util.Scanner;

/** This class implements the code for the GUI behind the checker game
 *
 * @author Arnold Shibu
 * @verison 1.0 Nov 9, 2021.
 *
 */
public class CheckersGUI extends Application{

    //this is a variable that is used to determine if the game still can accept interaction from the players
    private boolean gameOver = false;

    //sets the computer player variable to be true if the player opts in and false otherwise
    private static boolean computerPlayer = false;

    //how many events has the person triggered
    private int moves = 0;

    //movement recorded in a String
    private String move = "";

    //who plays first true it is black false it is white
    private char playerTurn ='x';

    //create a board
    private CheckersLogic gameSession = new CheckersLogic();

    //create a computerPlayer
    private CheckersComputerPlayer JohnDoeGUI = new CheckersComputerPlayer(gameSession);

    //create the blanks spaces on the board
    private Blank[][] gameSize = new Blank[8][8];


    //create a label
    private Label state = new Label("Black's Turn");

    //this is a new gridpane object to put spaces into
    private  GridPane gameBoard = new GridPane();

    //size of the board
    int size = 8;

    /** The start method is the method that is called to set up the UI for the first time .
     *
     * @parm a stage object
     * @return void
     * @exception ArrayIndexOutOfBoundsException is thrown if the board was unsuccessfully set up.
     */
    public void start(Stage primaryStage) throws ArrayIndexOutOfBoundsException {
        try {
            //fills the grid pane with blanks spaces to go ahead and put the game pieces in
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    gameBoard.add(gameSize[i][j] = new Blank(i, j), j, i);
                }
            }

            //get pieces on board
            updateBoard(gameSession.getBoard());


            //add the message of whose turn it is
            BorderPane message = new BorderPane();
            message.setCenter(gameBoard);
            message.setBottom(state);

            Scene scene = new Scene(message, 630, 630);


            //this goes after you've defined your scene, but before you display your stage

            primaryStage.setTitle("Checkers"); // Set the stage title
            primaryStage.setScene(scene); // Place the scene in the stage

            primaryStage.show(); // Display the stage

        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("The board provided to make into a GUI is bigger than expected");
            System.out.println("The board unexpectedly failed to create try again!");
        }
    }
    /** This inner class implements the blank spaces that each piece on the board relies on
     *
     * @author Arnold Shibu
     * @verison 1.0 Nov 9, 2021.
     *
     */
    public class Blank extends Pane {
        // the piece that this cell holds is stored in this
        private char piece = ' ';
        // X stores the row number
        // Y Stores the column number
        int x =0;
        int y = 0;

        /** This the default constructor for the inner class Blank
         *
         * @parm void
         * @return void
         */
        public Blank() {
            setStyle("-fx-border-color: black");
            this.setPrefSize(2000, 2000);
            this.setOnMouseClicked(e -> handleClick());


        }
        /** This the overridden constructor for the inner class Blank
         *
         * @parm integer that represents the row number the item is in
         * @parm integer that represents the column number the item is in
         * @return void
         */
        public Blank(int x, int y) {
            //updates variables and creates a blank
            this.x = x;
            this.y = y;
            this.setStyle("-fx-border-color: black");


            this.setPrefSize(2000, 2000);

            this.setOnMouseClicked(e -> handleClick());
        }

        /** This method  give back the coordinate value for where the piece the user is trying to move on the board, part of inner class
         *
         * @parm void
         * @return a String representing the coordinates of where the piece is located
         */
        public String coordinates(){
            //translates the coordinates in the proper string equivalent
            String move = "";
            int temp = x + 1;
            move =""+ temp;
            if (y == 7) {
                move = move + "a";
            } else if (y== 6) {
                move = move +"b";
            } else if (y == 5) {
                move = move +"c";
            } else if (y == 4) {
                move = move +"d";
            } else if (y == 3) {
                move = move +"e";
            } else if (y == 2) {
                move = move +"f";
            } else if (y == 1) {
                move = move +"g";
            }
            else if(y == 0) {
                move = move +"h";
            }
            return move;
        }

        /** This method retrieves the piece at the cell, part of inner class
         *
         * @parm void
         * @return a char with whether the piece there was a black piece 'x' or was a white piece 'o'
         */
        public char getItem(){
            return piece;
        }

        /** This method sets the piece at a cell, part of inner class
         *
         * @parm a character that represent what the String at this cell is supposed to be set to
         * @return void
         */
        public void setPiece(char c)
        {
            piece = c;
            if(piece == 'x'){
                //creates ellipse
                Ellipse blackCircle = new Ellipse();
                //set parameters of ellipse
                blackCircle.setCenterX(this.getWidth() /2);
                blackCircle.setCenterY(this.getHeight()/2);
                blackCircle.setRadiusX(this.getWidth()/2 - 10);
                blackCircle.setRadiusY(this.getWidth()/2 - 10);
                blackCircle.setStroke(Color.BLACK);
                blackCircle.setFill(Color.BLACK);
                //bind ellipse to cell
                blackCircle.centerXProperty().bind(this.widthProperty().divide(2));
                blackCircle.centerYProperty().bind(this.heightProperty().divide(2));
                blackCircle.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
                blackCircle.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));

                //finally add this circle to this pane
                getChildren().add(blackCircle);
            }
           if(piece == 'o'){
               Ellipse whiteCircle = new Ellipse();
               //set parameters of ellipse
               whiteCircle.setCenterX(this.getWidth() /2);
               whiteCircle.setCenterY(this.getHeight()/2);
               whiteCircle.setRadiusX(this.getWidth()/2 - 10);
               whiteCircle.setRadiusY(this.getWidth()/2 - 10);
               whiteCircle.setStroke(Color.BLACK);
               whiteCircle.setFill(Color.WHITE);

               //bind ellipse to cell
               whiteCircle.centerXProperty().bind(this.widthProperty().divide(2));
               whiteCircle.centerYProperty().bind(this.heightProperty().divide(2));
               whiteCircle.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
               whiteCircle.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));

               //finally add this circle to this pane
               getChildren().add(whiteCircle);
           }
           if(piece != 'o' && piece != 'x'){
               Ellipse blankCircle = new Ellipse();
               //set parameters of ellipse
               blankCircle.setCenterX(this.getWidth() /2);
               blankCircle.setCenterY(this.getHeight()/2);
               blankCircle.setRadiusX(this.getWidth()/2 - 10);
               blankCircle.setRadiusY(this.getWidth()/2 - 10);
               blankCircle.setStroke(Color.WHITESMOKE);
               blankCircle.setFill(Color.WHITESMOKE);

               //bind ellipse to cell
               blankCircle.centerXProperty().bind(this.widthProperty().divide(2));
               blankCircle.centerYProperty().bind(this.heightProperty().divide(2));
               blankCircle.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));
               blankCircle.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));

               //finally add this circle to this pane
               getChildren().add(blankCircle);
           }
        }
        /** This handles the event if a player clicks on a piece to move and returns an appropriate message in response, part of inner class
         *
         * @parm void
         * @return void
         */
        private void handleClick(){
            //handles event only if the game isn't over
            if(gameOver == false) {
                //handle if the piece being interacted with is black
                if (this.getItem() == playerTurn && moves == 0) {
                    if (playerTurn == 'x') {
                        state.setText("Black piece move");
                        move = coordinates() + "-";
                        moves++;
                        //handle if the piece being interacted with is white
                    } else if (playerTurn == 'o') {
                        state.setText("White piece move");
                        move = coordinates() + "-";
                        moves++;
                    }
                    //if this is the second click then pass to other  handler
                } else if (moves == 1) {
                    handleClickAgain();
                    //if the player is attempting to move a piece that isn't their piece
                } else {
                    if (playerTurn != 'B') {
                        String team;
                        if (playerTurn == 'x') {
                            team = " team black!";
                        } else {
                            team = " team white!";
                        }
                        state.setText("Invalid move, please move your piece" + team + "!");
                    }
                }
            }

        }
        /** This handles the event of where the player wants to move with the piece they selected, this is part of the inner class
         *
         * @parm void
         * @return void
         */
        private void handleClickAgain() {
            if (playerTurn != 'B' && gameOver == false) {
                //if this is the second move the player is trying to make
                if (moves == 1) {
                    int tempCurrent;
                    move = move + coordinates();
                    if (playerTurn == 'x') {
                        tempCurrent = 1;
                    } else {
                        tempCurrent = 0;
                    }
                    //if the move is valid make sure next player gets their turn
                    if (gameSession.isValid(move, tempCurrent)) {
                        if (playerTurn == 'x') {
                            moves = 0;
                            move = "";
                            playerTurn = 'o';
                            //push board update
                            updateBoard(gameSession.getBoard());
                        } else {
                            moves = 0;
                            move = "";
                            playerTurn = 'x';
                            //push board update
                            updateBoard(gameSession.getBoard());
                        }
                    } else {
                        String team= "";
                        if (playerTurn == 'x') {
                            team = " team black!";
                        } else {
                            team = " team white!";
                        }
                        state.setText("Invalid move, please move your piece of choice again" + team);
                    }
                }
                moves = 0;
                move = "";
            }
        }

    }

    /** This method updates the GUI board with the moves that get recorded
     *
     * @parm the 2d array that hold the board stored in the logic object
     * @return void
     * @throws ArrayIndexOutOfBoundsException if the board update goes beyond the size of the board, notifying the user
     */
    public void updateBoard(String [][] board) throws ArrayIndexOutOfBoundsException {
        try {
            //clear the board of artifacts from the other pieces moved
            int clear = 0;
            while (clear != 5) {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        gameSize[i][j].setPiece('N');
                    }
                }
                clear++;
            }

            //updates the game board to match the game board that is kept track of
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j].equals("x")) {
                        gameSize[i][j].setPiece('x');
                    } else if (board[i][j].equals("o")) {
                        gameSize[i][j].setPiece('o');
                    } else {
                        gameSize[i][j].setPiece('N');
                    }
                }
            }
            //checks whose turn it is and updates the label accordingly
            if (playerTurn == 'x') {
                state.setText("Black piece move");
            } else {
                if (computerPlayer == false) {
                    state.setText("White piece move");
                }
            }
            //checks to see if anyone won the game
            //check Black
            if (gameSession.gameStatus(1)) {
                state.setText("Game Over: Black Team has won the Game!");
                gameOver = true;
            }
            //check white
            if (gameSession.gameStatus(0)) {
                state.setText("Game Over: White Team has won the Game!");
                playerTurn = 'B';
                gameOver = true;
            }
            //if both win then it is a tie
            if (gameSession.gameStatus(1) && gameSession.gameStatus(0)) {
                state.setText("Game Over: Tie!");
                playerTurn = 'B';
                gameOver = true;
            }
            //if the computer player is allowed then after win check move the bot piece
            if (gameOver == false && computerPlayer == true && playerTurn == 'o') {
                JohnDoeGUI.move();
                playerTurn = 'x';
                updateBoard(board);

            }

        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Board out of Bounds caught! and resolved");
        }
    }

    /** This method is the main method for the Java Fx program that launches the appropriate version of the game
     *
     * @parm an array of Strings
     * @return void
     */
    public static void main(String[] args) {
        //creates scanner object
            Scanner choice = new Scanner(System.in);
            String whatToDo = "";
            //creates a while loop to retrieve the player's choice
            while (!(whatToDo.equals("C")) || !(whatToDo.equals("W"))) {
                System.out.println("Enter 'C' if you want to play in Command Line Mode; enter 'W' to play in a Windowed GUI!");
                whatToDo = choice.nextLine();
                if (whatToDo.equals("C")) {
                    CheckersTextConsole.main(args);
                }
                if (whatToDo.equals("W")) {
                    break;
                }
            }
            // ask the player and according to their choice a GUI pops up with computer being true or not
            whatToDo = "";
            while (!(whatToDo.equals("P")) || !(whatToDo.equals("C"))) {
                System.out.println("Begin Game. Enter 'P' if you want to play against another player; enter 'C' to play against computer. ");
                whatToDo = choice.nextLine();
                if (whatToDo.equals("P")) {
                    launch(args);
                }
                if (whatToDo.equals("C")) {
                    computerPlayer = true;
                    launch(args);
                }
            }
    }
}
