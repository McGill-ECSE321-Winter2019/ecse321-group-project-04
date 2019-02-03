package ca.mcgill.ecse321.cooperator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class Ecse321GroupProject04Application {

	public static void main(String[] args) {
		SpringApplication.run(Ecse321GroupProject04Application.class, args);
	}

  @RequestMapping("/")
  public String greeting(){
    return "Co-Op-Erator";
  }
}