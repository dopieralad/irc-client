package pl.dopieralad.university.sk2.irc.message;

import java.time.LocalDateTime;

public class Message {

    private final LocalDateTime time = LocalDateTime.now();
    private final String author;
    private final String content;

    public Message(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public static String serialize(Object object) {
        if (!(object instanceof Message)) {
            throw new IllegalArgumentException();
        }
        
        var message = (Message) object;
        return String.format("message %s %s", message.getAuthor(), message.getContent());
    }

    public static Message deserialize(String serializedMessage) {
        var parts = serializedMessage.split(" ", 3);
        var author = parts[1];
        var content = parts[2];
        return new Message(author, content);
    }
}
