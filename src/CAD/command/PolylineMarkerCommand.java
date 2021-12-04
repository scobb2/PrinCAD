package CAD.command;

import CAD.control.PolylineMarkerTool;
import CAD.util.Log;
import javafx.event.ActionEvent;

public class PolylineMarkerCommand extends CommandHandler {

	public PolylineMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);

	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Polyline Marker Event");

		_canvas.setActiveTool(new PolylineMarkerTool());
	}
}
