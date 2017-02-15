package AI.Command;

import Element.Square;

public class PickOneTreasure implements ICommand {

    Square square;

    public PickOneTreasure(Square square) {
        this.square = square;
    }

    @Override
    public void execute() {
        square.setTreasures(square.getTreasures() - 1);
    }

    @Override
    public void undo() {

        square.setTreasures(square.getTreasures() + 1);
    }
}
