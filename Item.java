public class Item {
	private String description;
	private int weight;

	public Item(String description, int weight) {
		super();
		this.description = description;
		this.weight = weight;
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
		return this.description + "(" + this.weight + ")";
	}
}