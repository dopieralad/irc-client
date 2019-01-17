package pl.dopieralad.university.sk2.irc.object;

@FunctionalInterface
public interface Deserializer<T> {

    T deserialize(String serializedObject);
}
