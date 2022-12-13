/** This class implements the console interface the user interacts with in the checkers game and contains the main
 *
 * @author Arnold Shibu
 * @verison 2.0 Nov 2, 2021.
 */
package ui;


import core.*;
import java.util.*;
public class CheckersTextConsole {
    /** The main method of the program and displays the text base UI
     *
     * @parm an array of Strings
     * @return void
     */
    public static void main (String[] args){

        boolean status = false;
        String move = "";
        boolean valid = false;
        int black = 1;
        int white = 0;
        int current = 1;
        boolean start = true;
        CheckersLogic board = new CheckersLogic();
        boolean computer = false;
        System.out.println(board.toString());
        CheckersComputerPlayer JohnDoe = new CheckersComputerPlayer(board);

        while (status == false){

            if (start == true) {

                start = false;
                Scanner choice = new Scanner(System.in);
                String whatToDo = "";
                while(!(whatToDo.equals("P")) || !(whatToDo.equals("C"))){
                    System.out.println("Begin Game. Enter 'P' if you want to play against another player; enter 'C' to play against computer. ");
                    whatToDo = choice.nextLine();

                    if(whatToDo.equals("C")){
                        computer = true;
                        System.out.println(board.toString());
                        break;

                    }
                    if(whatToDo.equals("P")){
                        computer = false;
                        System.out.println(board.toString());
                        break;

                    }
                }

            } else {
                System.out.println(board.toString());
            }

            if(computer == false) {
                if (current == black) {
                    System.out.println("PlayerX - your turn.");
                    System.out.println("Choose a cell position of piece to be moved and the new position. e.g., 3a-4b");
                    Scanner input = new Scanner(System.in);
                    move = input.nextLine();

                } else {
                    System.out.println("PlayerO - your turn.");
                    System.out.println("Choose a cell position of piece to be moved and the new position. e.g., 3a-4b");
                    Scanner input = new Scanner(System.in);
                    move = input.nextLine();
                }

                //check if move is valid
                if (board.isValid(move, current) == true) {
                    //check and store status
                    if (current == black) {
                        current = white;
                    } else {
                        current = black;
                    }
                } else {
                    current = black;
                }
                boolean xWin=board.gameStatus(black);
                boolean oWin= board.gameStatus(white);
                //checks win
                if (xWin == true && oWin == true) {
                    System.out.println("Tie!");
                    status = true;
                }
                else if (xWin == true) {
                    System.out.println("Player X won the game!");
                    status = true;
                }
                else if(oWin == true){
                    System.out.println("Player O won the game!");
                    status = true;
                }
                else{
                    status = status;
                }
            }
            else{
                if (current == black) {
                    System.out.println("PlayerX - your turn.");
                    System.out.println("Choose a cell position of piece to be moved and the new position. e.g., 3a-4b");
                    Scanner input = new Scanner(System.in);
                    move = input.nextLine();
                    //validate user's move
                    if (board.isValid(move, current) == true) {
                        //check and store status
                        if (current == black) {
                            current = white;
                        } else {
                            current = black;
                        }
                    } else {
                        current = black;
                    }
                }
                if(current == white){
                    if(JohnDoe.move() == true) {
                        current = black;
                    }
                }
                boolean xWin=board.gameStatus(black);
                boolean oWin= board.gameStatus(white);
                //checks win
                if (xWin == true && oWin == true) {
                    System.out.println("Tie!");
                    status = true;
                }
                else if (xWin == true) {
                    System.out.println("Player X won the game!");
                    status = true;
                }
                else if(oWin == true){
                    System.out.println("Player O won the game!");
                    status = true;
                }
                else{
                    status = status;
                }

            }

        }

    }

}
