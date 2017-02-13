package Graphic;

import Core.Game;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class View {

    public static void initView(Game game) {

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
