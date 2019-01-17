package pl.dopieralad.university.sk2.irc.client.provider;

import java.util.stream.Stream;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dopieralad.university.sk2.irc.message.Message;
import pl.dopieralad.university.sk2.irc.message.MessageRepository;

@Component
public class MessageProvider extends AbstractBackEndDataProvider<Message, String> {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageProvider(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    protected Stream<Message> fetchFromBackEnd(Query<Message, String> query) {
        return messageRepository.getMessages().stream();
    }

    @Override
    protected int sizeInBackEnd(Query<Message, String> query) {
        return messageRepository.getMessages().size();
    }
}
