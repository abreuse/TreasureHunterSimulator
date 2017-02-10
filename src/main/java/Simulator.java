import element.Adventurer;
import element.Game;
import element.Square;

import java.awt.GridLayout;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
import javax.swing.border.BevelBorder;


public class Simulator {

    public static void main(String[] args) {
        int height = 10;
        int width = 20;
        HashMap<String, Square> board = new HashMap<String, Square>();
        List<Adventurer> adventurers = new ArrayList<Adventurer>();

        board = populateBoard(board, height, width);
        adventurers = populateAdventurers(board, adventurers);

        Game game = new Game(board, height, width);

        logBoard(board);
        logAdventurers(adventurers);

        initView(height, width);
    }

    private static void logAdventurers(List<Adventurer> adventurers) {
        for (Adventurer adventurer : adventurers) {
            System.out.println("adventurer = " + adventurer);
        }
    }

    private static List<Adventurer> populateAdventurers(HashMap<String, Square> board, List<Adventurer> adventurers) {
        //TODO read file.txt and get adventurers positions
        adventurers = new ArrayList<Adventurer>();
        int adventurerPositionX;
        int adventurerPositionY;

        for (int i = 0; i < 5; i ++) {
            adventurerPositionX = ThreadLocalRandom.current().nextInt(1, 20 + 1);
            adventurerPositionY = ThreadLocalRandom.current().nextInt(1, 10 + 1);
            System.out.println("x = " + adventurerPositionX);
            System.out.println("y = " + adventurerPositionY);
            adventurers.add(new Adventurer(
                    board.get(
                        String.valueOf(adventurerPositionX)
                        + " " +
                        String.valueOf(adventurerPositionY)
                    ),
                    "ADAAGAA",
                    "E")
            );
        }

        return adventurers;
    }

    private static void logBoard(HashMap<String, Square> board) {

        for (Map.Entry<String, Square> entry : board.entrySet()) {
            System.out.print("key : " + entry.getKey());
            System.out.println(" value : " + entry.getValue());
        }
    }

    private static HashMap<String, Square> populateBoard(HashMap<String, Square> board, int height, int width) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(y == 0){
                    Square square = new Square(x, y, 0, true, false);
                    board.put(String.valueOf(x) + " " + String.valueOf(y), square);
                    Adventurer adventurer = new Adventurer(square, "AADAGGAA", "E");
                }
                else
                    board.put(String.valueOf(x) + " " + String.valueOf(y), new Square(x, y, 0, false, false));
            }
        }
        return board;
    }


    private static void initView(int height, int width) {
        final JFrame f = new JFrame("Frame Test");



        JPanel panel = new JPanel(new GridLayout(height, width, 0, 0));

        for (int i = 0; i < width * height; i++) {
            JLabel l = new JLabel(new ImageIcon("src/main/resources/kfc.jpg"), JLabel.CENTER);
            l.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            panel.add(l);
        }

        f.setContentPane(panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}