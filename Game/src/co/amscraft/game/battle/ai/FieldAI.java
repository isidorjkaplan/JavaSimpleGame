package co.amscraft.game.battle.ai;

import java.util.ArrayList;
import java.util.Set;

import co.amscraft.game.*;
import co.amscraft.game.battle.Battle;
import co.amscraft.game.battle.FieldClass;
import co.amscraft.game.gui.GUI;

public class FieldAI
{
	private String unit;
	private String enemyUnit;
	public static FieldAI AI = new FieldAI();
	private boolean Running;
	private Library Library = new Library();
	private int Permutation = 0;
	private int PermutationCap = 0;
	public static boolean turn;
	private boolean movingUnit;

	@SuppressWarnings("deprecation")
	public void RunAI()
	{
		try
		{
			Thread.currentThread().setName("FieldAI");
			Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
			Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
			int threadCount = 0;
			for (Thread thread : threadArray)
			{
				if (thread.getName().equals("FieldAI") && thread.isAlive())
				{
					threadCount = threadCount + 1;
				}
			}
			if (threadCount != 1)
			{
				for (Thread thread : threadArray)
				{
					if (thread.getName().equals("FieldAI") && thread.isAlive())
					{
						thread.stop();
						System.out.println("AI Thread Count: " + threadCount);
					}
				}
			}
			movingUnit = false;
			Running = true;
			turn = true;
			crash = 0;
			unit = null;
			enemyUnit = null;
			breakLoop = false;
			Library.setMoveLock(true);
			PermutationCap = FieldClass.battle.TheirUnits.size();
			Set<String> keySet = FieldClass.battle.TheirUnits.keySet();
			if (!FieldClass.battle.TheirUnits.isEmpty())
			{
				for (String Unit : keySet)
				{

					while (Battle.inBattle || !GUI.GUI.equals("Field") || movingUnit)
					{
						try
						{
							Thread.sleep(1000);
						}
						catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					unit = Unit;
					movingUnit = true;
					breakLoop = false;
					Library.setMoveLock(true);
					Permutation = Permutation + 1;
					if (FieldClass.battle.TheirUnits.get(unit) == null)
					{
						endAITurn();
					}
					else
						AttackAI();

				}
			}
			else
			{
				System.out.println("AI has no Units");
				turn = false;
				Library.setMoveLock(false);
				FieldClass.battle.turnStart();
			}
		}
		catch (Exception e)
		{
			System.out.println("A null pointer exception prevented in AI thread");
		}

	}

	private void endAITurn()
	{
		if (Permutation >= PermutationCap)
		{
			while (Battle.inBattle)
			{
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			Unit active = FieldClass.battle.getActiveUnit();
			System.out.println("AI Turn over");
			FieldClass.battle.moveMapToLocation(active.LocationX, active.LocationY, 10, 0.1);
			turn = false;
			movingUnit = false;
			Library.setMoveLock(false);
			FieldClass.battle.turnStart();
		}
		else
		{
			System.out.println("AI Turn not yet over");
			movingUnit = false;
		}
	}

	private void AttackAI()
	{
		ArrayList<String> enemyUnits = new ArrayList<String>();
		for (String key : Unit.myUnits.keySet())
		{
			if (FieldClass.battle.getDistance(Unit.myUnits.get(key),
					FieldClass.battle.TheirUnits.get(unit)) <= FieldClass.battle.TheirUnits.get(unit).Movement)
			{
				enemyUnits.add(key);
			}
		}
		if (enemyUnits.isEmpty())
		{
			for (String key : Unit.myUnits.keySet())
			{
				enemyUnits.add(key);

			}
		}
		int unitHealth = 1000;
		enemyUnit = "";
		for (String key : enemyUnits)
		{
			if (Unit.myUnits.get(key).getHealth() < unitHealth)
			{
				enemyUnit = key;
			}
		}
		if (!enemyUnit.equals(""))
			moveToLocation(Unit.myUnits.get(enemyUnit).LocationX, Unit.myUnits.get(enemyUnit).LocationY);
		endAITurn();
	}

	private void moveToLocation(int x, int y)
	{
		Library.setMoveLock(true);
		FieldClass.battle.moveMapToLocation(FieldClass.battle.TheirUnits.get(unit).LocationX,
				FieldClass.battle.TheirUnits.get(unit).LocationY, 10, 0.1);
		while (!breakLoop)
		{
			if (FieldClass.battle.TheirUnits.get(unit).LocationX > Unit.myUnits.get(enemyUnit).LocationX)
				moveUnit(FieldClass.battle.TheirUnits.get(unit).LocationX - 1,
						FieldClass.battle.TheirUnits.get(unit).LocationY);
			else if (FieldClass.battle.TheirUnits.get(unit).LocationX < Unit.myUnits.get(enemyUnit).LocationX)
				moveUnit(FieldClass.battle.TheirUnits.get(unit).LocationX + 1,
						FieldClass.battle.TheirUnits.get(unit).LocationY);
			if (FieldClass.battle.TheirUnits.get(unit).LocationY > Unit.myUnits.get(enemyUnit).LocationY)
				moveUnit(FieldClass.battle.TheirUnits.get(unit).LocationX,
						FieldClass.battle.TheirUnits.get(unit).LocationY - 1);
			else if (FieldClass.battle.TheirUnits.get(unit).LocationY < Unit.myUnits.get(enemyUnit).LocationY)
				moveUnit(FieldClass.battle.TheirUnits.get(unit).LocationX,
						FieldClass.battle.TheirUnits.get(unit).LocationY + 1);
			attackIfRange();

		}

	}

	private void attackIfRange()
	{
		int range = FieldClass.battle.getDistance(FieldClass.battle.TheirUnits.get(unit), Unit.myUnits.get(enemyUnit));
		if (range < 4)
		{
			if (!Battle.inBattle)
			{
				BattleAI AI = new BattleAI();
				Battle.battle.Unit1 = Unit.myUnits.get(enemyUnit);
				Battle.battle.Unit2 = FieldClass.battle.TheirUnits.get(unit);
				if (range < 4)
				{
					Battle.battle.Unit2Y = 2;
					Battle.battle.Unit1Y = 2 + range;
				}
				else if (range < 7)
				{
					Battle.battle.Unit2Y = 1;
					Battle.battle.Unit1Y = Battle.battle.Unit2Y + range;
				}
				else
				{
					Battle.battle.Unit2Y = 1;
					Battle.battle.Unit1Y = 6;
				}
				int move = AI.battleAI();
				if (move == 1 || move == 2 || move == 3 || move == 4)
				{
					Running = false;
					breakLoop = true;
					FieldClass.battle.startBattle(FieldClass.battle.TheirUnits.get(unit), enemyUnit);
				}
			}
		}
	}

	private boolean breakLoop = false;
	private int crash = 0;

	private void moveUnit(int x, int y)

	{
		if (FieldClass.battle.TheirUnits.get(unit).Movement < 1 || !Running)
		{
			breakLoop = true;
		}
		else if (FieldClass.battle.checkLocation(x, y))
		{
			if (FieldClass.battle.TheirUnits.get(unit).LocationX < x)
			{
				FieldClass.battle.TheirUnits.get(unit).direction = "east";
			}
			else if (FieldClass.battle.TheirUnits.get(unit).LocationX > x)
			{
				FieldClass.battle.TheirUnits.get(unit).direction = "west";
			}
			else if (FieldClass.battle.TheirUnits.get(unit).LocationY < y)
			{
				FieldClass.battle.TheirUnits.get(unit).direction = "south";
			}
			else if (FieldClass.battle.TheirUnits.get(unit).LocationY > y)
			{
				FieldClass.battle.TheirUnits.get(unit).direction = "north";
			}
			FieldClass.battle.TheirUnits.get(unit).Movement = FieldClass.battle.TheirUnits.get(unit).Movement - 1;
			FieldClass.battle.TheirUnits.get(unit).LocationX = x;
			FieldClass.battle.TheirUnits.get(unit).LocationY = y;
			FieldClass.battle.TheirUnits.get(unit).FieldSprite
					.setIcon(Library.miniSprite(FieldClass.battle.TheirUnits.get(unit).direction, unit, false));
			FieldClass.battle.setMapToUnit(FieldClass.battle.TheirUnits.get(unit));
			try
			{
				Thread.sleep(700);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		else if (FieldClass.battle.checkForFriendlyUnit(x, y) && Running)
		{
			Running = false;
			breakLoop = true;
			FieldClass.battle.startBattle(FieldClass.battle.TheirUnits.get(unit), enemyUnit);
		}
		else
		{
			crash = crash + 1;
			if (crash > 10)
			{
				breakLoop = true;
				System.out.println("FieldAI has Crashed");
			}

		}
	}

}
