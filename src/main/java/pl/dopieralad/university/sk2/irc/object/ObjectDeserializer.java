package pl.dopieralad.university.sk2.irc.object;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class ObjectDeserializer {

    private static final ObjectType[] OBJECT_TYPES = ObjectType.values();

    public Object deserialize(String serializedObject) {
        return Arrays.stream(OBJECT_TYPES)
            .filter(objectType -> {
                var prefix = objectType.getPrefix();
                return serializedObject.startsWith(prefix);
            })
            .map(ObjectType::getDeserializer)
            .map(deserializer -> deserializer.deserialize(serializedObject))
            .findFirst()
            .orElseThrow();
    }
}
