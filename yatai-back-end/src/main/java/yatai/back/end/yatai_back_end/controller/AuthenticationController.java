package yatai.back.end.yatai_back_end.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import yatai.back.end.yatai_back_end.dto.AuthenticationRequest;
import yatai.back.end.yatai_back_end.dto.AuthenticationResponse;
import yatai.back.end.yatai_back_end.dto.RegisterRequest;
import yatai.back.end.yatai_back_end.model.User;
import yatai.back.end.yatai_back_end.repository.UserRepository;
import yatai.back.end.yatai_back_end.service.JwtService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request) {
    User user = User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .build();
    userRepository.save(user);
    String jwtToken = jwtService.generateToken((UserDetails) user);
    return ResponseEntity.ok(AuthenticationResponse.builder()
        .token(jwtToken)
        .build());
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()));
    UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
    String jwtToken = jwtService.generateToken(user);
    return ResponseEntity.ok(AuthenticationResponse.builder()
        .token(jwtToken)
        .build());
  }
}