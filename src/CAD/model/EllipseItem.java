package CAD.model;

import CAD.fx3d.Map3d;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

public class EllipseItem extends CadItem {
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Mark the member variables public final thus making the class immutable
	///////////////////////////////////////////////////////////////////////////////////////////////
	public Double _x;
	public double _y, _xRadius, _yRadius;
	public boolean _selectionFlag = false;

	// Default constructor, values are overwritten as the cursor is dragged after
	// the first mouse release.
	public EllipseItem() {
		_xRadius = 0;
		_yRadius = 0;
		_x = null;
		_y = 0;
	}

	// Used for printing am Ellipse from a saved file
	public EllipseItem(double x, double y, double xRadius, double yRadius) {
		_xRadius = xRadius;
		_yRadius = yRadius;
		_x = x;
		_y = y;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// draws the ellipse
		gc.strokeOval(_x - _xRadius, _y - _yRadius, _xRadius * 2, _yRadius * 2);
	}

	@Override
	public String save() {
		return String.format("%1$s %2$f %3$f %4$f %5$f %6$f", "EllipseItem", _x, _y, _xRadius, _yRadius, this.z);
	}

	public static double load(String s) {
		// that parses the string back to the ellipse parameters
		return Double.parseDouble(s);
	}

	@Override
	public double getXMin() {
		return _x - _xRadius;
	}

	@Override
	public double getXMax() {
		return _x + _xRadius;
	}

	@Override
	public double getYMin() {
		return _y - _yRadius;
	}

	@Override
	public double getYMax() {
		return _y + _yRadius;
	}

	@Override
	public void setSelectionFlag() {
		if (!_selectionFlag)
			_selectionFlag = true;
		else
			_selectionFlag = false;
	}
	
	@Override
	public boolean getSelectionFlag() {
		return _selectionFlag;
	}

	@Override
	public EllipseItem copy() {
		EllipseItem copy = new EllipseItem(_x, _y, _xRadius, _yRadius);
		return copy;
	}

	@Override
	public Node get3dObject(Map3d map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double getElevation() {
		return z;
	}

	@Override
	public void setElevation(double z) {
		this.z = z;
	}
}
