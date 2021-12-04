package CAD.ui;

import CAD.command.BoxMarkerCommand;
import CAD.command.CircleMarkerCommand;
import CAD.command.EllipseMarkerCommand;
import CAD.command.ExMarkerCommand;
import CAD.command.LineMarkerCommand;
import CAD.command.PlusMarkerCommand;
import CAD.command.PolylineMarkerCommand;
import CAD.command.RectMarkerCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class CadManager {

	// CADTool commands
	private PlusMarkerCommand _plusMarker;
	private BoxMarkerCommand _boxMarker;
	private ExMarkerCommand _exMarker;
	private CircleMarkerCommand _circleMarker;
	private LineMarkerCommand _lineMarker;
	private RectMarkerCommand _rectMarker;
	private PolylineMarkerCommand _polylineMarker;
	private EllipseMarkerCommand _ellipseMarker;

	// Constructor that accepts a PrinCanvas
	public CadManager(PrinCanvas canvas) {
		_plusMarker = new PlusMarkerCommand(canvas);
		_boxMarker = new BoxMarkerCommand(canvas);
		_exMarker = new ExMarkerCommand(canvas);
		_circleMarker = new CircleMarkerCommand(canvas);
		_lineMarker = new LineMarkerCommand(canvas);
		_rectMarker = new RectMarkerCommand(canvas);
		_polylineMarker = new PolylineMarkerCommand(canvas);
		_ellipseMarker = new EllipseMarkerCommand(canvas);
	}

	// Build menu
	public Menu buildMenu() {
		// Create menu items
		MenuItem miPlus = new MenuItem("Plus");
		miPlus.setOnAction(e -> _plusMarker.action(e));

		MenuItem miBox = new MenuItem("Box");
		miBox.setOnAction(e -> _boxMarker.action(e));

		MenuItem miEx = new MenuItem("Ex");
		miEx.setOnAction(e -> _exMarker.action(e));

		MenuItem miCircle = new MenuItem("Circle");
		miCircle.setOnAction(e -> _circleMarker.action(e));

		MenuItem miLine = new MenuItem("Line");
		miLine.setOnAction(e -> _lineMarker.action(e));

		MenuItem miRect = new MenuItem("Rect");
		miRect.setOnAction(e -> _rectMarker.action(e));

		MenuItem miPL = new MenuItem("Polyline");
		miPL.setOnAction(e -> _polylineMarker.action(e));

		MenuItem miEllipse = new MenuItem("Ellipse");
		miEllipse.setOnAction(e -> _ellipseMarker.action(e));

		Menu markerMenu = new Menu("Markers");
		ObservableList<MenuItem> markerMenuItems = markerMenu.getItems();

		// add individual marker menu items to menu
		markerMenuItems.add(miPlus);
		markerMenuItems.add(miBox);
		markerMenuItems.add(miEx);
		markerMenuItems.add(miCircle);
		markerMenuItems.add(miLine);
		markerMenuItems.add(miRect);
		markerMenuItems.add(miPL);
		markerMenuItems.add(miEllipse);

		// create a menu
		Menu cadToolMenu = new Menu("CAD Tools");
		ObservableList<MenuItem> cadToolMenuItems = cadToolMenu.getItems();

		// add menu items to menu
		cadToolMenuItems.add(markerMenu);

		return cadToolMenu;

	}

	// Add buttons to bar
	public void addButtonsToBar(ObservableList<Node> CADnodes) {

		// Create buttons
		Button pm = new Button();
		pm.setMinWidth(80);
		pm.setText("Plus Marker");
		pm.setOnAction(e -> _plusMarker.action(e));

		Button bm = new Button();
		bm.setMinWidth(80);
		bm.setText("Box Marker");
		bm.setOnAction(e -> _boxMarker.action(e));

		Button xm = new Button();
		xm.setMinWidth(80);
		xm.setText("Ex Marker");
		xm.setOnAction(e -> _exMarker.action(e));

		Button cm = new Button();
		cm.setMinWidth(80);
		cm.setText("Circle Marker");
		cm.setOnAction(e -> _circleMarker.action(e));

		Button lm = new Button();
		lm.setMinWidth(80);
		lm.setText("Line Marker");
		lm.setOnAction(e -> _lineMarker.action(e));

		Button rm = new Button();
		rm.setMinWidth(80);
		rm.setText("Rect Marker");
		rm.setOnAction(e -> _rectMarker.action(e));

		Button plm = new Button();
		plm.setMinWidth(80);
		plm.setText("Polyline Marker");
		plm.setOnAction(e -> _polylineMarker.action(e));

		Button em = new Button();
		em.setMinWidth(80);
		em.setText("Ellipse Marker");
		em.setOnAction(e -> _ellipseMarker.action(e));

		CADnodes.add(pm);
		CADnodes.add(bm);
		CADnodes.add(xm);
		CADnodes.add(cm);
		CADnodes.add(lm);
		CADnodes.add(rm);
		CADnodes.add(plm);
		CADnodes.add(em);
	}

}