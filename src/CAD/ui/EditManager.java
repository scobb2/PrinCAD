package CAD.ui;

import CAD.command.DeleteCommand;
import CAD.command.PropertiesCommand;
import CAD.command.RedoCommand;
import CAD.command.ToggleSelectionCommand;
import CAD.command.UndoCommand;
import CAD.command.View3dCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class EditManager {

	// Edit commands
	private UndoCommand _undoCommand;
	private RedoCommand _redoCommand;
	private DeleteCommand _deleteCommand;
	private ToggleSelectionCommand _toggleSelectionCommand;
	private PropertiesCommand _propertiesCommand;
	private View3dCommand _view3dCommand;

	// Constructor that accepts a PrinCanvas
	public EditManager(PrinCanvas canvas) {
		_undoCommand = new UndoCommand(canvas);
		_redoCommand = new RedoCommand(canvas);
		_deleteCommand = new DeleteCommand(canvas);
		_toggleSelectionCommand = new ToggleSelectionCommand(canvas);
		_propertiesCommand = new PropertiesCommand(canvas);
		_view3dCommand = new View3dCommand(canvas);

	}

	// Build menu
	public Menu buildMenu() {
		// create menu items
		MenuItem miUndo = new MenuItem("Undo");
		miUndo.setOnAction(e -> _undoCommand.action(e));

		MenuItem miRedo = new MenuItem("Redo");
		miRedo.setOnAction(e -> _redoCommand.action(e));

		MenuItem miDelete = new MenuItem("Delete");
		miDelete.setOnAction(e -> _deleteCommand.action(e));

		MenuItem miToggleSelection = new MenuItem("Toggle Selection");
		miToggleSelection.setOnAction(e -> _toggleSelectionCommand.action(e));

		MenuItem miProperties = new MenuItem("Properties");
		miProperties.setOnAction(e -> _propertiesCommand.action(e));
		
		MenuItem miView3d = new MenuItem("View 3d");
		miView3d.setOnAction(e -> _view3dCommand.action(e));

		// create a menu
		Menu editMenu = new Menu("Edit");
		ObservableList<MenuItem> editMenuItems = editMenu.getItems();

		// add menu items to menu
		editMenuItems.add(miUndo);
		editMenuItems.add(miRedo);
		editMenuItems.add(miDelete);
		editMenuItems.add(miToggleSelection);
		editMenuItems.add(miProperties);
		editMenuItems.add(miView3d);

		return editMenu;
	}

	// Add buttons to bar
	public void addButtonsToBar(ObservableList<Node> nodes) {

		// Create buttons
		Button ub = new Button();
		ub.setMinWidth(80);
		ub.setText("Undo");
		ub.setOnAction(e -> _undoCommand.action(e));

		Button rb = new Button();
		rb.setMinWidth(80);
		rb.setText("Redo");
		rb.setOnAction(e -> _redoCommand.action(e));

		Button db = new Button();
		db.setMinWidth(80);
		db.setText("Delete");
		db.setOnAction(e -> _deleteCommand.action(e));

		Button tsb = new Button();
		tsb.setMinWidth(80);
		tsb.setText("Toggle Selection");
		tsb.setOnAction(e -> _toggleSelectionCommand.action(e));

		Button pb = new Button();
		pb.setMinWidth(80);
		pb.setText("Properties");
		pb.setOnAction(e -> _propertiesCommand.action(e));
		
		Button v3 = new Button();
		v3.setMinWidth(80);
		v3.setText("View 3d");
		v3.setOnAction(e -> _view3dCommand.action(e));

		nodes.add(ub);
		nodes.add(rb);
		nodes.add(db);
		nodes.add(tsb);
		nodes.add(pb);
		nodes.add(v3);
	}

}
