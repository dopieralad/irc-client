package pl.dopieralad.university.sk2.irc.channel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryChannelRepository implements ChannelRepository {

    private final Set<Channel> channels = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void addChannel(Channel channel) {
        channels.add(channel);
    }

    @Override
    public Set<Channel> getChannels() {
        return Collections.unmodifiableSet(channels);
    }
}
