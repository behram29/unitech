package az.unibank.unitech.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class ApplicationConfiguration {

    private static String privateKey;
    private static String publicKey;

    public void setPublicKey(String publicKey1) {
        publicKey = publicKey1;
    }

    public void setPrivateKey(String privateKey1) {
        privateKey = privateKey1;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        Duration duration = Duration.ofMinutes(2);
        return new RestTemplateBuilder()
                .setReadTimeout(duration)
                .setConnectTimeout(duration)
                .build();
    }
}
