package com.dadamohit.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	/*@Bean
  CommandLineRunner commandLineRunner(UserRepository userRepository) {
    return args -> {
      for (int i = 1; i < 10; i++) {
        User user = new User();
        user.setLastName("LastName " + i);
        user.setFirstName("FirstName " + i);
        Address address = new Address();
        address.setCity("City " + i);
        address.setState("State " + i);
        address.setCountry("Country " + i);
        address.setZipCode(i);
        user.setAddress(address);
        userRepository.save(user);
      }
    };
  }*/

}
