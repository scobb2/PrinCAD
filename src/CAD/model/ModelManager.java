package CAD.model;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import CAD.fx3d.Item3dInterface;
import CAD.fx3d.Model3dInterface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ModelManager implements Model3dInterface{

///////////////////////////////////////////////////////////////////////////////////////////////
// At this point a manager class is required to encapsulate the collection of
// CAD items.

	public ArrayList<CadItem> itemManager;  //  = new ArrayList<CadItem>()

	public void add(CadItem cadItem) {
		// Save model before deletions are made in case user wants to undo the action
		saveState();
		
		itemManager.add(cadItem);
	}
	
	public void remove() {  // Called when the delete button is pressed
		// Save model before deletions are made in case user wants to undo the action
		saveState();
		
		for (int i = 0; i < itemManager.size(); i++) {
			// if its selection flag is true, remove it from the array
			if (itemManager.get(i).getSelectionFlag() == true) {
				itemManager.remove(i);
				i--;
			}
		}
	}

	public void clear() {
		itemManager.clear();
	}

	public void draw(GraphicsContext _gc) {
		for (int i = 0; i < itemManager.size(); i++) {
			// check each shapes slection flag to determine its color to draw in
			if (itemManager.get(i).getSelectionFlag() == true)
				_gc.setStroke(Color.PURPLE);
			else
				_gc.setStroke(Color.ORANGERED);
			itemManager.get(i).draw(_gc);
		}
	}

	public void save(PrintWriter out) throws Exception {
		for (int i = 0; i < itemManager.size(); i++) {
			out.println(itemManager.get(i).save());
		}
	}
	
	
	// size of undo buffer
	final private int _undoSize; 
	
	private Deque<ModelData> _undoList;
	private Deque<ModelData> _redoList;
	ModelData modelData;

	
	// Constructor
	public ModelManager(int undoBufferSize) {
		_undoSize = undoBufferSize;
		itemManager = new ArrayList<CadItem>();
		_undoList = new ArrayDeque<ModelData>(_undoSize);
		_redoList = new ArrayDeque<ModelData>(_undoSize);
	}
	
	public void saveState() {
		saveModelData();
		_redoList.clear();
	}
	
	private void saveModelData() {
		if (_undoList.size() == _undoSize) {
			_undoList.removeLast();
		}
		_undoList.push(new ModelData(itemManager));
	}


	public void undo() {
		// checks to see if there is a state to undo
		if (_undoList.size() > 0) {
			// Saves (pushes) the current model state to the redo buffer.
			_redoList.push(new ModelData(itemManager));
		
			// Pops the last (pushed) model state off the undo buffer.
			// and sets the current model state to the last model state
			itemManager = _undoList.pop().getItems();
		}
	}

	public void redo() {
		if (_redoList.size() > 0) {
			// Saves (pushes) the current model state to the undo buffer.
			_undoList.push(new ModelData(itemManager));
		
			// Pops the last (pushed) model state off the redo buffer.
			// and sets the current model state to the last model state
			itemManager = _redoList.pop().getItems();			
		}
	}

	@Override
	public Iterable<Item3dInterface> get3dItems() {

		ArrayList<Item3dInterface> items = new ArrayList<Item3dInterface>();
		for (CadItem item : itemManager) {
			items.add(item);
		}
		
		return items;
	}

	public void setElevation(double z) {
		// TODO Auto-generated method stub
		// Cycle through selected items and change their elevation value
		for (int i = 0; i < itemManager.size(); i++) {
			// if its selection flag is true...
			if (itemManager.get(i).getSelectionFlag() == true) {
				itemManager.get(i).setElevation(z); // change its elevation value
				itemManager.get(i).setSelectionFlag(); // reset the selected item to unselected
				i--;
			}
		}
	}

}
