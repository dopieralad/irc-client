package pl.dopieralad.university.sk2.irc.message;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

public interface MessageRepository {

    @EventListener(Message.class)
    @Order(0)
    void addMessage(Message message);

    List<Message> getMessages();
}
