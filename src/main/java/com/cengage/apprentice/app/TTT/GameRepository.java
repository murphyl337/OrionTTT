package com.cengage.apprentice.app.TTT;

import java.util.HashMap;

import source.TTT.Game;

public class GameRepository {
    private static HashMap<Integer, Game> games;
    private static int nextId = 1;

    private GameRepository() {
    }

    public static Game get(final int id) {
        return getGames().get(id);
    }
    
    public static void put(Game game){
        getGames().put(nextId, game);
        nextId++;
    }
    
    public static int size(){
        return getGames().size();
    }

    public static void clear() {
        getGames().clear();
        nextId = 1;
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
