/** This class implements the logic behind the computer player's interaction
 *
 * @author Arnold Shibu
 * @verison 1.0 Nov 2, 2021.
 *
 */

package core;

public class CheckersComputerPlayer {
    //instance variables
    private CheckersLogic currentBoard;
    boolean result;

    //constructor for the bot
    /** This is the constructor for the class
     *
     * @parm an CheckerLogic object
     * @return void
     */
    public CheckersComputerPlayer(CheckersLogic board){
        currentBoard = board;
        result = false;
    }
    // a method that calls the bot to make a move according to their piece
    /** This method contains the functionality and  to move an X piece to the left
     *
     * @return true if the piece selected was moved to the left or right else it is false, notifying the player this is not possible
     */
    public boolean move(){

        for(int i = 0; i < currentBoard.size(); i++){
            for(int j = 0; j < currentBoard.size(); j++){
                if( (i != 0) && (currentBoard.figure(i,j)).equals("o")){
                    if(j == 7 && (currentBoard.figure(i,j)).equals("o")){
                        //move the piece to the left
                        result = moveLeft(i,j);
                        if(result == true){
                            return result;
                        }
                    }
                    else if(j == 0 && (currentBoard.figure(i,j)).equals("o")){
                        //move to the piece to the right
                        result = moveRight(i,j);
                        if(result == true){
                            return result;
                        }
                    }
                    else{
                        // try to move either to the left or right
                        result = moveLeft(i,j);
                        if(result == true){
                            return result;
                        }
                        result = moveRight(i,j);
                        if(result == true){
                            return result;
                        }
                    }
                }
            }
        }
        return false;
    }
    /** This method contains the functionality and  to move an X piece to the left
     *
     * @parm the x location of the given selected piece
     * @parm the y location of the given selected piece
     * @return true if the piece selected was moved to the left else it is false notifying the player this is not possible
     * @throws IndexOutOfBoundsException and returns false since the move was unable to be made
     */
    public boolean moveLeft(int x, int y) throws IndexOutOfBoundsException{
        try {
            //if the immediate left diagonal is open execute this
            if ((currentBoard.figure(x - 1, y - 1)).equals("_")) {
                currentBoard.setFigure(x - 1, y - 1, "o");
                currentBoard.setFigure(x, y, "_");
                return true;
            }
            //if the immediate diagonal is filled with an opponent's piece do this
            if ((currentBoard.figure(x - 1, y - 1)).equals("x")) {
                //if there is an open space, the next diagonal over
                if (x - 2 >= 0 && y - 2 >= 0) {
                    //if there is a piece either your piece or opponent's we can't move that
                    if ((currentBoard.figure(x - 2, y - 2)).equals("x") || (currentBoard.figure(x - 2, y - 2)).equals("o")) {
                        return false;
                    }
                    //if there is an empty space capture it
                    else {
                        currentBoard.setFigure(x - 2, y - 2, "o");
                        currentBoard.setFigure(x - 1, y - 1, "_");
                        currentBoard.setFigure(x, y, "_");
                        //take X
                        currentBoard.captureX();
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
    /** This method contains the functionality and  to move an X piece to the left
     *
     * @parm the x location of the given selected piece
     * @parm the y location of the given selected piece
     * @return true if the piece selected was moved to the right else it is false notifying the player this is not possible
     * @throws IndexOutOfBoundsException and returns false since the move was unable to be made
     */
    public boolean moveRight(int x, int y) throws IndexOutOfBoundsException{
        try {
            //if the immediate right diagonal is open execute this
            if ((currentBoard.figure(x - 1, y + 1)).equals("_")) {
                currentBoard.setFigure(x - 1, y + 1, "o");
                currentBoard.setFigure(x, y, "_");
                return true;
            }

            //if the immediate diagonal is filled with an opponent's piece do this
            if ((currentBoard.figure(x - 1, y + 1)).equals("x")) {
                //if there is an open space, the next diagonal over
                if (x - 2 >= 0 && y + 2 <= 7) {
                    //if there is a piece either your piece or opponent's we can't move that
                    if ((currentBoard.figure(x - 2, y + 2)).equals("x") || (currentBoard.figure(x - 2, y + 2)).equals("o")) {
                        return false;
                    }
                    //if there is an empty space capture it
                    else {
                        currentBoard.setFigure(x - 2, y + 2, "o");
                        currentBoard.setFigure(x - 1, y + 1, "_");
                        currentBoard.setFigure(x, y, "_");
                        //take X
                        currentBoard.captureX();
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
}
