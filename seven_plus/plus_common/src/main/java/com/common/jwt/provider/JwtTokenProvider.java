package com.common.jwt.provider;

import com.alibaba.fastjson.JSON;
import com.common.excption.AuthExceptionConstants;
import com.common.excption.BaseException;
import com.common.jwt.JwtConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 * Jwt token
 *
 * 1. create token
 * 2. valid token
 * Created by  2018/8/8.
 */
@Component
public class JwtTokenProvider {

    public static final String MODULE = "module";


    private JwtConfig jwtConfig;

    public JwtTokenProvider(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createJwtToken(Payload payload) {

        //data
        String key = payload.key();
        String value = JSON.toJSONString(payload);
        return this.createJwtToken(key,value,null);
    }

    public String createJwtToken( String key, String value ,Long ytlMillis) {
        //id
        String id = UUID.randomUUID().toString();

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtConfig.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        DefaultClaims claims = new DefaultClaims();
        claims.put(MODULE, key);
        claims.put(key, value);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(jwtConfig.getSubject())
                .setIssuer(jwtConfig.getIssuer())
                .setClaims(claims)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if(ytlMillis!=null && ytlMillis>0){
            long expMillis = nowMillis + ytlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }else if (jwtConfig.getTtlMillis() >= 0) {
            long expMillis = nowMillis + jwtConfig.getTtlMillis();
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Jws<Claims> parseJwtToken(String jwtToken) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtConfig.getSecret()))
                .parseClaimsJws(jwtToken);
        return claimsJws;
    }

    public <T extends Payload> T parseJwtToken(String jwtToken, Class<T> clazz) {
        Jws<Claims> claimsJws;
        if (null == jwtToken || "[object Undefined]".equals(jwtToken) || "".equals(jwtToken)){
            throw BaseException.build(AuthExceptionConstants.MISSING_ACCESS_TOKEN);
        }
        try{
            claimsJws = parseJwtToken(jwtToken);
        } catch (ExpiredJwtException e){
            throw BaseException.build(AuthExceptionConstants.ACCESS_TOKEN_HAS_EXPIRED);
        }
        return parseJwtToken(claimsJws, clazz);
    }

    public <T extends Payload> T parseJwtToken(Jws<Claims> claimsJws, Class<T> clazz) {
        Payload payload = null;
        Claims body = claimsJws.getBody();
        try {
            payload = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(payload==null){
            throw new RuntimeException("payload 解析失败");
        }
        String value = body.get(payload.key(), String.class);
        return JSON.parseObject(value, clazz);
    }

    public JwtConfig getJwtConfig() {
        return jwtConfig;
    }

    public void setJwtConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }
}
