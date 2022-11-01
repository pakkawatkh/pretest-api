package backend.pretest.service.token;

import backend.pretest.entity.User;
import backend.pretest.exception.BaseForbiddenException;
import backend.pretest.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    private final UserRepository userRepository;
    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;


    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String tokenize(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24); // 24 hours
        Date expiresAt = calendar.getTime();

        return JWT.create().withIssuer(issuer).withClaim("principal", user.getUserId()).withClaim("lastpass", user.getLast_password().toGMTString()).withExpiresAt(expiresAt).sign(algorithm());
    }

    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm()).withIssuer(issuer).build(); //Reusable verifier instance
            return verifier.verify(token);

        } catch (Exception e) {
            return null;
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }

    @SneakyThrows
    public User getUserByToken()   {
        String userId = this.userId();

        Optional<User> opt = userRepository.findById(userId);
        if (opt.isEmpty()) throw BaseForbiddenException.Forbidden();

        User user = opt.get();
        if (!this.lastPassword().equals("[" + user.getLast_password().toGMTString() + "]")) throw BaseForbiddenException.Forbidden();

        return user;
    }

    public String userId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (String) authentication.getPrincipal();
    }
    public String lastPassword() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication.getAuthorities().toString();
    }
}
