package CAD.model;

import java.util.ArrayList;

public class ModelData {  
	// Collection of cad items
	final private ArrayList<CadItem> _items;
	public ArrayList<CadItem> getItems() {
		return _items;
	}
	
	// Constructor
	public ModelData(ArrayList<CadItem> itemManager) {
		_items = new ArrayList<CadItem>();
		for (CadItem item : itemManager) {
			_items.add(item.copy());
		}
	}
	
	
}
