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

    @Autowired
    public ServerListener(Logger logger, ServerAdapter serverAdapter) {
        this.logger = logger;
        this.serverAdapter = serverAdapter;
    }

    @Scheduled(fixedDelay = 1) // Start right after last iteration
    public void listen() throws IOException {
        var message = serverAdapter.receive();
        logger.info(message);
        serverAdapter.sendAsync(message);
    }
}
