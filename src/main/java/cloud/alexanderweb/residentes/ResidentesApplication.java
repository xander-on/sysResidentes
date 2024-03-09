package cloud.alexanderweb.residentes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@Configuration @EnableMongoAuditing
public class ResidentesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResidentesApplication.class, args);
	}

}
