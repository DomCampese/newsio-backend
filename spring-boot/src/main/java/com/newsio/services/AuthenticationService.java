package com.newsio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.newsio.entities.Role;
import com.newsio.entities.User;
import com.newsio.models.AuthenticationRequest;
import com.newsio.models.AuthenticationResponse;
import com.newsio.models.RegisterRequest;
import com.newsio.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private final JwtService jwtService;
  @Autowired
  private final AuthenticationManager authenticationManager;
  
  public AuthenticationResponse register(RegisterRequest request) {
    User user = User.builder()
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .role(Role.USER)
      .build();

    userRepository.save(user);
    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
      .token(jwtToken)
      .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()
      )
    );
    // user is authenticated (username + password were correct)
    User user = userRepository.findByEmail(request.getEmail())
      .orElseThrow();
    String jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
      .token(jwtToken)
      .build();
  }
}
