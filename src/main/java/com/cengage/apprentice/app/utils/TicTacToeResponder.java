package com.cengage.apprentice.app.utils;

import java.io.FileNotFoundException;

import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.OrionResponse;

public class TicTacToeResponder implements Respondable {
    
    private TicTacToeController controller;

    public TicTacToeResponder(TicTacToeController controller){
        this.controller = controller;
    }

    public OrionResponse respond(OrionRequest request)
            throws FileNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

}
