package AI.Command;

import Element.Adventurer;
import Element.Square;

import java.util.HashMap;

public class AdventurerMoveEast extends AbstractAdventurerMove implements ICommand{

    public AdventurerMoveEast(Adventurer adventurer, HashMap<String, Square> board) {
        super(board, adventurer);
    }

    @Override
    public void execute() {
        adventurer.setPath(adventurer.getPath().substring(1, adventurer.getPath().length()));
        adventurer.getSquare().setAdventurer(null);
        adventurer.setSquare(board.get((adventurer.getPositionX() + 1) + " " + adventurer.getPositionY()));
        adventurer.getSquare().setAdventurer(adventurer);
    }

    @Override
    public void undo() {
        adventurer.getSquare().setAdventurer(null);
        adventurer.setSquare(board.get((adventurer.getPositionX() - 1) + " " + adventurer.getPositionY()));
        adventurer.getSquare().setAdventurer(adventurer);
        adventurer.setPath("A" + adventurer.getPath());
    }
}
