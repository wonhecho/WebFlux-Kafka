package net.zerotodev.api.consumer;

import lombok.RequiredArgsConstructor;
import net.zerotodev.api.consumer.Repository.ExampleRepository;
import net.zerotodev.api.consumer.domain.Message;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

// TEST
@SpringBootApplication
@EnableMongoAuditing  //요거랑!
@EnableReactiveMongoRepositories  //요거!
@RequiredArgsConstructor
public class ConsumerApplication implements CommandLineRunner {
	private final ExampleRepository exampleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
