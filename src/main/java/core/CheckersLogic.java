/** This class implements the logic behind the user interacts with in the checkers game
 *
 * @author Arnold Shibu
 * @verison 3.0 Nov 9 , 2021.
 *
 */
package core;
public class CheckersLogic {
    //instance variables that set up the board

    public String[][] board = {{"_", "x","_", "x","_", "x","_", "x"},
                                {"x", "_","x", "_","x", "_","x", "_"},
                                {"_", "x","_", "x","_", "x","_", "x"},
                                {"_", "_","_", "_","_", "_","_", "_"},
                                {"_", "_","_", "_","_", "_","_", "_"},
                                {"o", "_","o", "_","o", "_","o", "_"},
                                {"_", "o","_", "o","_", "o","_", "o"},
                                {"o", "_","o", "_","o", "_","o", "_"}};





    private String[] xAxis ={"h","g","f","e","d","c","b","a"};
    private String[] yAxis ={"1","2","3","4","5","6","7","8"};
    public int xCount = 0;
    public int oCount = 0;
    private Exception Index = new IndexOutOfBoundsException();

    /** This is the constructor, it initializes all the  instance variables to their default value
     *
     * @parm void
     * @return void
     */
    public CheckersLogic(){
       xCount = 12;
       oCount = 12;

    }

    public String[][] getBoard(){
        return board;
    }
    /** This method validates the move and proceeds to make the move if valid
     *
     * @parm moved the piece moved and where
     * @parm current the piece who's turn it is
     * @return boolean value representing if the move was valid and was done
     * @exception IndexOutOfBoundsException is thrown and handled by catch to warn user that the input is invalid
     * @exception NumberFormatException is thrown to let the user know that they need to type out their move appropriately
     */
    public boolean isValid(String moved, int current) throws IndexOutOfBoundsException, NumberFormatException{
        boolean result = true;
        String pieceSelected = "";
        String pieceAtLocation = "";
        try {
            int selectedY = getXValue(moved.substring(1, 2)) - 1; //column number
            int selectedX = Integer.parseInt(moved.substring(0, 1)) - 1; //row number
            int moveY = getXValue(moved.substring(4)) - 1; // column number of where to move
            int moveX = Integer.parseInt(moved.substring(3, 4)) - 1;//row number of where to move
            
            //check to see if they piece they are trying to move is within the bounds of the board
            if(moveY >= 8 || selectedY >= 8 || moveY <= -1  || selectedY <= -1){
                throw new IndexOutOfBoundsException();
            }

            // get the piece that they are trying to move in the piece selected variable
            if (current == 0) {
                pieceSelected = "o";
            } else{
                pieceSelected = "x";
            }

            //if the piece that have chosen matches the piece whose turn it is begin the execution of what is inside this if
            if (getPiece(moved).equals(pieceSelected)) {
                //if it is o's move execute this if statement
                if (pieceSelected.equals("o")) {
                    //if the player is trying to move a piece that is at the end of the board
                    if (selectedX == 0) {
                        return false;
                    }
                    //if the player is attempting to move the piece  forward or backward
                    else if (selectedX == moveX) {
                        return false;
                    }
                    //if the player attempts to move the piece left or right
                    else if (selectedY == moveY) {
                        return false;
                    }
                    //if the piece is on the right edge attempt to move to the left
                    else if(selectedY == 7 && board[selectedX][selectedY].equals("o")) {
                        //we can only attempt to move to the left
                        return moveOLeft(selectedX, selectedY);
                    }

                    //if the piece is on the left edge
                    else if(selectedY == 0 && board[selectedX][selectedY].equals("o")) {
                        //we can only move to the right
                        return moveORight(selectedX, selectedY);
                    }
                    //if the player is attempting to move to the left diagonal
                    else if(moveX == selectedX -1 && moveY == selectedY - 1){
                        return moveOLeft(selectedX, selectedY);
                    }
                    //if the player is attempting to move to the right diagonal
                    else if(moveX == selectedX -1 && moveY == selectedY + 1){
                        return moveORight(selectedX, selectedY);
                    }

                }

                //if the piece that is moved is x move
                else if(pieceSelected.equals("x")) {
                    //if the player tried to move a piece that has reached the end of the board
                    if (selectedX == 7) {
                        return false;
                    }
                    //if the player is attempting to move the piece  forward or backward
                    else if (selectedY == moveY) {
                        return false;
                    }
                    //if the player attempts to move the piece left or right
                    else if (selectedX == moveX) {
                        return false;
                    }

                    //if the piece is on the right edge attempt to move to the left
                    else if(selectedY == 7 && board[selectedX][selectedY].equals("x")) {
                        //we can only attempt to move to the left
                        return moveXLeft(selectedX, selectedY);
                    }
                    //if the piece is on the left edge
                    else if(selectedY == 0 && board[selectedX][selectedY].equals("x")) {
                        //we can only move to the right
                        return moveXRight(selectedX, selectedY);
                    }
                    //if the player is attempting to move to the left diagonal
                    else if(moveX == selectedX + 1 && moveY == selectedY - 1){
                        return moveXLeft(selectedX, selectedY);
                    }
                    //if the player is attempting to move to the right diagonal
                    else if(moveX == selectedX + 1 && moveY == selectedY + 1){
                        return moveXRight(selectedX, selectedY);
                    }

                }
                else{
                    System.out.println("You can't move that piece try again!");
                    return false;
                }
            }
            return false;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Please request a valid move because " + moved +" isn't valid as it is off the board and violates game rules. \n");
            return false;
        }
        catch(NumberFormatException e){
            System.out.println("Please type a valid move because " + moved +" isn't a valid coordinate. \n");
            return false;
        }
    }

