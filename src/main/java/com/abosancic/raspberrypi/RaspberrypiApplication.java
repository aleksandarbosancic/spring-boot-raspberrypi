package com.abosancic.raspberrypi;

import com.abosancic.raspberrypi.entity.Customer;
import com.abosancic.raspberrypi.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class RaspberrypiApplication {

	private static final Logger log = LoggerFactory.getLogger( RaspberrypiApplication.class);

	/**
	 * Main method, used to run the application.
	 *
	 * @param args the command line arguments
	 * @throws UnknownHostException if the local host name could not be resolved into an address
	 */
	public static void main(String[] args) throws UnknownHostException
	{
		SpringApplication app = new SpringApplication(RaspberrypiApplication.class);
		Environment env = app.run( args).getEnvironment();
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\t{}://localhost:{}{}\n\t" +
						"External: \t{}://{}:{}{}\n\t" +
						"Profile(s): \t{}\n----------------------------------------------------------",
						env.getProperty("spring.application.name"),
						protocol,
						env.getProperty("server.port"),
						env.getProperty("server.servlet.context-path"),
						protocol,
						InetAddress.getLocalHost().getHostAddress(),
						env.getProperty("server.port"),
						env.getProperty("server.servlet.context-path"),
						env.getActiveProfiles());
	}

	@Bean
	public CommandLineRunner demo( CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer( "Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L)
						 .ifPresent(customer -> {
							 log.info("Customer found with findById(1L):");
							 log.info("--------------------------------");
							 log.info(customer.toString());
							 log.info("");
						 });

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
