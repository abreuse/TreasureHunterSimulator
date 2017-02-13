package AI;

import AI.Command.*;
import Element.Adventurer;

public class RotateAI {

    public void rotateLeft(Adventurer adventurer)
    {
        CommandAI.addCommand(new AdventurerRotateLeft(adventurer));
    }


    public void rotateRight(Adventurer adventurer)
    {
        CommandAI.addCommand(new AdventurerRotateRight(adventurer));
    }
}
