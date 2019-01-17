package pl.dopieralad.university.sk2.irc.message;

import java.util.List;

public interface MessageRepository {

    void addMessage(Message message);

    List<Message> getMessages();
}
