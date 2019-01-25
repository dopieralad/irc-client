package pl.dopieralad.university.sk2.irc.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "irc.client")
public class ClientProperties {

    @NotBlank
    private final String login = getDefaultLogin();

    public String getLogin() {
        return login;
    }

    private static String getDefaultLogin() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ignored) {
            return "";
        }
    }
}
