package com.cengage.apprentice.app.TTT;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.TTT.Game;

public class GameRepositoryTest {
    Game game;
    
    @Before
    public void setup(){
        game = new Game(null, null, null);
    }

    @After
    public void tearDown() {
        GameRepository.clear();
    }

    @Test
    public void putAddsGameToRepo() throws Exception {
        GameRepository.put(game);

        assertEquals(1, GameRepository.size());
    }

    @Test
    public void putSetsGamesIdToNextId() throws Exception {
        GameRepository.put(game);
        
        assertEquals(1, game.getId());
    }
    
    @Test
    public void clearEmptiesContentsOfRepo() throws Exception {
        GameRepository.put(game);

        GameRepository.clear();

        assertTrue(GameRepository.isEmpty());
    }
    
    @Test
    public void getFetchesGameById() throws Exception {
        GameRepository.put(game);
        
        assertEquals(game, GameRepository.get(1));
    }
}
