package kr.ac.jj.shared.infrastructure.security.password;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

@SuppressWarnings("deprecation")
public class JJSha256PasswordEncoder extends MessageDigestPasswordEncoder {

    private final String rawPasswordPrefix = "jjuniv00";

    public JJSha256PasswordEncoder(String algorithm) {
        super(algorithm);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return super.encode(this.rawPasswordPrefix + rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword.startsWith("{") && encodedPassword.contains("}")) {
            return super.matches(this.rawPasswordPrefix + rawPassword, encodedPassword);
        }

        return super.matches(this.rawPasswordPrefix + rawPassword, encodedPassword.toLowerCase());
    }

}
