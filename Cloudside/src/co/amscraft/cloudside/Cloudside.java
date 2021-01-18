package co.amscraft.cloudside;

import co.amscraft.cloudside.graphics.Screen;
import co.amscraft.cloudside.graphics.ScreenObject;

import javax.swing.*;
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
        Cloudside.getInstance();
    }

    private Cloudside() {
        Cloudside.instance = this;
        //this.LoadResources();
        //this.screen = new CloudsideScreen();
        this.frame = new JFrame("Cloudside");
        this.frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 500);
        this.frame.setLocation(300, 50);
        this.frame.setVisible(true);
        this.entities = new ArrayList<Entity>();
        this.startTic();
        //Other stuff that happens on bootup
    }
    public boolean tic;
    private void startTic() {
        //code
        tic = true;
        while (true) {
            if (tic) {
                //Code
                this.getScreen().update();
            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void setScreen(String name) {
        this.getFrame().setContentPane(this.screens.get(name));
        this.screen = name;
        this.getFrame().repaint();
    }
    public void setScreen(Screen screen) {
        if (!this.screens.containsKey(screen.getName()))
            this.screens.put(screen.getName(), screen);
        this.setScreen(screen.getName());
    }

    private String screen;
    public Screen getScreen() {
        return screens.get(this.screen);
    }
    private HashMap<Integer, Class> classes = new HashMap<Integer, Class>();
    public Class getClass(int ID) {
        return classes.get(ID);
    }

    private void LoadResources() {
        //Make sure to also load all the other stuff. Like story file instructions and whatnot
        try {
            List<String> File = Files.readAllLines(Paths.get("classes.txt"));
            for (String object: File) {
                Class fromStingObject = (Class) this.fromString(object);
                classes.put(fromStingObject.ID, fromStingObject);
            }
            File = Files.readAllLines(Paths.get("graphics.txt"));
            for (String object: File) {
                Screen fromStringObject = (Screen) this.fromString(object);
                this.screens.put(fromStringObject.getName(), fromStringObject);
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
    public ArrayList<Entity> entities;
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    private JFrame frame;
    public JFrame getFrame() {
        return this.frame;
    }
    private HashMap<String, Screen> screens = new HashMap<String, Screen>();
    public Screen getScreen(String name) {
        if (!screens.containsKey(name)) {
            System.out.println("[Warning] Screen GUI " + name + " does not exist!");
            new NullPointerException().printStackTrace();
        }
        return screens.get(name);
    }
    public int zoom; //The zoom on entities
}
