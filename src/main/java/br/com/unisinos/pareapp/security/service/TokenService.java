package br.com.unisinos.pareapp.security.service;

import java.util.Map;

public interface TokenService {
    String newToken(Map<String, String> attributes);
    Map<String, String> verify(String token);
    boolean validate(String token);
}
