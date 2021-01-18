package co.amscraft.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Library {
	public static Library library = new Library();

	/** time at last frame */
	static long lastFrame;

	/** frames per second */
	static int fps;
	/** last fps time */
	static long lastFPS;

	public void createShape(float x2, float y2, float rotation, float xLength, float yLength) {
		// R,G,B,A Set The Color To Blue One Time Only
		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		// draw quad
		GL11.glPushMatrix();
		GL11.glTranslatef(x2, y2, 0);
		GL11.glRotatef(rotation, 0f, 0f, 1f);
		GL11.glTranslatef(-x2, -y2, 0);
		xLength = xLength / 2;
		yLength = yLength / 2;
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x2 - xLength, y2 - yLength);
		GL11.glVertex2f(x2 + xLength, y2 - yLength);
		GL11.glVertex2f(x2 + xLength, y2 + yLength);
		GL11.glVertex2f(x2 - xLength, y2 + yLength);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public void buildShapes() {
		// Clear The Screen And The Depth Buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		for (int object : Entity.Objects.keySet()) {
			createShape(Entity.Objects.get(object).x, Entity.Objects.get(object).y, Entity.Objects.get(object).rotation,
					Entity.Objects.get(object).xLength, Entity.Objects.get(object).yLength);
		}

	}

}
