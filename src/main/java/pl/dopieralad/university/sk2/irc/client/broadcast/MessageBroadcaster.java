package pl.dopieralad.university.sk2.irc.client.broadcast;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.dopieralad.university.sk2.irc.message.Message;

@Component
public class MessageBroadcaster extends AsyncBroadcaster<Message> {

    @EventListener(Message.class)
    public void listen(Message message) {
        broadcast(message);
    }
}
