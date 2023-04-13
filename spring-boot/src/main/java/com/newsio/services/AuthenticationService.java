package com.newsio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  
  public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
    boolean isEmailDuplicate = userRepository.findByEmail(request.getEmail()).isPresent();
    if (isEmailDuplicate) {
      return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }
    
    User user = User.builder()
      .firstName(request.getFirstName())
      .lastName(request.getLastName())
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .role(Role.USER)
      .build();

    userRepository.save(user);
    String jwtToken = jwtService.generateToken(user);
    AuthenticationResponse response = AuthenticationResponse.builder()
      .token(jwtToken)
      .build();
    return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
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
