package Core;

import AI.GameAI;
import Graphic.View;

public class Simulator {

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game(null, 0, 0);
        game.initGame();
        GameAI gameAI = new GameAI(game.getAdventurers(), game.getBoard());
        View view = new View();
        view.initView(game);


        while(!game.getAdventurers().isEmpty())
        {
            gameAI.nextTick();
            view.update(game);
        }

        System.out.println("END.");
        //TODO: condition pour reboucler jusqu'a la fin du jeu
        //TODO: repaint les components graphiques
    }
}