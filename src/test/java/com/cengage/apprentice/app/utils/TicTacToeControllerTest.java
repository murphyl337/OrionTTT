package com.cengage.apprentice.app.utils;

import org.junit.After;
import org.junit.Before;

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
    
    
}
