package edu.mayo.lpea.cad.cadence3.config;

import edu.mayo.lpea.cad.cadence3.model.entity.ExampleJpaUser;
import edu.mayo.lpea.cad.cadence3.repository.ExampleJpaUserRepository;
import edu.mayo.lpea.cad.cadence3.security.service.CustomizableAppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {ExampleJpaUserRepository.class})
@EntityScan(basePackageClasses = {ExampleJpaUser.class})
public class ExampleJpaUserConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExampleJpaUserConfig.class);

	@Bean
	public CustomizableAppUserService customizableAppUserService() {
		LOGGER.info("EXAMPLE USER CONFIG - Creating ExampleJpaUser");
		return new CustomizableAppUserService(new ExampleJpaUser());
	}
}
