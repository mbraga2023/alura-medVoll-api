package br.com.alura.med.voll.alura_medVoll_api.controller;

import br.com.alura.med.voll.alura_medVoll_api.dto.DtoAutenticacao;
import br.com.alura.med.voll.alura_medVoll_api.infra.security.DadosTokenJwt;
import br.com.alura.med.voll.alura_medVoll_api.infra.security.TokenService;
import br.com.alura.med.voll.alura_medVoll_api.models.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("")
    public ResponseEntity login(@RequestBody @Valid DtoAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJwt(tokenJwt));

    }
}
