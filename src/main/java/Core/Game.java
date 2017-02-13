package Core;

import Element.Adventurer;
import Element.Square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Game {

    private HashMap<String, Square> board;

    private List<Adventurer> adventurers;

    private int width;

    private int height;

    public Game(HashMap<String, Square> board, int height, int width) {
        adventurers = new ArrayList<>();
        this.board = board;
        this.width = width;
        this.height = height;
    }

    public void initGame() {
        List<String> elements = FileParser.parseBoardTxt("src/main/resources/board.txt");
        List<String> adventurers = FileParser.parseBoardTxt("src/main/resources/adventurers.txt");

        try {
            initBoard(elements);
            populateBoard(elements);
            populateAdventurers(adventurers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initBoard(List<String> elements) throws Exception {
        for (String element : elements) {
            String[] parts = element.split(" ");
            if (Objects.equals(parts[0], "C")) {
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
                Square square = new Square(x, y, 0, false, false);
                this.board.put(String.valueOf(x) + " " + String.valueOf(y), square);
            }
        }
    }


    private void populateBoard(List<String> elements) throws Exception {
        for (String element : elements) {
            String[] parts = element.split(" ");

            if(Objects.equals(parts[0], "C"))
                System.out.println("Board has already been initialized");

            else if(!Objects.equals(parts[0], "T")
                    && !Objects.equals(parts[0], "M")) {
                System.out.println("Letter code " + parts[0] + "not recognized");
            }
            else
            {
                if(!parts[1].contains("-"))
                    System.out.println("Must contain a tiret like X-Y");
                else{
                    String[] positions = parts[1].split("-");
                    switch (parts[0]) {
                        case "T":
                            if(parts.length != 3)
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
                            System.out.println("Game already initialized");
                            break;
                    }
                }
            }
        }
    }


    private void populateAdventurers(List<String> adventurers) {
        for (String adventurerStr : adventurers) {
            String[] parts = adventurerStr.split(" ");

            if(parts.length != 4)
                System.out.println("Invalid adventurer format txt");

            else if(!parts[1].contains("-"))
                System.out.println("Must contain a tiret like X-Y");

            else {
                String[] positions = parts[1].split("-");
                Adventurer adventurer = new Adventurer(parts[0]
                        , parts[2]
                        , parts[3]
                        , this.board.get(positions[0] + " " + positions[1]));
                adventurer.getSquare().setOccupied(true);
                this.adventurers.add(adventurer);
            }
        }
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
