package kr.ac.jj.shared.application.common.email.sender.javamail;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config.shared.email.sender-options.java-mail")
public class JavaMailSenderConfigProperties {

    private Properties sessionProperties;
    private String smtpAuthUsername;
    private String smtpAuthPassword;

    public Properties getSessionProperties() {
        return this.sessionProperties;
    }

    public void setSessionProperties(Properties sessionProperties) {
        this.sessionProperties = sessionProperties;
    }

    public String getSmtpAuthUsername() {
        return this.smtpAuthUsername;
    }

    public void setSmtpAuthUsername(String smtpAuthUsername) {
        this.smtpAuthUsername = smtpAuthUsername;
    }

    public String getSmtpAuthPassword() {
        return this.smtpAuthPassword;
    }

    public void setSmtpAuthPassword(String smtpAuthPassword) {
        this.smtpAuthPassword = smtpAuthPassword;
    }

}
