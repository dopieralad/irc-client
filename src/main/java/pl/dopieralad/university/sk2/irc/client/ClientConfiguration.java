package pl.dopieralad.university.sk2.irc.client;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class ClientConfiguration {

    private final InetAddress serverAddress;
    private final int serverPort;

    @Autowired
    public ClientConfiguration(ServerProperties serverProperties) {
        this.serverAddress = Optional
            .ofNullable(serverProperties.getAddress())
            .orElse(InetAddress.getLoopbackAddress());

        this.serverPort = Optional
            .ofNullable(serverProperties.getPort())
            .orElse(8080);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowser() throws URISyntaxException, IOException {
        System.setProperty("java.awt.headless", "false"); // Force Desktop support

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            var url = String.format("http://%s:%d", serverAddress.getHostName(), serverPort);
            var uri = new URI(url);
            Desktop.getDesktop().browse(uri);
        }

        System.setProperty("java.awt.headless", "true"); // Revoke Desktop support
    }
}
