package CAD.model;

import CAD.fx3d.Map3d;
import CAD.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;

public class LineItem extends CadItem {
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Mark the member variables public final thus making the class immutable
	///////////////////////////////////////////////////////////////////////////////////////////////
	public double _x;
	public double _y;
	public double _xEnd;
	public double _yEnd;
	public boolean _selectionFlag = false;
	
	public final CadLine _cadLine;
	
	// Constructor takes in the x and y start and end values of the line.
	public LineItem(double x, double y, double xEnd, double yEnd) {
		// Mark the member variables public final thus making the class immutable
		_x = x;
		_y = y;
		_xEnd = xEnd;
		_yEnd = yEnd;
		
		_cadLine = new CadLine(_x, _y, _xEnd, _yEnd);
	}

	@Override
	public void draw(GraphicsContext gc) {
		// draws the line
		gc.strokeLine(_x, _y, _xEnd, _yEnd);
	}

	@Override
	public String save() {
		return String.format("%1$s %2$f %3$f %4$f %5$f %6$f", "LineItem", _x, _y, _xEnd, _yEnd, this.z);
	}

	public static double load(String s) {
		// that parses the string back to the line parameters
		return Double.parseDouble(s);
	}

	@Override
	public double getXMin() {
		return Math.min(_x, _xEnd);
	}

	@Override
	public double getXMax() {
		return Math.max(_x, _xEnd);
	}

	@Override
	public double getYMin() {
		return Math.min(_y, _yEnd);
	}

	@Override
	public double getYMax() {
		return Math.max(_y, _yEnd);
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
	public LineItem copy() {
		LineItem copy = new LineItem(_x, _y, _xEnd, _yEnd);
		return copy;
	}

	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DARKGREEN);
		material.setSpecularColor(Color.AZURE);
		
		double xc = (this._x + this._xEnd) / 2.0;
		double yc = (this._y + this._yEnd) / 2.0;
		double rotationAngle = 0.0 - Math.acos(_cadLine.cos) * 180.0 / Math.PI;
		
		Cylinder cylinder = new Cylinder(5, map.Scale(_cadLine.len));
		cylinder.setMaterial(material);
		cylinder.setRotationAxis(Rotate.Z_AXIS);
		cylinder.setRotate(rotationAngle);
		
		Xform xform = new Xform();
		xform.getChildren().add(cylinder);
		xform.setTx(map.ToWorldX(xc));
		xform.setTy(map.ToWorldY(yc));
		xform.setTz(map.ToWorldZ(this.z));
		
		return xform;

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