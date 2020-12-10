package com.drsoft.employee.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.drsoft.employee.config.ErrorCode;
import com.drsoft.employee.config.Response;
import com.drsoft.employee.entity.EmployeeEntity;
import com.drsoft.employee.exception.TokenExpiredException;
import com.drsoft.employee.repository.EmployeeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	private final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");

		try {

			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				String jwtToken = authorizationHeader.substring(7);

				jwtTokenUtil.validateToken(jwtToken);
				String name = jwtTokenUtil.getUsernameFromToken(jwtToken);

				EmployeeEntity userDetails = employeeRepo.findByName(name);

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, null);
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			}

		} catch (ExpiredJwtException e) {
			handleTokenException(response, Response.createErrorResponse(ErrorCode.ERROR_EXPIRED_TOKEN.getCode(),
					ErrorCode.ERROR_EXPIRED_TOKEN.getDescription()));
			throw new TokenExpiredException(ErrorCode.ERROR_EXPIRED_TOKEN.getCode(),
					ErrorCode.ERROR_EXPIRED_TOKEN.getDescription());
		} catch (Exception e) {
			logger.error("Error jwt request filter >>>  "+e.getMessage());
			e.printStackTrace();
		}

		chain.doFilter(request, response);
	}
	private void handleTokenException(HttpServletResponse response, Response errorResponse) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }

}
