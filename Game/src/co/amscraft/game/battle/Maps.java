package co.amscraft.game.battle;

import java.util.HashMap;

import co.amscraft.game.*;

public class Maps
{

	public static HashMap<String, Boolean> permissions;
	private Library Library = new Library();

	public void initiateMap(String map)
	{
		permissions = new HashMap<String, Boolean>();
		if (map.equals("SacredTree"))
		{
			permissions.clear();
			Library.setMapBackround("SacredTree");
			FieldClass.battle.song = "Arabesque";
			FieldClass.battle.backround = "SacredTree";
			FieldClass.battle.SquareSize = 32;
			permissions.put("8, 5", false);
			permissions.put("7, 5", false);
			permissions.put("7, 4", false);
			permissions.put("8, 4", false);
			permissions.put("9, 4", false);
			permissions.put("11, 3", false);
			permissions.put("9, 3", false);
			permissions.put("8, 3", false);
			permissions.put("7, 3", false);
			permissions.put("6, 3", false);
			permissions.put("5, 3", false);
			permissions.put("4, 3", false);
			permissions.put("0, 2", false);
			permissions.put("1, 2", false);
			permissions.put("2, 2", false);
			permissions.put("3, 2", false); // to 13 2 (Border)
			for (int x = 3; x < 14; x++)
			{
				permissions.put(x + ", 2", false);
			}
			for (int y = 0; y < 10; y++)
			{
				permissions.put("13, " + y, false);
			}
			for (int x = -1; x < 14; x++)
			{
				permissions.put(x + ", 9", false);
			}
			for (int y = -1; y < 10; y++)
			{
				permissions.put("-1, " + y, false);
			}

		}
		if (map.equals("Road"))
		{
			permissions.clear();
			Library.setMapBackround("Road");
			FieldClass.battle.song = "LittleTown";
			FieldClass.battle.backround = "Road";
			FieldClass.battle.SquareSize = 32;
			permissions.put("11, 5", false);
			permissions.put("10, 5", false);
			permissions.put("9, 5", false);
			permissions.put("9, 6", false);
			permissions.put("10, 6", false);
			permissions.put("11, 6", false);
			permissions.put("11, 7", false);
			permissions.put("4, 7", false);
			permissions.put("3, 7", false);
			permissions.put("2, 7", false);
			permissions.put("2, 8", false);
			permissions.put("3, 8", false);
			permissions.put("4, 8", false);
			permissions.put("10, 8", false);
			permissions.put("4, 9", false);
			permissions.put("3, 9", false);
			permissions.put("2, 9", false);
			permissions.put("3, 10", false);
			permissions.put("6, 10", false);
			permissions.put("15, 10", false);
			permissions.put("16, 10", false);
			permissions.put("16, 11", false);
			permissions.put("15, 11", false);
			permissions.put("14, 11", false);
			permissions.put("6, 11", false);
			permissions.put("5, 11", false);
			permissions.put("4, 11", false);
			permissions.put("4, 12", false);
			permissions.put("5, 12", false);
			permissions.put("6, 12", false);
			permissions.put("10, 12", false);
			permissions.put("11, 12", false);
			permissions.put("14, 12", false);
			permissions.put("15, 12", false);
			permissions.put("16, 12", false);
			permissions.put("15, 13", false);
			permissions.put("11, 13", false);
			permissions.put("10, 13", false);
			permissions.put("9, 13", false);
			permissions.put("6, 14", false);
			permissions.put("5, 13", false);
			permissions.put("4, 13", false);
			permissions.put("2, 13", false);
			permissions.put("1, 13", false);
			permissions.put("0, 13", false);
			permissions.put("0, 14", false);
			permissions.put("1, 14", false);
			permissions.put("2, 14", false);
			permissions.put("10, 14", false);
			permissions.put("13, 14", false);
			permissions.put("14, 15", false);
			permissions.put("13, 15", false);
			permissions.put("12, 15", false);
			permissions.put("5, 15", false);
			permissions.put("2, 15", false);
			permissions.put("1, 15", false);
			permissions.put("1, 16", false);
			permissions.put("7, 16", false);
			permissions.put("13, 16", false);
			permissions.put("18, 17", false);
			permissions.put("17, 17", false);
			permissions.put("7, 17", false);
			permissions.put("3, 17", false);
			permissions.put("2, 17", false);
			permissions.put("1, 17", false);
			permissions.put("0, 18", false);
			permissions.put("1, 18", false);
			permissions.put("2, 18", false);
			permissions.put("3, 18", false);
			permissions.put("4, 18", false);
			permissions.put("5, 18", false);
			permissions.put("6, 18", false);
			permissions.put("7, 18", false);
			permissions.put("0, 18", false);
			permissions.put("0, 18", false);
			permissions.put("6, 19", false);
			permissions.put("5, 19", false);
			permissions.put("4, 19", false);
			permissions.put("9, 7", false);
			permissions.put("10, 7", false);

			for (int x = -1; x < 21; x++)
			{
				permissions.put(x + ", -1", false);
			}
			for (int y = -1; y < 21; y++)
			{
				permissions.put("20, " + y, false);
			}
			for (int x = -1; x < 21; x++)
			{
				permissions.put(x + ", -1", false);
			}
			for (int y = -1; y < 21; y++)
			{
				permissions.put("-1, " + y, false);
			}
		}
		else if (map.equals("Desert"))
		{
			permissions.clear();
			Library.setMapBackround("Desert");
			FieldClass.battle.song = "TheLoomingBattle_0";
			FieldClass.battle.backround = "Desert";
			FieldClass.battle.SquareSize = 32;

		}
		else if (map.equals("Temple"))
		{
			Library.setMapBackround("Temple");
			FieldClass.battle.song = "DramaticReveal";
			FieldClass.battle.backround = "Temple";
			FieldClass.battle.SquareSize = 32;
		}
	}
}
