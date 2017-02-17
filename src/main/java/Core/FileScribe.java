package Core;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileScribe {

    private static String output = "./output.txt";

    private static int turn = 0;

    public static void writeLines(List<String> lines) throws IOException {

        FileWriter fw = null;
        try
        {
            fw = new FileWriter(output, true);
            for (String line : lines) {
                fw.write(line + "\n");
            }
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
        finally {
            fw.close();
        }
    }


    public static void separateTurn() throws IOException {

        FileWriter fw = null;
        try
        {
            fw = new FileWriter(output, true);
            turn++;
            fw.write("\n###################################################\n" +
                    "\t\t\t\tTurn " + turn + "\n");
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
        finally {
            fw.close();
        }
    }

    public static boolean deleteFile()
    {
        try {
            return Files.deleteIfExists(Paths.get(output));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
