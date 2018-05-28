package com.wb.myhhdb.myhhdbimporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wb.myhhdb.myhhdbimporter.entity.BookingCSVEntityRepository;
import com.wb.myhhdb.myhhdbimporter.entity.BookingCSVEntry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Currency;
import java.util.Date;

@SpringBootApplication
public class MyhhdbImporterApplication {

	private static final Logger log = LoggerFactory.getLogger(MyhhdbImporterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MyhhdbImporterApplication.class, args);

	}

/*	@Bean
	public CommandLineRunner demo(BookingCSVEntityRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new BookingCSVEntry(new Date(), new Date(), "Maddin", "Testtext", "Testreason",
					22.01, Currency.getInstance("EUR"), -10.0, Currency.getInstance("EUR"),
					));

			// fetch all customers
			log.info("Entries found with findAll():");
			log.info("-------------------------------");
			for (BookingCSVEntry entry : repository.findAll()) {
				log.info(entry.toString());
			}
			log.info("");

		};
    }*/

}
