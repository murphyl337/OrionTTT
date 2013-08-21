package com.cengage.apprentice.app.utils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.cengage.apprentice.app.main.OrionRequest;

public class TicTacToeResponderTest {
    TicTacToeResponder responder;
    TicTacToeController controller;
    private static final String NEW_GAME = "GET /game/new/ HTTP/1.1\r\nHost: localhost:5000\r\n";
    OrionRequest newGameRequest;
    
    @Before
    public void setup(){
        newGameRequest = RequestParser.parse(NEW_GAME);
    }
    
    @Test
    public void respondCallsControllerNewGameMethodForNewGameRequest() throws Exception {
        controller = Mockito.mock(TicTacToeController.class);
        responder = new TicTacToeResponder(controller, "");

        responder.respond(newGameRequest);
        
        Mockito.verify(controller).newGame();
    }
}
