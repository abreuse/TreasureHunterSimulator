package Graphic;

import Core.Game;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Objects;

public class View {

    private JFrame frame;
    private JPanel panel;

    public View() {
        frame = new JFrame("Treasure Hunter");
        panel = new JPanel();
    }

    public void initView(Game game) throws InterruptedException {
        panel.setLayout(new GridLayout(game.getHeight(), game.getWidth(), 0, 0));
        update(game, 0);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void update(Game game, long delay) throws InterruptedException {

        Thread.sleep(delay);

        panel.removeAll();

        JLabel label = null;

        for (int y = 1; y <= game.getHeight() ; y++) {
            for (int x = 1; x <= game.getWidth(); x++) {

                if(game.getBoard().get(x + " " + y).isMountain())
                    label = new JLabel(new ImageIcon(getClass().getResource("/img/mountain.png")), JLabel.CENTER);

                else if (game.getBoard().get(x + " " + y).getTreasures() > 0
                        && game.getBoard().get(x + " " + y).getAdventurer() != null)
                    label = new JLabel(new ImageIcon(getClass().getResource("/img/mining.png")), JLabel.CENTER);

                else if(game.getBoard().get(x + " " + y).getAdventurer() != null)
                {
                    if(Objects.equals(game.getBoard().get(x + " " + y).getAdventurer().getOrientation(), "N")) {
                        label = new JLabel(new ImageIcon(getClass().getResource("/img/adventurer-north.png")), JLabel.CENTER);
                    }

                    else if(Objects.equals(game.getBoard().get(x + " " + y).getAdventurer().getOrientation(), "E")) {
                        label = new JLabel(new ImageIcon(getClass().getResource("/img/adventurer-east.png")), JLabel.CENTER);
                    }

                    else if(Objects.equals(game.getBoard().get(x + " " + y).getAdventurer().getOrientation(), "S")) {
                        label = new JLabel(new ImageIcon(getClass().getResource("/img/adventurer-south.png")), JLabel.CENTER);
                    }

                    else if(Objects.equals(game.getBoard().get(x + " " + y).getAdventurer().getOrientation(), "W")) {
                        label = new JLabel(new ImageIcon(getClass().getResource("/img/adventurer-west.png")), JLabel.CENTER);
                    }
                }

                else if(game.getBoard().get(x + " " + y).getTreasures() > 0)
                {
                    label = new JLabel(String.valueOf(game.getBoard().get(x + " " + y).getTreasures()), JLabel.CENTER);
                    label.setBackground(Color.yellow);
                    label.setOpaque(true);
                }

                else
                    label = new JLabel(new ImageIcon(getClass().getResource("/img/grass.png")), JLabel.CENTER);

                label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                panel.add(label);
            }

        }
        panel.updateUI();
    }
}
