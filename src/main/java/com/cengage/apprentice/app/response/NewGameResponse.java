package com.cengage.apprentice.app.response;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.cengage.apprentice.app.utils.MimeTypes;
import com.cengage.apprentice.app.utils.OrionConfigurator;

public class NewGameResponse implements OrionResponse {

    private static final Logger LOGGER = Logger
            .getLogger(NewGameResponse.class);
    private static final long serialVersionUID = 1L;
    private static final int STATUSCODE200 = 200;

    private String header;
    private String body;
    private int id;

    public NewGameResponse(int id) {
        this.id = id;
        setBody();
        setHeader();
    }

    public String getBody() {
        return body;
    }

    public String getHeader() {
        return header;
    }

    public void setBody() {
        File newGameFile = getNewGameFile();
        String newGameString;
        try {
            newGameString = FileUtils.readFileToString(newGameFile);
            String idAdded = newGameString.replace("gameId", String.valueOf(this.id));
            this.body = idAdded;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private File getNewGameFile() {
        File newGameFile = new File(OrionConfigurator.getRootDirectory()
                + "/newGame.html");
        return newGameFile;
    }

    public void setHeader() {
        final ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setStatus(STATUSCODE200);
        responseHeader.setContentType(MimeTypes.get("html"));
        responseHeader.setContentLength((long) getBody().length());
        this.header = responseHeader.composeHeader();
    }

    public void write(OutputStream output, Object body) throws IOException {
        output.write(getHeader().getBytes(Charset.forName("UTF-8")));
        output.write(getBody().getBytes(Charset.forName("UTF-8")));
    }

}
