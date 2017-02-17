package Core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileParser {

    public static List<String> parseBoardTxt(String file) throws NoSuchFileException {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            return stream.collect(Collectors.toList());
        }
        catch (NoSuchFileException e){
            throw new NoSuchFileException("Le fichier " + file + " n'existe pas.");
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
