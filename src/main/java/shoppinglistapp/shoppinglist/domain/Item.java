package shoppinglistapp.shoppinglist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String itemname;
	
	//monella itemilla voi olla monta sama osasto
	//mutta jokaisella itemilla voi olla vain yksi osasto
	@ManyToOne
	@JsonIgnoreProperties ("items") 
	@JoinColumn(name = "sectionId")
	private Section section;


	public Item() {}
	
	public Item(String itemname, Section section) {
		super();
		this.itemname = itemname;
		this.section = section;
		
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	// toString
	@Override
	public String toString() {
		if (this.section != null)
		return "List [id=" + id + ", itemname=" + itemname +  " section =" + this.getSection() +"]";
		else
		return "List [id=" + id + ", itemname=" + itemname + "]";	
			
	}

}