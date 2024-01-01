package edu.mayo.lpea.cad.cadence3.config;

import edu.mayo.lpea.cad.cadence3.model.entity.ExampleAzureUser;
import edu.mayo.lpea.cad.cadence3.repository.ExampleAzureUserRepository;
import edu.mayo.lpea.cad.cadence3.security.service.CustomizableAppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {ExampleAzureUserRepository.class})
@EntityScan(basePackageClasses = {ExampleAzureUser.class})
//@PropertySources({
//		@PropertySource("classpath:application.properties"),
//		@PropertySource("classpath:application.yml")
//})
public class ExampleAzureUserConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExampleAzureUserConfig.class);

	@Bean
	public CustomizableAppUserService customizableAppUserService() {
		LOGGER.info("EXAMPLE USER CONFIG - Creating ExampleAzureUser");
		return new CustomizableAppUserService(new ExampleAzureUser());
	}
}
