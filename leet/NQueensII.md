Description:

Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

Solution:

```java
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        dfs(board, 0, res);
        return res.size();
    }
    
    public void dfs(char[][] board, int colIndex, List<List<String>> res) {
        if (colIndex == board.length) {
            res.add(construct(board));
            return ;
        }
        
        for (int i = 0; i < board.length; i++) {
            if (validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, res);
                board[i][colIndex] = '.';
            }
        }
    }
    
    public List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for (int i = 0; i < board.length; i++) {
            res.add(new String(board[i]));
        }
        return res;
    }
    
    public boolean validate(char[][] board, int rowIndex, int colIndex) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < colIndex; j++) {
                if (board[i][j] == 'Q' && (colIndex - j == rowIndex - i || colIndex - j == i - rowIndex || rowIndex == i)) {
                    return false;
                }
            }
        }
        return true;
    }
```