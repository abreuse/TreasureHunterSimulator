package AI.Command;

import Element.Adventurer;
import Element.Square;

import java.util.HashMap;

public abstract class AbstractAdventurerRotate {

    protected Adventurer adventurer;

    AbstractAdventurerRotate(Adventurer adventurer) {
        this.adventurer = adventurer;
    }
}
