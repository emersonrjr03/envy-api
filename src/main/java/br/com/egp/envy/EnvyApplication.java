package br.com.egp.envy;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("br.com.egp.envy.repository")
@EnableAutoConfiguration
public class EnvyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EnvyApplication.class, args);
	}

}
