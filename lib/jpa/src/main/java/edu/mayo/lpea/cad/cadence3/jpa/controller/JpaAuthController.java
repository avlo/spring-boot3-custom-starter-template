package edu.mayo.lpea.cad.cadence3.jpa.controller;

import edu.mayo.lpea.cad.cadence3.security.PreExistingUserException;
import edu.mayo.lpea.cad.cadence3.security.entity.AppUserAuthUser;
import edu.mayo.lpea.cad.cadence3.security.service.AuthUserService;
import edu.mayo.lpea.cad.cadence3.web.model.AppUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JpaAuthController {
	private static final Logger LOGGER = LoggerFactory.getLogger(JpaAuthController.class);
	private final AuthUserService authUserService;

	@Autowired
	public JpaAuthController(AuthUserService authUserService) {
		this.authUserService = authUserService;
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		AppUserDto user = new AppUserDto();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register")
	public String registration(@ModelAttribute("user") AppUserDto appUserDto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			LOGGER.info("User [{}] returned with with binding errors.", result.getFieldErrors());
			model.addAttribute("user", appUserDto);
			return "redirect:/register";
		}

		try {
			AppUserAuthUser appUserAuthUser = authUserService.createUser(appUserDto.getUsername(), appUserDto.getPassword());
			LOGGER.info("Registered AppUserAuthUser [{}]", appUserAuthUser.getAuthUserName());
			model.addAttribute("user", appUserDto);
			return "redirect:/login";
		} catch (PreExistingUserException e) {
			LOGGER.info("User [{}] already exists.", appUserDto.getUsername());
			model.addAttribute("user", appUserDto);
			return "redirect:/login";
		}
	}
}
