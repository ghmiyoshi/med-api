package br.com.alura.med.infra.security;

import static java.util.Objects.nonNull;

import br.com.alura.med.application.usecases.autenticacao.AutenticarUsuario;
import br.com.alura.med.application.usecases.autenticacao.GerarToken;
import br.com.alura.med.infra.handler.TokenInvalidoException;
import br.com.alura.med.infra.utils.ObjectMapperUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String PREFIX_TOKEN = "Bearer ";

    private final AutenticarUsuario autenticarUsuario;
    private final GerarToken gerarToken;

    /* Filtro para interceptar todas as requisições */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException,
            IOException {
        try {
            var tokenJwt = recuperarToken(request);

            if (nonNull(tokenJwt)) {
                var subject = gerarToken.getSubject(tokenJwt);
                var usuario = autenticarUsuario.loadUserByUsername(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (TokenInvalidoException ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(ObjectMapperUtils.writeObjectInJsonWithNullFields(
                    ProblemDetailforStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getReason())));
        }
    }

    private String recuperarToken(final HttpServletRequest request) {
        var authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        if (nonNull(authorizationHeader)) {
            return authorizationHeader.replace(PREFIX_TOKEN, "");
        }
        return null;
    }

    private ProblemDetail ProblemDetailforStatusAndDetail(final HttpStatus httpStatus,
                                                          final String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, detail);
        problemDetail.setType(URI.create("https://api.med.com/errors"));
        problemDetail.setStatus(httpStatus);
        problemDetail.setDetail(detail);

        return problemDetail;
    }
}
