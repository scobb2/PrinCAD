package CAD.model;


import CAD.fx3d.Item3dInterface;
import CAD.fx3d.Map3d;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

public abstract class CadItem implements Item3dInterface{

	public abstract void draw(GraphicsContext gc);

	public abstract String save();
	
	public abstract double getXMin();
	
	public abstract double getXMax();
	
	public abstract double getYMin();
	
	public abstract double getYMax();
	
	public abstract void setSelectionFlag();
	
	public abstract boolean getSelectionFlag();

	public abstract CadItem copy();
	
	public abstract Node get3dObject(Map3d map);
	
	public double z; // elevation of objects
	
	public abstract double getElevation();
	
	public abstract void setElevation(double z);

}
