package br.com.jorgeadao.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.jorgeadao.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                var servletPath = request.getServletPath();

                if (servletPath.startsWith("/tasks/")) {
                    //Authentication data processing
                    var authorization = request.getHeader("Authorization");
                    var authEncoded = authorization.substring("Basic".length()).trim();

                    byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

                    var authString = new String(authDecoded);

                    String[] credentials = authString.split(":");
                    String username = credentials[0];
                    String password = credentials[1];

                    //User validation
                    var user = this.userRepository.findByUsername(username);
                    if (user == null) {
                        response.sendError(401);
                    }
                    else {
                        //Password validation
                        var passwordVerified = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                        if (passwordVerified.verified) {
                            request.setAttribute("idUser", user.getId()); //Set 'idUser' along with authentication
                            filterChain.doFilter(request, response);
                        }
                        else {
                            response.sendError(401);
                        }
                    }
                }
                else {
                    filterChain.doFilter(request, response);
                }
    } 
}