package br.com.alura.med.infra.security;

import br.com.alura.med.service.autenticacao.AutenticacaoService;
import br.com.alura.med.service.autenticacao.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String PREFIX_TOKEN = "Bearer ";

    private final TokenService tokenService;
    private final AutenticacaoService autenticacaoService;

    /* Filtro para interceptar todas as requisições */
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {
        var tokenJwt = recuperarToken(request);

        if (nonNull(tokenJwt)) {
            var subject = tokenService.getSubject(tokenJwt);
            var usuario = autenticacaoService.loadUserByUsername(subject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                                                                         usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(final HttpServletRequest request) {
        var authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        if (nonNull(authorizationHeader)) {
            return authorizationHeader.replace(PREFIX_TOKEN, "");
        }

        return null;
    }

}
