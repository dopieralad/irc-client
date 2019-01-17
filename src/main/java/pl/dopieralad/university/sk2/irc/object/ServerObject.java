package pl.dopieralad.university.sk2.irc.object;

public interface ServerObject<T> {

    T deserialize(String serializedObject);

    String serialize(T object);
}
