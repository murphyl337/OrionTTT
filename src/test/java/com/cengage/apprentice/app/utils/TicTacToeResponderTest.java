package com.cengage.apprentice.app.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.FileResponse;
import com.cengage.apprentice.app.response.OrionResponse;

public class TicTacToeResponderTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    File tempFile;
    TicTacToeResponder responder;
    TicTacToeController controller;
    private static final String STATIC_RESOURCE = "GET /temp.txt HTTP/1.1\r\nHost: localhost:5000\r\n";
    private static final String NEW_GAME = "GET / HTTP/1.1\r\nHost: localhost:5000\r\n";
    private static final String UPDATE_GAME = "GET /game/update?game=1&move=0,0&player=X HTTP/1.1\n localhost:5000";
    private static final String UNKNOWN_REQUEST = "GET /derp HTTP/1.1\n localhost:5000";
    OrionRequest newGameRequest, staticResourceRequest, updateGameRequest, unknownRequest;

    @Before
    public void setup() throws IOException {
        newGameRequest = RequestParser.parse(NEW_GAME);
        updateGameRequest = RequestParser.parse(UPDATE_GAME);
        staticResourceRequest = RequestParser.parse(STATIC_RESOURCE);
        unknownRequest = RequestParser.parse(UNKNOWN_REQUEST);
        tempFile = folder.newFile("temp.txt");
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
    public void respondCallsControllerUpdateGameWithRequestForUpdateGameRequest()
            throws Exception {
        controller = Mockito.mock(TicTacToeController.class);
        responder = new TicTacToeResponder(controller, "");

        responder.respond(updateGameRequest);

        Mockito.verify(controller).updateGame(updateGameRequest);
    }

    @Test
    public void respondReturnsFileResponseForExistingStaticResources()
            throws Exception {
        responder = new TicTacToeResponder(controller, folder.getRoot()
                .getAbsolutePath());

        OrionResponse response = responder.respond(staticResourceRequest);
        
        assertEquals(FileResponse.class, response.getClass());
    }
    
    @Test
    public void respondReturns500ErrorForRequestThatIsNotStaticResourceOrTTTRequest() throws Exception {
        responder = new TicTacToeResponder(controller, "");
        
        OrionResponse response = responder.respond(unknownRequest);
        
        assertTrue(response.getHeader().contains("500"));
    }
}
