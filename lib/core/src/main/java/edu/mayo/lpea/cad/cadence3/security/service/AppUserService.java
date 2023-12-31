package edu.mayo.lpea.cad.cadence3.security.service;

import edu.mayo.lpea.cad.cadence3.security.entity.AppUser;
import edu.mayo.lpea.cad.cadence3.security.entity.CustomizableAppUser;
import edu.mayo.lpea.cad.cadence3.security.repository.AppUserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AppUserService {
  private final AppUserRepository appUserRepository;

  @Autowired
  public AppUserService(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  public CustomizableAppUser<AppUser> findById(Long id) {
    return appUserRepository.findById(id).get();
  }

  public CustomizableAppUser<AppUser> findByAppUser(@NonNull AppUser appUser) {
    return Objects.isNull(appUser.getId()) ? appUser : findById(appUser.getId());
  }

  @Transactional
  public CustomizableAppUser<AppUser> save(@NonNull CustomizableAppUser<? extends AppUser> appUser) {
    CustomizableAppUser<AppUser> appUserToSave = findByAppUser(appUser.getInstantiatedCustomAppUserType());
    return Objects.isNull(appUserToSave.getId()) ? appUserRepository.save(appUserToSave.getInstantiatedCustomAppUserType()) : appUserToSave.getInstantiatedCustomAppUserType();
  }
}
