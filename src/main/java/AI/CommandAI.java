package AI;

import AI.Command.ICommand;

import java.util.ArrayList;
import java.util.List;

public class CommandAI {

    private static List<ICommand> commands = new ArrayList<>();

    private static List<ICommand> history = new ArrayList<>();

    public static List<ICommand> getCommandList()
    {
        return commands;
    }

    public static void addCommand(ICommand command)
    {
        commands.add(command);
    }

    public static void saveCommands()
    {
        for (ICommand command : commands) {
            history.add(command);
        }
        commands.clear();
    }

    public static void executeAllCommands()
    {
        for (ICommand command : commands) {
            command.execute();
        }
    }

    public static void undoAllCommands()
    {
        for (int i = history.size() - 1; i > 0; i--) {
            history.get(i).undo();
            history.remove(i);
        }
    }


    public static void undoCommand()
    {
        history.get(history.size() - 1).undo();
        history.remove(history.size() - 1);
    }


    public static List<ICommand> getHistory() {
        return history;
    }
}
