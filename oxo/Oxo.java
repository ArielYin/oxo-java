class Oxo {

    private Board board;
    private Display display;
    int count = 0;

    public static void main(String[] args) {
        Oxo game = new Oxo();
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if ("test".equals(args[i]))
                    game.test();
            }
        } else {
            game.initialise();
            game.run();
        }
    }

    private void initialise() {
        board = new Board();
        display = new Display();
    }

    private void run() {
        String move;
        display.gameStart();

        while (board.getGameOver() == false && count < 9) {
            display.displayBoard(board.getBoard());
            move = display.getMove(count);
            board.playerTurn(move);
            count++;
            if (count == 9)
                break;

        }
        display.displayBoard(board.getBoard());
        display.gameEnd(board.getPlayer());
    }

    void test() {
        Board b = new Board();
        Display d = new Display();
        b.test();
        d.test();
    }
}
