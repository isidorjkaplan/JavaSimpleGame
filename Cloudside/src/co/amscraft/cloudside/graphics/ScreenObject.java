package co.amscraft.cloudside.graphics;

import co.amscraft.cloudside.Cloudside;

import java.awt.*;

/**
 * Created by Izzy on 2017-04-27.
 */
public class ScreenObject {
    public int X;//100 = right side 0 = left
    public int Y;//0 = bottem, 100 = top
    public Component JObject;
    public String parentScreen;
    public ScreenObject(Component component, int X, int Y, String screen) {
        this.X = X;
        this.Y = Y;
        this.JObject = component;
        this.parentScreen = screen;

    }
    public void rescale() {
        final int WIDTH = Cloudside.getInstance().getFrame().getWidth();
        final int HEIGHT = Cloudside.getInstance().getFrame().getHeight();
        final int x = (int) (WIDTH*((double) X/100));
        final int y = (int) ( HEIGHT*((double) Y/100));
        this.JObject.setLocation(x, y);

    }
}
