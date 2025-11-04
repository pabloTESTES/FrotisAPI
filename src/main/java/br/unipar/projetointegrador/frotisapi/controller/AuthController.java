package br.unipar.projetointegrador.frotisapi.controller;

import br.unipar.projetointegrador.frotisapi.dto.LoginRequest;
import br.unipar.projetointegrador.frotisapi.dto.LoginResponse;
import br.unipar.projetointegrador.frotisapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getCpf(), loginRequest.getSenha());

        if (token != null) {
            // Se o serviço retornou um token, o login foi bem-sucedido.
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            // Se o serviço retornou nulo, as credenciais são inválidas.
            return ResponseEntity.status(401).build(); // 401 Unauthorized
        }
    }
}