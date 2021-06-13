package com.dadamohit.userservice;

import com.dadamohit.userservice.models.Address;
import com.dadamohit.userservice.models.User;
import com.dadamohit.userservice.repositories.UserRepository;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
  RestTemplate restTemplate(){
	  return new RestTemplate();
  }

	/*@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
    return args -> {
      for (int i = 1; i < 10; i++) {
        User user = new User();
        user.setLastName("LastName " + i);
        user.setFirstName("FirstName " + i);
        user.setMedias(Arrays.asList("a","b","c"));
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
