package pl.dopieralad.university.sk2.irc.object;

import pl.dopieralad.university.sk2.irc.channel.Channel;
import pl.dopieralad.university.sk2.irc.message.Message;

public enum ObjectType {

    MESSAGE("message", Message::serialize, Message::deserialize),
    CHANNEL("get_channels", null, Channel::deserialize);

    private final String prefix;
    private final Serializer serializer;
    private final Deserializer deserializer;

    ObjectType(String prefix, Serializer serializer, Deserializer deserializer) {
        this.prefix = prefix;
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    public String getPrefix() {
        return prefix;
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public Deserializer getDeserializer() {
        return deserializer;
    }
}
