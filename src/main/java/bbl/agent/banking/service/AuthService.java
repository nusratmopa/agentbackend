package bbl.agent.banking.service;

import bbl.agent.banking.dto.request.LoginRequestDTO;
import bbl.agent.banking.dto.response.LoginResponseDTO;
import bbl.agent.banking.entities.RefreshToken;
import bbl.agent.banking.repositories.UserRepository;
import bbl.agent.banking.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    //private final AuthenticationProvider authenticationProvider;
    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    RefreshTokenService refreshTokenService;


    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

        if(authentication.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authentication);
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setAccessToken(token);
            loginResponseDTO.setRefreshToken(refreshToken.getToken());
            return loginResponseDTO;
        }else{
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    /*public RefreshToken createRefreshToken(String username){
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userRepository.findByUsername(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }*/
}
