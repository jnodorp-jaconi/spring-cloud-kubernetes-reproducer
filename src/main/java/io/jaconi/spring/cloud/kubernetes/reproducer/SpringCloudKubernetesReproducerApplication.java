package io.jaconi.spring.cloud.kubernetes.reproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;

@SpringBootApplication
public class SpringCloudKubernetesReproducerApplication implements CommandLineRunner {

	@Autowired
	private ConfigurableEnvironment environment;

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudKubernetesReproducerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		environment.getPropertySources().stream()
				.filter(propertySource -> propertySource.getName().equals("composite-secrets"))
				.forEach(propertySource -> {
					System.out.println(propertySource.getName());

					if (propertySource instanceof EnumerablePropertySource<?> enumerablePropertySource) {
						for (var property : enumerablePropertySource.getPropertyNames()) {
							System.out.printf("%s: %s%n", property, enumerablePropertySource.getProperty(property));
						}
					} else {
						System.out.println("???");
					}

					System.out.printf("%n");
				});
	}
}
