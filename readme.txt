Important Note on how to get code coverage report, you will fist click the file "Code Coverage Report" then you will click on "index.html"

The Junit code is in the test file and the source code is in the core and ui file respectively.


important note about moves,
if you are try to capture a piece of the enemy team your inout should be as follows:
SelectPiece-PieceToCapture

*The code will automatically make the jump so don't input the diagonal space after the piece you are
*trying to capture! in GUI Mode and in Console Mode

Example:

Board:
3 _|_|_|
2 _|o|_|
1 _|_|x|
  a b c 

Player X's turn
Input:1c-2b

Board after move:

3 x|_|_|
2 _|_|_|
1 _|_|_|
  a b c 