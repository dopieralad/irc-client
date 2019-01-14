package pl.dopieralad.university.sk2.irc.server;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties(prefix = "irc.server")
public class ServerProperties {

    @NotBlank
    private String host = "localhost";

    @Positive
    @Max(65535)
    private int port = 6667;

    @Positive
    private int pollingInterval = 200;

    @DataSizeUnit(DataUnit.BYTES)
    private DataSize headerLength = DataSize.ofBytes(32);

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPollingInterval() {
        return pollingInterval;
    }

    public void setPollingInterval(int pollingInterval) {
        this.pollingInterval = pollingInterval;
    }

    public DataSize getHeaderLength() {
        return headerLength;
    }

    public void setHeaderLength(DataSize headerLength) {
        this.headerLength = headerLength;
    }
}
