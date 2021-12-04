package CAD.ui;

import java.io.PrintWriter;

import CAD.control.CanvasToolInterface;
import CAD.command.CanvasCommandInterface;
import CAD.control.CadTool;
import CAD.control.LineSelectionTool;
import CAD.control.RectSelectionTool;
import CAD.model.BoxItem;
import CAD.model.CadItem;
import CAD.model.CircleItem;
import CAD.model.EllipseItem;
import CAD.model.ExItem;
import CAD.model.LineItem;
import CAD.model.ModelManager;
import CAD.model.PlusItem;
import CAD.model.RectItem;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

//Drawing canvas for the Prin CAD tools application
public class PrinCanvas extends Canvas implements CanvasToolInterface, CanvasCommandInterface{

	public boolean newTrigger = false;
	
	public void setNewTrigger(boolean bool) {
		newTrigger = bool;
	}
	
	public boolean getNewTrigger() {
		return newTrigger;
	}

	public String saveLocation = null;
	
	public String getSaveLocation() {
		return saveLocation;
	}
	
	// Reference to ModelManager in Cad.model
	private ModelManager _model;

	public ModelManager getModelManager() {
		return _model;
	}

	// Reference to graphics context
	private GraphicsContext _gc;

	public GraphicsContext getGC() {
		return _gc;
	}

	// Current selection tool
	private CadTool _selectionTool;

	// Active tool
	private CadTool _activeTool;

	public void setActiveTool(CadTool activeTool) {
		_activeTool = activeTool;
	}

	// Data constructor
	public PrinCanvas(double width, double height) {
		// invoke (call) parent class constructor
		super(width, height);

		// Get graphics context and fill with background color
		_gc = getGraphicsContext2D();
		_gc.setFill(MainForm.appSettings.getCanvasBackgroundColor());
		_gc.fillRect(0, 0, MainForm.appSettings.getCanvasWidth(), MainForm.appSettings.getCanvasHeight());

		// Subscribe to mouse events
		setOnMousePressed(e -> onMousePressed(e));
		setOnMouseDragged(e -> onMouseDrag(e));
		setOnMouseReleased(e -> onMouseRelease(e));

		_selectionTool = new RectSelectionTool();
		_activeTool = _selectionTool;

		// Initialize the ModelManager _model variable
		_model = new ModelManager(MainForm.appSettings.getUndoBufferSize());

	}

	// Toggle selection type
	public void toggleSelectionType() {
		if (_selectionTool instanceof RectSelectionTool) {
			_selectionTool = new LineSelectionTool();
		} else {
			_selectionTool = new RectSelectionTool();
		}
		_activeTool = _selectionTool;
	}

	// Set back to selection mode
	public void reset() {
		_activeTool = new RectSelectionTool();

	}

	// Save created CAD item and set back to selection mode
	public void reset(CadItem cadItem) {
		_model.add(cadItem);
		_activeTool = _selectionTool;
	}

	// Handle mouse pressed (button down)
	private void onMousePressed(MouseEvent e) {

		_activeTool.onMousePressed(this, e);
	}

	// Handle mouse drag (only called when mouse button IS depressed)
	private void onMouseDrag(MouseEvent e) {

		_activeTool.onMouseDrag(this, e);
	}

	// Handle mouse release (button up)
	private void onMouseRelease(MouseEvent e) {

		_activeTool.onMouseRelease(this, e);
	}

	// Draw all graphic objects
	public void draw() {
		_gc.fillRect(0, 0, getWidth(), getHeight());
		_gc.setStroke(Color.ORANGERED); // comment out now that this is set in ModelManager???
		_gc.setLineWidth(0);
		_model.draw(_gc);
	}

	public void saveToFile(PrintWriter out) throws Exception {
		_model.save(out);
	}

	public void clearItemManager() {
		_model.clear();
	}

	public void setSaveLocation(String string) {
		saveLocation = string;
	}

	public void loadToArr(String rawTxt) {

		String[] splited = rawTxt.split(" ");

		switch (splited[0]) {
		case "RectItem":
			RectItem rect = new RectItem(0, 0, 0, 0);
			rect._x = RectItem.load(splited[1]);
			rect._y = RectItem.load(splited[2]);
			rect._w = RectItem.load(splited[3]);
			rect._h = RectItem.load(splited[4]);
			_model.add(rect);
			break;

		case "PolylineItem":
			// Polyline saving is still under construction, thus it can't be loaded
			break;

		case "PlusItem":
			PlusItem plus = new PlusItem(0, 0, 0);
			plus._x = PlusItem.load(splited[1]);
			plus._y = PlusItem.load(splited[2]);
			plus._MarkerSize = PlusItem.load(splited[3]);
			plus.z = PlusItem.load(splited[4]);
			_model.add(plus);
			break;

		case "LineItem":
			LineItem line = new LineItem(0, 0, 0, 0);
			line._x = LineItem.load(splited[1]);
			line._y = LineItem.load(splited[2]);
			line._xEnd = LineItem.load(splited[3]);
			line._yEnd = LineItem.load(splited[4]);
			line.z = LineItem.load(splited[5]);
			_model.add(line);
			break;

		case "ExItem":
			ExItem ex = new ExItem(0, 0, 0);
			ex._x = ExItem.load(splited[1]);
			ex._y = ExItem.load(splited[2]);
			ex._MarkerSize = ExItem.load(splited[3]);
			ex.z = ExItem.load(splited[4]);
			_model.add(ex);
			break;

		case "EllipseItem":
			EllipseItem ellipse = new EllipseItem(0, 0, 0, 0);
			ellipse._x = EllipseItem.load(splited[1]);
			ellipse._y = EllipseItem.load(splited[2]);
			ellipse._xRadius = EllipseItem.load(splited[3]);
			ellipse._yRadius = EllipseItem.load(splited[4]);
			ellipse.z = EllipseItem.load(splited[5]);
			_model.add(ellipse);
			break;

		case "CircleItem":
			CircleItem circle = new CircleItem(0, 0, 0);
			circle._x = CircleItem.load(splited[1]);
			circle._y = CircleItem.load(splited[2]);
			circle._radius = CircleItem.load(splited[3]);
			circle.z = CircleItem.load(splited[4]);
			_model.add(circle);
			break;

		case "BoxItem":
			BoxItem box = new BoxItem(0, 0, 0);
			box._x = BoxItem.load(splited[1]);
			box._y = BoxItem.load(splited[2]);
			box._MarkerSize = BoxItem.load(splited[3]);
			box.z = BoxItem.load(splited[4]);
			_model.add(box);
			break;
		}
	}
	
	// Ask for elevation and then set elevation of selected items
	public void handlePropertiesCommand() {
		double z = ElevationPropForm.Show(0.0);
		_model.setElevation(z);
	}

}
