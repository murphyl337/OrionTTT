package com.cengage.apprentice.app;

import static com.cengage.apprentice.app.utils.OrionConfigurator.getPort;
import static com.cengage.apprentice.app.utils.OrionConfigurator.getRootDirectory;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.log4j.Logger;

import com.cengage.apprentice.app.main.OrionServer;
import com.cengage.apprentice.app.utils.OrionConfigurator;
import com.cengage.apprentice.app.utils.Responder;

public class ServerRunner 
{
    private static final Logger LOGGER = Logger.getLogger(ServerRunner.class);
    
    public static void main( String[] args ) throws IOException
    {
        OrionConfigurator.parseArgs(args);
        final ServerSocket serverSocket = new ServerSocket(getPort());
        final OrionServer server = new OrionServer(serverSocket,
                OrionConfigurator.getRootDirectory());
        LOGGER.info("Orion-TTT server listening on port: " + getPort());
        server.listen(new Responder(getRootDirectory()));
    }
}
