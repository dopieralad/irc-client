package pl.dopieralad.university.sk2.irc.client.broadcast;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.vaadin.flow.shared.Registration;

public abstract class AsyncBroadcaster<T> implements Broadcaster<T> {

    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Set<Consumer<T>> subscribers = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void broadcast(T object) {
        executor.execute(() -> subscribers.forEach(subscriber -> subscriber.accept(object)));
    }

    @Override
    public Registration register(Consumer<T> listener) {
        subscribers.add(listener);

        return () -> subscribers.remove(listener);
    }
}
