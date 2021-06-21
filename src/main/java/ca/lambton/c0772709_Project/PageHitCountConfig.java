package ca.lambton.c0772709_Project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageHitCountConfig {

	@Bean
	public PageHitCount getPageCounter() {
		return new PageHitCount();
	}
}
