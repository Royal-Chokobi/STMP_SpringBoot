package kollus.stmp.stmp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "kollus")
@PropertySource(value = {"classpath:application-kollus.yml"})
public class KollusConfig {

    @Value("${mailAddress}")
    private String mailAddress;
    @Value("${secretKey}")
    private String secretKey;
    @Value("${host}")
    private String host;
    @Value("${protocol}")
    private String protocol;
    @Value("${startTime}")
    private String startTime;
    @Value("${endTime}")
    private String endTime;

    public String getMailAddress() { return mailAddress; }

    public void setMailAddress(String customKey) {
        this.mailAddress = customKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[mailAddress=" + mailAddress + ", secretKey=" + secretKey + ", host=" + host +  ", protocol=" + protocol +"]";
    }
}
