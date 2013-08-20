package com.cengage.apprentice.app.utils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cengage.apprentice.app.TTT.GameRepository;
import com.cengage.apprentice.app.main.OrionRequest;

public class TicTacToeControllerTest {
    OrionRequest newGameRequest;
    TicTacToeController controller;

    @Before
    public void setup() {
        String newGameRequestString = "GET /game/new HTTP/1.1\n localhost:5000";
        newGameRequest = RequestParser.parse(newGameRequestString);
        controller = new TicTacToeController();
    }

    @After
    public void tearDown() {
        GameRepository.clear();
    }

    @Test
    public void processRequestAddsGameToRepoForNewGameRequest()
            throws Exception {
        controller.processRequest(newGameRequest);

        assertEquals(1, GameRepository.size());
    }
}