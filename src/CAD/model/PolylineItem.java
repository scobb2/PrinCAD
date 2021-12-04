package CAD.model;

import CAD.fx3d.Map3d;
import CAD.util.Log;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

public class PolylineItem extends CadItem {
	
	public boolean _selectionFlag = false;
	public final CadPoint[] _points;
	
	public PolylineItem(CadPoint[] points) {
		_points = points;
	}
	
	// load circle item from string data
	public static PolylineItem load(String data) {
		
		PolylineItem item = null;
		try {
			String[] tokens = data.split(",");
			CadPoint[] points = new CadPoint[tokens.length];
			for(int i = 0; i < tokens.length; i++) {
				String temp = tokens[i].replace("(", "").replace(")", "");
				String[] pts = temp.split(" ");
				
				double x = Double.parseDouble(pts[0]);
				double y = Double.parseDouble(pts[1]);
				points[i] = new CadPoint(x, y);
			}
			item = new PolylineItem(points);
		}
		catch (Exception ex) {
			Log.error("Invalid PolylineItem data string: " + data, ex);
		}
		return item;
	}

	@Override
	public void draw(GraphicsContext gc) {
		CadPoint p1 = _points[0];
		for (int i=1; i<_points.length; i++) {
			CadPoint p2 = _points[i];
			gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
			p1 = p2;
		}
	}

	@Override
	public String save() {
		String s = "";
		
		for (int i=0; i<_points.length; i++) {
			CadPoint p = _points[i];
			s += String.format("(%1$f %2$f),", p.x, p.y);
		}
		
		return s;
	}

	@Override
	public double getXMin() {
		double min = _points[0].x;
		for (int i = 1; i < _points.length; i++) {
			if (_points[i].x < min)
				min = _points[i].x;
		}
		return min;
	}

	@Override
	public double getXMax() {
		double max = _points[0].x;
		for (int i = 1; i < _points.length; i++) {
			if (_points[i].x > max)
				max = _points[i].x;
		}
		return max;
	}

	@Override
	public double getYMin() {
		double min = _points[0].y;
		for (int i = 1; i < _points.length; i++) {
			if (_points[i].y < min)
				min = _points[i].y;
		}
		return min;
	}

	@Override
	public double getYMax() {
		double max = _points[0].y;
		for (int i = 1; i < _points.length; i++) {
			if (_points[i].y > max)
				max = _points[i].y;
		}
		return max;
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
	public PolylineItem copy() {
		PolylineItem copy = new PolylineItem(_points);
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