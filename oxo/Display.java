import java.util.*;

class Display {

    private String name1;
    private String name2;
    private Scanner sc = new Scanner(System.in);
    private int rownum = 3, colnum = 3;
    Test t = new Test();
    private char[][] displayBoard = { { ' ', '1', '2', '3' }, { 'a', '.', '.', '.' }, { 'b', '.', '.', '.' },
            { 'c', '.', '.', '.' } };

    public void gameStart() {

        System.out.println("Welcome to Noughts & Crosses!");
        System.out.print("Input the player 1 name:\n");

        try {
            String input = sc.next();
            name1 = input;
        } catch (Exception e) {
            throw new Error(e);
        }

        System.out.print("Input the player 2 name:\n ");
        try {
            String input = sc.next();
            name2 = input;

        } catch (Exception e) {
            throw new Error(e);
        }
        System.out.println("Player 1: " + name1 + "\nPlayer 2: " + name2);
    }

    public void displayBoard(char[][] currBoard) {
        copyBoard(currBoard);
        printBoard();
    }

    private void copyBoard(char[][] currBoard) {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                displayBoard[i][j] = currBoard[i - 1][j - 1];
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(" " + displayBoard[i][j]);
            }
            System.out.println("");
        }
    }

    public String getMove(int count) {
        if (count % 2 == 0) {
            System.out.print("Player " + name1 + " choose your move(eg.A1 or a1): ");
        } else {
            System.out.print("Player " + name2 + " choose your move(eg.A1 or a1): ");
        }
        String move = sc.next();
        if (CheckMove(move) == true) {
            return move;
        } else {
            System.out.println("Please [row][column](eg.A1 or b2):");
            return getMove(count);
        }
    }

    public boolean CheckMove(String move) {
        if (isValidCol(move, colnum) && isValidRow(move, rownum)) {
            return true;
        }
        return false;
    }

    public void gameEnd(char player) {
        if (player == '1') {
            System.out.println("Player " + name1 + " has won the game!");
        } else if (player == '0') {
            System.out.println("Player " + name2 + " has won the game!");
        } else {
            System.out.println("The game was a draw...");
        }
    }

    private boolean isValidRow(String s, int rownum) {
        if (!isCorrectLength(s))
            return false;

        String temp = s.toLowerCase();
        char c = temp.charAt(0);
        if (c - 'a' < rownum && Character.isLowerCase(c))
            return true;
        return false;
    }

    private boolean isValidCol(String s, int colnum) {
        if (!isCorrectLength(s))
            return false;

        char c = s.charAt(1);
        if (!Character.isDigit(c))
            return false;

        int n = Character.getNumericValue(s.charAt(1));
        if (n <= colnum && n > 0)
            return true;

        return false;
    }

    private boolean isCorrectLength(String s) {
        if (s != null && s.length() == 2)
            return true;

        return false;
    }

    void test() {
        t.is(false, isCorrectLength("ant"));
        t.is(false, isCorrectLength("a"));
        t.is(true, isCorrectLength("as"));
        t.is(false, isValidCol("d4", colnum));
        t.is(false, isValidCol("d0", colnum));
        t.is(false, isValidCol("d13", colnum));
        t.is(true, isValidCol("a1", colnum));
        t.is(true, isValidCol("11", colnum));
        t.is(true, isValidCol("c1", colnum));
        t.is(false, isValidRow("e3", rownum));
        t.is(false, isValidRow("53", rownum));
        t.is(false, isValidRow("a234", rownum));
        t.is(true, isValidRow("c1", rownum));
        t.is(true, isValidRow("b3", rownum));

        t.results();
    }
}
