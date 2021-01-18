package co.amscraft.game.storyline;

import java.util.ArrayList;

import co.amscraft.game.Classes;
import co.amscraft.game.Library;
import co.amscraft.game.Main;
import co.amscraft.game.Unit;

import co.amscraft.game.battle.FieldClass;

import co.amscraft.game.gui.GUI;
import co.amscraft.game.gui.LoadGUI;

public class Prologue
{

	private static ArrayList<Unit> enemies;

	public void dialogue()
	{
		Library Library = new Library();
		if (Main.story == 0)
		{
			Library.setText("", "Enter your name new Climate Change Activist?");
			Library.openTextField("Activist");

		}
		else if (Main.story == 1)
		{

				if (!GUI.textBox.getText().equals(""))
				{
					Main.name = GUI.textBox.getText();
				}
				Library.save();
				Library.closeTextField();
				Unit MyUnit = new Unit(50, ("Knight"));
				MyUnit.setAttack(1, "Sword");
				MyUnit.getSprite().XSize = 280;
				MyUnit.getSprite().YSize = 240;
				MyUnit.getSprite().XLocation = 100;
				MyUnit.getSprite().YLocation = 15;
				MyUnit.LocationX = 6;
				MyUnit.LocationY = 7;
				MyUnit.direction = "north";
				MyUnit.Class = Classes.list.get("Knight");
				MyUnit.name = Main.name;
				Unit.myUnits.put(MyUnit.name, MyUnit);
				Unit MrN = new Unit(65, "Mage2");
				MrN.defeatQuote = "You have not heard the last of me!";
				MrN.getSprite().XLocation = 80;
				MrN.getSprite().YLocation = 15;
				MrN.getSprite().XSize = 280;
				MrN.getSprite().YSize = 240;
				MrN.getSprite().file = "Mage2";
				MrN.LocationX = 6;
				MrN.LocationY = 5;
				MrN.name = "????";
				MrN.Class = Classes.list.get("DarkMage");
				MrN.setAttack(1, "Corruption");
				MrN = Library.levelToLevel(MrN, 5);
				enemies = new ArrayList<Unit>();
				enemies.add(MrN);
				FieldClass.battle.start("Temple", enemies);
				Library.setSong("Mystery");
				Library.setSprite(enemies.get(0).getSprite());
				Library.setText("Trump Supporter", "I refuse not to litter!!!");
				Library.setMoveLock(true);
				FieldClass.battle.talking = true;
		}
		else if (Main.story == 2)
		{
			Library.setText("????", "We dont have to fight.");
		}
		else if (Main.story == 3)
		{
			Library.setText("????", "You can still walk away from this");
		}
		else if (Main.story == 4)
		{
			Library.setSprite(Unit.myUnits.get(Main.name).getSprite());
			Library.setText(Main.name, "No, I cant. Not now!");

		}
		else if (Main.story == 5)
		{
			Library.setText(Main.name, "Not after everything. . .");
		}
		else if (Main.story == 6)
		{
			Library.setText("????", "Fine. Then stop me if you can!");
			Library.setSprite(enemies.get(0).getSprite());
		}
		else if (Main.story == 7)
		{
			Library.clearSprite();
			Library.clearText();
			FieldClass.battle.talking = false;
			Library.setMoveLock(false);
			FieldClass.counter.stopTimer();
			FieldClass.counter.startTimer(0.1);
			FieldClass.battle.startSong();
		}
		else if (Main.story == 8)
		{
			FieldClass.battle.addEnemyUnit(enemies.get(0));
			FieldClass.battle.talking = true;
			Library.setMoveLock(true);
			Library.setSong("Mystery");
			Library.setSprite(enemies.get(0).getSprite());
			Library.setText("????", ". . . . .");
		}
		else if (Main.story == 9)
		{
			Library.setText("????", "I. . I wont lose here");
		}
		else if (Main.story == 10)
		{
			Library.setText("????", "You wont win!");
		}
		else if (Main.story == 11)
		{
			Main.Level = 1;
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
		if (Main.Level == 0)
			Main.story = Main.story + 1;

	}
}
