package co.amscraft.cloudside;

import java.io.Serializable;

/**
 * Created by Izzy on 2017-04-23.
 */
public class Class implements Serializable {
    final int ID;
    final String name;
    public Class(int id, String name) {
        this.ID = id;
        this.name = name;
    }



}
