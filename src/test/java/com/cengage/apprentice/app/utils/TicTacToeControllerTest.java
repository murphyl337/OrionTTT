package com.cengage.apprentice.app.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import source.TTT.Board;
import source.TTT.Game;

import com.cengage.apprentice.app.TTT.GameRepository;
import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;
import com.google.gson.Gson;

public class TicTacToeControllerTest {
    OrionRequest newGameRequest, updateGameRequest, malformedUpdateGameRequest;
    TicTacToeController controller;
    Gson gson;

    @Before
    public void setup() {
        String newGameRequestString = "GET /game/new HTTP/1.1\n localhost:5000";
        String updateGameRequestString = "GET /game/update?game=1&move=0,0&player=X HTTP/1.1\n localhost:5000";
        String malformedUpdateGameRequestString = "GET /game/update&player=X HTTP/1.1\n localhost:5000";
        newGameRequest = RequestParser.parse(newGameRequestString);
        updateGameRequest = RequestParser.parse(updateGameRequestString);
        malformedUpdateGameRequest = RequestParser
                .parse(malformedUpdateGameRequestString);
        controller = new TicTacToeController();
        gson = new Gson();
    }

    @After
    public void tearDown() {
        GameRepository.clear();
    }

    @Test
    public void updateGameUpdatesBoardForValidMove() throws Exception {
        Board board = new Board();
        Game game = new Game(board, null, null);
        GameRepository.put(1, game);

        controller.updateGame(updateGameRequest);

        assertEquals("X", board.getSpace(0, 0));
    }

    @Test
    public void updateGameDoesNotUpdateBoardForAlreadyTakenMove() {
        Board board = new Board();
        board.setSpace(0, 0, "O");
        Game game = new Game(board, null, null);
        GameRepository.put(2, game);

        controller.updateGame(updateGameRequest);

        assertEquals("O", board.getSpace(0, 0));
    }

    @Test
    public void updateGameReturns500StatusCodeResposneForMalformedRequest()
            throws Exception {
        Board board = new Board();
        board.setSpace(0, 0, "O");
        Game game = new Game(board, null, null);
        GameRepository.put(3, game);

        OrionResponse response = controller.updateGame(malformedUpdateGameRequest);

        assertEquals(StatusCodeResponse.class, response.getClass());
    }

}
