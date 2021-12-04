package CAD.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import CAD.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//Open file command
public class OpenFileCommand extends CommandHandler {

	// Constructor
	public OpenFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Open File Event");

		try {
			_canvas.clearItemManager();

			Stage st = new Stage();
			FileChooser fileChooser = new FileChooser();
			File selectFile = fileChooser.showOpenDialog(st);

			_canvas.setSaveLocation(selectFile.toString());

			FileReader readFile = new FileReader(selectFile);

			BufferedReader buff = new BufferedReader(readFile);

			String rawTxt = buff.readLine();

			while (rawTxt != null) {
				_canvas.loadToArr(rawTxt);
				rawTxt = buff.readLine();
			}
			try {
				buff.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// draws all the now-loaded items from the opened .txt file
		_canvas.draw();

	}
}
