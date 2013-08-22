package com.cengage.apprentice.app.TTT;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import source.TTT.Game;

public class GameRepositoryTest {

    @After
    public void tearDown() {
        GameRepository.clear();
    }

    @Test
    public void putAddsGameToRepo() throws Exception {
        Game game = new Game(null, null, null);
        GameRepository.put(game);

        assertEquals(1, GameRepository.size());
    }

    @Test
    public void putIncrementsNextId() throws Exception {
        Game game = new Game(null, null, null);
        Game game2 = new Game(null, null, null);
        GameRepository.put(game);
        GameRepository.put(game2);

        assertNotNull(GameRepository.get(2));
    }

    @Test
    public void putSetsGamesId() throws Exception {
        Game game = new Game(null, null, null);
        GameRepository.put(game);
        
        assertEquals(1, game.getId());
    }

    @Test
    public void clearEmptiesContentsOfRepo() throws Exception {
        Game game = new Game(null, null, null);
        GameRepository.put(game);

        GameRepository.clear();

        assertTrue(GameRepository.isEmpty());
    }
}
