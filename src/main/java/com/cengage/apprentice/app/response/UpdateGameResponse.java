package com.cengage.apprentice.app.response;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import source.TTT.Game;

import com.cengage.apprentice.app.utils.MimeTypes;
import com.google.gson.Gson;

public class UpdateGameResponse implements OrionResponse {
    private static final long serialVersionUID = 1L;
    private static final int STATUSCODE200 = 200;

    private String header;
    private String body;
    private Game game;
    
    public UpdateGameResponse(Game game){
        this.game = game;
        setBody();
        setHeader();
    }

    public String getBody() {
        return body;
    }

    public String getHeader() {
        return header;
    }

    public void setBody(){
        Gson gson = new Gson();
        String board = gson.toJson(game.getBoard());
        this.body = board;
    }

    public void setHeader() {
        final ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setStatus(STATUSCODE200);
        responseHeader.setContentType(MimeTypes.get("json"));
        responseHeader.setContentLength((long)getBody().length());
        this.header = responseHeader.composeHeader();
    }

    public void write(OutputStream output, Object body) throws IOException {
        output.write(getHeader().getBytes(Charset.forName("UTF-8")));
        output.write(getBody().getBytes(Charset.forName("UTF-8")));
    }

}
