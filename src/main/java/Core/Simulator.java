package Core;

import AI.CommandAI;
import AI.GameAI;
import Graphic.View;

import java.util.MissingFormatArgumentException;

public class Simulator {

    public static void main(String[] args) throws Exception {

        FileScribe.deleteFile();

        String mapFile;
        try{
            mapFile = args[0];
        }catch (ArrayIndexOutOfBoundsException e){
            throw new MissingFormatArgumentException("Veuillez spécifier les fichiers en paramètres (board, adventurers).");
        }

        String adventurersFile;
        try{
            adventurersFile = args[1];
        }catch (ArrayIndexOutOfBoundsException e){
            throw new MissingFormatArgumentException("Veuillez spécifier les fichiers en paramètres (board, adventurers).");
        }

        Game game = Game.getInstance();
        game.initGame(mapFile, adventurersFile);
        GameAI gameAI = new GameAI(game.getAdventurers(), game.getBoard());
        View view = new View();
        view.initView(game);


        while(!game.getAdventurers().isEmpty())
        {
            gameAI.nextTick();
            view.update(game, 1000);
        }

        System.out.println("Going backwards");

        //Undo just for fun
        while(!CommandAI.getHistory().isEmpty())
        {
            CommandAI.undoCommand();
            view.update(game, 100);
        }
    }
}