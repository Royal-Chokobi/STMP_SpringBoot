package kollus.stmp.stmp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KollusConfig {

    @Value("${kollus.mailAddress}")
    private String mailAddress;
    @Value("${kollus.secretKey}")
    private String secretKey;
    @Value("${kollus.host}")
    private String host;
    @Value("${kollus.protocol}")
    private String protocol;
    @Value("${kollus.startTime}")
    private String startTime;
    @Value("${kollus.endTime}")
    private String endTime;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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
        return "KollusConfig{" +
                "mailAddress='" + mailAddress + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", host='" + host + '\'' +
                ", protocol='" + protocol + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
