package com.cengage.apprentice.app.utils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cengage.apprentice.app.main.OrionRequest;

public class TicTacToeResponderTest {
    TicTacToeResponder responder;
    TicTacToeController controller;
    private static final String NEW_GAME = "GET / HTTP/1.1\r\nHost: localhost:5000\r\n";
    private static final String UPDATE_GAME = "GET /game/update?game=1&move=0,0&player=X HTTP/1.1\n localhost:5000";
    OrionRequest newGameRequest, nonGameRequest, updateGameRequest;

    @Before
    public void setup() {
        newGameRequest = RequestParser.parse(NEW_GAME);
        updateGameRequest = RequestParser.parse(UPDATE_GAME);
    }

    @Test
    public void respondCallsControllerNewGameMethodForNewGameRequest()
            throws Exception {
        controller = Mockito.mock(TicTacToeController.class);
        responder = new TicTacToeResponder(controller, "");

        responder.respond(newGameRequest);

        Mockito.verify(controller).newGame();
    }
    
    @Test
    public void respondCallsControllerUpdateGameWithRequestForUpdateGameRequest() throws Exception {
        controller = Mockito.mock(TicTacToeController.class);
        responder = new TicTacToeResponder(controller, "");
        
        responder.respond(updateGameRequest);
        
        Mockito.verify(controller).updateGame(updateGameRequest);
    }
}
