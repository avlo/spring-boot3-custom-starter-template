package edu.mayo.lpea.cad.cadence3;

import edu.mayo.lpea.cad.cadence3.security.service.AuthUserService;
import edu.mayo.lpea.cad.cadence3.web.service.AppUserDtoService;
import edu.mayo.lpea.cad.cadence3.web.service.AppUserDtoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;

@AutoConfiguration
@EnableWebSecurity
public class JpaSecurityConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(JpaSecurityConfig.class);

	@Bean
//	@ConditionalOnBean(name = "mvc")
//	@ConditionalOnMissingBean
	public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
		LOGGER.info("Loading JPA - Endpoint authorization configuration");
		http.authorizeHttpRequests(authorize -> authorize
//						.requestMatchers(mvc.pattern("**/**")).permitAll()
				                                        .requestMatchers(mvc.pattern("/css/**")).permitAll()
				                                        .requestMatchers(mvc.pattern("/images/**")).permitAll()
				                                        .requestMatchers(mvc.pattern("/register")).permitAll()
				                                        .requestMatchers(mvc.pattern("/users/**")).hasRole("USER")
				                                        .anyRequest().authenticated() // anyRequest() defines a rule chain for any request which did not match the previous rules
		).formLogin(form -> form
				                    .loginPage("/login")
				                    .loginProcessingUrl("/loginuser")
				                    .defaultSuccessUrl("/users")
				                    .permitAll()
		).logout(logout -> logout
				                   .logoutRequestMatcher(mvc.pattern("/logout")).permitAll()
		);
		return http.build();
	}

	@Bean
	AppUserDtoService appUserDtoService(AuthUserService authUserService) {
		return new AppUserDtoServiceImpl(authUserService);
	}
}
