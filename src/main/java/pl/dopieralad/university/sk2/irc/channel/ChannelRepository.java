package pl.dopieralad.university.sk2.irc.channel;

import java.util.Set;

public interface ChannelRepository {

    void addChannel(Channel channel);

    Set<Channel> getChannels();
}
