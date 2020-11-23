package shoppinglistapp.shoppinglist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import shoppinglistapp.shoppinglist.domain.User;
import shoppinglistapp.shoppinglist.domain.UserRepository;

@ExtendWith(SpringExtension.class)   // JUnit5 eli Jupiter
@DataJpaTest
public class UserRepositoryTest {

	 @Autowired
     private UserRepository Urepository;
	
	 @Autowired
		private TestEntityManager Del;

//List<Category> category = Crepository.findByName("Horror");
	 
		@Test // testataan BookRepositoryn findByTitle()-metodin toimivuutta
		public void findByUsername() {
			User users = Urepository.findByUsername("admin");
			assertThat(users);
		}

		@Test
		public void createNewCategory() {
			User user = new User("testi", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "TESTI");
			Urepository.save(user);
			assertThat(user.getId()).isNotNull();
		}

		@Test
		void deleteById() {
			User user = new User("testi", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "TESTI");
			final Long id = Del.persistAndGetId(user, Long.class);
			Urepository.deleteById(id);
			Del.flush();
			User testi = Del.find(User.class, id);
			assertThat(testi).isNull();
		}

	}


//@Test // testataan BookRepositoryn findByTitle()-metodin toimivuutta
//public void findByName() {
	//List<Category> category = Crepository.findByName("Horror");
	//assertThat(category).hasSize(1);
	//assertThat(category.get(0).getName()).isEqualTo("Horror");
//}