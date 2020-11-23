package shoppinglistapp.shoppinglist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import shoppinglistapp.shoppinglist.web.ItemController;
import shoppinglistapp.shoppinglist.web.MainController;
import shoppinglistapp.shoppinglist.web.SectionController;
import shoppinglistapp.shoppinglist.web.UserDetailServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;



@ExtendWith(SpringExtension.class)   // JUnit5 eli Jupiter
@SpringBootTest
public class ShoppinglistApplicationTests {

	@Autowired
	private ItemController ItemController;
	
	@Autowired
	private MainController MainController;
	
	@Autowired
	private SectionController SectionController;
	
	@Autowired
	private UserDetailServiceImpl UserDetailServiceImpl;
	
	
	
	@Test
	public void contextLoads1() {
		assertThat(ItemController).isNotNull();
	}
	
	@Test
	public void contextLoads2() {
		assertThat(MainController).isNotNull();
	}
	
	@Test
	public void contextLoads3() {
		assertThat(SectionController).isNotNull();
	}
	
	@Test
	public void contextLoads4() {
		assertThat(UserDetailServiceImpl).isNotNull();
	}

}
