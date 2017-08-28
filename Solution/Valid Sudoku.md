Description:

Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

![Sudoku example](http://7xnyvm.com1.z0.glb.clouddn.com/LeetCode-36.png)

A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

Solution:

注意
不一定要这个数独是有解，只要当前已经填充的数字是合法的就可以。

思路
玩游戏先弄懂规则最重要。数独好像国处很受欢迎，但我还没见过人玩。

由于只要当前已经填充的数字是合法的就可以，因此只需要判断9*9网格的每一行、每一列、9个小九宫格是否合法。如果在每一行、每一列、每个9个小九宫格内有重复数字则不合法。

三个循环，各判断行、列、小九宫格是否合法，为了节省时间，可以用bitmap来代表这个数字是否出现，bitmap可以用数组，只有0到9的数字为index，false和true为值，出现过值为true， 关于vector里面装bool类型，在<<Effective STL>> 这本书里有说明，最好不要在vector装bool类型。
由于有规律，三个在不同循环判断的可以写在一个里面。

```java
public boolean isValidSudoku(char[][] board){
    int n = 9;
    int[][] rowMap = new int[n][n];
    int[][] colMap = new int[n][n];
    int[][] boardMap = new int[n][n];

    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            if(board[i][j] == '.'){
                continue;
            }
            int index = board[i][j] - '0' - 1;

            // row 
            if(rowMap[i][index] == true){
                return false;
            }
            rowMap[i][index] = true;

            // col 
            if(colMap[j][index] == true){
                return false;
            }
            colMap[j][index] = true;

            // board
            if(boardMap[i/3*3+j/3][index] == true){
                return false;
            }
            boardMap[i/3*3+j/3][index] = true;
        }
    }
    return true;
}
```