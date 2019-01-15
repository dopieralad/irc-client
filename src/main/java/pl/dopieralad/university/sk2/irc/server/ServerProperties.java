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
    private final String host = "localhost";

    @Positive
    @Max(65535)
    private final int port = 6667;

    @DataSizeUnit(DataUnit.BYTES)
    private final DataSize headerLength = DataSize.ofBytes(32);

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public DataSize getHeaderLength() {
        return headerLength;
    }
}
