package pl.dopieralad.university.sk2.irc.server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

@Component
public class ServerAdapter {

    private final ServerSocket serverSocket;
    private final int headerLength;

    public ServerAdapter(ServerSocket serverSocket, @Value("#{serverProperties.headerLength}") DataSize headerLength) {
        this.serverSocket = serverSocket;
        this.headerLength = Math.toIntExact(headerLength.toBytes());
    }

    public void send(String content) throws IOException {
        var outputStream = serverSocket.getOutputStream();
        var contentLength = String.valueOf(content.length());
        var header = StringUtils.leftPad(contentLength, headerLength, "0");
        var message = header + content;
        var bytes = message.getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes);
    }

    @Async
    public void sendAsync(String content) throws IOException {
        send(content);
    }

    public String receive() throws IOException {
        var inputStream = serverSocket.getInputStream();
        var headerBytes = inputStream.readNBytes(headerLength);
        var header = new String(headerBytes);
        var contentLength = Integer.parseInt(header);
        var bytes = inputStream.readNBytes(contentLength);
        return StringUtils.toEncodedString(bytes, StandardCharsets.UTF_8);
    }
}
