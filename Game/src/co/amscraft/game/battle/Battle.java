package co.amscraft.game.battle;

import java.util.ArrayList;

import co.amscraft.game.Library;
import co.amscraft.game.Unit;
import co.amscraft.game.battle.ai.BattleAI;
import co.amscraft.game.gui.BattleGUI;
import co.amscraft.game.gui.GUI;

public class Battle
{
	public Unit Unit1;
	public Unit Unit2;
	private static Library Library = new Library();
	public static Battle battle = new Battle();
	private BattleAI battleAI = new BattleAI();
	public int Unit1Y;
	public int Unit2Y;
	public static boolean inBattle;

	public void startUp(Unit unit1, Unit unit2, String soundtrack, String backround)
	{
		startUp(unit1, unit2, soundtrack, 0, backround, 3);
	}

	public void startUp(Unit unit1, Unit unit2, String soundtrack, double time, String backround, int distance)
	{
		inBattle = true;
		GUI gui = new GUI();
		if (!GUI.GUI.equals("Battle"))
			gui.battleGUI();
		else
			System.out.println("In battle AI already");
		if (!GUI.GUI.equals("Battle"))
			gui.battleGUI();
		Unit1 = unit1;
		Unit2 = unit2;
		Library.setMapBackround(backround);
		Library.setSong(soundtrack, time);
		Library.clearText();
		BattleGUI.move1.setText(Unit1.getAttack(1).display);
		BattleGUI.move2.setText(Unit1.getAttack(2).display);
		BattleGUI.move3.setText(Unit1.getAttack(3).display);
		if (BattleGUI.move1.getText().equals(("No Attack")))
			BattleGUI.move1.setEnabled(false);
		if (BattleGUI.move2.getText().equals(("No Attack")))
			BattleGUI.move2.setEnabled(false);
		if (BattleGUI.move3.getText().equals(("No Attack")))
			BattleGUI.move3.setEnabled(false);

		if (distance < 4)
		{
			Unit2Y = 2;
			Unit1Y = 2 + distance;
		}
		else if (distance < 7)
		{
			Unit2Y = 1;
			Unit1Y = Unit2Y + distance;
		}
		else
		{
			Unit2Y = 1;
			Unit1Y = 6;
		}

		Library.miniIcon(Unit1.getSprite().file, Unit1Y, true);
		Library.miniIcon(Unit2.getSprite().file, Unit2Y, false);
		Library.updateHealthBar(true, Unit1);
		Library.updateHealthBar(false, Unit2);
	}

	private ArrayList<Boolean> enabled;

	public void turnStart(int move)
	{
		enabled = new ArrayList<Boolean>();
		enabled.add(BattleGUI.move1.isEnabled());
		enabled.add(BattleGUI.move2.isEnabled());
		enabled.add(BattleGUI.move3.isEnabled());
		enabled.add(BattleGUI.Up.isEnabled());
		enabled.add(BattleGUI.Down.isEnabled());
		BattleGUI.move1.setEnabled(false);
		BattleGUI.move2.setEnabled(false);
		BattleGUI.move3.setEnabled(false);
		BattleGUI.Up.setEnabled(false);
		BattleGUI.Down.setEnabled(false);
		if (Unit1.stats.get("Speed") >= Unit2.stats.get("Speed"))
		{
			int enemyMove = (battleAI.battleAI());
			myTurn(move);
			new java.util.Timer().schedule(new java.util.TimerTask()
			{
				@Override
				public void run()
				{
					if (inBattle)
					{
						if (Library.diceRoll(Unit2.ReflexAI))
							enemyTurn(battleAI.battleAI());
						else
							enemyTurn(enemyMove);

					}
				}
			}, 1000);

		}
		else
		{
			enemyTurn(battleAI.battleAI());
			new java.util.Timer().schedule(new java.util.TimerTask()
			{

				@Override
				public void run()
				{
					if (inBattle)
					{

						if (enabled.get(0))
							BattleGUI.move1.setEnabled(true);
						if (enabled.get(1))
							BattleGUI.move2.setEnabled(true);
						if (enabled.get(2))
							BattleGUI.move3.setEnabled(true);
						if (enabled.get(3))
							BattleGUI.Up.setEnabled(true);
						if (enabled.get(4))
							BattleGUI.Down.setEnabled(true);
						myTurn(move);
					}

				}

			}, 1000);
		}

	}

	public int calculateDistance()
	{
		if (Unit1Y > Unit2Y)
		{
			return Unit1Y - Unit2Y;
		}
		else
		{
			return Unit2Y - Unit1Y;
		}
	}

