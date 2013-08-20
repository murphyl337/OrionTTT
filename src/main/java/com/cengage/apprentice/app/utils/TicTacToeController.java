package com.cengage.apprentice.app.utils;

import source.TTT.Board;
import source.TTT.Game;

import com.cengage.apprentice.app.TTT.GameRepository;
import com.cengage.apprentice.app.main.OrionRequest;

public class TicTacToeController {

    
    
    public void processRequest(OrionRequest request) {
        if("/game/new/".equals(request.getRoute())){
            Game game = new Game(null,null,null);
            GameRepository.put(game);
        }
    }

}
