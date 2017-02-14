package AI;

import AI.Command.AdventurerMoveEast;
import AI.Command.AdventurerMoveNorth;
import AI.Command.AdventurerMoveSouth;
import AI.Command.AdventurerMoveWest;
import Element.Adventurer;
import Element.Square;

import java.util.HashMap;

public class MoveAI {

    public void move(Adventurer adventurer, HashMap<String, Square> board)
    {
        switch (adventurer.getOrientation())
        {
            case "N":
                if(canMoveTo(adventurer.getPositionX(), adventurer.getPositionY() - 1, board, adventurer))
                    CommandAI.addCommand(new AdventurerMoveNorth(adventurer, board));
                break;

            case "S":
                if(canMoveTo(adventurer.getPositionX(), adventurer.getPositionY() + 1, board, adventurer))
                    CommandAI.addCommand(new AdventurerMoveSouth(adventurer, board));
                break;

            case "W":
                if(canMoveTo(adventurer.getPositionX() - 1, adventurer.getPositionY(), board, adventurer))
                    CommandAI.addCommand(new AdventurerMoveWest(adventurer, board));
                break;

            case "E":
                if(canMoveTo(adventurer.getPositionX() + 1, adventurer.getPositionY(), board, adventurer))
                    CommandAI.addCommand(new AdventurerMoveEast(adventurer, board));
                break;
            default:
                System.out.println("No orientation");
                break;
        }
    }


    private boolean canMoveTo(int x, int y, HashMap<String, Square> board, Adventurer adventurer)
    {
        if(!board.containsKey(x + " " + y)
                || board.get(x + " " + y).isMountain())
        {
            int i = 0;
            while(adventurer.getPath().charAt(i) == 'A')
            {
                i++;
            }
            adventurer.setPath(adventurer.getPath().substring(i, adventurer.getPath().length()));
            return false;
        }

        return board.get(x + " " + y).getAdventurer() == null;
    }
}
