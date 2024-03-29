package br.com.alura.med.service.autenticacao;

import br.com.alura.med.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenService {

    private static final String ZONE_ID = "America/Sao_Paulo";

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(final Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API med")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId()) // Para adicionar mais
                    // informacoes no token
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token jwt!", exception);
        }
    }

    public String getSubject(final String tokenJwt) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API med")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().atZone(ZoneId.of(ZONE_ID)).plusHours(2).toInstant();
    }

}
