package com.itis.kikoff.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.itis.kikoff.models.auth.Token;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.repositories.TokenRepository;
import com.itis.kikoff.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    public TokenRepository tokensRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean check(String token) {
        try {
            DecodedJWT decodedJWT =  JWT.require(Algorithm.HMAC256("secret"))
                    .build()
                    .verify(token);
            Optional<Token> optionalToken = tokensRepository.findByUser_Id(Long.parseLong(decodedJWT.getSubject()));
            if (optionalToken.isPresent()) {
                return !decodedJWT.getExpiresAt().before(new Date());
            } else {
                throw new EntityNotFoundException("Token not found");
            }
        } catch (TokenExpiredException e) {
            return false;
        }
    }

    @Override
    public boolean timeCheck(DecodedJWT decodedJWT, long limit) {
        Date time = decodedJWT.getExpiresAt();
        Date nowTime = new Date();
        return (nowTime.getTime() - time.getTime()) <= limit;
    }

    @Override
    public String generateToken(User user) {
        Date date = new Date();
        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(new Date(date.getTime() + 604800000))
                .sign(Algorithm.HMAC256("secret"));
        Token token1 = new Token(token, date, user);
        tokensRepository.save(token1);
        return token;
    }
    @Override
    public Token generatingToken(User user) {
        Date date = new Date();
        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("role", user.getRole().toString())
                .withClaim("email", user.getEmail())
                .withExpiresAt(new Date(date.getTime() + 604800000))
                .sign(Algorithm.HMAC256("secret"));
        Token token1 = new Token(token, date, user);
        tokensRepository.save(token1);
        return token1;
    }

    @Transactional
    @Override
    public void clearToken(String token) {
        try {
            DecodedJWT decodedJWT =  JWT.require(Algorithm.HMAC256("secret"))
                    .build()
                    .verify(token);
            tokensRepository.deleteByUser_Id(Long.parseLong(decodedJWT.getSubject()));
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token not found");
        }
    }
}
