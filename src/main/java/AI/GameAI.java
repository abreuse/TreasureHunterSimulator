package AI;

import Element.Adventurer;
import Element.Square;

import java.util.HashMap;
import java.util.List;

public class GameAI {

    private List<Adventurer> adventurers;

    private HashMap<String, Square> board;

    private MoveAI moveAI;

    private RotateAI rotateAI;

    public GameAI(List<Adventurer> adventurers, HashMap<String, Square> board) {
        this.adventurers = adventurers;
        this.board = board;
        moveAI = new MoveAI();
        rotateAI = new RotateAI();
    }

    public void nextTick() throws InterruptedException {
        for (Adventurer adventurer : adventurers) {
            if(adventurer.getSquare().getTreasures() == 0)
            {
                if(adventurer.getPath().charAt(0) == 'A')
                    moveAI.move(adventurer, board);

                else if(adventurer.getPath().charAt(0) == 'G')
                    rotateAI.rotateLeft(adventurer);

                else if(adventurer.getPath().charAt(0) == 'D')
                    rotateAI.rotateRight(adventurer);
            }
            else
                adventurer.getSquare().setTreasures(adventurer.getSquare().getTreasures() - 1);
        }

        CommandAI.executeAllCommands();
        CommandAI.saveCommands();

        //TODO: condition pour reboucler jusqu'a la fin du jeu
        //TODO: repaint les components graphiques
        wait(1000);
    }
}
