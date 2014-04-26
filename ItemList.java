import java.util.HashMap;
import java.util.Set;


public class ItemList {
	private HashMap<String, Item> items;
	
	public ItemList()
	{
		items = new HashMap<String, Item>();
	}
	
	public void addItem(String name, String description, int weight)
	{
		this.items.put(name, new Item(name, description, weight));
	}
	
	public String getItemString()
	{
		if (!items.isEmpty())
		{
	    	String returnString = new String("");
	        Set<String> keys = items.keySet();
	        
	        for (String key : keys)
	        {
	        	returnString += "\n- " + this.items.get(key).getLongDescription();
	        }
	        
	        return returnString;
		}
		else
		{
			return "";
		}
	}
	
	public boolean hasItem(String key)
	{
		return items.containsKey(key);
	}
	
	public boolean isEmpty()
	{
		return items.isEmpty();
	}
	
	public void removeItem(String key)
	{
		items.remove(key);
	}
	
	public Item getItem(String key)
	{
		return items.get(key);
	}
	
	public void addItem(String name, Item item)
	{
		this.items.put(name, item);
	}

	public void addItem(Item item) {
		items.put(item.getName(), item);
	}
}
