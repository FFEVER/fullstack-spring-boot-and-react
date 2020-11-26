package com.packt.cardatabase;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import com.packt.cardatabase.domain.User;
import com.packt.cardatabase.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class CardatabaseApplication {

    private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);
        logger.info("Hello Spring Boot");
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Owner owner1 = new Owner("Nattaphol", "Johnas");
            Owner owner2 = new Owner("Ornprapha", "Johnas");
            Owner owner3 = new Owner("Laong", "Lady");
            List<Owner> ownerList = Arrays.asList(owner1, owner2, owner3);
            ownerRepository.saveAll(ownerList);

            Car car1 = new Car("Tesla", "Model S", "Space Grey", "DGB-3333", 2020, 30000);
            Car car2 = new Car("Tesla", "Model X", "Black", "DGS-5555", 2018, 63000);
            Car car3 = new Car("Tesla", "Model X", "White", "DGS-6666", 2017, 63000);
            Car car4 = new Car("Ford", "Mustang", "Red", "AAG-1234", 2014, 59000);
            Car car5 = new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000);
            List<Car> carList1 = Arrays.asList(car1, car2, car3);
            List<Car> carList2 = Arrays.asList(car4, car5);

            owner1.setCars(new HashSet<>(carList1));
            owner2.setCars(new HashSet<>(carList2));

            ownerRepository.save(owner1);
            ownerRepository.save(owner2);
            carRepository.saveAll(carList1);
            carRepository.saveAll(carList2);

            logger.info("findByBrandContains(): {}", carRepository.findByBrandContains("yo"));
            logger.info("findByBrandOrderByYearAsc(): {}", carRepository.findByBrandOrderByYearAsc("Tesla"));
            logger.info("findAll() with sorting: {}", carRepository.findAll(Sort.by(Sort.Direction.ASC, "brand", "model", "year")));

            // username: user, password: user
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            User user = new User("user", encoder.encode("user"), "USER");
            userRepository.save(user);

            // username: admin, password: admin
            User admin = new User("admin", encoder.encode("admin"), "ADMIN");
            userRepository.save(admin);
        };
    }

}
