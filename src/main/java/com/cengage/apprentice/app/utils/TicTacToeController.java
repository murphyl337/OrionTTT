package com.cengage.apprentice.app.utils;

import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;

public class TicTacToeController {
    
    public OrionResponse newGame(){
        return new StatusCodeResponse(200);
    }

}
