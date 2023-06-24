package br.com.alura.med.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BCryptPasswordEncoderUtils {

    public static String encode(final String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }

}
