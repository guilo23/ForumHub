package com.bia.ForumHub.config;

import com.bia.ForumHub.repository.UsuarioRepositorio;
import com.bia.ForumHub.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println(">> SecurityFilter executando para: " + request.getRequestURI());
        String tokenJWT = recuperarToken(request);

        System.out.println("Token JWT: " + tokenJWT);

        if (tokenJWT != null) {
            try {
                var subject = tokenService.getSubject(tokenJWT);
                System.out.println("Subject do token: " + subject);

                var usuario = usuarioRepositorio.findByEmail(subject);
                System.out.println("Usuário encontrado: " + (usuario != null ? usuario.getEmail() : "null"));

                if (usuario != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                System.out.println("Erro no token: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null; // não lança exceção aqui para não bloquear requisições sem token
        }

        return authorizationHeader.substring(7); // pega só o token sem "Bearer "
    }
}