    /** This method contains the functionality and  to move an X piece to the left
     *
     * @parm the x location of the given selected piece
     * @parm the y location of the given selected piece
     * @return true if the piece selected was moved to the left else it is false notifying the player this is not possible
     * @throws IndexOutOfBoundsException and returns false since the move was unable to be made
     */
    public boolean moveXLeft(int x, int y) throws IndexOutOfBoundsException {
        try {
            //throw invalid index exception
            if(x >= 8 || x >= 8 || x <= -1  || y <= -1){
                throw new IndexOutOfBoundsException();
            }
            //if the immediate left diagonal is open execute this
            if ((board[x + 1][y - 1]).equals("_")) {
                board[x + 1][y - 1] = "x";
                board[x][y] = "_";
                return true;
            }
            //if the immediate diagonal is filled with an opponent's piece do this
            if ((board[x + 1][y - 1]).equals("o")) {
                //if there is an open space, the next diagonal over
                if (x + 2 <= 7 && y - 2 >= 0) {
                    //if there is a piece either your piece or opponent's we can't move that
                    if ((board[x + 2][y - 2]).equals("x") || (board[x + 2][y - 2]).equals("o")) {
                        return false;
                    }
                    //if there is an empty space capture it
                    else {
                        board[x + 2][y - 2] = "x";
                        board[x + 1][y - 1] = "_";
                        board[x][y] = "_";
                        //take O
                        oCount--;
                        return true;
                    }
                }
                //if we reach here this means we cannot move because we are at the edge of the board
                else {
                    return false;
                }
            }
            //if the immediate left is filled with our piece we can't move
            return false;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("In valid move, please make a valid move");
            return false;
        }

    }

    /** This method contains the functionality and  to move an o piece to the left
     *
     * @parm the x location of the given selected piece
     * @parm the y location of the given selected piece
     * @return true if the piece selected was moved to the left else it is false notifying the player this is not possible
     * @throws IndexOutOfBoundsException and returns false since the move was unable to be made
     */
    public boolean moveOLeft(int x, int y) throws IndexOutOfBoundsException{
        try {
            //throw invalid index exception
            if(x >= 8 || x >= 8 || x <= -1  || y <= -1){
                throw new IndexOutOfBoundsException();
            }
            //if the immediate left diagonal is open execute this
            if ((board[x - 1][y - 1]).equals("_")) {
                board[x - 1][y - 1] = "o";
                board[x][y] = "_";
                return true;
            }
            //if the immediate diagonal is filled with an opponent's piece do this
            if ((board[x - 1][y - 1]).equals("x")) {
                //if there is an open space, the next diagonal over
                if (x - 2 >= 0 && y - 2 >= 0) {
                    //if there is a piece either your piece or opponent's we can't move that
                    if ((board[x - 2][y - 2]).equals("x") || (board[x - 2][y - 2]).equals("o")) {
                        return false;
                    }
                    //if there is an empty space capture it
                    else {
                        board[x - 2][y - 2] = "o";
                        board[x - 1][y - 1] = "_";
                        board[x][y] = "_";
                        //take X
                        xCount--;
                        return true;
                    }
                }
                //if we reach here this means we cannot move because we are at the edge of the board
                else {
                    return false;
                }
            }
            //if the immediate left is filled with our piece we can't move
            return false;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("In valid move, please make a valid move");
            return false;
        }
    }

