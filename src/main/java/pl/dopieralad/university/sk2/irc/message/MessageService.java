package pl.dopieralad.university.sk2.irc.message;

import java.io.IOException;

public interface MessageService {
    void send(String message) throws IOException;
}
