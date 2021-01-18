package co.amscraft.game;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Main {
	public static void main(String[] argv) {
		Entity e = new Entity();
		e.xLength = 10;
		e.yLength = 10;
		e.rotation = 45;
		e.y = 400;
		Entity.Objects.put(0, e);
		Entity.Objects.put(1, new Entity());
		Tic.tic.start();
	}

}
