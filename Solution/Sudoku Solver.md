Description:

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character '.'.

You may assume that there will be only one unique solution.

![Sudoku example](http://7xnyvm.com1.z0.glb.clouddn.com/LeetCode-36.png)

A sudoku puzzle...

![Sudoku Solver](http://7xnyvm.com1.z0.glb.clouddn.com/LeetCode-37.png)

...and its solution numbers marked in red.

Solution:
题目的意思应该是给一个未完成的数独，然后通过程序可以自动填充来求解数独。

先查看现有的数字用Valid Sudoku检查符合数独的条件不
递归
从board的第一格开遍历，
1. 如果当前符号不为'.'，则遍历该行中下一格（先行后列），如果遍历到的格数大于了最大列数，则遍历行，终止条件行数也大于最大行数
2. 为'.'时，从1-9,依次插入，再重复整个过程

```java
int n = 9;
boolean[][] rowMap = new boolean[n][n];
boolean[][] colMap = new boolean[n][n];
boolean[][] boardMap = new boolean[n][n];

public void solveSudoku(char[][] board){
    if(isValidSudoku(board) != false){
        recursiveSudoku(board, 0, 0);
    }
}

public boolean recursiveSudoku(char[][] board, int row, int col){
    if (row >= 9) {
        return true;
    }
    
    if (col >= 9){
        return recursiveSudoku(board, row+1, 0);
    }
    
    if (board[row][col] != '.'){
        return recursiveSudoku(board, row, col+1);
    }
   
    int area;
    for(int i=0; i < 9; ++i){
        area = (row/3) * 3 + (col/3);
        if (rowMap[row][i] || colMap[col][i] || boardMap[area][i]){
            continue;
        }
   
        board[row][col] = (char) (i + '1');
        rowMap[row][i] = colMap[col][i] = boardMap[area][i] = true;
        if (recursiveSudoku(board, row, col+1)){
            return true;
        }

        board[row][col] = '.';
        rowMap[row][i] = colMap[col][i] = boardMap[area][i] = false;
    }
        return false;
}

public boolean isValidSudoku(char[][] board) {
    
    for(int i = 0; i < 9; i++){
        for(int j = 0; j < 9; j++){
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

