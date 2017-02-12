package Core;

import Element.Adventurer;
import Element.Square;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class Simulator {

    public static void main(String[] args) {

        Game game = new Game(null, 0, 0);

        initGame(game);
        initView(game);
    }


    private static void initGame(Game game) {
        List<String> elements = GameParser.parseBoardTxt("src/main/resources/board.txt");
        List<String> adventurers = GameParser.parseBoardTxt("src/main/resources/adventurers.txt");

        try {
            initBoard(elements, game);
            populateBoard(elements, game);
            populateAdventurers(adventurers, game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void initBoard(List<String> elements, Game game) throws Exception {
        for (String element : elements) {
            String[] parts = element.split(" ");
            if (Objects.equals(parts[0], "C")) {
                game.setBoard(new HashMap<>());
                game.setHeight(Integer.parseInt(parts[2]));
                game.setWidth(Integer.parseInt(parts[1]));
                break;
            }
        }

        if(game.getBoard() == null)
            throw new Exception("You must specify a map size in your txt file");

        for (int x = 1; x <= game.getWidth(); x++) {
            for (int y = 1; y <= game.getHeight(); y++) {
                Square square = new Square(x, y, 0, false, false);
                game.getBoard().put(String.valueOf(x) + " " + String.valueOf(y), square);
            }
        }
    }


    private static void populateBoard(List<String> elements, Game game) throws Exception {
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

                            else if (game.getBoard().containsKey(positions[0] + " " + positions[1]))
                                game.getBoard()
                                        .get(positions[0] + " " + positions[1])
                                        .setTreasures(Integer.parseInt(parts[2]));
                            break;

                        case "M":
                            if(parts.length != 2)
                                System.out.println("Invalid mountain format txt");

                            else if (game.getBoard().containsKey(positions[0] + " " + positions[1]))
                                game.getBoard()
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


    private static void populateAdventurers(List<String> adventurers, Game game) {
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
                        , game.getBoard().get(positions[0] + " " + positions[1]));
                adventurer.getSquare().setOccupied(true);
                game.getAdventurers().add(adventurer);
            }
        }
    }


    private static void initView(Game game) {

        final JFrame f = new JFrame("Frame Test");
        JPanel panel = new JPanel(new GridLayout(game.getHeight(), game.getWidth(), 0, 0));
        JLabel label = null;

        for (int y = 1; y <= game.getHeight() ; y++) {
            for (int x = 1; x <= game.getWidth(); x++) {

                if(game.getBoard().get(x + " " + y).isMountain())
                    label = new JLabel(new ImageIcon("src/main/resources/img/mountain.png"), JLabel.CENTER);

                else if(game.getBoard().get(x + " " + y).isOccupied())
                    label = new JLabel(new ImageIcon("src/main/resources/img/adventurer-south-grass.png"), JLabel.CENTER);

                else if(game.getBoard().get(x + " " + y).getTreasures() > 0)
                    label = new JLabel(String.valueOf(game.getBoard().get(x + " " + y).getTreasures()), JLabel.CENTER);

                else if (game.getBoard().get(x + " " + y).getTreasures() > 0
                        && game.getBoard().get(x + " " + y).isOccupied())
                    label = new JLabel(new ImageIcon("src/main/resources/img/adventurer-east.png"), JLabel.CENTER);

                else
                    label = new JLabel(new ImageIcon("src/main/resources/img/grass.png"), JLabel.CENTER);

                label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                panel.add(label);
            }

        }
        f.setContentPane(panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}