package edu.mayo.lpea.cad.cadence3.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public interface LocalAuthorities {
	Collection<GrantedAuthority> getGrantedAuthorities(String username);
}
