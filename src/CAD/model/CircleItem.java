package CAD.model;

import CAD.fx3d.Map3d;
import CAD.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class CircleItem extends CadItem {
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Mark the member variables public final thus making the class immutable
	///////////////////////////////////////////////////////////////////////////////////////////////
	public double _x;
	public double _y;
	public double _radius;
	public boolean _selectionFlag = false;


	// Constructor takes in the x and y center values and radius of a circle.
	public CircleItem(double x, double y, double radius) {
		// Mark the member variables public final thus making the class immutable
		_x = x;
		_y = y;
		_radius = radius;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// draws the circle
		gc.strokeOval(_x - _radius, _y - _radius, _radius * 2, _radius * 2);
	}

	@Override
	public String save() {
		return String.format("%1$s %2$f %3$f %4$f %5$f", "CircleItem", _x, _y, _radius, this.z);
	}

	public static double load(String s) {
		// that parses the string back to the circle parameters
		return Double.parseDouble(s);
	}

	@Override
	public double getXMin() {
		return _x - _radius;
	}

	@Override
	public double getXMax() {
		return _x + _radius;
	}

	@Override
	public double getYMin() {
		return _y - _radius;
	}

	@Override
	public double getYMax() {
		return _y + _radius;
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
	public CircleItem copy() {
		CircleItem copy = new CircleItem(_x, _y, _radius);
		return copy;
	}

	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DARKRED);
		material.setSpecularColor(Color.RED);
		
		Sphere sphere = new Sphere(map.Scale(_radius));
		sphere.setMaterial(material);
		
		Xform xform = new Xform();
		xform.getChildren().add(sphere);
		xform.setTx(map.ToWorldX(_x));
		xform.setTy(map.ToWorldY(_y));
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
