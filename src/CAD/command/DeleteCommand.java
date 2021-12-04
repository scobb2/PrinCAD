package CAD.command;

import CAD.util.Log;
import javafx.event.ActionEvent;

public class DeleteCommand extends CommandHandler {

	// Constructor
	public DeleteCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Delete Event");
		
		// iterate through every item in the Model Manager
//		for (int i = 0; i < _canvas.getModelManager().itemManager.size(); i++) {
//			// if its selection flag is true, remove it from the array
//			if (_canvas.getModelManager().itemManager.get(i).getSelectionFlag() == true) {
//				_canvas.getModelManager().itemManager.remove(i);
//			}
//		}

		_canvas.getModelManager().remove(); // deletes the selected items
		
		_canvas.draw();  // re draw the canvas after selected items have been removed
		
	}
}