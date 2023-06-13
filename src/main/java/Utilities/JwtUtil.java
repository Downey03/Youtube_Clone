package Utilities;

import DTO.UserDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;

import java.util.Date;

public class JwtUtil {
    private final static String clientSecret = "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";
    static Algorithm algorithm = Algorithm.HMAC256(clientSecret);
    public static String generateJwt(String subject, long expirationMillis) {
       return JWT.create()
               .withIssuedAt(new Date(System.currentTimeMillis()))
               .withSubject(subject)
               .withClaim("role", "user")
               .withClaim("userId", 123)
               .sign(algorithm);
    }

    public static String generateToken(UserDTO user) {
        return JWT.create()
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withSubject("user")
                .withClaim("role", "user")
                .withClaim("userId", user.getName())
                .sign(algorithm);
    }

    public static boolean verifyToken(String tkn){
        try{
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedTkn = verifier.verify(tkn);

        }catch (Exception e){

        }
        return false;
    }
}
