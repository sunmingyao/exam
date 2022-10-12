package com.ph.exam.support.shiro.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ph.exam.support.constant.Constant;
import com.ph.exam.support.exception.ExamException;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : sunmingyao
 */
@Data
@Component
public class TokenServiceImpl implements TokenService {

    @Override
    public String createToken(String jobNumber, String salt, String issuer) {

        Date date = new Date(System.currentTimeMillis() + Constant.TOKEN_EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(salt);
        return JWT.create()
                .withClaim("jobNumber", String.valueOf(jobNumber))
                .withClaim("salt", salt)
                .withIssuer(issuer)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    @Override
    public void verify(String token, String salt, String issuer) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(salt);
            JWTVerifier verifier;

            if (issuer == null) {
                verifier = JWT.require(algorithm)
                        .withClaim("jobNumber", getJobNumber(token))
                        .build();
            } else {
                verifier = JWT.require(algorithm)
                        .withClaim("jobNumber", getJobNumber(token))
                        .withIssuer(issuer)
                        .build();
            }
            // 校验TOKEN
            verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new ExamException("token验证失败");
        }
    }


    @Override
    public String getJobNumber(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("jobNumber").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    @Override
    public String getSalt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("salt").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    @Override
    public String getIssuer(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuer();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
