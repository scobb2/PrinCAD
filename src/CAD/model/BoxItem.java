package CAD.model;

import CAD.fx3d.Map3d;
import CAD.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class BoxItem extends CadItem {
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Mark the member variables public final thus making the class immutable
	///////////////////////////////////////////////////////////////////////////////////////////////
	public double _x;
	public double _y;
	public double _MarkerSize;
	public boolean _selectionFlag = false;

	public BoxItem(double x, double y, double MarkerSize) {
		// Mark the member variables public final thus making the class immutable
		_x = x;
		_y = y;
		_MarkerSize = MarkerSize;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// Math to draw the tiny rect
		gc.strokeRect(_x - _MarkerSize, _y - _MarkerSize, _MarkerSize * 2, _MarkerSize * 2);
	}

	@Override
	public String save() {
		return String.format("%1$s %2$f %3$f %4$f %5$f", "BoxItem", _x, _y, _MarkerSize, this.z);
	}

	public static double load(String s) {
		// that parses the string back to the box parameters
		return Double.parseDouble(s);
	}

	@Override
	public double getXMin() {
		return _x - _MarkerSize;
	}

	@Override
	public double getXMax() {
		return _x + _MarkerSize;
	}

	@Override
	public double getYMin() {
		return _y - _MarkerSize;
	}

	@Override
	public double getYMax() {
		return _y + _MarkerSize;
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
	public BoxItem copy() {
		BoxItem copy = new BoxItem(_x, _y, _MarkerSize);
		return copy;
	}

	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DEEPSKYBLUE);
		material.setSpecularColor(Color.LIGHTPINK);
		
//		double w2 = this._w / 2.0;
//		double h2 = this._h / 2.0;
		
		Box box = new Box(map.Scale(_MarkerSize * 2), map.Scale(_MarkerSize * 2), map.Scale(_MarkerSize * 2));
		box.setMaterial(material);
		
		Xform xform = new Xform();
		xform.getChildren().add(box);xform.setTx(map.ToWorldX(_x + _MarkerSize));
		xform.setTy(map.ToWorldY(_y + _MarkerSize));
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