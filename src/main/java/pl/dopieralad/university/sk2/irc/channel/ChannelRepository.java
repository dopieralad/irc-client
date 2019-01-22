package pl.dopieralad.university.sk2.irc.channel;

import java.util.Set;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

public interface ChannelRepository {

    @EventListener(Channel.class)
    @Order(0)
    void addChannel(Channel channel);

    Set<Channel> getChannels();
}
