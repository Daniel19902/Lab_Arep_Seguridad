package edu.escuelaing.arem.Encriptar;

import edu.escuelaing.arem.model.User;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encripto {

    private final String keyPrivate = "arep202123456789";

    public Encripto() {
    }

    public String encriptar (String password) {
        String temp = "";
        try {
            Key aesKey = new SecretKeySpec(keyPrivate.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(password.getBytes());
            temp = Base64.getEncoder().encodeToString(encrypted);
        }catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e){
            e.printStackTrace();
        }
        return temp;
    }

    public String generateToken(User user)  {
        JwtClaims claims = new JwtClaims();
        claims.setSubject(user.getUserName());
        System.out.println(user.getUserName()+" generar token");
        claims.setClaim(Data.CONTEXT_NAME, user.getUserName());
        //claims.setClaim(Data.CONTEXT_PASSWORD, user.getPassword());
        claims.setExpirationTimeMinutesInTheFuture(Data.TOKEN_DURATION);

        Key key = new HmacKey(Data.KEY.getBytes(StandardCharsets.UTF_8));
        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(key);
        jws.setDoKeyValidation(false);
        String token = "";
        try {
            token = jws.getCompactSerialization();
        }catch (JoseException e){
            e.printStackTrace();
        }
        return token;
    }

    public String getInfoToken(String token) {
        if(token == null){
            return "";
        }

        Key key = new HmacKey(Data.KEY.getBytes(StandardCharsets.UTF_8));
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(60)
                .setRequireSubject()
                .setVerificationKey(key)
                .setRelaxVerificationKeyValidation()
                .build();
        try {
            JwtClaims claims = jwtConsumer.processToClaims(token);
            System.out.println(claims.getClaimValue(Data.CONTEXT_NAME).toString()+" TOKEN INFO");
            return claims.getClaimValue(Data.CONTEXT_NAME).toString();
        }catch (InvalidJwtException e){
            e.printStackTrace();
        }
        return "";

    }


}
