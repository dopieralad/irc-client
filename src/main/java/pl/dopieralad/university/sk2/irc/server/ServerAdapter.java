package pl.dopieralad.university.sk2.irc.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerAdapter implements Closeable {

    private final ServerProperties serverProperties;

    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    @Autowired
    public ServerAdapter(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;

    }

    @PostConstruct
    public void connect() throws IOException {
        socket = new Socket(serverProperties.getHost(), serverProperties.getPort());
        outputStream = new BufferedOutputStream(socket.getOutputStream());
        inputStream = new BufferedInputStream(socket.getInputStream());
    }

    @PreDestroy
    public void disconnect() throws IOException {
        this.close();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
