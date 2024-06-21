package br.com.alura.med.application.usecases.autenticacao;

import br.com.alura.med.infra.handler.TokenInvalidoException;
import br.com.alura.med.infra.persistence.usuario.UsuarioEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Duration;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GerarToken {

    private static final String ZONE_ID = "America/Sao_Paulo";

    @Value("${api.security.token.secret}")
    private String secret;

    public String execute(final UsuarioEntity usuarioEntity) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API med")
                    .withSubject(usuarioEntity.getLogin())
                    .withClaim("id", usuarioEntity.getId()) // Para adicionar mais
                    // informacoes no token
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new TokenInvalidoException("Erro ao gerar o token jwt!");
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
            throw new TokenInvalidoException("Token JWT inválido ou expirado");
        }
    }

    private Instant dataExpiracao() {
        return Instant.now().plus(Duration.ofHours(2));
    }
}
