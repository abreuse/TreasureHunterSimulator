import Core.FileScribe;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FileWriterUnitTest {

    private String output;

    @Before
    public void setup(){
        output = "./output.txt";
    }


    @Test
    public void should_write_lines_to_output_txt_file() throws Exception
    {
        List<String> stringList = new ArrayList<>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        FileScribe.writeLines(stringList);
    }
}
