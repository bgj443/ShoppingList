package shoppinglistapp.shoppinglist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import shoppinglistapp.shoppinglist.domain.User;
import shoppinglistapp.shoppinglist.domain.UserRepository;
import shoppinglistapp.shoppinglist.domain.Item;
import shoppinglistapp.shoppinglist.domain.ItemRepository;
import shoppinglistapp.shoppinglist.domain.Section;
import shoppinglistapp.shoppinglist.domain.SectionRepository;


@SpringBootApplication
public class ShoppinglistApplication {
	private static final Logger log = LoggerFactory.getLogger(ShoppinglistApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShoppinglistApplication.class, args);

	}

	// testi datan luominen h2-tietokanta
	@Bean
	public CommandLineRunner listDemo(ItemRepository ItemRepository, UserRepository UserRepository, SectionRepository  SectionRepository) {
		return (args) -> {

			log.info("save a couple of sections");
			 SectionRepository.save(new Section("Vegetable"));
			 SectionRepository.save(new Section("Meat"));
			 SectionRepository.save(new Section("Dairy"));
			 SectionRepository.save(new Section("Frozen"));
			 SectionRepository.save(new Section("Canned goods"));
			 SectionRepository.save(new Section("Snacks"));
			 SectionRepository.save(new Section("Health and Beauty"));
			 SectionRepository.save(new Section("Personal care"));
			 SectionRepository.save(new Section("Toiletries"));
			 SectionRepository.save(new Section("Fresh Produce"));
			 SectionRepository.save(new Section("Other"));
			
			log.info("save a couple of items");
			ItemRepository.save(new Item("Maitoa", SectionRepository.findByName("Dairy").get(0))); 
			ItemRepository.save(new Item("Jauhelihaa", SectionRepository.findByName("Meat").get(0))); 
			ItemRepository.save(new Item("Pizza", SectionRepository.findByName("Frozen").get(0))); 
			ItemRepository.save(new Item("Hammastahnaa", SectionRepository.findByName("Personal care").get(0))); 
			ItemRepository.save(new Item("Hammasharja", SectionRepository.findByName("Personal care").get(0))); 
			ItemRepository.save(new Item("Sipsia", SectionRepository.findByName("Snacks").get(0))); 
			ItemRepository.save(new Item("Tomaattia", SectionRepository.findByName("Fresh Produce").get(0))); 
			ItemRepository.save(new Item("Kasvomaskeja", SectionRepository.findByName("Health and Beauty").get(0))); 
			ItemRepository.save(new Item("Pekonia", SectionRepository.findByName("Meat").get(0))); 
			ItemRepository.save(new Item("Ananasta", SectionRepository.findByName("Canned goods").get(0))); 
			ItemRepository.save(new Item("Ananasta", SectionRepository.findByName("Fresh Produce").get(0))); 
			
			log.info("save a couple of users");
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			UserRepository.save(user1);
			UserRepository.save(user2);
			
			log.info("fetch all items");
			for (Item item : ItemRepository.findAll()) {
				log.info(item.toString());
			}
			
		};
	}
}
