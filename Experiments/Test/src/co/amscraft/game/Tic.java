package co.amscraft.game;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Tic {
	public static Tic tic = new Tic();
	public boolean on = true;

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		Library.lastFPS = getTime(); // call before loop to initialise fps timer

		// init OpenGL her
		while (!Display.isCloseRequested() && on == true) {
			int delta = getDelta();
			update(delta, 0);
			Collision();
			Library.library.buildShapes();
			Display.update();
			Display.sync(60); // cap fps to 60fps
		}

		Display.destroy();
	}

	/**
	 * Calculate how many milliseconds have passed since last frame.
	 * 
	 * @return milliseconds passed since last frame
	 */
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - Library.lastFrame);
		Library.lastFrame = time;

		return delta;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public void updateFPS() {
		if (getTime() - Library.lastFPS > 1000) {
			Display.setTitle("FPS: " + Library.fps);
			Library.fps = 0;
			Library.lastFPS += 1000;
		}
		Library.fps++;
	}

	public void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public void update(int delta, int entity) {
		// rotate quad
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			// Entity.Objects.get(entity).rotation += 0.15f * delta;
			Entity.Objects.get(1).rotation += 0.15f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			Entity.Objects.get(entity).x -= 0.40f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			Entity.Objects.get(entity).x += 0.40f * delta;

		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			Entity.Objects.get(entity).y -= 0.40f * delta;
		if (Keyboard.isKeyDown(Keyboard.KEY_UP))
			Entity.Objects.get(entity).y += 0.40f * delta;

		// keep quad on the screen
		if (Entity.Objects.get(entity).x < 0)
			Entity.Objects.get(entity).x = 0;
		if (Entity.Objects.get(entity).x > 800)
			Entity.Objects.get(entity).x = 800;
		if (Entity.Objects.get(entity).y < 0)
			Entity.Objects.get(entity).y = 0;
		if (Entity.Objects.get(entity).y > 600)
			Entity.Objects.get(entity).y = 600;
		Tic.tic.updateFPS(); // update FPS Counter
	    if (Entity.Objects.get(entity).x > Entity.Objects.get(1).x)
	    	Entity.Objects.get(1).x += 0.15f * delta;
	    if (Entity.Objects.get(entity).x < Entity.Objects.get(1).x)
	    	Entity.Objects.get(1).x -= 0.15f * delta;
	    if (Entity.Objects.get(entity).y > Entity.Objects.get(1).y)
	    	Entity.Objects.get(1).y += 0.15f * delta;
	    if (Entity.Objects.get(entity).y < Entity.Objects.get(1).y)
	    	Entity.Objects.get(1).y -= 0.15f * delta;
	    generateEnemies(delta);

	}
	public void generateEnemies(int delta) {
		for (int key: Entity.Objects.keySet()) {
			if (Entity.Objects.get(key) != null) {
			if ((Entity.Objects.get(key).x < 0 ||
			Entity.Objects.get(key).x > 800 || 
			Entity.Objects.get(key).y < 0 ||
			Entity.Objects.get(key).y > 600) && key != (1) && key != 0 && Entity.Objects.get(key) != null) {
				Entity.Objects.remove(key);
			}
			if (key != 1 && key != 0 && Entity.Objects.get(key) != null) {
				Entity.Objects.get(key).y += 0.5f * delta;
			}
			}
		}
		Random random = new Random();
		int r = random.nextInt(100);
		if (r > 98) {
			System.out.println("random! " + r);
			Entity e = new Entity();
			e.closeOnImpact = false;
			e.y = 0;
			e.x = random.nextInt(600);
			e.xLength = 10;
			e.yLength = 10;
			e.rotation = 45;
			boolean found = false;
			int i = 0;
			while (!found) {
				if (Entity.Objects.get(i) == null) {
					Entity.Objects.put(i, e);
					found = true;
				}
				else {
					i++;
				}
			}
		}
	}

	public boolean Collision() {
		for (Entity entity : Entity.Objects.values()) {
			for (Entity target : Entity.Objects.values()) {
				if (entity != target) {
					if ((entity.x > (target.x - (target.xLength / 2)) && entity.x < (target.x + (target.xLength / 2))
							&& entity.y > (target.y - (target.yLength / 2))
							&& entity.y < (target.y + (target.yLength / 2)))) {
						System.out.println("Collission");
						if (entity.closeOnImpact == true || target.closeOnImpact == true) {
							// System.exit(0);
							on = false;
						}
					}
				}
			}
		}
		return false;

	}
}
