package pl.dopieralad.university.sk2.irc.channel;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Channel {

    private final String name;

    public Channel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Set<Channel> deserialize(String serializedChannels) {
        var parts = serializedChannels.split(" ", 2);

        return Arrays.stream(parts)
            .skip(1)
            .flatMap(channels -> Arrays.stream(channels.split(",")))
            .map(Channel::new)
            .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Channel channel = (Channel) o;
        return name.equals(channel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
