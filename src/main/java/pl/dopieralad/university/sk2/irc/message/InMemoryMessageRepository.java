package pl.dopieralad.university.sk2.irc.message;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryMessageRepository implements MessageRepository {

    private final List<Message> messages = Collections.synchronizedList(new LinkedList<>());

    @Override
    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
