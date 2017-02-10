package element;

import java.util.HashMap;

public class Game {

    private HashMap<String, Square> board;

    private int width;

    private int height;

    public Game(HashMap<String, Square> board, int height, int width) {
        this.board = board;
        this.width = width;
        this.height = height;
    }
}
