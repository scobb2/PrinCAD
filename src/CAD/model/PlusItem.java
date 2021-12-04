package CAD.model;

import CAD.fx3d.Map3d;
import CAD.fx3d.Xform;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;

public class PlusItem extends CadItem {
	///////////////////////////////////////////////////////////////////////////////////////////////
	// Mark the member variables public final thus making the class immutable
	///////////////////////////////////////////////////////////////////////////////////////////////
	public double _x;
	public double _y;
	public double _MarkerSize;
	public boolean _selectionFlag = false;

	public PlusItem(double x, double y, double MarkerSize) {
		// Mark the member variables public final thus making the class immutable
		_x = x;
		_y = y;
		_MarkerSize = MarkerSize;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// draws the plus
		gc.strokeLine(_x - _MarkerSize, _y, _x + _MarkerSize, _y);
		gc.strokeLine(_x, _y - _MarkerSize, _x, _y + _MarkerSize);
	}

	@Override
	public String save() {
		return String.format("%1$s %2$f %3$f %4$f %5$f", "PlusItem", _x, _y, _MarkerSize, this.z);
	}

	public static double load(String s) {
		// that parses the string back to the plus parameters
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
	public PlusItem copy() {
		PlusItem copy = new PlusItem(_x, _y, _MarkerSize);
		return copy;
	}

	@Override
	public Node get3dObject(Map3d map) {
		final PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.DARKGREEN);
		material.setSpecularColor(Color.AZURE);
		
		//double rotationAngle = 0.0 - Math.acos(_cadLine.cos) * 180.0 / Math.PI;
		
		Cylinder cylinder = new Cylinder(_MarkerSize / 12, _MarkerSize / 1.5);
		cylinder.setMaterial(material);
		cylinder.setRotationAxis(Rotate.Y_AXIS);
		cylinder.setRotate(0);
		
		Cylinder cylinder2 = new Cylinder(_MarkerSize / 12, _MarkerSize / 1.5);
		cylinder2.setMaterial(material);
		cylinder2.setRotationAxis(Rotate.Z_AXIS);
		cylinder2.setRotate(90);
		
		Cylinder cylinder3 = new Cylinder(_MarkerSize / 12, _MarkerSize / 1.5);
		cylinder3.setMaterial(material);
		cylinder3.setRotationAxis(Rotate.X_AXIS);
		cylinder3.setRotate(90);
		
		Xform xform = new Xform();
		xform.getChildren().add(cylinder);
		xform.getChildren().add(cylinder2);
		xform.getChildren().add(cylinder3);
		xform.setTx(map.ToWorldX(_x + _MarkerSize));
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