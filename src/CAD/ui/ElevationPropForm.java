package CAD.ui;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ElevationPropForm {
	
	// static entry point
	public static double Show(double defaultValue) {
		ElevationPropForm form = new ElevationPropForm(defaultValue);
		form.ask();
		return form._elevation;
	}
	
	Stage _stage;
	private double _elevation = 0;
	
	private ElevationPropForm(double defaultValue) {
		_elevation = defaultValue;
	}
	
	@SuppressWarnings("static-access")
	private void ask() {
		BorderPane pane = new BorderPane();
		
		Button ok = new Button("OK");
		ok.setMinWidth(80);
		ok.setOnAction(e -> action(e));
		pane.setBottom(ok);
		
		Label labelProp = new Label("Enter Elevation: ");
		TextField textProp = new TextField(((Double)_elevation).toString());
		
		GridPane grid = new GridPane();
		grid.addRow(0,  labelProp, textProp);
		grid.setColumnSpan(textProp,  2);
		grid.setHalignment(labelProp, HPos.RIGHT);
		pane.setCenter(grid);
		
		Scene scene = new Scene(pane, 500, 250);
		
		_stage = new Stage();
		_stage.setTitle("Enter Elevation");
		_stage.setScene(scene);
		_stage.showAndWait();
		String s = textProp.getText();
		
		_elevation = Double.parseDouble(s);
	}
	
	public void action(ActionEvent e) {
		_stage.close();

	}
}