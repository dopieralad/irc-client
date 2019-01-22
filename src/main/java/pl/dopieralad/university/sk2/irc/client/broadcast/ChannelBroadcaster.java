package pl.dopieralad.university.sk2.irc.client.broadcast;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.dopieralad.university.sk2.irc.channel.Channel;

@Component
public class ChannelBroadcaster extends AsyncBroadcaster<Channel> {

    @EventListener(Channel.class)
    public void listen(Channel channel) {
        broadcast(channel);
    }
}
