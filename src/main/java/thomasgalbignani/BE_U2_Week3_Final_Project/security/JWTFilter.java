package thomasgalbignani.BE_U2_Week3_Final_Project.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.User;
import thomasgalbignani.BE_U2_Week3_Final_Project.exceptions.UnauthorizedException;
import thomasgalbignani.BE_U2_Week3_Final_Project.services.UserService;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Header presente e scritto bene
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer "))
            throw new UnauthorizedException("Check that the Authorization header is compiled correctly");

        // Corretto?!, estrai il token
        String token = header.replace("Bearer ", "");

        // Verifichiamo che il token sia valido
        jwtTools.verifyToken(token);

        String userId = jwtTools.getIdFromToken(token);

        // Recupera l'utente dal database
        User user = userService.findById(UUID.fromString(userId));

        // Crea l'authentication con le authorities dell'utente
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getRoleAsAuthority()
        );

        // Imposta l'authentication nel SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}