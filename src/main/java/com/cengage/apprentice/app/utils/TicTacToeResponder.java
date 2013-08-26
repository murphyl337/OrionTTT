package com.cengage.apprentice.app.utils;

import java.io.FileNotFoundException;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.FileResponse;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;

public class TicTacToeResponder implements Respondable {
    private static final String GAME_NEW = "/";
    private static final String GAME_UPDATE = "/game/update";
    private TicTacToeController controller;
    private String rootDir;

    public TicTacToeResponder(TicTacToeController controller, String rootDir) {
        this.controller = controller;
        this.rootDir = rootDir;
    }

    public OrionResponse respond(OrionRequest request)
            throws FileNotFoundException {
        if (GAME_NEW.equals(request.getRoute())) {
            return controller.newGame();
        } else if (request.getRoute().contains(GAME_UPDATE)) {
            return controller.updateGame(request);
        } else if (FileChecker.fileExists(rootDir, request.getRoute())) {
            return new FileResponse(rootDir, request.getRoute());
        }
        return new StatusCodeResponse(500);
    }

}
