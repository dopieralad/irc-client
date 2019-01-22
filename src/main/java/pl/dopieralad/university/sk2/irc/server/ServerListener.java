package pl.dopieralad.university.sk2.irc.server;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.dopieralad.university.sk2.irc.object.ObjectDeserializer;

@Component
public class ServerListener {

    private final ServerAdapter serverAdapter;
    private final ObjectDeserializer objectDeserializer;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ServerListener(ServerAdapter serverAdapter, ObjectDeserializer objectDeserializer, ApplicationEventPublisher eventPublisher) {
        this.serverAdapter = serverAdapter;
        this.objectDeserializer = objectDeserializer;
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedDelay = 1) // Start right after last iteration
    public void listen() throws IOException {
        var command = serverAdapter.receive();
        var object = objectDeserializer.deserialize(command);
        if (object instanceof Collection) {
            ((Collection) object).forEach(eventPublisher::publishEvent);
        } else {
            eventPublisher.publishEvent(object);
        }
    }
}
