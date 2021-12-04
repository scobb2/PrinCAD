package CAD.command;

import CAD.control.PlusMarkerTool;
import CAD.util.Log;
import javafx.event.ActionEvent;

public class PlusMarkerCommand extends CommandHandler {

	public PlusMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);

	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Plus Marker Event");

		_canvas.setActiveTool(new PlusMarkerTool());
	}
}
