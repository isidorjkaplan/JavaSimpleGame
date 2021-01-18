package co.amscraft.game;

import java.util.HashMap;

import javax.swing.JLabel;

import co.amscraft.game.battle.Attack;
import co.amscraft.game.battle.Sprite;

public class Unit
{
	public static HashMap<String, Unit> myUnits = new HashMap<String, Unit>();
	public static HashMap<String, Unit> deadUnits = new HashMap<String, Unit>();

	public Unit(int UnitHealth, Sprite sprite) {
		stats.put("MaxHealth", UnitHealth);
		Sprite = sprite;
		defaultUnit();
	}

	public Unit(int UnitHealth, String sprite) {
		stats.put("MaxHealth", UnitHealth);
		Sprite = new Sprite(sprite);
		defaultUnit();
	}

	private void defaultUnit()
	{
		Attack1 = Attack.Attacks.get("Empty");
		Attack2 = Attack.Attacks.get("Empty");
		Attack3 = Attack.Attacks.get("Empty");
		defeatQuote = "No. . . Must go on. .";
		name = "";
		stats.put("Speed", 0);
		stats.put("Attack", 10);
		ReflexAI = 50;
		Movement = 5;
		stats.put("Movement", 5);
		Class = Classes.list.get("Knight");
		healToFull();
	}

	public String levelQuote = "I feel stronger!!";
	private double Health;
	public int Level = 3;
	public double exp = 0;
	private Attack Attack1;
	public Classes Class;
	private Attack Attack2;
	private Attack Attack3;
	private Sprite Sprite;
	public int ReflexAI;
	public int LocationX = 1;
	public JLabel FieldSprite = new JLabel();
	public int LocationY = 1;
	public String direction = "south";
	public String name;
	public String defeatQuote;
	public int Movement = 5;
	public HashMap<String, Integer> stats = new HashMap<String, Integer>();

	public void setHealth(double health)
	{
		Health = health;
	}

	public void heal(int amount)
	{
		Health = Health + amount;
		if (Health > stats.get("MaxHealth"))
			healToFull();

	}

	public void healToFull()
	{
		Health = stats.get("MaxHealth");
		Movement = stats.get("Movement");
	}

	public void setMaxHealth(int maxHealth)
	{
		stats.put("MaxHealth", maxHealth);
	}

	public void setAttack(int slot, String attack)
	{
		if (slot == 1)
		{
			Attack1 = Attack.Attacks.get(attack);
		}
		else if (slot == 2)
		{
			Attack2 = Attack.Attacks.get(attack);
		}
		else if (slot == 3)
		{
			Attack3 = Attack.Attacks.get(attack);
		}

	}

	public Sprite getSprite()
	{
		return Sprite;
	}

	public double getHealth()
	{
		return Health;
	}

	public int getMaxHealth()
	{
		return stats.get("MaxHealth");
	}

	public Attack getAttack(int slot)
	{
		if (slot == 1)
		{
			return Attack1;
		}
		else if (slot == 2)
		{
			return Attack2;
		}
		else if (slot == 3)
		{
			return Attack3;
		}
		else
		{
			return null;
		}

	}

}
