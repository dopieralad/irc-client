package pl.dopieralad.university.sk2.irc.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ServerListener {

    private final Logger logger;
    private final ServerAdapter serverAdapter;
    private final ServerProperties serverProperties;

    @Autowired
    public ServerListener(Logger logger, ServerAdapter serverAdapter, ServerProperties serverProperties) {
        this.logger = logger;
        this.serverAdapter = serverAdapter;
        this.serverProperties = serverProperties;
    }

    @Scheduled(fixedDelayString = "#{serverProperties.pollingInterval}")
    public void listen() throws IOException {
        int headerLength = Math.toIntExact(serverProperties.getHeaderLength().toBytes());
        var stream = serverAdapter.getInputStream();
        int available = stream.available();
        if (available < headerLength) {
            return;
        }
        var contentLengthBytes = stream.readNBytes(headerLength);
        var contentLength = Integer.parseInt(new String(contentLengthBytes));
        var contentBytes = stream.readNBytes(contentLength);
        var content = new String(contentBytes);
        logger.info(content);
    }
}
