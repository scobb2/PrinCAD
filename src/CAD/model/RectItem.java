package CAD.model;

import CAD.fx3d.Map3d;
import CAD.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class RectItem extends CadItem {
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Mark the member variables public final thus making the class immutable
	///////////////////////////////////////////////////////////////////////////////////////////////
	public double _x;
	public double _y;
	public double _w;
	public double _h;
	public double _depth;
	public boolean _selectionFlag = false;

	// Constructor takes in the x and y start and end values of the rect.
	public RectItem(double x, double y, double w, double h) {
		// Mark the member variables public final thus making the class immutable
		_x = x;
		_y = y;
		_w = w;
		_h = h;
		_depth = (w + h) / 2;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// draws the rect
		gc.strokeRect(_x, _y, _w, _h);
	}

	@Override
	public String save() {
		return String.format("%1$s %2$f %3$f %4$f %5$f %6$f", "RectItem", _x, _y, _w, _h, this.z);
	}

	public static double load(String s) {
		// that parses the string back to the rect parameters
		return Double.parseDouble(s);
	}

	@Override
	public double getXMin() {
		return _x;
	}

	@Override
	public double getXMax() {
		return _x + _w;
	}

	@Override
	public double getYMin() {
		return _y;
	}

	@Override
	public double getYMax() {
		return _y + _h;
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
	public RectItem copy() {
		RectItem copy = new RectItem(_x, _y, _w, _h);
		return copy;
	}

	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DEEPSKYBLUE);
		material.setSpecularColor(Color.LIGHTPINK);
		
		double w2 = this._w / 2.0;
		double h2 = this._h / 2.0;
		
		Box box = new Box(map.Scale(this._w), map.Scale(this._h), map.Scale(this._depth));
		box.setMaterial(material);
		
		Xform xform = new Xform();
		xform.getChildren().add(box);xform.setTx(map.ToWorldX(this._x + w2)); // was XLeft
		xform.setTy(map.ToWorldY(this._y + h2));	// was YTop
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