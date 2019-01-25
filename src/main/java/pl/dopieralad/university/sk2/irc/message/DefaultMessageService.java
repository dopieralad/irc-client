package pl.dopieralad.university.sk2.irc.message;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dopieralad.university.sk2.irc.client.ClientProperties;
import pl.dopieralad.university.sk2.irc.server.ServerAdapter;

@Service
public class DefaultMessageService implements MessageService {

    private final ServerAdapter serverAdapter;
    private final ClientProperties clientProperties;

    @Autowired
    public DefaultMessageService(ServerAdapter serverAdapter, ClientProperties clientProperties) {
        this.serverAdapter = serverAdapter;
        this.clientProperties = clientProperties;
    }

    @Override
    public void send(String message) throws IOException {
        var login = clientProperties.getLogin();
        var fullMessage = String.format("%s %s", login, message);
        serverAdapter.send(fullMessage);
    }
}
