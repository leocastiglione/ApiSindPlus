package sindplus.api.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import sindplus.api.security.JwtUtil;

@RestController
@RequestMapping("/sindicos")
public class AuthController {

  @Autowired private AuthenticationManager authManager;
  @Autowired private JwtUtil jwtUtil;

  // POST /api/auth/login → retorna JWT
  @PostMapping
  public ResponseEntity<?> login(@RequestBody LoginRequest req) {
    try {
      // 1. Autentica com o AuthenticationManager
      authManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              req.getEmail(), req.getSenha()
          )
      );

      String token = jwtUtil.generateToken(req.getEmail());

      return ResponseEntity.ok(
          Map.of("token", token, "email", req.getEmail())
      );

    } catch (BadCredentialsException e) {
      return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .body(Map.of("erro", "E-mail ou senha inválidos"));
    }
  }
}

@Data
class LoginRequest {
  private String email;
  private String senha;
}