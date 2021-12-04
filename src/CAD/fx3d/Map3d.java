package CAD.fx3d;

public class Map3d {

	private final double _xCenter, _yCenter, _zCenter, _scale;
	
	public Map3d(double xCenter, double yCenter, double zCenter, double scale) {
		_xCenter = xCenter;
		_yCenter = yCenter;
		_zCenter = zCenter;
		_scale = scale;
	}
	
	public double ToWorldX(double xModel) {
		return Scale(xModel - _xCenter);
	}
	
	public double ToWorldY(double yModel) {
		return Scale(yModel - _yCenter);
	}
	
	public double ToWorldZ(double zModel) {
		return Scale(zModel - _zCenter);
	}
	
	public double Scale(double value) {
		return value * _scale;
	}
}
