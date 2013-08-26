package com.cengage.apprentice.app.TTT;

import java.util.HashMap;

import source.TTT.Game;

public class GameRepository {
    private static HashMap<Integer, Game> games;

    private GameRepository() {
    }

    public static Game get(final int id) {
        return getGames().get(id);
    }
    
    public static Game put(int id, Game game){
        getGames().put(id, game);
        return game;
    }
    
    public static int size(){
        return getGames().size();
    }

    public static void clear() {
        getGames().clear();
    }
    
    public static boolean isEmpty(){
        return getGames().isEmpty();
    }

    private static HashMap<Integer, Game> getGames() {
        if (games == null) {
            initializeGames();
        }
        return games;
    }

    private static void initializeGames() {
        games = new HashMap<Integer, Game>();
    }

}
