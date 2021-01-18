package co.amscraft.cloudside;


import co.amscraft.cloudside.screen.CloudsideScreen;
import co.amscraft.cloudside.screen.Shape;
import co.amscraft.cloudside.screen.Vertex;
import org.lwjgl.opengl.GL11;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Izzy on 2017-04-23.
 */
public class Cloudside {
    public static void main(String[] args) {
        Cloudside.getInstance().getScreen().start();
        Cloudside x = Cloudside.getInstance();
        Entity e = new Entity();
        Shape y = new Shape(5);
        y.vertices.add(new Vertex(50, 100));
        y.vertices.add(new Vertex(100, 50));
        y.vertices.add(new Vertex(150, 150));
        y.vertices.add(new Vertex(2000, 0));
        y.vertices.add(new Vertex(0, 0));
        e.shapes.add(y);
        x.entities.add(e);
    }

    private Cloudside() {
        this.LoadResources();
        //this.screen = new CloudsideScreen();
        this.entities = new ArrayList<Entity>();
        //Other stuff that happens on bootup
    }
    public void runLogic() {

    }
    private HashMap<Integer, Class> classes = new HashMap<Integer, Class>();
    public Class getClass(int ID) {
        return classes.get(ID);
    }
    private void LoadResources() {
        //Make sure to also load all the other stuff. Like story file instructions and whatnot
        try {
            List<String> ClassFile = Files.readAllLines(Paths.get("classes.txt"));
            for (String object: ClassFile) {
                Class fromStingObject = (Class) this.fromString(object);
                classes.put(fromStingObject.ID, fromStingObject);
            }
            //Now load other stuff like story and whatnot
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object fromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    private static Cloudside instance;
    public static Cloudside getInstance() {
        if (Cloudside.instance == null) {
            Cloudside.instance = new Cloudside();
        }
        return Cloudside.instance;
    }
    private CloudsideScreen screen;
    public CloudsideScreen getScreen() {
        if (this.screen == null) {
            this.screen = new CloudsideScreen();
        }
        return this.screen;
    }
    public ArrayList<Entity> entities;
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }


}
