package bbl.agent.banking.controller;

import bbl.agent.banking.dto.request.LoginRequestDTO;
import bbl.agent.banking.dto.response.LoginResponseDTO;
import bbl.agent.banking.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    public AuthController() {
    }
    @PostMapping(value = {"/login"})
    public ResponseEntity<?> authenticateAgentUser(@RequestBody LoginRequestDTO loginRequestDTO){
        try {
            return ResponseEntity.ok(authService.login(loginRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Bad Credential");
        }
    }

    @PostMapping(value = {"/register"})
    public ResponseEntity<?> registerAgentUser(@RequestBody LoginRequestDTO loginRequestDTO){
        try {

        } catch (Exception e) {
        }
        return null;
    }
}
