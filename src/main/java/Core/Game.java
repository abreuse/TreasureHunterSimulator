package Core;

import Element.Adventurer;
import Element.Square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {

    private HashMap<String, Square> board;

    private List<Adventurer> adventurers;

    private int width;

    private int height;

    public Game(HashMap<String, Square> board, int height, int width) {
        adventurers = new ArrayList<Adventurer>();
        this.board = board;
        this.width = width;
        this.height = height;
    }

    public HashMap<String, Square> getBoard() {
        return board;
    }

    public void setBoard(HashMap<String, Square> board) {
        this.board = board;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    public void setAdventurers(List<Adventurer> adventurers) {
        this.adventurers = adventurers;
    }
}
