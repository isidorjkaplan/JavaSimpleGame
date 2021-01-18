package co.amscraft.game.storyline;

import java.util.ArrayList;

import co.amscraft.game.Classes;
import co.amscraft.game.Library;
import co.amscraft.game.Main;
import co.amscraft.game.Unit;

import co.amscraft.game.battle.FieldClass;
import co.amscraft.game.gui.LoadGUI;

public class Story1
{
	public void dialogue()
	{
		Library Library = new Library();
		if (Main.story == 0)
		{
			FieldClass.battle = new FieldClass();
			ArrayList<Unit> enemies = new ArrayList<Unit>();
			Unit enemy = new Unit(50, "Bandit");
			enemy.name = "Bandit";
			enemy.Class = Classes.list.get("Knight");
			enemy = Library.levelToLevel(enemy, Library.myAverageLevel());
			enemy.LocationY = 2;
			enemy.LocationX = 18;
			enemy.getSprite().file = "roshan";
			enemies.add(enemy);
			Unit.myUnits.get(Main.name).LocationX = 3;
			Unit.myUnits.get(Main.name).LocationY = 11;
			Unit.myUnits.get(Main.name).direction = "south";
			FieldClass.battle.start("Road", enemies);
			FieldClass.battle.talking = true;
			Library.setMoveLock(true);
			Library.setSprite(Unit.myUnits.get(Main.name).getSprite());
			Library.setText(Main.name, "What a strange dream");
			Library.setSong("TownTheme");
		}
		else if (Main.story == 1)
		{
			Library.setText(Main.name, "It felt so real to . . ");
		}
		else if (Main.story == 2)
		{
			Library.setText(Main.name, "!!!!");
		}
		else if (Main.story == 3)
		{
			Library.setText(Main.name, "Whoes there!");
		}
		else if (Main.story == 4)
		{
			Library.clearSprite();
			Library.clearText();
			FieldClass.battle.talking = false;
			Library.setMoveLock(false);
			Library.restartFieldSong();
		}
		else if (Main.story == 5)
		{
			FieldClass.battle.talking = true;
			Library.setMoveLock(true);
			Library.setSprite(Unit.myUnits.get(Main.name).getSprite());
			Library.setText(Main.name, "Phew that was close. . . Stupid bandits.");
			Library.setSong("TownTheme");
		}
		else if (Main.story == 6)
		{
			FieldClass.battle.talking = true;
			Library.setMoveLock(true);
			Library.setSprite(Unit.myUnits.get(Main.name).getSprite());
			Library.setText(Main.name, "Damn it! More of them!");
		}
		else if (Main.story == 7)
		{
			Library.setText(Main.name, "Guess he brought some friends");
		}
		else if (Main.story == 8)
		{
			Library.clearText();
			Library.clearSprite();
			Library.setMoveLock(false);
			FieldClass.battle.talking = false;
			Unit enemy = new Unit(50, "Bandit1");
			enemy.Class = Classes.list.get("Bandit");
			enemy = Library.levelToLevel(enemy, Library.myAverageLevel());
			enemy.LocationY = 3;
			enemy.name = "Bandit1";
			enemy.direction = "west";
			enemy.LocationX = 19;
			enemy.getSprite().file = "roshan";
			FieldClass.battle.addEnemyUnit(enemy);
			enemy = new Unit(50, "Bandit2");
			enemy.Class = Classes.list.get("Bandit");
			enemy = Library.levelToLevel(enemy, Library.myAverageLevel());
			enemy.direction = "west";
			enemy.LocationX = 19;
			enemy.getSprite().file = "roshan";
			enemy.name = "Bandit2";
			enemy.LocationY = 2;
			FieldClass.battle.addEnemyUnit(enemy);
			Library.restartFieldSong();
			FieldClass.battle.healUnits("Mine");
			Library.updateHealthBar(true, Unit.myUnits.get(Main.name));
		}
		else if (Main.story == 9)
		{
			FieldClass.battle.talking = true;
			Library.setMoveLock(true);
			Library.setSprite(Unit.myUnits.get(Main.name).getSprite());
			Library.setText(Main.name, "Guess thats the last of them!");
		}
		else if (Main.story == 10)
		{
			Main.Level = 2;
			Main.story = 0;
			Library.reviveUnits();
			Library.save();
			LoadGUI GUI = new LoadGUI();
			GUI.createGUI();
		}
		else
		{
			System.exit(0);
		}
		if (Main.Level == 1)
			Main.story = Main.story + 1;
	}
}
