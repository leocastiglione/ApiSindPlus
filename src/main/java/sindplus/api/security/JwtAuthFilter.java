package sindplus.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sindplus.api.config.UsuarioDetailsService;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	

	  @Autowired private JwtUtil jwtUtil;
	  @Autowired private UsuarioDetailsService userDetailsService;

	  @Override
	  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			  throws ServletException, IOException {
		 if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			    filterChain.doFilter(request, response);
			    return;
		 }		  


	    String authHeader = request.getHeader("Authorization");

	    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	      filterChain.doFilter(request, response);
	      return;
	    }


	    String token = authHeader.substring(7);
	    String email = jwtUtil.extractEmail(token);


	    if (email != null &&
	        SecurityContextHolder.getContext().getAuthentication() == null) {

	      UserDetails userDetails =
	          userDetailsService.loadUserByUsername(email);

	      if (jwtUtil.isValid(token, userDetails)) {
	        
	        UsernamePasswordAuthenticationToken auth =
	            new UsernamePasswordAuthenticationToken(
	                userDetails, null, userDetails.getAuthorities()
	            );
	        auth.setDetails(
	            new WebAuthenticationDetailsSource().buildDetails(request)
	        );
	        SecurityContextHolder.getContext().setAuthentication(auth);
	      }
	    }

	    filterChain.doFilter(request, response);
	  }

}
