package co.amscraft.cloudside.graphics;

import co.amscraft.cloudside.Cloudside;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Izzy on 2017-04-26.
 */
public class Screen extends JPanel implements Serializable {
    //make sure to set the name
    public Screen(final boolean HAS_ENTITIES, final boolean GAME_PAUSED) {
        this.HAS_ENTITIES = HAS_ENTITIES;
        this.GAME_PAUSED = GAME_PAUSED;
        components = new ArrayList<ScreenObject>();
    }
    public final boolean GAME_PAUSED;
    public final boolean HAS_ENTITIES;
    public void setScreen() {
        Cloudside.getInstance().setScreen(this);
    }
    public ArrayList<ScreenObject> components;
    //Stores buttons and other static screen stuff
    public ArrayList<JLabel> entity;
    // Stores the JLabel's for all the entities on the screen
    public void update() {
        for (ScreenObject component: components) {
            component.rescale();
        }
        /*
         * On every update, it must grab all the entities array list objects.
         * For each one, it makes sure the entity is still spawned, if not it is removed from the list
         * For each entity, re-calibrate its screen positon and values
         *
         * For each static component, re-calibrate its screen postions
         */
        //Cloudside.getInstance().getFrame().repaint();
    }
}