    /** This method contains the functionality and  to move an X piece to the right
     *
     * @parm the x location of the given selected piece
     * @parm the y location of the given selected piece
     * @return true if the piece selected was moved to the left else it is false notifying the player this is not possible
     * @throws IndexOutOfBoundsException and returns false since the move was unable to be made
     */
    public boolean moveXRight(int x, int y) throws IndexOutOfBoundsException{
        try {
            //if the immediate right diagonal is open execute this
            if ((board[x + 1][y + 1]).equals("_")) {
                board[x + 1][y + 1] = "x";
                board[x][y] = "_";
                return true;
            }

            //if the immediate diagonal is filled with an opponent's piece do this
            if ((board[x + 1][y + 1]).equals("o")) {
                //if there is an open space, the next diagonal over
                if (x + 2 <= 7 && y + 2 <= 7) {
                    //if there is a piece either your piece or opponent's we can't move that
                    if ((board[x + 2][y + 2]).equals("x") || (board[x + 2][y + 2]).equals("o")) {
                        return false;
                    }
                    //if there is an empty space capture it
                    else {
                        board[x + 2][y + 2] = "x";
                        board[x + 1][y + 1] = "_";
                        board[x][y] = "_";
                        //take o
                        oCount--;
                        return true;
                    }
                }
                //if we reach here this means we cannot move because we are at the edge of the board
                else {
                    return false;
                }
            }
            //if the immediate left is filled with our piece we can't move
            return false;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("In valid move, please make a valid move.");
            return false;
        }
    }

    /** This method contains the functionality and  to move an o piece to the right
     *
     * @parm the x location of the given selected piece
     * @parm the y location of the given selected piece
     * @return true if the piece selected was moved to the left else it is false notifying the player this is not possible
     * @throws IndexOutOfBoundsException and returns false since the move was unable to be made
     */
    public boolean moveORight(int x, int y) throws IndexOutOfBoundsException{
        try {
            //if the immediate right diagonal is open execute this
            if ((board[x - 1][y + 1]).equals("_")) {
                board[x - 1][y + 1] = "o";
                board[x][y] = "_";
                return true;
            }

            //if the immediate diagonal is filled with an opponent's piece do this
            if ((board[x - 1][y + 1]).equals("x")) {
                //if there is an open space, the next diagonal over
                if (x - 2 >= 0 && y + 2 <= 7) {
                    //if there is a piece either your piece or opponent's we can't move that
                    if ((board[x - 2][y + 2]).equals("x") || (board[x - 2][y + 2]).equals("o")) {
                        return false;
                    }
                    //if there is an empty space capture it
                    else {
                        board[x - 2][y + 2] = "o";
                        board[x - 1][y + 1] = "_";
                        board[x][y] = "_";
                        //take X
                        xCount--;
                        return true;
                    }
                }
                //if we reach here this means we cannot move because we are at the edge of the board
                else {
                    return false;
                }
            }
            //if the immediate left is filled with our piece we can't move
            return false;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("In valid move, please make a valid move.");
            return false;
        }
    }

    /** This method is a helper that retrieves the piece the player is trying to move
     *
     * @parm moved which says the location of the piece player is moving
     * @return piece at the index
     * @exception IndexOutOfBoundsException thrown and caught to let the user know they are trying to use go to a part of the array not possible
     */
    private String getPiece(String moved) throws IndexOutOfBoundsException{
        try {
            String temp = "";
            int yLocation = 0;
            int xLocation = 0;
            temp = moved.substring(1, 2);
            xLocation = Integer.parseInt(moved.substring(0, 1)) - 1;
            yLocation = getXValue(temp) - 1;
            if (yLocation >= 8 || yLocation <= -1) {
                throw new IndexOutOfBoundsException();
            }
            return board[xLocation][yLocation];

        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Piece not found with given location.");
            return "N";
        }

    }

