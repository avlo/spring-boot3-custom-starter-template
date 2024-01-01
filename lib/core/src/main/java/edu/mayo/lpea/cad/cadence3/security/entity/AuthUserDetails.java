package edu.mayo.lpea.cad.cadence3.security.entity;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthUserDetails extends UserDetails {
  UserDetails getUser();
}
