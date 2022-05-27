package az.unibank.unitech.security;

import az.unibank.unitech.config.ApplicationConfiguration;
import az.unibank.unitech.enums.JwtTokenTypeEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class JwtTokenUtil {

    public String createToken(String userPin, JwtTokenTypeEnum tokenTypeEnum) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + tokenTypeEnum.getValidityTime());
        return Jwts.builder()
                .setClaims(tokenTypeEnum.apply(userPin))
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.RS256, getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() {
//        try {
//            KeyFactory kf = KeyFactory.getInstance("RSA");
//            String privateKey = ApplicationConfiguration.getPrivateKey();
//            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder()
//                    .decode(privateKey));
//            return kf.generatePrivate(keySpecPKCS8);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;


        PrivateKey privateKey = null;
        try {
            String mfstr = "BZC";
            String carg = URLDecoder.decode(mfstr, "UTF-8");
            byte[] v5 = Base64.getDecoder().decode(carg.getBytes());
            KeyFactory kf = KeyFactory.getInstance("RSA");
                        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(v5);
            privateKey = kf.generatePrivate(keySpecPKCS8);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");
        }
        return privateKey;

    }

    public String getUserPinFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        System.out.println(expiration.toString());

        //befire truedursa exp at diger teerfde tut

        return expiration.before(new Date());
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private PublicKey getPublicKey() {
        try {
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(ApplicationConfiguration.getPublicKey()));
            return kf.generatePublic(keySpecX509);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();
    }


}
