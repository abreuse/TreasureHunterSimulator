import AI.Command.AdventurerRotateLeft;
import AI.CommandAI;
import Element.Adventurer;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommandAIUnitTest {

    @Test
    public void should_add_command_to_command_list() throws Exception
    {
        CommandAI.addCommand(new AdventurerRotateLeft(new Adventurer()));
        assertFalse(CommandAI.getCommands().isEmpty());
    }

    @Test
    public void should_remove_command_to_command_list() throws Exception
    {
        CommandAI.addCommand(new AdventurerRotateLeft(new Adventurer()));
        CommandAI.saveCommands();
        assertTrue(CommandAI.getCommands().isEmpty());
    }
}
