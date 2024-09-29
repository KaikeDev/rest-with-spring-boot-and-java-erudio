package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.security.AccountCredentialVO;
import br.com.erudio.data.vo.v1.security.TokenVO;
import br.com.erudio.repositories.UserRepositories;
import br.com.erudio.security.jwt.JwtTokenProvider;

@Service
public class AuthServices {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepositories repository;

	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialVO data) {
		try {

			var userName = data.getUsername();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

			var user = repository.findByUserName(userName);
			
			var tokenResponse = new TokenVO();
			if (user != null ) {
					tokenResponse = tokenProvider.createAccessToken(userName, user.getRoles());
			}else {
				throw new	UsernameNotFoundException("Username "+ userName+ " not found!");
			}
			
			return ResponseEntity.ok(tokenResponse);
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}

}
