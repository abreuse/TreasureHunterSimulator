package AI.Command;

import Element.Adventurer;
import Element.Square;

import java.util.HashMap;

public abstract class AbstractAdventurerMove {

    protected HashMap<String, Square> board;

    protected Adventurer adventurer;

    public AbstractAdventurerMove(HashMap<String, Square> board, Adventurer adventurer) {
        this.board = board;
        this.adventurer = adventurer;
    }
}
