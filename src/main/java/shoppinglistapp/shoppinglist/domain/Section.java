package shoppinglistapp.shoppinglist.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long sectionid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "section")
	@JsonIgnoreProperties("section")
	private List<Item> items;
	
	public Section() {
	}
	
	public Section(String name) {
		super();
		this.name = name;
	}

	public Long getSectionId() {
		return sectionid;
	}

	public void setSectionId(Long sectionid) {
		this.sectionid = sectionid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Section [sectionId=" + sectionid + ", name=" + name + "]";
	}
	
}