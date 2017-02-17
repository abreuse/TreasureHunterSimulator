import Core.Game;
import exception.WrongBoardSizeException;
import org.junit.Before;
import org.junit.Test;

public class GameUnitTest {

    private String boardFile;

    private String adventurersFile;

    private Game game;

    @Before
    public void setup(){
        game = Game.getInstance();
        boardFile = "src/test/resources/board.txt";
        adventurersFile = "src/test/resources/adventurers.txt";
    }

    @Test
    public void should_init_game() throws Exception
    {
        game.initGame(boardFile, adventurersFile);
    }

    @Test(expected = WrongBoardSizeException.class)
    public void board_size_should_be_greater_than_zero() throws Exception {
        boardFile = "src/test/resources/board-negative.txt";
        game.initGame(boardFile, adventurersFile);
    }
}
