package AI.Command;

import Element.Adventurer;

import java.util.Objects;

public class AdventurerRotateRight extends AbstractAdventurerRotate implements ICommand{

    public AdventurerRotateRight(Adventurer adventurer) {
        super(adventurer);
    }

    @Override
    public void execute() {
        adventurer.setPath(adventurer.getPath().substring(1, adventurer.getPath().length()));

        if(Objects.equals(adventurer.getOrientation(), "N"))
            adventurer.setOrientation("E");

        if(Objects.equals(adventurer.getOrientation(), "E"))
            adventurer.setOrientation("S");

        if(Objects.equals(adventurer.getOrientation(), "S"))
            adventurer.setOrientation("W");

        if(Objects.equals(adventurer.getOrientation(), "W"))
            adventurer.setOrientation("N");
    }

    @Override
    public void undo() {
        AdventurerRotateLeft rotateLeftCommand = new AdventurerRotateLeft(this.adventurer);
        adventurer.setPath("RR" + adventurer.getPath());
        rotateLeftCommand.execute();
    }
}
