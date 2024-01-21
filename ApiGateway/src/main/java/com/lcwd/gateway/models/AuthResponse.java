package com.lcwd.gateway.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {

    private String userId;
    private String accessToken;
    private String refreshToken;

    private long expireAt;

    private Collection<String> authories;
}
