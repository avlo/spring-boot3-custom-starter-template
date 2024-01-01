package edu.mayo.lpea.cad.cadence3;

import edu.mayo.lpea.cad.cadence3.security.AppUserLocalAuthorities;
import edu.mayo.lpea.cad.cadence3.security.repository.AppUserAuthUserRepository;
import edu.mayo.lpea.cad.cadence3.security.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@AutoConfiguration
@EnableWebSecurity
@ConditionalOnBean(CustomizableAppUserService.class)
@EnableJpaRepositories(basePackages = "edu.mayo.lpea.cad.cadence3.security.repository")
//@EnableJpaRepositories
// TODO: below should not be required
@EntityScan(basePackages = "edu.mayo.lpea.cad.cadence3.security.entity")
public class SecurityCoreConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityCoreConfig.class);

	@Bean
//	@ConditionalOnMissingBean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		LOGGER.info("DB - H2 Console active at /h2-console/");
		return web -> web.ignoring().requestMatchers(PathRequest.toH2Console());
	}

	@Bean
//	@ConditionalOnMissingBean(name = {"azureAuthUserDetailsService"}) // check for azure variation
	@ConditionalOnMissingBean
	public AuthUserDetailsService authUserDetailsService(DataSource dataSource, PasswordEncoder passwordEncoder) {
		return new AuthUserDetailServiceImpl(dataSource, passwordEncoder);
	}

	@Bean
//	@ConditionalOnMissingBean(name = {"azureAuthUserService"}) // check for azure variation
	@ConditionalOnMissingBean
	public AuthUserService authUserService(
			CustomizableAppUserService customizableAppUserService,
			AuthUserDetailsService authUserDetailService,
			AppUserService appUserService,
			AppUserAuthUserRepository appUserAuthUserRepository) {
		return new AuthUserServiceImpl(customizableAppUserService, authUserDetailService, appUserService, appUserAuthUserRepository);
	}

	@Bean
	public AppUserLocalAuthorities appUserLocalAuthorities(AuthUserService authUserService) {
		return new AppUserLocalAuthorities(authUserService);
	}
}
