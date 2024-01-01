package edu.mayo.lpea.cad.cadence3.web.service;

import edu.mayo.lpea.cad.cadence3.security.entity.AppUserAuthUser;
import edu.mayo.lpea.cad.cadence3.web.model.AppUserDto;
import java.util.List;

public interface AppUserDtoService {
	List<AppUserDto> getAllAppUsersAsDto();

	default List<AppUserDto> convertAppUserToDto(List<AppUserAuthUser> users) {
		return users.stream().map(this::mapToUserDto).toList();
	}
	default AppUserDto mapToUserDto(AppUserAuthUser appUserAuthUser) {
		AppUserDto userDto = new AppUserDto();
		userDto.setUsername(appUserAuthUser.getAuthUserName());
		userDto.setId(appUserAuthUser.getAppUserId());
		return userDto;
	}
}
