package edu.mayo.lpea.cad.cadence3.azure.service;

import edu.mayo.lpea.cad.cadence3.azure.entity.AzureUserDetailsImpl;
import edu.mayo.lpea.cad.cadence3.security.PreExistingUserException;
import edu.mayo.lpea.cad.cadence3.security.entity.AuthUserDetails;
import edu.mayo.lpea.cad.cadence3.security.entity.AuthUserDetailsImpl;
import edu.mayo.lpea.cad.cadence3.security.service.AuthUserDetailServiceImpl;
import edu.mayo.lpea.cad.cadence3.security.service.AuthUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AzureUserDetailServiceImpl implements AuthUserDetailsService {
  private static final Logger LOGGER = LoggerFactory.getLogger(AzureUserDetailServiceImpl.class);
  private final AuthUserDetailServiceImpl authUserDetailsService;

  private static final String ADOAUTH2_DEFAULT_ROLE = "ANONYMOUS";

  public AzureUserDetailServiceImpl(AuthUserDetailServiceImpl authUserDetailsService) {
    LOGGER.info("ADOAUTH2 - Loading AzureUserDetailServiceImpl");
    this.authUserDetailsService = authUserDetailsService;
  }

  @Override
  public boolean userExists(String userName) {
    return authUserDetailsService.userExists(userName);
  }

  @Override
  public AuthUserDetails createAuthUser(String username, String password) throws PreExistingUserException {
    authUserDetailsService.createUser(new AzureUserDetailsImpl(new AuthUserDetailsImpl(authUserDetailsService.createAuthUser(username, password, ADOAUTH2_DEFAULT_ROLE))));
    return loadUserByUsername(username);
  }

  @Override
  public AuthUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new AzureUserDetailsImpl(authUserDetailsService.loadUserByUsername(username));
  }
}
