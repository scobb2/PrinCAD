package CAD.ui;

import CAD.util.Log;

//import java.io.IOException;

//
//import CAD.ui.Log.LoggingLevel;
//import javafx.scene.paint.Color;
//
//// Expose app settings
//public interface AppSettings {
//
//	public void restore() throws IOException;
//
//	public void save() throws IOException;
//
//	public int getCanvasHeight();
//
//	public void setCanvasHeight(int canvasHeight);
//
//	public int getCanvasWidth();
//
//	public void setCanvasWidth(int sceneWidth);
//
//	public Color getCanvasBackgroundColor();
//
//	public void setCanvasBackgroundColor(Color bgColor);
//
//	public int getSceneHeight();
//
//	public void setSceneHeight(int sceneHeight);
//
//	public int getSceneWidth();
//
//	public void setSceneWidth(int sceneWidth);
//
//	public Color getSceneBackgroundColor();
//
//	public void setSceneBackgroundColor(Color bgColor);
//
//	public LoggingLevel getLoggingLevel();
//
//}

import javafx.scene.paint.Color;

//Expose app settings
public interface AppSettings {

	public void restore() throws Exception;

	public void save() throws Exception;

	public int getCanvasHeight();

	public void setCanvasHeight(int canvasHeight);

	public int getCanvasWidth();

	public void setCanvasWidth(int sceneWidth);

	public Color getCanvasBackgroundColor();

	public void setCanvasBackgroundColor(Color bgColor);

	public int getSceneHeight();

	public void setSceneHeight(int sceneHeight);

	public int getSceneWidth();

	public void setSceneWidth(int sceneWidth);

	public Color getSceneBackgroundColor();

	public void setSceneBackgroundColor(Color bgColor);

	public Log.LoggingLevel getLoggingLevel();

	public int getUndoBufferSize();
}
