package edu.mayo.lpea.cad.cadence3.security.service;

import edu.mayo.lpea.cad.cadence3.security.entity.AppUser;
import edu.mayo.lpea.cad.cadence3.security.entity.CustomizableAppUser;

public class CustomizableAppUserService {
	private final AppUser appUser;
	public CustomizableAppUserService(AppUser appUser) {
		this.appUser = appUser;
	}
	public CustomizableAppUser<AppUser> createNewAppUser() {
		return appUser.createNewCustomAppUserInstance();
	}
}
