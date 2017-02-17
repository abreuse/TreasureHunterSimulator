package Core;

import Element.Adventurer;
import Element.Square;
import exception.WrongBoardSizeException;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {

    private static Game instance = new Game(null, 0, 0);

    private HashMap<String, Square> board;

    private List<Adventurer> adventurers;

    private int width;

    private int height;

    private Game(HashMap<String, Square> board, int height, int width) {
        adventurers = new ArrayList<>();
        this.board = board;
        this.width = width;
        this.height = height;
    }

    public static Game getInstance() {
        return instance;
    }

    public void initGame(String mapFile, String adventurersFile) throws Exception {

        List<String> elements = FileParser.parseBoardTxt(mapFile);
        List<String> adventurers = FileParser.parseBoardTxt(adventurersFile);

            initBoard(elements);
            populateBoard(elements);
            populateAdventurers(adventurers);
    }


    public void initBoard(List<String> elements) throws Exception {

        System.out.println("Initializing board size...");
        for (String element : elements) {
            element = element.trim();
            if(element.charAt(0) == 'C')
            {
                String[] parts = element.split(" ");

                if(Integer.parseInt(parts[1]) <= 0 || Integer.parseInt(parts[2]) <= 0)
                {
                    throw new WrongBoardSizeException("Width and height must be greater than zero");
                }

                this.board = new HashMap<>();
                this.height = Integer.parseInt(parts[2]);
                this.width = Integer.parseInt(parts[1]);
                break;
            }
        }

        if(this.getBoard() == null)
            throw new Exception("You must specify a map size in your txt file");

        for (int x = 1; x <= this.getWidth(); x++) {
            for (int y = 1; y <= this.getHeight(); y++) {
                this.board.put(
                        String.valueOf(x) + " " + String.valueOf(y),
                        new Square(x, y, 0, null, false)
                );
            }
        }
    }


    private void populateBoard(List<String> elements) throws Exception {

        System.out.println("Populating board...");
        for (String element : elements) {
            element = element.trim();

            if(element.charAt(0) == 'C')
                continue;

            else if(element.charAt(0) != 'T' && element.charAt(0) != 'M') {
                System.out.println("Letter code " + element.charAt(0) + "not recognized");
            }
            else
            {
                String[] parts = element.split(" ");
                if(!parts[1].matches("\\d+-\\d+"))
                    System.out.println("Field " + parts[1] + " not match the regex");
                else{
                    String[] positions = parts[1].split("-");

                    if(!respectTheLimits(Integer.parseInt(positions[0]), Integer.parseInt(positions[1])))
                    {
                        System.out.println("Coordinates [" + positions[0] + ";"
                                + positions[1] + "] don't fit in the map size");
                        continue;
                    }

                    switch (parts[0]) {
                        case "T":
                            if(parts.length != 3 && !parts[2].matches("\\d+"))
                                System.out.println("Invalid treasure format txt");

                            else if (this.board.containsKey(positions[0] + " " + positions[1]))
                                this.board
                                        .get(positions[0] + " " + positions[1])
                                        .setTreasures(Integer.parseInt(parts[2]));
                            break;

                        case "M":
                            if(parts.length != 2)
                                System.out.println("Invalid mountain format txt");

                            else if (this.board.containsKey(positions[0] + " " + positions[1]))
                                this.board
                                        .get(positions[0] + " " + positions[1])
                                        .setMountain(true);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }


    private void populateAdventurers(List<String> adventurers) {

        System.out.println("Populating adventurers...");
        for (String adventurerStr : adventurers) {
            adventurerStr = adventurerStr.trim();
            String[] parts = adventurerStr.split(" ");

            if(!respectAdventurerFormat(parts))
                continue;

            else {
                String[] positions = parts[1].split("-");

                if(!respectTheLimits(Integer.parseInt(positions[0]), Integer.parseInt(positions[1])))
                {
                    System.out.println("Coordinates [" + positions[0] + ";"
                            + positions[1] + "] don't fit in the map size");
                    continue;
                }

                if(!isFree(this.board.get(positions[0] + " " + positions[1])))
                    continue;

                Adventurer adventurer = new Adventurer(parts[0]
                        , parts[3]
                        , parts[2]
                        , this.board.get(positions[0] + " " + positions[1]));
                adventurer.getSquare().setAdventurer(adventurer);
                this.adventurers.add(adventurer);
            }
        }
    }

    private boolean respectTheLimits(int x, int y)
    {
        return x <= width && y <= height;
    }

    private boolean isFree(Square square)
    {
        return !(square.isMountain() || square.getAdventurer() != null);
    }

    private boolean respectAdventurerFormat(String[] parts){
        if(parts.length != 4)
        {
            System.out.println("Invalid adventurer format txt");
            return false;
        }

        else if(!parts[1].matches("\\d+-\\d+"))
        {
            System.out.println("Field " + parts[1] + " not match the regex");
            return false;
        }

        else if(!parts[2].matches("N|E|S|W"))
        {
            System.out.println("Orientation " + parts[2] + " is not valid");
            return false;
        }

        else if(!parts[3].matches("(A|D|G)+"))
        {
            System.out.println("Path " + parts[3] + " is not valid");
            return false;
        }

        else
            return true;
    }

    public HashMap<String, Square> getBoard() {
        return board;
    }

    public void setBoard(HashMap<String, Square> board) {
        this.board = board;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    public void setAdventurers(List<Adventurer> adventurers) {
        this.adventurers = adventurers;
    }
}