	public void myTurn(int attack)
	{

		if (attack < 4)
		{
			if (Unit1.getAttack(attack).distance.get(calculateDistance()))
			{

				Unit2.setHealth(
						Unit2.getHealth() - (Unit1.getAttack(attack).damage * (Unit1.stats.get("Attack") / 10)));
				Unit1.getAttack(attack).playEffects();
				Library.updateHealthBar(false, Unit2);
				if (Unit2.getHealth() < 0)
					battleEnd(true, Unit2);

			}
			else
			{
				Library.setText("", "That attack does not work at that range");
			}
		}
		else if (attack == 4)
		{
			if (!((Unit1Y - 1) == Unit2Y) && Unit1Y - 1 != 0)
			{
				Unit1Y = Unit1Y - 1;
				Library.miniIcon(Unit1.getSprite().file, Unit1Y, true);
			}
		}
		else if (attack == 5)
		{
			if (!((Unit1Y + 1) == Unit2Y) && Unit1Y + 1 != 7)
			{
				Unit1Y = Unit1Y + 1;
				Library.miniIcon(Unit1.getSprite().file, Unit1Y, true);
			}
		}
	}

	public void enemyTurn(int attack)
	{
		new java.util.Timer().schedule(new java.util.TimerTask()
		{
			@Override
			public void run()
			{
				if (inBattle)
				{
					Library.clearText();
					GUI.f.getContentPane().repaint();
					if (enabled.get(0))
						BattleGUI.move1.setEnabled(true);
					if (enabled.get(1))
						BattleGUI.move2.setEnabled(true);
					if (enabled.get(2))
						BattleGUI.move3.setEnabled(true);
					if (enabled.get(3))
						BattleGUI.Up.setEnabled(true);
					if (enabled.get(4))
						BattleGUI.Down.setEnabled(true);
					if (Unit1.getHealth() < 0)
					{
						battleEnd(false, Unit1);

					}

				}
			}
		}, 900);
		if (attack < 4)
		{
			if (Unit2.getAttack(attack).distance.get(calculateDistance()))
			{
				Unit1.setHealth(
						Unit1.getHealth() - (Unit2.getAttack(attack).damage * (Unit2.stats.get("Attack") / 10)));
				Unit2.getAttack(attack).playEffects();
				Library.updateHealthBar(true, Unit1);
				Library.setText("", Unit2.name + Unit2.getAttack(attack).hitMessage);
			}
			else
			{
				Library.setText("", Unit2.name + "'s attack missed");
			}

		}
		else if (attack == 4)
		{
			if (!((Unit2Y - 1) == Unit1Y) && Unit2Y - 1 != 0)
			{
				Unit2Y = Unit2Y - 1;
				Library.miniIcon(Unit2.getSprite().file, Unit2Y, false);
			}
		}
		else if (attack == 5)
		{
			if (!((Unit2Y + 1) == Unit1Y) && Unit2Y + 1 != 0)
			{
				Unit2Y = Unit2Y + 1;
				Library.miniIcon(Unit2.getSprite().file, Unit2Y, false);
			}

		}

	}

	private boolean battleWon;

	public void battleEnd(boolean outcome, Unit defeatedUnit)
	{
		inBattle = false;
		Library.setText(defeatedUnit.name, defeatedUnit.defeatQuote);
		battleWon = outcome;
		if (!outcome)
		{
			Library.setSong("Soliloquy_1");
			Unit.myUnits.remove(Unit1.name);
			Unit.deadUnits.put(Unit1.name, Unit1);
			FieldClass.battle.TheirUnits.put(Unit2.name, Unit2);
		}
		else
		{
			FieldClass.battle.TheirUnits.remove(Unit2.name);
			double Level = Unit1.Level;
			Level = Level + (Level - Unit2.Level);
			Unit1.exp = Unit1.exp + (50 / ((Level) * 0.2));
			if (Unit1.exp > 100)
				Unit1 = Unit1.Class.levelUp(Unit1);
			else
				System.out.println(Unit1.exp + ", " + Unit1.Level);
			Unit.myUnits.put(Unit1.name, Unit1);
		}
		BattleGUI.move1.setEnabled(false);
		BattleGUI.move2.setEnabled(false);
		BattleGUI.move3.setEnabled(false);
		BattleGUI.Up.setEnabled(false);
		BattleGUI.Down.setEnabled(false);

	}

	public boolean wonBattle()
	{
		if (battleWon && !inBattle)
			return true;
		else
			return false;

	}

}
