package pl.dopieralad.university.sk2.irc.client.broadcast;

import java.util.function.Consumer;

import com.vaadin.flow.shared.Registration;

public interface Broadcaster<T> {

    void broadcast(T object);

    Registration register(Consumer<T> listener);
}
