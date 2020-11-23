package shoppinglistapp.shoppinglist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import shoppinglistapp.shoppinglist.domain.Section;
import shoppinglistapp.shoppinglist.domain.SectionRepository;


@ExtendWith(SpringExtension.class)   // JUnit5 eli Jupiter
@DataJpaTest
public class SectionRepositoryTest {

    @Autowired
    private SectionRepository SRepository;
	
	@Autowired
	private TestEntityManager Dell;

	@Test // testataan BookRepositoryn findByTitle()-metodin toimivuutta
	public void findByName() {
		List<Section> section = SRepository.findByName("Fresh Produce");
		assertThat(section).hasSize(1);
		assertThat(section.get(0).getName()).isEqualTo("Fresh Produce");
	}

	@Test
	public void createNewSection() {
		Section section = new Section("Testi");

		SRepository.save(section);
		assertThat(section.getSectionId()).isNotNull();
	}

	@Test
	void deleteById() {
		Section section = new Section("Testi3");
		final Long id = Dell.persistAndGetId(section, Long.class);
		SRepository.deleteById(id);
		Dell.flush();
		Section Testi_Osasto = Dell.find(Section.class, id);
		assertThat(Testi_Osasto).isNull();
	}

}

