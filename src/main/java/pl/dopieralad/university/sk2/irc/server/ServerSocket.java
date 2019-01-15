package pl.dopieralad.university.sk2.irc.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerSocket extends Socket {

    @Autowired
    public ServerSocket(ServerProperties serverProperties) throws IOException {
        super(serverProperties.getHost(), serverProperties.getPort());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        var inputStream = super.getInputStream();
        return new BufferedInputStream(inputStream);
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        var outputStream = super.getOutputStream();
        return new BufferedOutputStream(outputStream);
    }
}
