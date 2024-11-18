package com.packt.cardatabase;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	public static void main(String[] args) {
		// Do I restart?
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application started");
	}

	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;

	public CardatabaseApplication(CarRepository carRepository, OwnerRepository ownerRepository) {
		this.carRepository = carRepository;
		this.ownerRepository = ownerRepository;
	}

	public void run(String... args) throws Exception {

		Owner owner1 = new Owner("John", "Doe");
		Owner owner2 = new Owner("Jane", "Doe");
		ownerRepository.saveAll(Arrays.asList(owner1, owner2));
		carRepository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2023, 59000, owner1));
		carRepository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2020, 29000, owner2));
		carRepository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2022, 39000, owner2));

		for (Car car : carRepository.findAll()) {
			logger.info("Brand: {}, Model: {}", car.getBrand(), car.getModel());
		}
	}

}
