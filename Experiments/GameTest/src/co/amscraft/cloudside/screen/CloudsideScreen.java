package co.amscraft.cloudside.screen;

import co.amscraft.cloudside.Cloudside;
import co.amscraft.cloudside.Entity;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Izzy on 2017-04-25.
 */
public class CloudsideScreen {
    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    org.lwjgl.opengl.Display.setDisplayMode(new DisplayMode(800, 600));
                    org.lwjgl.opengl.Display.create();

                } catch (LWJGLException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
                initGL(); // init OpenGL
                // init OpenGL her
                while (!org.lwjgl.opengl.Display.isCloseRequested()) {
                    //update(delta, 0);
                    Cloudside.getInstance().runLogic();
                    Cloudside.getInstance().getScreen().buildShapes();
                    org.lwjgl.opengl.Display.update();
                    org.lwjgl.opengl.Display.sync(60); // cap fps to 60fps
                }
                org.lwjgl.opengl.Display.destroy();
            }
        }).start();

    }


    private void initGL() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    /** time at last frame */
    private long lastFrame;

    /** frames per second */
    private int fps;
    /** last fps time */
    private long lastFPS;

    private void createEntity(Entity entity) {

        // R,G,B,A Set The Color To Blue One Time Only
        for (Shape shape: entity.shapes) {
            GL11.glColor3f(0.5f, 0.5f, 1.0f);
            // draw quad
            GL11.glPushMatrix();
            //GL11.glTranslatef(x2, y2, 0);
            //GL11.glRotatef(rotation, 0f, 0f, 1f);
            //GL11.glTranslatef(-100, -100, 0);
            GL11.glBegin(shape.shape);
            for (Vertex vertex: shape.vertices) {
                GL11.glVertex2d(vertex.X, vertex.Y);
            }

            //GL11.glVertex2f(x2 + xLength, y2 - yLength);
            //GL11.glVertex2f(x2 + xLength, y2 + yLength);
            //GL11.glVertex2f(x2 - xLength, y2 + yLength);
            GL11.glEnd();
            GL11.glPopMatrix();
        }
    }

    private void buildShapes() {
        // Clear The Screen And The Depth Buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        //Code to create a shape
        for (Entity e: Cloudside.getInstance().getEntities()) {
            this.createEntity(e);
        }


    }

    
}
