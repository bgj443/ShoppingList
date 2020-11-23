package shoppinglistapp.shoppinglist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import shoppinglistapp.shoppinglist.domain.Item;
import shoppinglistapp.shoppinglist.domain.ItemRepository;


//Test create, delete and search functionalities

//@RunWith(SpringRunner.class)  //JUnit4
@ExtendWith(SpringExtension.class) // JUnit5 eli Jupiter
@DataJpaTest
public class ItemRepositoryTest {

	@Autowired
	private ItemRepository ItemRepository;

	@Autowired
	private TestEntityManager Del;

	@Test // testataan BookRepositoryn findByTitle()-metodin toimivuutta
	public void findByTitle() {
		List<Item> items = ItemRepository.findByItemname("Maitoa");

		assertThat(items).hasSize(1);
		assertThat(items.get(0).getItemname()).isEqualTo("Maitoa");
	}

	@Test
	public void createNewCategory() {
		Item item = new Item("Talouspaperia", null);

		ItemRepository.save(item);
		assertThat(item.getId()).isNotNull();
	}

	@Test
	void deleteById() {
		Item item = new Item("Kauhujen testi", null);
		final Long id = Del.persistAndGetId(item, Long.class);
		ItemRepository.deleteById(id);
		Del.flush();
		Item after = Del.find(Item.class, id);
		assertThat(after).isNull();
	}

}