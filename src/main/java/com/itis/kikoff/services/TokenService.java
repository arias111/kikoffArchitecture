package com.itis.kikoff.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.itis.kikoff.models.auth.Token;
import com.itis.kikoff.models.auth.User;

public interface TokenService {
    boolean check(String token);
    boolean timeCheck(DecodedJWT decodedJWT, long limit);
    String generateToken(User user);
    Token generatingToken(User user);
    void clearToken(String token);
}
