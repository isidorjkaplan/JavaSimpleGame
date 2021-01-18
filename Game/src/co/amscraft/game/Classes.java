package co.amscraft.game;

import java.util.ArrayList;
import java.util.HashMap;
import co.amscraft.game.battle.Attack;
import co.amscraft.game.gui.BattleGUI;

public class Classes
{
	public String name;
	public HashMap<String, Integer> stats = new HashMap<String, Integer>();
	public HashMap<String, Integer> statsCap = new HashMap<String, Integer>();
	public HashMap<Integer, String> moves = new HashMap<Integer, String>();
	public static HashMap<String, Classes> list = new HashMap<String, Classes>();

	public Classes(String Name) {
		stats.put("Movement", 0);
		stats.put("Attack", 0);
		stats.put("Speed", 0);
		stats.put("MaxHealth", 0);
		statsCap.put("Movement", 0);
		statsCap.put("Attack", 0);
		statsCap.put("Speed", 0);
		statsCap.put("MaxHealth", 0);
		name = Name;
	}

	public static boolean choosingSpell = false;

	public void selectSpell(int slot)
	{
		choosingSpell = false;
		Unit.myUnits.get(moveUnit).setAttack(slot, moves.get(Unit.myUnits.get(moveUnit).Level));
	}

	private String moveUnit;

	public Unit levelUp(Unit unit)
	{
		moveUnit = unit.name;
		System.out.println(unit.name + ", " + unit.Class.name + ", " + moves.get(unit.Level) + ", " + unit.Level);
		if (Unit.myUnits.get(unit.name) != null)
			Library.Functions.setText(unit.name, unit.levelQuote);
		if (moves.get(unit.Level) != null)
		{
			ArrayList<Attack> attacks = new ArrayList<Attack>();
			for (int selected = 1; selected < 4; selected++)
			{
				attacks.add(unit.getAttack(selected));
			}
			if (!attacks.contains(Attack.Attacks.get(moves.get(unit.Level))))
			{
				int selected = 1;
				boolean found = false;
				while (!found && selected < 4)
				{
					if (unit.getAttack(selected) == Attack.Attacks.get("Empty"))
					{
						found = true;
					}
					else
						selected = selected + 1;
				}
				if (found)
					unit.setAttack(selected, moves.get(unit.Level));
				else
				{
					if (Unit.myUnits.containsKey(unit.name))
					{
						BattleGUI.move1.setEnabled(true);
						BattleGUI.move2.setEnabled(true);
						BattleGUI.move3.setEnabled(true);
						choosingSpell = true;
						Library.Functions.setText("", "Please select a abilty to replace");
					}
					else
					{
						System.out.println(moves.get(unit.Level));
					}
				}
			}
			boolean done = false;
			while (!done)
			{
				for (String key : stats.keySet())
				{
					if (Unit.myUnits.get(unit.name) != null)
						System.out.println(key + ": " + unit.stats.get(key));
					if (Library.Functions.diceRoll(stats.get(key)))
					{
						unit.stats.put(key,
								(int) (unit.stats.get(key) + Library.Functions.randomNumber(statsCap.get(key))));
						if (Unit.myUnits.get(unit.name) != null)
							System.out.println(key + ": " + unit.stats.get(key));
						done = true;
					}
				}
			}
		}
		for (int i = 1; i < 4; i++)
		{
			if (unit.getAttack(i) == null)
			{
				unit.setAttack(i, "Empty");
			}
		}
		unit.Level = unit.Level + 1;
		unit.exp = unit.exp - 100;
		return unit;
	}
}