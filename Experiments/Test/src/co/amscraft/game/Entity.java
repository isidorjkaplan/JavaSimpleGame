package co.amscraft.game;

import java.util.ArrayList;
import java.util.HashMap;

public class Entity {
	/** position of quad */
	float x = 400, y = 300;
	/** angle of quad rotation */
	float rotation = 0;
	float xLength = 50, yLength = 50;
	public static HashMap<Integer, Entity> Objects = new HashMap<Integer, Entity>();
	boolean closeOnImpact = true;

}
