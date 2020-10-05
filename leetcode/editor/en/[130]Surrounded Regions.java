//Given a 2D board containing 'X' and 'O' (the letter O), capture all regions su
//rrounded by 'X'. 
//
// A region is captured by flipping all 'O's into 'X's in that surrounded region
//. 
//
// Example: 
//
// 
//X X X X
//X O O X
//X X O X
//X O X X
// 
//
// After running your function, the board should be: 
//
// 
//X X X X
//X X X X
//X X X X
//X O X X
// 
//
// Explanation: 
//
// Surrounded regions shouldn’t be on the border, which means that any 'O' on th
//e border of the board are not flipped to 'X'. Any 'O' that is not on the border 
//and it is not connected to an 'O' on the border will be flipped to 'X'. Two cell
//s are connected if they are adjacent cells connected horizontally or vertically.
// 
// Related Topics Depth-first Search Breadth-first Search Union Find


// DFS
class Solution {
    int m;
    int n;
    List<int[]> boundary;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;
        m = board.length;
        n = board[0].length;
        boundary = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            boundary.add(new int[]{i, 0});
            boundary.add(new int[]{i, n - 1});
        }
        for (int j = 0; j < n; j++) {
            boundary.add(new int[]{0, j});
            boundary.add(new int[]{m - 1, j});
        }

        for (int i = 0; i < boundary.size(); i++) {
            int[] pair = boundary.get(i);
            dfs(board, pair[0], pair[1]);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == 'E')
                    board[i][j] = 'O';
            }
        }
    }

    public void dfs(char[][] board, int r, int c) {
        // 1. != 'X'
        // 2. != 'E': avoid duplicate -> stack overflow
        if (board[r][c] != 'O')
            return;
        board[r][c] = 'E';

        for (int i = 0; i < 4; i++) {
            int nr = r + dirs[i][0], nc = c + dirs[i][1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                dfs(board, r + dirs[i][0], c + dirs[i][1]);
            }
        }
    }
}

// BFS
class Solution {
    int m;
    int n;
    List<int[]> boundary;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0)
            return;
        m = board.length;
        n = board[0].length;
        boundary = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            boundary.add(new int[]{i, 0});
            boundary.add(new int[]{i, n - 1});
        }
        for (int j = 0; j < n; j++) {
            boundary.add(new int[]{0, j});
            boundary.add(new int[]{m - 1, j});
        }

        // BFS PART STARTS
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < boundary.size(); i++) {
            int[] pair = boundary.get(i);
            int r = pair[0], c = pair[1];
            if (board[r][c] == 'O')
                q.offer(new int[]{r, c});
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int[] head = q.poll();
                int row = head[0], col = head[1];
                board[row][col] = 'E';
                for (int k = 0; k < 4; k++) {
                    int nr = row + dirs[k][0], nc = col + dirs[k][1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'O') {
                        q.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        // BFS PART ENDS

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == 'E')
                    board[i][j] = 'O';
            }
        }
    }
}
