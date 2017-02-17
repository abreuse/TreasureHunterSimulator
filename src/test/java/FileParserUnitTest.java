import Core.FileParser;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.NoSuchFileException;

public class FileParserUnitTest {

    private String boardFile;

    private String adventurersFile;

    @Before
    public void setup(){
        boardFile = "src/test/resources/board.txt";
        adventurersFile = "src/test/resources/adventurers.txt";
    }

    @Test
    public void should_parse_board_txt_file() throws Exception
    {
        FileParser.parseBoardTxt(boardFile);
    }

    @Test
    public void should_parse_adventurers_txt_file() throws Exception
    {
        FileParser.parseBoardTxt(adventurersFile);
    }

    @Test(expected = NoSuchFileException.class)
    public void should_return_exception_if_file_is_doesnt_exist() throws NoSuchFileException {
        boardFile = "src/test/resources/board-not-exist.txt";
        FileParser.parseBoardTxt(boardFile);
    }
}
