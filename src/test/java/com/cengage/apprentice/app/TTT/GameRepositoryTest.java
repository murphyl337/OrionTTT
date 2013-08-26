package com.cengage.apprentice.app.TTT;

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
        GameRepository.put(1, game);

        assertEquals(1, GameRepository.size());
    }

    @Test
    public void clearEmptiesContentsOfRepo() throws Exception {
        Game game = new Game(null, null, null);
        GameRepository.put(1, game);

        GameRepository.clear();

        assertTrue(GameRepository.isEmpty());
    }
}
