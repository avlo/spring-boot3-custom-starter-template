package edu.mayo.lpea.cad.cadence3.security.service;

import edu.mayo.lpea.cad.cadence3.security.PreExistingUserException;
import edu.mayo.lpea.cad.cadence3.security.entity.AppUserAuthUser;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

public interface AuthUserService {

  boolean userExists(String userName);

  AppUserAuthUser createUser(String username, String password) throws PreExistingUserException;

  List<AppUserAuthUser> getAllAppUsersMappedAuthUsers();

  Collection<GrantedAuthority> getGrantedAuthorities(String username);
}
