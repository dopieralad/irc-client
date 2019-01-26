package pl.dopieralad.university.sk2.irc.server;

import java.io.IOException;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

@Component
public class ServerAdapter {

    private final ApplicationContext applicationContext;
    private final int headerLength;

    private ServerSocket serverSocket;
    private boolean isShuttingDown = false;

    public ServerAdapter(ApplicationContext applicationContext, @Value("#{serverProperties.headerLength}") DataSize headerLength) {
        this.applicationContext = applicationContext;
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

    public String receive() throws IOException {
        try {
            var inputStream = serverSocket.getInputStream();
            var headerBytes = inputStream.readNBytes(headerLength);
            var header = new String(headerBytes);
            var contentLength = Integer.parseInt(header);
            var bytes = inputStream.readNBytes(contentLength);
            return StringUtils.toEncodedString(bytes, StandardCharsets.UTF_8);
        } catch (NumberFormatException | SocketException e) {
            restart();
            return receive();
        }
    }

    @PostConstruct
    private void restart() {
        if (!isShuttingDown) {
            this.serverSocket = applicationContext.getBean(ServerSocket.class);
        }
    }

    @PreDestroy
    private void shutdown() throws IOException {
        isShuttingDown = true;
        send("/disconnect");
    }
}
