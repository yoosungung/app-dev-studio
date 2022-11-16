package kr.ac.jj.shared.infrastructure.security.crypto.password;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {

    private @Autowired PasswordEncoder passwordEncoder;

    @Test
    public void createEncodedPassword() {
        String rawPassword = "1111"; // 테스트 패스워드

        String encodedPassword1 = passwordEncoder.encode(rawPassword);
        String encodedPassword2 = passwordEncoder.encode(rawPassword);
        String encodedPassword3 = passwordEncoder.encode(rawPassword);
        String encodedPassword4 = passwordEncoder.encode(rawPassword);
        String encodedPassword5 = passwordEncoder.encode(rawPassword);

        System.out.println(StringUtils.leftPad("", 80, "="));

        System.out.println(String.format("암호화 알고리즘 : %s", passwordEncoder.getClass().getName()));

        System.out.println(StringUtils.leftPad("", 80, "-"));

        System.out.println(String.format("원본 패스워드 : %s", rawPassword));

        System.out.println(StringUtils.leftPad("", 80, "-"));

        System.out.println(String.format("암호화된 패스워드 1 : %s", encodedPassword1));
        System.out.println(String.format("암호화된 패스워드 2 : %s", encodedPassword2));
        System.out.println(String.format("암호화된 패스워드 3 : %s", encodedPassword3));
        System.out.println(String.format("암호화된 패스워드 4 : %s", encodedPassword4));
        System.out.println(String.format("암호화된 패스워드 5 : %s", encodedPassword5));

        System.out.println(StringUtils.leftPad("", 80, "-"));

        System.out.println(String.format("패스워드 확인 1 : %s", passwordEncoder.matches(rawPassword, encodedPassword1)));
        System.out.println(String.format("패스워드 확인 2 : %s", passwordEncoder.matches(rawPassword, encodedPassword2)));
        System.out.println(String.format("패스워드 확인 3 : %s", passwordEncoder.matches(rawPassword, encodedPassword3)));
        System.out.println(String.format("패스워드 확인 4 : %s", passwordEncoder.matches(rawPassword, encodedPassword4)));
        System.out.println(String.format("패스워드 확인 5 : %s", passwordEncoder.matches(rawPassword, encodedPassword5)));

        System.out.println(StringUtils.leftPad("", 80, "="));
    }

}
