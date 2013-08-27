package com.cengage.apprentice.app.utils;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import source.TTT.Board;
import source.TTT.Game;

import com.cengage.apprentice.app.TTT.GameRepository;
import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;
import com.google.gson.Gson;

public class TicTacToeControllerTest {
    OrionRequest newGameRequest, updateGameRequest, malformedUpdateGameRequest,
            badMoveRequest;
    TicTacToeController controller;
    Gson gson;

    @Before
    public void setup() {
        String newGameRequestString = "GET /game/new HTTP/1.1\n localhost:5000";
        String updateGameRequestString = "GET /game/update?game=1&move=0,0&player=X HTTP/1.1\n localhost:5000";
        String malformedUpdateGameRequestString = "GET /game/update&player=X HTTP/1.1\n localhost:5000";
        String badMoveRequestString = "GET /game/update?game=1&move=ASKLDJ&player=X HTTP/1.1\n localhost:5000";
        newGameRequest = RequestParser.parse(newGameRequestString);
        updateGameRequest = RequestParser.parse(updateGameRequestString);
        malformedUpdateGameRequest = RequestParser
                .parse(malformedUpdateGameRequestString);
        badMoveRequest = RequestParser.parse(badMoveRequestString);
        controller = new TicTacToeController();
        gson = new Gson();
    }

    @After
    public void tearDown() {
        GameRepository.clear();
    }

    @Test
    public void newGameStoresAGameInGameRepository() throws Exception {
        assertEquals(0, GameRepository.size());

        controller.newGame();

        assertEquals(1, GameRepository.size());
    }

    @Test
    public void updateGameUpdatesBoardForValidMove() throws Exception {
        Board board = new Board();
        Game game = new Game(board, null, null);
        GameRepository.put(game);

        controller.updateGame(updateGameRequest);

        assertEquals("X", board.getSpace(0, 0));
    }

    @Test
    public void updateGameDoesNotUpdateBoardForAlreadyTakenMove() {
        Board board = new Board();
        board.setSpace(0, 0, "O");
        Game game = new Game(board, null, null);
        GameRepository.put(game);

        controller.updateGame(updateGameRequest);

        assertEquals("O", board.getSpace(0, 0));
    }

    @Test
    public void updateGameReturns500StatusCodeResponseForMalformedRequest()
            throws Exception {
        Board board = new Board();
        board.setSpace(0, 0, "O");
        Game game = new Game(board, null, null);
        GameRepository.put(game);

        OrionResponse response = controller
                .updateGame(malformedUpdateGameRequest);

        assertEquals(StatusCodeResponse.class, response.getClass());
    }
    
    @Test
    public void updateGameReturns500StatusCodeResponseForInvalidMove() throws Exception {
        Board board = new Board();
        board.setSpace(0, 0, "O");
        Game game = new Game(board, null, null);
        GameRepository.put(game);
        
        OrionResponse response = controller.updateGame(badMoveRequest);
        assertTrue(response.getHeader().contains("500"));
    }

}
