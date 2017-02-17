import AI.MoveAI;
import Core.Game;
import Element.Adventurer;
import Element.Square;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoveAIUnitTest {

    private String boardFile;

    private String adventurersFile;

    private Game game;

    private MoveAI moveAI;

    @Before
    public void setup() throws Exception {
        game = Game.getInstance();
        boardFile = "src/test/resources/board.txt";
        adventurersFile = "src/test/resources/adventurers.txt";
        moveAI = new MoveAI();

        game.initGame(boardFile, adventurersFile);
    }

    @Test
    public void should_move_to_square() throws Exception
    {
        boolean bool = moveAI.canMoveTo(9, 4, game.getBoard(), new Adventurer("name",
                "AADDGG",
                "N",
                new Square(10, 5, 0, null, false)));

        assertEquals(true, bool);
    }


    @Test
    public void should_not_move_to_square_mountain() throws Exception
    {
        game.getBoard().get("9 4").setMountain(true);
        boolean bool = moveAI.canMoveTo(9, 4, game.getBoard(), new Adventurer("name",
                "AADDGG",
                "N",
                new Square(10, 5, 0, null, false)));

        assertEquals(false, bool);
    }


    @Test
    public void should_not_move_to_square_adventurer() throws Exception
    {
        game.getBoard().get("9 4").setAdventurer(new Adventurer());
        boolean bool = moveAI.canMoveTo(9, 4, game.getBoard(), new Adventurer("name",
                "AADDGG",
                "N",
                new Square(10, 5, 0, null, false)));

        assertEquals(false, bool);
    }

    @Test
    public void should_not_move_to_square_outside_board() throws Exception
    {
        boolean bool = moveAI.canMoveTo(21, 10, game.getBoard(), new Adventurer("name",
                "AADDGG",
                "N",
                new Square(20, 10, 0, null, false)));

        assertEquals(false, bool);
    }
}