    /** This return the x value given a string of the desired index
     *
     * @parm String that contains Y movement
     * @return the integer value representation of the move
     * @exception IndexOutOfBoundsException is thrown and caught in order to return an invalid X to warn user
     */
    private int getXValue(String temp) throws IndexOutOfBoundsException{
        try {
            if (temp.equals("a")) {
                return 8;
            } else if (temp.equals("b")) {
                return 7;
            } else if (temp.equals("c")) {
                return 6;
            } else if (temp.equals("d")) {
                return 5;
            } else if (temp.equals("e")) {
                return 4;
            } else if (temp.equals("f")) {
                return 3;
            } else if (temp.equals("g")) {
                return 2;
            }
            else if (temp.equals("h")) {
                return 1;
            }
            else {
                throw new IndexOutOfBoundsException();
            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(temp + " isn't a x location on the board.");
            return -1;
        }
    }

    /** This method prints the board
     *
     * @parm void
     * @return the string containing board to be printed
     * @throws IndexOutOfBoundsException and is caught and a message is told to the user that the code ran into an issue
     */
    public String toString() throws IndexOutOfBoundsException{
        try {
            String result = "";
            for (int i = board.length - 1; i >= 0; i--) {
                result = result + yAxis[i] + " | ";
                for (int j = board.length - 1; j >= 0; j--) {
                    if (j != 0) {
                        result = result + board[i][j] + " | ";
                    } else {
                        result = result + board[i][j] + " |" + "\n";
                    }
                }

            }
            for (int i = board.length - 1; i >= 0; i--) {
                if (i == board.length - 1) {
                    result = result + "    " + xAxis[i];
                } else if (i == 0) {
                    result = result + "   " + xAxis[i] + "  ";
                } else {
                    result = result + "   " + xAxis[i];
                }
            }
            return result + "\n";
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("The Code went out of bounds!");
            return "";
        }
    }

    /** This method return true if the game is won by a team false it game is still in progress
     *
     * @parm takes in an integer corresponding with a team
     * @return boolean value representing if the move was valid and was done
     * @throws ArrayIndexOutOfBoundsException and is caught and the user is told what happened and normal procedure continues
     */
    public boolean gameStatus(int team) throws ArrayIndexOutOfBoundsException{
        try {
            double tally = 0;
            //black team is selected
            if (team == 1) {
                //check if Ocount is zero meaning there is no more enemy pieces
                if (oCount == 0) { //correct
                    return true;
                }
                //iterates through and checks if there are enemy piece that are stuck
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        //if the piece reaches end of board it is stuck
                        if (i == 0 && board[i][j].equals("o")) {
                            tally++;
                        }
                        //checks the left side column and stuck
                        else if (j == 0 && board[i][j].equals("o")) {
                            if ((board[i - 1][j + 1]).equals("x")) {
                                if (i - 2 >= 0 && j + 2 >= 0) {
                                    if ((board[i - 2][j + 2]).equals("x") || (board[i - 2][j + 2]).equals("o")) {
                                        tally++;
                                    }
                                } else {
                                    tally++;
                                }
                            }
                            if ((board[i - 1][j + 1]).equals("o")) {
                                tally++;
                            }
                        }
                        //checks right column and stuck
                        else if (j == 7 && board[i][j].equals("o")) {
                            if ((board[i - 1][j - 1]).equals("x")) {
                                if (i - 2 >= 0 && j - 2 >= 0) {
                                    if ((board[i - 2][j - 2]).equals("x") || (board[i - 2][j - 2]).equals("o")) {
                                        tally++;
                                    }
                                } else {
                                    tally++;
                                }
                            }
                            if ((board[i - 1][j - 1]).equals("o")) {
                                tally++;
                            }
                        }
                        // can move either left or right
                        else if (board[i][j].equals("o") && i != 0 && j != 7 && j != 0) {
                            //check right
                            if ((board[i - 1][j + 1]).equals("x")) {
                                if (i - 2 >= 0 && j + 2 >= 0) {
                                    if ((board[i - 2][j + 2]).equals("x") || (board[i - 2][j + 2]).equals("o")) {
                                        tally = tally + 0.5;
                                    }
                                } else {
                                    tally = tally + 0.5;
                                }
                            }
                            if ((board[i - 1][j + 1]).equals("o")) {
                                tally = tally + 0.5;
                            }
                            //check left
                            if ((board[i - 1][j - 1]).equals("x")) {
                                if (i - 2 >= 0 && j - 2 >= 0) {
                                    if ((board[i - 2][j - 2]).equals("x") || (board[i - 2][j - 2]).equals("o")) {
                                        tally = tally + 0.5;
                                    }
                                } else {
                                    tally = tally + 0.5;
                                }
                            }
                            if ((board[i - 1][j - 1]).equals("o")) {
                                tally = tally + 0.5;
                            }

                        }
                    }
                }
                // Test Tally System.out.println("O Tally: " + tally);
                if ((int) tally == oCount) {
                    return true;
                }
            }
            //if it is team o turn
            else {
                if (xCount == 0) {
                    return true;
                }

                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        //if the piece reaches end of board it is stuck
                        if (i == 7 && board[i][j].equals("x")) {
                            tally++;
                        }
                        //are we stuck on the left Column
                        else if (j == 0 && board[i][j].equals("x")) {
                            //if the immediate diagonal is filled with an opponent's piece do this
                            if ((board[i + 1][j + 1]).equals("o")) {
                                //if there is an open space, the next diagonal over
                                if (i + 2 <= 7 && j + 2 <= 7) {
                                    //if there is a piece either your piece or opponent's we can't move that
                                    if ((board[i + 2][j + 2]).equals("x") || (board[i + 2][j + 2]).equals("o")) {
                                        tally++;
                                    }
                                } else {
                                    tally++;
                                }
                            }
                            if ((board[i + 1][j + 1]).equals("x")) {
                                tally++;
                            }
                        }
                        //are we stuck on the right Column
                        else if (j == 7 && board[i][j].equals("x")) {
                            //if the immediate diagonal is filled with an opponent's piece do this
                            if ((board[i + 1][j - 1]).equals("o")) {
                                //if there is an open space, the next diagonal over
                                if (i + 2 <= 7 && j - 2 >= 0) {
                                    //if there is a piece either your piece or opponent's we can't move that
                                    if ((board[i + 2][j - 2]).equals("x") || (board[i + 2][j - 2]).equals("o")) {
                                        tally++;
                                    }
                                } else {
                                    tally++;
                                }
                            }
                            if ((board[i + 1][j - 1]).equals("x")) {
                                tally++;
                            }
                        }
                        //are we on the board, but not in the boarder regions
                        else if (board[i][j].equals("x") && i != 0 && j != 7 && j != 0) {

                            //check right movements
                            if ((board[i + 1][j + 1]).equals("o")) {
                                //if there is an open space, the next diagonal over
                                if (i + 2 <= 7 && j + 2 <= 7) {
                                    //if there is a piece either your piece or opponent's we can't move that
                                    if ((board[i + 2][j + 2]).equals("x") || (board[i + 2][j + 2]).equals("o")) {
                                        tally = tally + 0.5;
                                    }
                                }
                                else {
                                    tally = tally + 0.5;
                                }
                            }
                            if ((board[i + 1][j + 1]).equals("x")) {
                                tally = tally + 0.5;
                            }

                            //check left movements
                            //if the immediate diagonal is filled with an opponent's piece do this
                            if ((board[i + 1][j - 1]).equals("o")) {
                                //if there is an open space, the next diagonal over
                                if (i + 2 <= 7 && j - 2 >= 0) {
                                    //if there is a piece either your piece or opponent's we can't move that
                                    if ((board[i + 2][j - 2]).equals("x") || (board[i + 2][j - 2]).equals("o")) {
                                        tally = tally + 0.5;
                                    }
                                } else {
                                    tally = tally + 0.5;
                                }
                            }
                            if ((board[i + 1][j - 1]).equals("x")) {
                                tally = tally + 0.5;
                            }
                        }

                    }
                }
                // Test Tally System.out.println("X Tally: " + tally);
                if (tally == xCount) {
                    return true;
                }
            }
            return false;
        }
        catch (Exception e) {
            System.out.println("Board Noticed an edge case and attempted to fix the tally");
            return false;
        }
    }

    /** This method return the piece at a current index
     *
     * @parm takes in an integer corresponding with the x and y location of a piece
     * @return a String value that is either an X or O depenging on if the index is valid
     * @exception if the index passed are outside the board an exception is thrown
     */
    public String figure(int x, int y) throws IndexOutOfBoundsException{
        try {
            if (x > board.length || y > board.length) {
                throw new IndexOutOfBoundsException();
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (i == x && j == y) {
                        return board[i][j];
                    }
                }
            }
            return "n";
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("No piece is located at the location provided to retrieve.");
            return "N";
        }
    }
    /** This method sets the piece at a given index
     *
     * @parm takes in an integer corresponding with the x and y location of a piece
     * @return a String value that is either an X or O depenging on if the index is valid
     * @exception if the index passed are outside the board an exception is thrown
     */
    public void setFigure(int x, int y, String piece) throws IndexOutOfBoundsException{
        try {
            board[x][y] = piece;
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("No piece is located at the location provided to change");
            throw new IndexOutOfBoundsException();
        }
    }
    /** This method decrements the number of x pieces on the board
     *
     * @parm void
     * @return void
     */
    public void captureX(){
        xCount--;
    }
    /** This method decrements the number of x pieces on the board
     *
     * @parm void
     * @return the current size of board
     */
    public int size (){
        return board.length;
    }

}
