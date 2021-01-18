package co.amscraft.game.storyline;

import java.util.ArrayList;

import co.amscraft.game.Classes;
import co.amscraft.game.Library;
import co.amscraft.game.Main;
import co.amscraft.game.Unit;
import co.amscraft.game.battle.FieldClass;

public class Story2
{

	public void dialogue()
	{
		Library Library = new Library();
		if (Main.story == 0)
		{
			FieldClass.battle = new FieldClass();
			Unit MrN = new Unit(65, "Mage2");
			ArrayList<Unit> enemies = new ArrayList<Unit>();
			MrN.defeatQuote = "You have not heard the last of me!";
			MrN.getSprite().XLocation = 80;
			MrN.getSprite().YLocation = 15;
			MrN.getSprite().XSize = 280;
			MrN.getSprite().YSize = 240;
			MrN.LocationX = 6;
			MrN.LocationY = 4;
			MrN.name = "Mr. N";
			MrN.Class = Classes.list.get("DarkMage");
			MrN.setAttack(1, "Corruption");
			MrN = Library.levelToLevel(MrN, (int) (Library.myAverageLevel() + (double) (Library.myAverageLevel() / 5)));
			enemies.add(MrN);
			Unit.myUnits.get(Main.name).LocationX = 6;
			Unit.myUnits.get(Main.name).LocationY = 7;
			Unit.myUnits.get(Main.name).direction = "north";
			FieldClass.battle.start("Temple", enemies);
			FieldClass.battle.talking = true;
			Library.setMoveLock(true);
			Library.setSprite(Unit.myUnits.get(Main.name).getSprite());
			Library.setText(Main.name, "Who is that. They look familiar");
			Library.setSong("Mystery");
		}
		else if (Main.story == 1)
		{
			Library.setText("????", "Who are you. . . ");
			Library.setSprite(FieldClass.battle.TheirUnits.get("Mr. N").getSprite());
		}
		else if (Main.story == 2)
		{
			Library.setText("????", "Where are my manners. I'm Mr. N");
		}
		else if (Main.story == 3)
		{
			Library.setText("Mr. N", "Now that you know my name. . .");
		}
		else if (Main.story == 4)
		{
			Library.setText("Mr. N", "You will die!!!!");

		}
		else if (Main.story == 5)
		{
			FieldClass.battle.talking = false;
			Library.setMoveLock(false);
			Library.clearSprite();
			Library.clearText();
			Library.restartFieldSong();
		}
		else if (Main.story == 6)
		{
			FieldClass.battle.talking = true;
			Library.setMoveLock(true);
			Unit MrN = new Unit(65, "Mage2");
			MrN.defeatQuote = "You have not heard the last of me!";
			MrN.getSprite().XLocation = 80;
			MrN.getSprite().YLocation = 15;
			MrN.getSprite().XSize = 280;
			MrN.getSprite().YSize = 240;
			MrN.LocationX = 6;
			MrN.LocationY = 4;
			MrN.name = "Mr. N";
			MrN.Class = Classes.list.get("DarkMage");
			MrN.setAttack(1, "Corruption");
			MrN = Library.levelToLevel(MrN, (int) (Library.myAverageLevel() + (double) (Library.myAverageLevel() / 5)));
			FieldClass.battle.addEnemyUnit(MrN);
			Library.setSprite(MrN.getSprite());
			Library.setText("Mr. N", "Time for me to summon re-enforcements");
		}
		else if (Main.story == 7)
		{
			Unit enemy = new Unit(50, "Briggand");
			enemy.Class = Classes.list.get("Knight");
			enemy = Library.levelToLevel(enemy, Library.myAverageLevel());
			enemy.LocationY = 6;
			enemy.name = "Familiar";
			enemy.direction = "west";
			enemy.LocationX = 2;
			enemy.getSprite().file = "Briggand";
			FieldClass.battle.addEnemyUnit(enemy);
			enemy = new Unit(50, "Briggand");
			enemy.Class = Classes.list.get("Knight");
			enemy = Library.levelToLevel(enemy,
					(int) (Library.myAverageLevel() - (double) (Library.myAverageLevel() / 5)));
			enemy.direction = "west";
			enemy.LocationX = 6;
			enemy.getSprite().file = "Briggand";
			enemy.name = "Familiar1";
			enemy.LocationY = 6;
			FieldClass.battle.addEnemyUnit(enemy);
			enemy = new Unit(50, "Briggand");
			enemy.Class = Classes.list.get("Knight");
			enemy = Library.levelToLevel(enemy,
					(int) (Library.myAverageLevel() - (double) (Library.myAverageLevel() / 5)));
			enemy.direction = "west";
			enemy.LocationX = 8;
			enemy.getSprite().file = "Briggand";
			enemy.name = "Familiar2";
			enemy.LocationY = 4;
			FieldClass.battle.addEnemyUnit(enemy);
			enemy = new Unit(50, "Briggand");
			enemy.Class = Classes.list.get("Knight");
			enemy = Library.levelToLevel(enemy,
					(int) (Library.myAverageLevel() - (double) (Library.myAverageLevel() / 5)));
			enemy.direction = "west";
			enemy.LocationX = 6;
			enemy.getSprite().file = "Briggand";
			enemy.name = "Familiar3";
			enemy.LocationY = 2;
			FieldClass.battle.addEnemyUnit(enemy);
			Library.restartFieldSong();
			FieldClass.battle.healUnits("Mine");
			Library.setMoveLock(false);
			FieldClass.battle.talking = false;
			Library.clearSprite();
			Library.clearText();
		}
		else if (Main.story == 8)
		{
		}
		else
		{
			System.exit(0);
		}
		if (Main.Level == 2)
			Main.story = Main.story + 1;
	}
}
