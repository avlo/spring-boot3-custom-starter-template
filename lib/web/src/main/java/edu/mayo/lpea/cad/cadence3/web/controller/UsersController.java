package edu.mayo.lpea.cad.cadence3.web.controller;

import edu.mayo.lpea.cad.cadence3.web.model.AppUserDto;
import edu.mayo.lpea.cad.cadence3.web.service.AppUserDtoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PropertySource("classpath:application.properties")
public class UsersController {
  private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
  private final AppUserDtoService appUserDtoService;

  @Value("${environmentlabel}")
  private String environmentLabel;

  @Autowired
  public UsersController(AppUserDtoService appUserDtoService) {
    this.appUserDtoService = appUserDtoService;
  }

  @Secured({ "ROLE_ANONYMOUS", "ANONYMOUS","ROLE_USER", "USER"})
  @GetMapping("/users")
  public String users(HttpServletRequest request, Model model) {
    List<AppUserDto> users = appUserDtoService.getAllAppUsersAsDto();
    LOGGER.info("Fetched users: {}", users);
    model.addAttribute("environmentlabel", environmentLabel);
    model.addAttribute("users", users);
    return "users";
  }
}
