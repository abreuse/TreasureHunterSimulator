package Core;

import Graphic.View;

public class Simulator {

    public static void main(String[] args) {
        Game game = new Game(null, 0, 0);
        game.initGame();
        View.initView(game);

        //WHILE not end

    }
}