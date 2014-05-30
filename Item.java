public class Item {
	private String description;
	private int weight;
	private String name;
	private boolean edible;

	public Item(String name, String description, int weight) {
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.edible = false;
	}

	public Item(String name, String description, int weight, boolean edible) {
		this(name, description, weight);
		this.edible = edible;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String getLongDescription() {
		return this.name + " : " + this.description + "(" + this.weight + ")";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEdible() {
		return edible;
	}

	public void setEdible(boolean edible) {
		this.edible = edible;
	}
}
