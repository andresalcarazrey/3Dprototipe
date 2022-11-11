package com.politecnicomalaga.gdxbasic3d;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

/**
 * See: http://blog.xoppa.com/basic-3d-using-libgdx-2/
 * @author Xoppa
 */
public class GDXBasic3DGame implements ApplicationListener, InputProcessor {
	public PerspectiveCamera cam;
	public ModelBatch modelBatch;
	public Model model1, model2;
	public ModelInstance instance1, instance2;
	private Environment environment;
    private float camX,camY,camZ;
	private int camLookX, camLookY, camLookZ;
	private boolean bChanged = false;


	@Override
	public void create() {
		modelBatch = new ModelBatch();

		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.Fog, 0.6f, 0.8f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -0.4f, -0.7f, -0.2f));
		environment.add(new DirectionalLight().set(0.2f, 0.2f, 0.2f, 0.4f, 0.7f, 0.2f));
		cam = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camX = 10f;
		camY = 10f;
		camZ = 10f;
		camLookX = 0;
		camLookY = 0;
		camLookZ = 0;
		cam.position.set(camX, camY, camZ);
		cam.lookAt(camLookX,camLookY,camLookZ);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		ModelBuilder modelBuilder = new ModelBuilder();
		model1 = modelBuilder.createBox(5f, 5f, 5f,
				new Material(ColorAttribute.createDiffuse(Color.SKY)),
				Usage.Position | Usage.Normal);
		instance1 = new ModelInstance(model1);

		model2 = modelBuilder.createSphere(4f, 4f, 4f,10,10,
				new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				Usage.Position | Usage.Normal);

		instance2 = new ModelInstance(model2,-5.0f,2.0f,2.0f);

		Gdx.input.setInputProcessor(this);
		bChanged = false;
	}

	@Override
	public void render() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		if (bChanged) {
			bChanged = false;
			cam.position.set(camX, camY, camZ);
			cam.lookAt(camLookX,camLookY,camLookZ);
			cam.update();
		}

		modelBatch.begin(cam);
		modelBatch.render(instance1, environment);
		modelBatch.render(instance2, environment);
		modelBatch.end();
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		model1.dispose();
		model2.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown(int keycode) {

		switch (keycode) {
			case Input.Keys.A: camX += 1.0f;
				break;
			case Input.Keys.S: camZ += 1.0f;
				break;
			case Input.Keys.W: camZ -= 1.0f;
				break;
			case Input.Keys.D: camX -= 1.0f;
				break;
			case Input.Keys.Y: camLookX += 1;
				break;
			case Input.Keys.U: camLookX -= 1;
				break;
			case Input.Keys.H: camLookY += 1;
				break;
			case Input.Keys.J: camLookY -= 1;
				break;
			case Input.Keys.N: camLookZ += 1;
				break;
			case Input.Keys.M: camLookZ -= 1;
				break;


		}

		bChanged = true;
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
