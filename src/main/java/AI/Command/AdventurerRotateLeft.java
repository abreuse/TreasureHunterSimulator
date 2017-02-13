package AI.Command;

import Element.Adventurer;

import java.util.Objects;

public class AdventurerRotateLeft extends AbstractAdventurerRotate implements ICommand{

    public AdventurerRotateLeft(Adventurer adventurer) {
        super(adventurer);
    }

    @Override
    public void execute() {
        adventurer.setPath(adventurer.getPath().substring(1, adventurer.getPath().length()));

        if(Objects.equals(adventurer.getOrientation(), "N"))
            adventurer.setOrientation("W");

        if(Objects.equals(adventurer.getOrientation(), "W"))
            adventurer.setOrientation("N");

        if(Objects.equals(adventurer.getOrientation(), "S"))
            adventurer.setOrientation("E");

        if(Objects.equals(adventurer.getOrientation(), "E"))
            adventurer.setOrientation("S");
    }

    @Override
    public void undo() {
        AdventurerRotateRight rotateRightCommand = new AdventurerRotateRight(this.adventurer);
        adventurer.setPath("GG" + adventurer.getPath());
        rotateRightCommand.execute();
    }
}
