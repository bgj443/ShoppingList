package shoppinglistapp.shoppinglist.web;

import shoppinglistapp.shoppinglist.domain.ItemRepository;
import shoppinglistapp.shoppinglist.domain.Section;
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
public class SectionController {

	@Autowired
	private SectionRepository sectionRepository;

	// Kaikki restit
	// kategoria

	// REST service to get all departments
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody List<Section> getSectionsRest() {
		return (List<Section>) sectionRepository.findAll();
	}

	// REST service to get department by id
	@RequestMapping(value = "/sections/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Section> findSectionRest(@PathVariable("id") Long dId) {
		return sectionRepository.findById(dId);
	}

	// REST service to save new department
	@RequestMapping(value = "/sections", method = RequestMethod.POST)
	public @ResponseBody Section saveSectionRest(@RequestBody Section section) {
		return sectionRepository.save(section);
	}

	// näytä kaikki osastot
	@RequestMapping(value = "/sectionlist", method = RequestMethod.GET)
	public String sectionList(Model model) {
		model.addAttribute("sections", sectionRepository.findAll());
		model.addAttribute("Section", new Section());
		return "sectionlist";
	}

	// muokkaa osastoa
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editsection/{sectionid}")
	public String editSection(@PathVariable("sectionid") Long sectionid, Model model) {
		model.addAttribute("Section", sectionRepository.findById(sectionid));
		
		return "editsection";
	}

	// poista osasto
	@PreAuthorize("hasAuthority('ADMIN')") // tarkistaa käyttäjän ja estää poiston myös syötekentässä
	@RequestMapping(value = "/deletesection/{sectionid}", method = RequestMethod.GET)
	public String deleteSection(@PathVariable("sectionid") Long sectionid, Model model) {
		sectionRepository.deleteById(sectionid);
		return "redirect:/sectionlist";
	}
	
	

	// lisää osasto
	@RequestMapping(value = "/addsection")
	public String addSection(Model model) {
		model.addAttribute("Section", new Section());
		model.addAttribute("sections", sectionRepository.findAll());
		return "addsection";
	}

	// tallenna osasto
	@RequestMapping(value = "/saveg", method = RequestMethod.POST)
	public String savesection(Section section) {
		sectionRepository.save(section);
		return "redirect:sectionlist";
	}

}
