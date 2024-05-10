package br.com.alura.med.naousar.controller;

import br.com.alura.med.infra.persistence.usuario.UsuarioEntity;
import br.com.alura.med.naousar.domain.usuario.DadosAutenticacao;
import br.com.alura.med.naousar.infra.security.DadosTokenJwt;
import br.com.alura.med.naousar.service.autenticacao.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public DadosTokenJwt efetuarLogin(@RequestBody @Valid final DadosAutenticacao dadosAutenticacao) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutenticacao.login(),
                dadosAutenticacao.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        log.info("{}::efetuarLogin - Usuário autenticado", getClass().getSimpleName());

        var tokenJwt = tokenService.gerarToken((UsuarioEntity) authentication.getPrincipal());
        log.info("{}::efetuarLogin - Token gerado com sucesso", getClass().getSimpleName());
        return new DadosTokenJwt(tokenJwt);
    }

}
