package pl.dopieralad.university.sk2.irc.object;

@FunctionalInterface
public interface Serializer<T> {

    String serialize(T object);
}
