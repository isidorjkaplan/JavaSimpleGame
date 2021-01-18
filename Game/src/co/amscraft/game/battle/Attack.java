package co.amscraft.game.battle;

import java.util.HashMap;

import co.amscraft.game.Library;

public class Attack
{
	public Attack() {
		damage = 0;
		maxUses = 1;
		uses = 0;
		name = "Empty";
		display = "No Attack";
		distance.put(-1, false);
		distance.put(0, false);
		distance.put(1, false);
		distance.put(2, false);
		distance.put(3, false);
		distance.put(4, false);
		distance.put(5, false);
		distance.put(6, false);
		distance.put(7, false);
		distance.put(8, false);
		hitMessage = "Did not attack!";
	}

	public void playEffects()
	{
		if (!effect.equals(""))
		{
			Library.Functions.playSoundEffect(effect);
		}
	}

	public String effect = "";
	public String display;
	public String useMessage;
	public String hitMessage;
	public static HashMap<String, Attack> Attacks = new HashMap<String, Attack>();
	public double damage;
	public HashMap<Integer, Boolean> distance = new HashMap<Integer, Boolean>();
	public String name;
	public int uses;
	public int maxUses;
}
