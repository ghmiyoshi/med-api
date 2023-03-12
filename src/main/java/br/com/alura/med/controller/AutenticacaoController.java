package br.com.alura.med.controller;

import br.com.alura.med.domain.usuario.DadosAutenticacao;
import br.com.alura.med.domain.usuario.Usuario;
import br.com.alura.med.infra.security.DadosTokenJwt;
import br.com.alura.med.service.autenticacao.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping
    public DadosTokenJwt efetuarLogin(@RequestBody @Valid final DadosAutenticacao dadosAutenticacao) {
        log.info("{}::efetuarLogin - Dados recebidos: {}", getClass().getSimpleName(), dadosAutenticacao);
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(),
                                                                          dadosAutenticacao.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        log.info("{}::efetuarLogin - Usuário autenticado", getClass().getSimpleName());

        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        log.info("{}::efetuarLogin - Token gerado com sucesso", getClass().getSimpleName());
        return new DadosTokenJwt(tokenJwt);
    }

}
