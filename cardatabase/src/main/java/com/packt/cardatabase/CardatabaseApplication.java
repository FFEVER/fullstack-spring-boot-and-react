package com.packt.cardatabase;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class CardatabaseApplication {

    private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

    @Autowired
    private CarRepository carRepository;

    public static void main(String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);
        logger.info("Hello Spring Boot");
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            carRepository.save(new Car("Tesla", "Model S", "Space Grey", "DGB-3333", 2020, 30000));
            carRepository.save(new Car("Tesla", "Model X", "Black", "DGS-5555", 2018, 63000));
            carRepository.save(new Car("Tesla", "Model X", "White", "DGS-6666", 2017, 63000));
            carRepository.save(new Car("Ford", "Mustang", "Red", "AAG-1234", 2014, 59000));
            carRepository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2018, 39000));

            logger.info("findByBrandContains(): {}", carRepository.findByBrandContains("yo"));
            logger.info("findByBrandOrderByYearAsc(): {}", carRepository.findByBrandOrderByYearAsc("Tesla"));
            logger.info("findAll() with sorting: {}", carRepository.findAll(Sort.by(Sort.Direction.ASC, "brand", "model", "year")));
        };
    }

}
