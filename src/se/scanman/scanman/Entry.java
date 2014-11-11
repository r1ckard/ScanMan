package se.scanman.scanman;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "e")
public class Entry {

	private String additiveName;
	private String additiveRedIngredients;
	private String additiveValue;
	private String additiveYellowIngredients;

	private String allergenName;
	private String allergenRedIngredients;
	private String allergenValue;
	private String allergenYellowIngredients;

	public Entry() {
	}

	@XmlElement(name = "additive_name")
	public String getAdditiveName() {
		return additiveName;
	}

	public void setAdditiveName(final String name) {
		this.additiveName = name;
	}

	@XmlElement(name = "additive_value")
	public String getAdditiveValue() {
		return additiveValue;
	}

	public void setAdditiveValue(final String value) {
		this.additiveValue = value;
	}

	@XmlElement(name = "additive_red_ingredients")
	public String getAdditiveRedIngredients() {
		return additiveRedIngredients;
	}

	public void setAdditiveRedIngredients(final String redIngredients) {
		this.additiveRedIngredients = redIngredients;
	}

	@XmlElement(name = "additive_yellow_ingredients")
	public String getAdditiveYellowIngredients() {
		return additiveYellowIngredients;
	}

	public void setAdditiveYellowIngredients(final String yellowIngredients) {
		this.additiveYellowIngredients = yellowIngredients;
	}

	@XmlElement(name = "allergen_name")
	public String getAllergenName() {
		return allergenName;
	}

	public void setAllergenName(final String name) {
		this.allergenName = name;
	}

	@XmlElement(name = "allergen_value")
	public String getAllergenValue() {
		return allergenValue;
	}

	public void setAllergenValue(final String value) {
		this.allergenValue = value;
	}

	@XmlElement(name = "allergen_red_ingredients")
	public String getAllergenRedIngredients() {
		return allergenRedIngredients;
	}

	public void setAllergenRedIngredients(final String redIngredients) {
		this.allergenRedIngredients = redIngredients;
	}

	@XmlElement(name = "allergen_yellow_ingredients")
	public String getAllergenYellowIngredients() {
		return allergenYellowIngredients;
	}

	public void setAllergenYellowIngredients(final String yellowIngredients) {
		this.allergenYellowIngredients = yellowIngredients;
	}

}
