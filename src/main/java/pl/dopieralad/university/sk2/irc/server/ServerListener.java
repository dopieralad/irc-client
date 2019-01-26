package pl.dopieralad.university.sk2.irc.server;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.dopieralad.university.sk2.irc.message.Message;

@Component
public class ServerListener {

    private final ServerAdapter serverAdapter;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ServerListener(ServerAdapter serverAdapter, ApplicationEventPublisher eventPublisher) {
        this.serverAdapter = serverAdapter;
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedDelay = 1) // Start right after last iteration
    public void listen() throws IOException {
        var command = serverAdapter.receive();
        eventPublisher.publishEvent(new Message("", command));
    }
}
