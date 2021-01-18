package co.amscraft.game.battle;

import co.amscraft.game.Library;
import co.amscraft.game.Unit;
import co.amscraft.game.battle.ai.FieldAI;
import co.amscraft.game.gui.BattleGUI;
import co.amscraft.game.gui.Counter;
import co.amscraft.game.gui.FieldGUI;
import co.amscraft.game.gui.GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FieldClass
{
	public static FieldClass battle = new FieldClass();
	public FieldGUI gui = new FieldGUI();
	public Library Library = new Library();
	private Maps maps = new Maps();
	public int SquareSize;
	private String mapName;
	public String song;
	public static boolean inBattle = false;
	public static Counter counter = new Counter();
	private String Objective;
	private Unit boss;
	public double MapX = 0;
	public double MapY = 0;
	public String backround;
	public boolean talking = false;
	public HashMap<String, Unit> TheirUnits = new HashMap<String, Unit>();

	public boolean checkObjectives()
	{
		boolean Return = false;
		if (Objective.equals("Kill All"))
		{
			if (TheirUnits.isEmpty())
			{
				Return = true;
			}
		}
		else if (Objective.equals("Boss"))
		{
			if (!TheirUnits.containsValue(boss))
			{
				Return = true;
			}
		}
		return Return;
	}

	public void setObjective(String objective, Unit unit)
	{
		if (objective.equals("Boss"))
		{
			Objective = objective;
			boss = unit;
		}
		else if (objective.equals("Kill All"))
		{
			Objective = objective;
		}
	}

	public void start(String map)
	{
		start(map, new ArrayList<Unit>());
	}

	public void start(String map, ArrayList<Unit> enemyUnits)
	{
		talking = false;
		Objective = "Kill All";
		if (!enemyUnits.isEmpty())
		{
			for (Unit unit : enemyUnits)
			{
				TheirUnits.put(unit.name, unit);
			}
		}
		maps.initiateMap(map);
		gui.buildField();
		Library.setMapBackround(backround);
		mapName = map;
		inBattle = true;
		healUnits("Both");
		Library.updateHealthBar(true, getActiveUnit());
		Library.setSong(song);
		Library.clearText();
		Library.clearSprite();
		possibleMovesCheck();
		counter.startTimer(0.1);

	}

	private boolean won = false;

	@SuppressWarnings("null")
	public boolean wonBattle()
	{
		if (!inBattle && won)
		{
			return true;
		}
		else if (!inBattle && !won)
		{
			return false;
		}
		else
		{
			return (Boolean) null;
		}
	}

	public void resumeField()
	{
		if (Unit.myUnits.isEmpty())
		{
			System.out.println("You lost!");
			System.exit(0);
			inBattle = false;
			Library.runStory();
			won = false;
		}
		talking = false;
		buildField();
		Library.clearSprite();
		Library.clearText();
		startSong();
		Library.updateHealthBar(true, getActiveUnit());
		possibleMovesCheck();
		if (checkObjectives())
		{
			Library.runStory();
		}
	}

	public void startSong()
	{
		Library.setSong(song, counter.getTime());
	}

	public void turnStart()
	{
		for (String unit : Unit.myUnits.keySet())
		{
			Unit.myUnits.get(unit).Movement = Unit.myUnits.get(unit).stats.get("Movement");
		}
		for (String unit : TheirUnits.keySet())
		{
			TheirUnits.get(unit).Movement = TheirUnits.get(unit).stats.get("Movement");
		}
		Unit unit = getActiveUnit();
		setMapToUnit(unit);
		possibleMovesCheck();
	}

	public void healUnits(String whoes)
	{
		if (whoes.equals("Mine") || whoes.equals("Both"))
		{
			for (String unit : Unit.myUnits.keySet())
			{
				Unit.myUnits.get(unit).healToFull();
			}
		}
		else if (whoes.equals("Theirs") || whoes.equals("Both"))
		{
			for (String unit : TheirUnits.keySet())
			{
				TheirUnits.get(unit).healToFull();
			}
		}
	}

	public void addEnemyUnit(Unit unit)
	{
		TheirUnits.put(unit.name, unit);
		buildField();
	}

	public void buildField()
	{
		gui.buildField();
		maps.initiateMap(mapName);
	}

	public void moveEnemyUnit(int x, int y, Unit unit)
	{

	}

	public Unit getActiveUnit()
	{
		return Unit.myUnits.get(FieldGUI.units.getSelectedItem().toString());
	}

	public void moveUnit(int x, int y, String unit)
	{
		System.out.println(x + ", " + y);
		if (Unit.myUnits.get(unit).LocationX < x)
		{
			Unit.myUnits.get(unit).direction = "east";
		}
		else if (Unit.myUnits.get(unit).LocationX > x)
		{
			Unit.myUnits.get(unit).direction = "west";
		}
		else if (Unit.myUnits.get(unit).LocationY < y)
		{
			Unit.myUnits.get(unit).direction = "south";
		}
		else if (Unit.myUnits.get(unit).LocationY > y)
		{
			Unit.myUnits.get(unit).direction = "north";
		}
		if (checkLocation(x, y))
		{
			Unit.myUnits.get(unit).LocationX = x;
			Unit.myUnits.get(unit).LocationY = y;
			setMapToLocation(x, y);
			Unit.myUnits.get(unit).FieldSprite
					.setIcon(Library.miniSprite(Unit.myUnits.get(unit).direction, unit, true));
			GUI.f.repaint();
			possibleMovesCheck(x, y);
			Unit.myUnits.get(unit).Movement = Unit.myUnits.get(unit).Movement - 1;

		}
		else if (checkForEnemyUnit(x, y))
		{
			startBattle(TheirUnits.get(getEnemyUnit(x, y)));
		}

	}

	public void startBattle(Unit enemy, String myUnit)
	{
		Battle.battle = new Battle();
		faceUnits(myUnit, enemy.name);
		enemy = TheirUnits.get(enemy.name);
		Unit unit = Unit.myUnits.get(myUnit);
		try
		{
			Battle.battle.startUp(unit, enemy, song, counter.getTime(), backround, getDistance(unit, enemy));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			BattleGUI gui = new BattleGUI();
			try
			{
				gui.battleScene();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Battle.battle.startUp(unit, enemy, song, counter.getTime(), backround, getDistance(unit, enemy));
		}
		// Fix for optional backround later
	}

	public void startBattle(Unit enemy)
	{

		String unit = getActiveUnit().name;
		startBattle(enemy, unit);
	}

	private boolean checkForUnit(int x, int y)
	{
		boolean Return = true;
		if (Return)
		{
			for (String unit : TheirUnits.keySet())
			{
				if (TheirUnits.get(unit).LocationX == x && TheirUnits.get(unit).LocationY == y)
				{
					Return = false;
					break;
				}
			}
			if (Return)
			{
				for (String unit : Unit.myUnits.keySet())
				{
					if (Unit.myUnits.get(unit).LocationX == x && Unit.myUnits.get(unit).LocationY == y)
					{
						Return = false;
						break;
					}
				}
			}
		}
		return !Return;
	}

	private boolean checkForEnemyUnit(int x, int y)
	{
		boolean Return = true;

		for (String unit : TheirUnits.keySet())
		{
			if (TheirUnits.get(unit).LocationX == x && TheirUnits.get(unit).LocationY == y)
			{
				Return = false;
				break;
			}
		}
		return !Return;
	}

	public boolean checkForFriendlyUnit(int x, int y)
	{
		boolean Return = true;
		for (String key : Unit.myUnits.keySet())
		{
			if (Unit.myUnits.get(key).LocationX == x && Unit.myUnits.get(key).LocationY == y)
			{
				Return = false;
				break;
			}
		}
		return !Return;
	}

	private String getEnemyUnit(int x, int y)
	{

		String Return = "";
		for (String unit : TheirUnits.keySet())
		{
			if (TheirUnits.get(unit).LocationX == x && TheirUnits.get(unit).LocationY == y)
			{
				Return = unit;
				break;
			}
		}
		return Return;
	}

	public String getFriendlyUnit(int x, int y)
	{

		String Return = "";
		for (String key : Unit.myUnits.keySet())
		{
			if (Unit.myUnits.get(key).LocationX == x && Unit.myUnits.get(key).LocationY == y)
			{
				Return = key;
				break;
			}
		}
		return Return;
	}

	public String getEnemyUnit(String unitName)
	{
		String Return = "";
		for (String unit : TheirUnits.keySet())
		{
			if (TheirUnits.get(unit).name.equals(unitName))
			{
				Return = unit;
			}

		}
		return Return;
	}

	private void possibleMovesCheck(int x, int y)
	{
		if (getActiveUnit().Movement > 0)
		{
			if (!checkLocation(x - 1, y) && !checkForEnemyUnit(x - 1, y))
				FieldGUI.West.setEnabled(false);
			else
				FieldGUI.West.setEnabled(true);
			if (!checkLocation(x + 1, y) && !checkForEnemyUnit(x + 1, y))
				FieldGUI.East.setEnabled(false);
			else
				FieldGUI.East.setEnabled(true);
			if (!checkLocation(x, y - 1) && !checkForEnemyUnit(x, y - 1))
				FieldGUI.North.setEnabled(false);
			else
				FieldGUI.North.setEnabled(true);
			if (!checkLocation(x, y + 1) && !checkForEnemyUnit(x, y + 1))
				FieldGUI.South.setEnabled(false);
			else
				FieldGUI.South.setEnabled(true);
			boolean units = false;
			FieldGUI.battle.removeAllItems();
			for (String unit : TheirUnits.keySet())
			{
				if (getDistance(getActiveUnit(), TheirUnits.get(unit)) < 4)
				{
					units = true;
					FieldGUI.battle.addItem(TheirUnits.get(unit).name);
					FieldGUI.battle.setEnabled(true);

				}
			}
			if (!units)
			{
				FieldGUI.battle.setEnabled(false);
			}
		}
		else
		{
			FieldGUI.West.setEnabled(false);
			FieldGUI.North.setEnabled(false);
			FieldGUI.East.setEnabled(false);
			FieldGUI.South.setEnabled(false);
			boolean end = true;
			for (String unit : Unit.myUnits.keySet())
			{
				if (Unit.myUnits.get(unit).Movement > 0)
				{
					end = false;
				}

			}
			if (end)
			{
				new java.util.Timer().schedule(new java.util.TimerTask()
				{
					@Override
					public void run()
					{
						FieldAI.AI.RunAI();
					}
				}, 1);
			}
		}
		if (Library.movesLocked)
		{
			Library.setMoveLock(true);
		}

		GUI.f.getContentPane().repaint();
	}

	public int getDistance(Unit unit1, Unit unit2)
	{
		int X = 0;
		int Y = 0;
		if (unit1.LocationX > unit2.LocationX)
		{
			X = unit1.LocationX - unit2.LocationX;
		}
		else
		{
			X = unit2.LocationX - unit1.LocationX;
		}
		if (unit1.LocationY > unit2.LocationY)
		{
			Y = unit1.LocationY - unit2.LocationY;
		}
		else
		{
			Y = unit2.LocationY - unit1.LocationY;
		}
		return X + Y;

	}

	public void possibleMovesCheck()
	{
		possibleMovesCheck(getActiveUnit().LocationX, getActiveUnit().LocationY);
	}

	public boolean checkLocation(int x, int y)
	{
		boolean Return;
		try
		{
			Return = Maps.permissions.get(x + ", " + y);
		}
		catch (NullPointerException e)
		{
			Return = true;
		}
		if (Return)
			Return = !checkForUnit(x, y);
		return Return;
	}

	public void placeUnits()
	{

		FieldGUI.units.removeAllItems();

		for (String key : Unit.myUnits.keySet())
		{
			FieldGUI.units.addItem(Unit.myUnits.get(key).name);
			GUI.jp.add(Unit.myUnits.get(key).FieldSprite);
			Unit.myUnits.get(key).FieldSprite.setLocation(
					(Unit.myUnits.get(key).LocationX * SquareSize) + (int) (MapX * SquareSize),
					(Unit.myUnits.get(key).LocationY * SquareSize) + (int) (MapY * SquareSize));
			Unit.myUnits.get(key).FieldSprite.setSize((int) (SquareSize * 0.8), (int) (SquareSize * 1.2));
			Unit.myUnits.get(key).FieldSprite.setIcon(Library.miniSprite(Unit.myUnits.get(key).direction, key, true));

		}
		for (String unit : TheirUnits.keySet())
		{
			GUI.jp.add(TheirUnits.get(unit).FieldSprite);
			TheirUnits.get(unit).FieldSprite.setLocation(
					(TheirUnits.get(unit).LocationX * SquareSize) + (int) (MapX * SquareSize),
					(TheirUnits.get(unit).LocationY * SquareSize) + (int) (MapY * SquareSize));
			TheirUnits.get(unit).FieldSprite.setSize((int) (SquareSize * 0.8), (int) (SquareSize * 1.2));
			TheirUnits.get(unit).FieldSprite.setIcon(Library.miniSprite(TheirUnits.get(unit).direction, unit, false));

		}

		setMapToUnit(getActiveUnit());

	}

	public void setMapToUnit(Unit unit)
	{
		int x = unit.LocationX;
		int y = unit.LocationY;
		setMapToLocation(x, y);

	}

	public void setMapToLocation(int x, int y)
	{
		MapX = -x + 4;
		MapY = -y + 3;
		updateSpritesLocation();
	}

	public void moveMapToLocation(int x, int y, int delay, double pixels)
	{
		x = -x + 4;
		y = -y + 3;

		while (MapX != x && MapY != y)
		{
			if (MapX > x)
			{
				MapX = MapX - pixels;
				if (MapX < x)
				{
					MapX = x;
				}
			}
			else if (MapX < x)
			{
				MapX = MapX + pixels;
				if (MapX > x)
				{
					MapX = x;
				}
			}
			if (MapY > y)
			{
				MapY = MapY - pixels;
				if (MapY < y)
				{
					MapY = y;
				}
			}
			else if (MapY < y)
			{
				MapY = MapY + pixels;
				if (MapY > y)
				{
					MapY = y;
				}
			}

			GUI.backround.setLocation((int) MapX * SquareSize, (int) MapY * SquareSize);
			updateSpritesLocation();
			GUI.f.repaint();
			if (!(MapX == x && MapY == y))
			{
				try
				{
					Thread.sleep(delay);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public void faceUnits(String myUnit, String enemyUnit)
	{
		if (Unit.myUnits.get(myUnit).LocationX > TheirUnits.get(enemyUnit).LocationX)
		{
			Unit.myUnits.get(myUnit).direction = "west";
			TheirUnits.get(enemyUnit).direction = "east";
		}
		else if (Unit.myUnits.get(myUnit).LocationX < TheirUnits.get(enemyUnit).LocationX)
		{
			Unit.myUnits.get(myUnit).direction = "east";
			TheirUnits.get(enemyUnit).direction = "west";
		}
		if (Unit.myUnits.get(myUnit).LocationY > TheirUnits.get(enemyUnit).LocationY)
		{
			Unit.myUnits.get(myUnit).direction = "north";
			TheirUnits.get(enemyUnit).direction = "south";
		}
		else if (Unit.myUnits.get(myUnit).LocationY < TheirUnits.get(enemyUnit).LocationY)
		{
			Unit.myUnits.get(myUnit).direction = "south";
			TheirUnits.get(enemyUnit).direction = "north";
		}

	}

	public void updateSpritesLocation()
	{
		GUI.backround.setLocation((int) (MapX * SquareSize), (int) (MapY * SquareSize));
		for (String key : Unit.myUnits.keySet())
		{
			Unit.myUnits.get(key).FieldSprite.setLocation(
					(Unit.myUnits.get(key).LocationX * SquareSize) + (int) (MapX * SquareSize),
					(Unit.myUnits.get(key).LocationY * SquareSize) + (int) (MapY * SquareSize));

		}
		for (String unit : TheirUnits.keySet())
		{
			TheirUnits.get(unit).FieldSprite.setLocation(
					(TheirUnits.get(unit).LocationX * SquareSize) + (int) (MapX * SquareSize),
					(TheirUnits.get(unit).LocationY * SquareSize) + (int) (MapY * SquareSize));

		}
	}
}
