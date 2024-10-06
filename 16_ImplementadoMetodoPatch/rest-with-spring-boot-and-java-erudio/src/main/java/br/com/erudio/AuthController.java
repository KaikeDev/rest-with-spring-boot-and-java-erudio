package br.com.erudio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.security.AccountCredentialVO;
import br.com.erudio.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {
 
	
	@Autowired
	AuthServices authServices;

	
	@SuppressWarnings("rawtypes")
	@Operation(summary = " Authenticates a user and returns a token")
	@PostMapping( value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialVO data) {
		if (checkIfParamsIsNotNull(data))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid cliente request");
		var token = authServices.signin(data);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid cliente request");
		}else {
			return token;
		}
	}

	private boolean checkIfParamsIsNotNull(AccountCredentialVO data) {
		return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null
				|| data.getPassword().isBlank();
	}
	
	

}
