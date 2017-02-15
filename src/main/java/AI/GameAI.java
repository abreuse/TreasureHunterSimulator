package AI;

import AI.Command.PickOneTreasure;
import Element.Adventurer;
import Element.Square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GameAI {

    private List<Adventurer> adventurers;

    private List<Adventurer> exhaustedAdventurers;

    private HashMap<String, Square> board;

    private MoveAI moveAI;

    private RotateAI rotateAI;

    public GameAI(List<Adventurer> adventurers, HashMap<String, Square> board) {
        this.exhaustedAdventurers = new ArrayList<>();
        this.adventurers = adventurers;
        this.board = board;
        moveAI = new MoveAI();
        rotateAI = new RotateAI();
    }

    public void nextTick() throws InterruptedException {
        for (Adventurer adventurer : adventurers) {
            if(adventurer.getSquare().getTreasures() == 0)
            {
                if(!Objects.equals(adventurer.getPath(), "")){
                    if(adventurer.getPath().charAt(0) == 'A')
                        moveAI.move(adventurer, board);

                    else if(adventurer.getPath().charAt(0) == 'G')
                        rotateAI.rotateLeft(adventurer);

                    else if(adventurer.getPath().charAt(0) == 'D')
                        rotateAI.rotateRight(adventurer);
                }
                else
                    exhaustedAdventurers.add(adventurer);
            }
            else
                CommandAI.addCommand(new PickOneTreasure(adventurer.getSquare()));
                //adventurer.getSquare().setTreasures(adventurer.getSquare().getTreasures() - 1);
        }

        if(!exhaustedAdventurers.isEmpty())
        {
            for (Adventurer adventurer : exhaustedAdventurers) {
                adventurers.remove(adventurer);
            }
            exhaustedAdventurers.clear();

        }

        CommandAI.executeAllCommands();
        CommandAI.saveCommands();
    }
}
