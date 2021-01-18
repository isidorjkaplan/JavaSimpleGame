package co.amscraft.cloudside.screen;

import java.util.ArrayList;

/**
 * Created by Izzy on 2017-04-25.
 */
public class Shape {
    int shape;
    public ArrayList<Vertex> vertices;
    public Shape(int shape) {
        this.shape = shape;
        this.vertices = new ArrayList<Vertex>();
    }

}
