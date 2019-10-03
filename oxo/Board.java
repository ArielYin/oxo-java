class Board {
    private char[][] gameBoard;
    private Boolean gameOver;
    private char player = '1';
    private int rownum = 3, colnum = 3;
    Test t = new Test();

    public Board() {
        gameBoard = new char[rownum][colnum];
        for (int i = 0; i < rownum; i++) {
            for (int j = 0; j < colnum; j++) {
                gameBoard[i][j] = '.';
            }
        }
        gameOver = false;
    }

    public char[][] getBoard() {
        return gameBoard;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public char getPlayer() {
        return player;
    }

    public void playerTurn(String move) {
        int[] moveXY;

        this.checkString(move);
        moveXY = this.returnXY(move);

        if (checkBoardSpace(moveXY) == true)
            return;

        boardMove(moveXY);
        if (checkWin() == true) {
            gameOver = true;
            return;
        }
        switchPlayers();
    }

    private Boolean checkBoardSpace(int[] moveXY) {
        if (gameBoard[moveXY[0]][moveXY[1]] == '.') {
            return false;
        }
        System.out.println("Invalid move: This position is already taken");
        return true;
    }

    private void boardMove(int[] moveXY) {
        if (player == '1') {
            gameBoard[moveXY[0]][moveXY[1]] = 'O';
        } else {
            gameBoard[moveXY[0]][moveXY[1]] = 'X';
        }
    }

    private void switchPlayers() {
        if (player == '1')
            player = '0';
        else
            player = '1';
    }

    private Boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (compareCells(gameBoard[i][0], gameBoard[i][1], gameBoard[i][2]) == true)
                return true;
            if (compareCells(gameBoard[0][i], gameBoard[1][i], gameBoard[2][i]) == true)
                return true;
        }
        if (compareCells(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]) == true)
            return true;
        if (compareCells(gameBoard[0][2], gameBoard[1][1], gameBoard[2][0]) == true)
            return true;

        return false;
    }

    private Boolean compareCells(char cell1, char cell2, char cell3) {
        if (cell1 == '.')
            return false;
        else if (cell1 == cell2 && cell1 == cell3)
            return true;
        else
            return false;
    }

    private String checkCol(int col) {
        if (col >= colnum || col < 0)
            throw new Error("Col out of bounds");

        String s = "";
        for (int i = 0; i < rownum; i++) {
            s += gameBoard[i][col];
        }
        return s;
    }

    private String checkRow(int row) {
        if (row >= rownum || row < 0)
            throw new Error("Row out of bounds");

        String s = "";
        for (int i = 0; i < colnum; i++) {
            s += gameBoard[row][i];
        }
        return s;
    }

    private String checkLDiag() {
        String s = "";
        int j = 0;
        for (int i = 0; i < rownum; i++) {
            s += gameBoard[i][j];
            j++;
        }
        return s;
    }

    private String checkRDiag() {
        String s = "";
        int j = rownum - 1;

        for (int i = 0; i < rownum; i++) {
            s += gameBoard[i][j];
            j--;
        }
        return s;
    }

    private Boolean checkString(String move) {
        String[] test = move.split(" ");
        if (test.length != 1) {
            return false;
        } else if (move.length() != 2) {
            return false;
        }
        return true;
    }

    private int[] returnXY(String move) {
        int[] temp = { 0, 0 };
        move = move.toUpperCase();
        temp[0] = move.charAt(0) - 'A';
        temp[1] = Character.getNumericValue(move.charAt(1)) - 1;
        return temp;
    }

    private void testInit() {
        for (int i = 0; i < rownum; i++) {
            for (int j = 0; j < colnum; j++) {
                gameBoard[j][i] = '.';
            }
        }
        gameBoard[0][0] = '0';
        gameBoard[1][1] = 'X';
        gameBoard[2][2] = 'X';
        gameBoard[0][2] = '0';
    }

    void test() {
        testInit();
        t.is(checkRow(0), "0.0");
        t.is(checkRow(1), ".X.");
        t.is(checkRow(2), "..X");
        t.is(checkCol(0), "0..");
        t.is(checkCol(1), ".X.");
        t.is(checkCol(2), "0.X");
        t.is(checkLDiag(), "0XX");
        t.is(checkRDiag(), "0X.");

        t.results();
    }
}
