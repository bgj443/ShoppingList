package shoppinglistapp.shoppinglist.web;

import shoppinglistapp.shoppinglist.domain.Item;
import shoppinglistapp.shoppinglist.domain.ItemRepository;
import shoppinglistapp.shoppinglist.domain.SectionRepository;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@CrossOrigin
@Controller
public class ItemController {
	@Autowired
	private ItemRepository itemrepository;
	@Autowired
	private SectionRepository SectionRepository;


	// login
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	// show all items and add item
	@RequestMapping(value = "/itemlist", method = RequestMethod.GET)
	public String shoppinglist(Model model) {
		model.addAttribute("items", itemrepository.findAll());
		model.addAttribute("item", new Item());
		model.addAttribute("sections", SectionRepository.findAll());
		return "itemlist";
	}

	// Kaikki restit
	// item

	// REST get all items
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public @ResponseBody List<Item> itemListRest() {
		return (List<Item>) itemrepository.findAll();
	}

	// REST get item by id
	@RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Item> findBookRest(@PathVariable("id") Long itemId) {
		return itemrepository.findById(itemId);
	}

	// REST save new item
	@RequestMapping(value = "/items", method = RequestMethod.POST)
	public @ResponseBody Item saveItemRest(@RequestBody Item item) {
		return itemrepository.save(item);
	}

	// lisää item
	@RequestMapping(value = "/add")
	public String addItem(Model model) {
		model.addAttribute("item", new Item());
		model.addAttribute("sections", SectionRepository.findAll());

		return "additem";
	}
	

	// tallenna item
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Item item) {
		itemrepository.save(item);
		return "redirect:itemlist";
	}

	// poista item
	//@PreAuthorize("hasAuthority('ADMIN')") // tarkistaa käyttäjän ja estää poiston myös syötekentässä
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteItem(@PathVariable("id") Long itemId, Model model) {
		itemrepository.deleteById(itemId);
		return "redirect:/itemlist";
	}

	// muokkaa item
	@RequestMapping(value = "/edititem/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("item", itemrepository.findById(id));
		model.addAttribute("sections", SectionRepository.findAll());
		return "edititem";
	}

    //päivitä
    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String saveEdit(@PathVariable("id")long id, Item item, Model model) {
		item.setId(id);
		itemrepository.save(item);
		return "redirect:../itemlist";
	}
}

