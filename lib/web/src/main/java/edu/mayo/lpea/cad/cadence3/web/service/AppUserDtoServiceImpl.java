package edu.mayo.lpea.cad.cadence3.web.service;

import edu.mayo.lpea.cad.cadence3.security.service.AuthUserService;
import edu.mayo.lpea.cad.cadence3.web.model.AppUserDto;
import java.util.List;
import lombok.NonNull;

public class AppUserDtoServiceImpl implements AppUserDtoService {

	private final AuthUserService authUserService;

	public AppUserDtoServiceImpl(@NonNull AuthUserService authUserService) {
		this.authUserService = authUserService;
	}

	@Override
	public List<AppUserDto> getAllAppUsersAsDto() {
		return convertAppUserToDto(authUserService.getAllAppUsersMappedAuthUsers());
	}
}
