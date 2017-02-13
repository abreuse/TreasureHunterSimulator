package AI.Command;

import Element.Adventurer;
import Element.Square;

import java.util.HashMap;

public class AdventurerMoveWest extends AbstractAdventurerMove implements ICommand{

    public AdventurerMoveWest(Adventurer adventurer, HashMap<String, Square> board) {
        super(board, adventurer);
    }

    @Override
    public void execute() {
        adventurer.setPath(adventurer.getPath().substring(1, adventurer.getPath().length()));
        adventurer.setSquare(board.get(adventurer.getPositionX() + " " + (adventurer.getPositionY() + 1)));
    }

    @Override
    public void undo() {
        adventurer.setSquare(board.get(adventurer.getPositionX() + " " + (adventurer.getPositionY() - 1)));
        adventurer.setPath("A" + adventurer.getPath());
    }
}
