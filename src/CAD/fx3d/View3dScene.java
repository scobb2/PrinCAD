package CAD.fx3d;

//modified from code found at 
//https://docs.oracle.com/javase/8/javafx/graphics-tutorial/sampleapp3d.htm#CJAHFAF
//

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class View3dScene extends Scene {

	// static entry point
	public static void Show(Model3dInterface model) {

		View3dScene scene = new View3dScene(model, new Xform(), 1024, 786);

		Stage stage = new Stage();
		stage.setTitle("My New Stage Title");
		stage.setScene(scene);
		stage.show();
	}

	final private Model3dInterface _model;
	final Xform _world;

	final Xform _axisGroup = new Xform();
	final Xform _modelGroup = new Xform();

	final PerspectiveCamera camera = new PerspectiveCamera(true);
	final Xform cameraXform = new Xform();
	final Xform cameraXform2 = new Xform();
	final Xform cameraXform3 = new Xform();
	private static final double CAMERA_INITIAL_DISTANCE = -450;
	private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
	private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
	private static final double CAMERA_NEAR_CLIP = 0.1;
	private static final double CAMERA_FAR_CLIP = 10000.0;

	private static final double AXIS_LENGTH = 250.0;

	private static final double CONTROL_MULTIPLIER = 0.1;
	private static final double SHIFT_MULTIPLIER = 10.0;
	private static final double MOUSE_SPEED = 0.1;
	private static final double ROTATION_SPEED = 2.0;
	private static final double TRACK_SPEED = 0.3;

	double mousePosX;
	double mousePosY;
	double mouseOldX;
	double mouseOldY;
	double mouseDeltaX;
	double mouseDeltaY;

	// default constructor
	private View3dScene(Model3dInterface model, Xform world, double height, double width) {
		super(world, height, width, true);

		_model = model;
		_world = world;

		buildCamera();
		buildAxes();
		// buildMolecule();
		buildModel();

		setFill(Color.GREY);
		handleKeyboard(world);
		handleMouse(world);

		setCamera(camera);
	}

	//
	// The handleMouse() method is used in the MoleculeSampleApp application to
	// handle the different 3D camera views.
	// This method is used in the Getting Started with JavaFX 3D Graphics tutorial.
	//
	private void handleMouse(final Node root) {

		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseOldX = me.getSceneX();
				mouseOldY = me.getSceneY();
			}
		});
		setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				mouseOldX = mousePosX;
				mouseOldY = mousePosY;
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseDeltaX = (mousePosX - mouseOldX);
				mouseDeltaY = (mousePosY - mouseOldY);

				double modifier = 1.0;
				double modifierFactor = 1.0;

				if (me.isControlDown()) {
					modifier = CONTROL_MULTIPLIER;
				}
				if (me.isShiftDown()) {
					modifier = SHIFT_MULTIPLIER;
				}
				if (me.isPrimaryButtonDown()) {
					cameraXform.ry.setAngle(
							cameraXform.ry.getAngle() - mouseDeltaX * modifierFactor * modifier * ROTATION_SPEED); //
					cameraXform.rx.setAngle(
							cameraXform.rx.getAngle() + mouseDeltaY * modifierFactor * modifier * ROTATION_SPEED); // -
				} else if (me.isSecondaryButtonDown()) {
					double z = camera.getTranslateZ();
					double newZ = z + mouseDeltaX * MOUSE_SPEED * modifier;
					camera.setTranslateZ(newZ);
				} else if (me.isMiddleButtonDown()) {
					cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED); // -
					cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED); // -
				}
			}
		}); // setOnMouseDragged
	} // handleMouse

	//
	// The handleKeyboard() method is used in the MoleculeSampleApp application to
	// handle the different 3D camera views.
	// This method is used in the Getting Started with JavaFX 3D Graphics tutorial.
	//
	private void handleKeyboard(final Node root) {

		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case Z:
					cameraXform2.t.setX(0.0);
					cameraXform2.t.setY(0.0);
					cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
					cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
					break;
				case X:
					_axisGroup.setVisible(!_axisGroup.isVisible());
					break;
				case V:
					_modelGroup.setVisible(!_modelGroup.isVisible());
					break;
				default:
					break;
				} // switch
			} // handle()
		}); // setOnKeyPressed
	} // handleKeyboard()

	private void buildCamera() {
		_world.getChildren().add(cameraXform);
		cameraXform.getChildren().add(cameraXform2);
		cameraXform2.getChildren().add(cameraXform3);
		cameraXform3.getChildren().add(camera);
		cameraXform3.setRotateZ(180.0);

		camera.setNearClip(CAMERA_NEAR_CLIP);
		camera.setFarClip(CAMERA_FAR_CLIP);
		camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
		cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
		cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
	}

	private void buildAxes() {
		final PhongMaterial redMaterial = new PhongMaterial();
		redMaterial.setDiffuseColor(Color.DARKRED);
		redMaterial.setSpecularColor(Color.RED);

		final PhongMaterial greenMaterial = new PhongMaterial();
		greenMaterial.setDiffuseColor(Color.DARKGREEN);
		greenMaterial.setSpecularColor(Color.GREEN);

		final PhongMaterial blueMaterial = new PhongMaterial();
		blueMaterial.setDiffuseColor(Color.DARKBLUE);
		blueMaterial.setSpecularColor(Color.BLUE);

		final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
		final Box yAxis = new Box(1, AXIS_LENGTH, 1);
		final Box zAxis = new Box(1, 1, AXIS_LENGTH);

		xAxis.setMaterial(redMaterial);
		yAxis.setMaterial(greenMaterial);
		zAxis.setMaterial(blueMaterial);

		_axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
		_axisGroup.setVisible(true);
		_world.getChildren().addAll(_axisGroup);
	}

	private void buildModel() {

		Map3d map = new Map3d(300, 300, 0, 0.25);

		Xform modelXform = new Xform();
		ObservableList<Node> modelNodes = modelXform.getChildren();

		for (Item3dInterface item : _model.get3dItems()) {
			Node node = item.get3dObject(map);
			if (node != null) {
				modelNodes.add(node);
			}
		}

		_modelGroup.getChildren().add(modelXform);
		_world.getChildren().addAll(_modelGroup);
	}
}
