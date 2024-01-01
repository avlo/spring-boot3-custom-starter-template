package edu.mayo.lpea.cad.cadence3.security.service;

import edu.mayo.lpea.cad.cadence3.security.PreExistingUserException;
import edu.mayo.lpea.cad.cadence3.security.entity.AuthUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserDetailsService extends UserDetailsService {
	boolean userExists(String userName);
	AuthUserDetails createAuthUser(String username, String password) throws PreExistingUserException;
	AuthUserDetails loadUserByUsername(String username);
}
