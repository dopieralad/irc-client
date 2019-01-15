package pl.dopieralad.university.sk2.irc.server;

import java.io.IOException;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerSocket extends Socket {

    @Autowired
    public ServerSocket(ServerProperties serverProperties) throws IOException {
        super(serverProperties.getHost(), serverProperties.getPort());
    }
}
