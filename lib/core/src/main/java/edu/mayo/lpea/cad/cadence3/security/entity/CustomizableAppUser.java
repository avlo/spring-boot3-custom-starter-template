package edu.mayo.lpea.cad.cadence3.security.entity;

public interface CustomizableAppUser <T extends AppUser> {
  Long getId();
  T getInstantiatedCustomAppUserType();
  T createNewCustomAppUserInstance();
}
