package bbl.agent.banking.service;

import bbl.agent.banking.entities.RefreshToken;
import bbl.agent.banking.exceptions.AgentBankingException;
import bbl.agent.banking.repositories.RefreshTokenRepository;
import bbl.agent.banking.repositories.UserRepository;
import bbl.agent.banking.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${app-jwt-RefreshToke-expiration-ms}")
    private Long refreshTokenDurationMs;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;

    public RefreshToken createRefreshToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUserId(customUserDetails.getUserId());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new AgentBankingException.BadRequestException("Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        //return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
        return 0;
    }
}
