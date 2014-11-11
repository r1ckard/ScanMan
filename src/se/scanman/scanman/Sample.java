package se.scanman.scanman;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "o")
public class Sample {

	private List<Entry> additives;
	private List<Entry> allergens;

	private String canStoreName;
	private String ingredients;
	private String processedIngredients;

	private String productName;

	private String upc;

	public Sample() {
	}

	@XmlElementWrapper(name = "additives")
	@XmlElement(name = "e")
	public List<Entry> getAdditives() {
		if (additives == null) {
			additives = new ArrayList<Entry>();
		}
		return additives;
	}

	public void setAdditives(final List<Entry> additives) {
		this.additives = additives;
	}

	@XmlElementWrapper(name = "allergens")
	@XmlElement(name = "e")
	public List<Entry> getAllergens() {
		if (allergens == null) {
			allergens = new ArrayList<Entry>();
		}
		return allergens;
	}

	public void setAllergens(final List<Entry> allergens) {
		this.allergens = allergens;
	}

	@XmlElement(name = "canStoreName")
	public String getCanStoreName() {
		return canStoreName;
	}

	public void setCanStoreName(final String canStoreName) {
		this.canStoreName = canStoreName;
	}

	@XmlElement(name = "ingredients")
	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(final String ingredients) {
		this.ingredients = ingredients;
	}

	@XmlElement(name = "processedIngredients")
	public String getProcessedIngredients() {
		return processedIngredients;
	}

	public void setProcessedIngredients(final String processedIngredients) {
		this.processedIngredients = processedIngredients;
	}

	@XmlElement(name = "product_name")
	public String getProductName() {
		return productName;
	}

	public void setProductName(final String productName) {
		this.productName = productName;
	}

	@XmlElement(name = "upc")
	public String getUpc() {
		return upc;
	}

	public void setUpc(final String upc) {
		this.upc = upc;
	}

}
