package br.com.alura.med.infra.security;

import br.com.alura.med.domain.service.AutenticacaoService;
import br.com.alura.med.domain.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Objects.nonNull;

@AllArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final AutenticacaoService autenticacaoService;

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String PREFIX_TOKEN = "Bearer ";

    /* Filtro para interceptar todas as requisições */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var tokenJwt = recuperarToken(request);

        if (nonNull(tokenJwt)) {
            var subject = tokenService.getSubject(tokenJwt);
            var usuario = autenticacaoService.loadUserByUsername(subject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        if (nonNull(authorizationHeader)) {
            return authorizationHeader.replace(PREFIX_TOKEN, "");
        }

        return null;
    }

}
