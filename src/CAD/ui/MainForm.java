package CAD.ui;

import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
import java.net.URL;

import CAD.util.Log;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

//MainForm - the main window into the PrinCad application
public class MainForm extends Application {

	// Manager of file commands
	private FileManager _fileManager;

	// Manager of edit commands
	private EditManager _editManager;

	// Manager of CADTool commands
	private CadManager _cadManager;

	// The one and only app settings
	public static AppSettings appSettings = new AppSettingsProp();

	// static main entry into PrinCad application
	public static void main(String[] args) {

		try {
			// restore app settings
			appSettings.restore();

			// Launch the javaFX application
			Log.info("PrinCad begin execution"); // call after restoring app settings
			launch(args);
			Log.info("PrinCad end execution");

			// Save app settings
			appSettings.save();
		} catch (Exception ex) {
			Log.error("PrinCad crash with exception ", ex);
		}
	}

	// Override the start
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Create drawing canvas
		PrinCanvas canvas = new PrinCanvas(appSettings.getCanvasWidth(), appSettings.getCanvasHeight());

		// Create file manager
		_fileManager = new FileManager(canvas);

		// Create edit manager
		_editManager = new EditManager(canvas);

		// Create edit manager
		_cadManager = new CadManager(canvas);

		// Create the typical monolithic border layout
		// Attach canvas to center of layout
		BorderPane pane = new BorderPane(canvas);
		Color sceneBackgroundColor = appSettings.getSceneBackgroundColor();
		pane.setStyle(FormatStyleColor(sceneBackgroundColor));

		// Create menu bar
		MenuBar mb = new MenuBar();
		pane.setTop(mb);

		ObservableList<Menu> menus = mb.getMenus();

		// add file menu to menu bar
		Menu fileMenu = _fileManager.buildMenu();
		menus.add(fileMenu);

		// add edit menu to menu bar
		Menu editMenu = _editManager.buildMenu();
		menus.add(editMenu);

		// add Cad menu to menu bar
		Menu cadMenu = _cadManager.buildMenu();
		menus.add(cadMenu);

		// Create VBox on the right to hold file/edit buttons
		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.TOP_CENTER);
		pane.setRight(vbox);

		// Add buttons to bar
		ObservableList<Node> nodes = vbox.getChildren();
		_fileManager.addButtonsToBar(nodes);
		_editManager.addButtonsToBar(nodes);

		// Create a VBox on the left to hold cad buttons
		VBox CADvbox = new VBox(5);
		CADvbox.setPadding(new Insets(10));
		CADvbox.setAlignment(Pos.TOP_CENTER);
		pane.setLeft(CADvbox);

		// Add buttons to bar
		ObservableList<Node> CADnodes = CADvbox.getChildren();
		_cadManager.addButtonsToBar(CADnodes);

		// Create a scene, attach layout pane to scene,
		// set the initial size and background color
		Scene scene = new Scene(pane, appSettings.getSceneWidth(), appSettings.getSceneHeight(), sceneBackgroundColor);

		// Apply application styles
		File file = new File("AppStyles.css");
		if (!file.exists()) {
			Log.info(file.toString() + " does not exist");
		} else {
			URL url = file.toURI().toURL();
			scene.getStylesheets().add(url.toExternalForm());
		}

		// Attach scene to stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("CSCI 240 PrinCad Project");
		primaryStage.show();
	}

	// Format color for use with setStyle
	private String FormatStyleColor(Color color) {
		String rx = String.format("%02X", Math.round(color.getRed() * 255.0));
		String gx = String.format("%02X", Math.round(color.getGreen() * 255.0));
		String bx = String.format("%02X", Math.round(color.getBlue() * 255.0));
		String fx = "-fx-background-color: #" + rx + gx + bx + ";";
		return fx;
	}
}
