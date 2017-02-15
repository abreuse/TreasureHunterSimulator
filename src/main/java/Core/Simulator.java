package Core;

import AI.CommandAI;
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
            view.update(game, 1000);
        }

        System.out.println("END.");


        //Undo just for fun
        while(!CommandAI.getHistory().isEmpty())
        {
            CommandAI.undoCommand();
            view.update(game, 100);
        }
    }
}