package ca.mcgill.ecse321.cooperator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CooperatorApplication {
  public static void main(String[] args) {
    SpringApplication.run(CooperatorApplication.class, args);
  }

  @RequestMapping("/")
  public String greeting() {
    return "Co-Op-Erator Student View Backend";
  }
}
