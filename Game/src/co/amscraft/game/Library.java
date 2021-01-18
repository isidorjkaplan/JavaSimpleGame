package co.amscraft.game;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import co.amscraft.game.battle.Attack;
import co.amscraft.game.battle.FieldClass;
import co.amscraft.game.battle.Sprite;
import co.amscraft.game.gui.BattleGUI;
import co.amscraft.game.gui.CutsceneGUI;
import co.amscraft.game.gui.FieldGUI;
import co.amscraft.game.gui.GUI;
import co.amscraft.game.storyline.Prologue;
import co.amscraft.game.storyline.Story1;
import co.amscraft.game.storyline.Story2;

public class Library
{
	public static Library Functions = new Library();
	private static String preBackround;
	private static String Song = "Null";
	private static final String KEY = "some-secret-key-of-your-choice";

	public void restartFieldSong()
	{
		FieldClass.counter.stopTimer();
		FieldClass.counter.startTimer(0.1);
		FieldClass.battle.startSong();
	}

	public Unit generateUnit(String name, int Level, int x, int y, String spriteFile, String Class)
	{
		Unit enemy = new Unit(50, "Bandit");
		enemy.Class = Classes.list.get(Class);
		enemy = levelToLevel(enemy, Level);
		enemy.LocationY = y;
		enemy.LocationX = x;
		enemy.getSprite().file = spriteFile;
		return enemy;
	}

	public int myAverageLevel()
	{
		double i = 0;
		double level = 0;
		for (Unit unit : Unit.myUnits.values())
		{
			level = level + unit.Level;
			i = i + 1;
		}
		return (int) (level / i);
	}

	public String encrypt(final String text)
	{
		return Base64.getEncoder().encodeToString((this.xor(text.getBytes())));
	}

	public String decrypt(final String hash)
	{
		return new String(this.xor(Base64.getDecoder().decode((hash.getBytes()))));
	}

	@SuppressWarnings("static-access")
	private byte[] xor(final byte[] input)
	{
		final byte[] output = new byte[input.length];
		final byte[] secret = this.KEY.getBytes();
		int spos = 0;
		for (int pos = 0; pos < input.length; ++pos)
		{
			output[pos] = (byte) (input[pos] ^ secret[spos]);
			spos += 1;
			if (spos >= secret.length)
			{
				spos = 0;
			}
		}
		return output;
	}

	public void runStory()
	{
		if (Main.Level == 0)
		{
			Prologue story0 = new Prologue();
			story0.dialogue();
		}
		else if (Main.Level == 1)
		{
			Story1 Story1 = new Story1();
			Story1.dialogue();
		}
		else if (Main.Level == 2)
		{
			Story2 Story2 = new Story2();
			Story2.dialogue();
		}
	}

	public boolean diceRoll(int probability)
	{
		int number = (int) Math.ceil(Math.random() * 100);
		if (number < probability)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public double randomNumber(int number)
	{
		return Math.ceil(Math.random() * number);
	}

	public boolean movesLocked = false;

	public void setMoveLock(boolean locked)
	{
		movesLocked = locked;
		if (locked)
		{
			FieldGUI.North.setEnabled(false);
			FieldGUI.East.setEnabled(false);
			FieldGUI.West.setEnabled(false);
			FieldGUI.South.setEnabled(false);
			FieldGUI.units.setEnabled(false);
			FieldGUI.battle.setEnabled(false);
			GUI.f.getContentPane().repaint();
		}
		else
		{
			FieldGUI.units.setEnabled(true);
			FieldGUI.battle.setEnabled(true);
			FieldClass.battle.possibleMovesCheck();
			GUI.f.getContentPane().repaint();
		}
	}

	public void miniIcon(String icon, int positon, boolean side)
	{
		try
		{
			if (side)
			{
				BattleGUI.MiniUnit.setIcon(
						new ImageIcon(ImageIO.read(new File("liberary/images/sprites/mini/north/" + icon + ".png"))
								.getScaledInstance(25, 35, Image.SCALE_SMOOTH)));
				BattleGUI.MiniUnit.setLocation(225, 222 + (positon * 25));
			}
			else
			{
				BattleGUI.MiniUnit2.setIcon(
						new ImageIcon(ImageIO.read(new File("liberary/images/sprites/mini/south/" + icon + ".png"))
								.getScaledInstance(25, 35, Image.SCALE_SMOOTH)));
				BattleGUI.MiniUnit2.setLocation(225, 222 + (positon * 25));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		GUI.f.getContentPane().repaint();
	}

	public void save()
	{
		FileWriter write = null;
		try
		{
			write = new FileWriter("Saves.txt", false);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		PrintWriter print_line = new PrintWriter(write);
		print_line.printf("%s" + "%n", encrypt(Main.Level + ""));
		print_line.printf("%s" + "%n", encrypt(Main.story + ""));
		print_line.printf("%s" + "%n", encrypt(Main.name));
		for (String key : Unit.myUnits.keySet())
		{
			print_line.printf("%s" + "%n", encrypt("Unit: " + Unit.myUnits.get(key).name));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).getMaxHealth() + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).getAttack(1).name));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).getAttack(2).name));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).getAttack(3).name));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).getSprite().file));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).getSprite().XLocation + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).getSprite().YLocation + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).getSprite().XSize + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).getSprite().YSize + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).LocationX + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).LocationY + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).stats.get("Speed") + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).stats.get("Attack") + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).stats.get("Movement") + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).defeatQuote));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).levelQuote));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).Level + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).exp + ""));
			print_line.printf("%s" + "%n", encrypt(Unit.myUnits.get(key).Class.name));
		}
		print_line.close();
	}

	public void load()
	{
		try
		{
			Unit.myUnits.clear();
			List<String> Line = Files.readAllLines(Paths.get("Saves.txt"));
			HashMap<Integer, String> Lines = new HashMap<Integer, String>();
			int i = 0;
			for (String String : Line)
			{
				Lines.put(i, decrypt(String));
				i = i + 1;
			}
			Main.Level = Integer.parseInt(Lines.get(0));
			Main.story = Integer.parseInt(Lines.get(1));
			Main.name = Lines.get(2);
			for (int key = 0; key < Lines.size(); key++)
			{
				String line = Lines.get(key);
				if (line.contains("Unit: "))
				{
					line = line.replaceAll("Unit: ", "");
					Unit unit = new Unit(50, "Corrin");
					unit.name = line;
					unit.setMaxHealth(Integer.parseInt(Lines.get(key + 1)));
					unit.setAttack(1, Lines.get(key + 2));
					unit.setAttack(2, Lines.get(key + 3));
					unit.setAttack(3, Lines.get(key + 4));
					unit.getSprite().file = Lines.get(key + 5);
					unit.getSprite().XLocation = Integer.parseInt(Lines.get(key + 6));
					unit.getSprite().YLocation = Integer.parseInt(Lines.get(key + 7));
					unit.getSprite().XSize = Integer.parseInt(Lines.get(key + 8));
					unit.getSprite().YSize = Integer.parseInt(Lines.get(key + 9));
					unit.LocationX = Integer.parseInt(Lines.get(key + 10));
					unit.LocationY = Integer.parseInt(Lines.get(key + 11));
					unit.stats.put("Speed", Integer.parseInt(Lines.get(key + 12)));
					unit.stats.put("Attack", Integer.parseInt(Lines.get(key + 13)));
					unit.stats.put("Movement", Integer.parseInt(Lines.get(key + 14)));
					unit.defeatQuote = Lines.get(key + 15);
					unit.levelQuote = Lines.get(key + 16);
					unit.Level = Integer.parseInt(Lines.get(key + 17));
					unit.exp = Double.parseDouble(Lines.get(key + 18));
					unit.Class = Classes.list.get(Lines.get(key + 19));
					Unit.myUnits.put(unit.name, unit);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}

	public void reviveUnits()
	{
		for (String key : Unit.deadUnits.keySet())
		{
			Unit.myUnits.put(key, Unit.deadUnits.get(key));
		}
	}

	public static void createDefaultAttacks()
	{
		Attack emptyAttack = new Attack();
		Attack punch = new Attack();
		punch.damage = 5;
		punch.maxUses = 50;
		punch.name = "Punch";
		punch.display = "Punch: 1, 5";
		punch.hitMessage = " punched you!";
		punch.distance.put(1, true);
		punch.effect = "battle/swing3";
		Attack fireball = new Attack();
		fireball.damage = 25;
		fireball.maxUses = 10;
		fireball.name = "Fireball";
		fireball.display = "Fireball: 1-3, 25";
		fireball.distance.put(1, true);
		fireball.distance.put(2, true);
		fireball.effect = "battle/fireball";
		fireball.distance.put(3, true);
		fireball.hitMessage = " blasted you with a fireball!";
		Attack bow = new Attack();
		bow.damage = 40;
		bow.maxUses = 10;
		bow.name = "Bow";
		bow.display = "Bow: 3-4, 40";
		bow.distance.put(3, true);
		bow.effect = "battle/arrow";
		bow.distance.put(4, true);
		bow.hitMessage = " shot you with an arrow!";
		Attack sword = new Attack();
		sword.damage = 30;
		sword.maxUses = 10;
		sword.name = "Sword";
		sword.display = "Sword: 1-2, 30";
		sword.distance.put(1, true);
		sword.distance.put(2, true);
		sword.effect = "battle/sword-unsheathe5";
		sword.hitMessage = " Stabbed you!";
		Attack Knife = new Attack();
		Knife.damage = 40;
		Knife.maxUses = 10;
		Knife.name = "Knife";
		Knife.display = "Knife: 1 & 4, 30";
		Knife.distance.put(1, true);
		Knife.distance.put(4, true);
		Knife.effect = "battle/sword-unsheathe5";
		Knife.hitMessage = " knifed you!";
		Attack shadowblast = new Attack();
		shadowblast.damage = 25;
		shadowblast.maxUses = 10;
		shadowblast.name = "Knife";
		shadowblast.display = "Shadows: 3-4, 25";
		shadowblast.distance.put(3, true);
		shadowblast.effect = "battle/spell";
		shadowblast.distance.put(4, true);
		shadowblast.hitMessage = " Blasted you with Shadows!";
		Attack corruption = new Attack();
		corruption.damage = 50;
		corruption.maxUses = 10;
		corruption.effect = "battle/magic1";
		corruption.name = "Corruption";
		corruption.display = "Corruption: 1, 50";
		corruption.distance.put(1, true);
		corruption.hitMessage = " Unleashed pure dark magic on you!";
		Attack.Attacks.put("Empty", emptyAttack);
		Attack.Attacks.put(punch.name, punch);
		Attack.Attacks.put(fireball.name, fireball);
		Attack.Attacks.put(bow.name, bow);
		Attack.Attacks.put(sword.name, sword);
		Attack.Attacks.put(shadowblast.name, shadowblast);
		Attack.Attacks.put(corruption.name, corruption);
		Attack.Attacks.put(Knife.name, Knife);
	}

	public static void buildDefaultClasses()
	{
		Classes Mage = new Classes("Mage");
		Mage.moves.put(1, "Fireball");
		Mage.stats.put("Attack", 60);
		Mage.stats.put("Speed", 20);
		Mage.stats.put("MaxHealth", 45);
		Mage.statsCap.put("Attack", 2);
		Mage.statsCap.put("Speed", 30);
		Mage.statsCap.put("MaxHealth", 5);
		Classes.list.put("Mage", Mage);
		Classes DarkMage = Mage;
		DarkMage.name = "DarkMage";
		DarkMage.stats.put("Attack", 80);
		DarkMage.stats.put("MaxHealth", 60);
		DarkMage.moves.put(4, "Shadow Blast");
		DarkMage.moves.put(7, "Corruption");
		DarkMage.moves.put(15, "Fireball");
		Classes.list.put("DarkMage", DarkMage);
		Classes Knight = new Classes("Knight");
		Knight.moves.put(4, "Sword");
		Knight.name = "Knight";
		Knight.stats.put("Attack", 50);
		Knight.stats.put("Speed", 10);
		Knight.stats.put("MaxHealth", 80);
		Knight.statsCap.put("Attack", 2);
		Knight.statsCap.put("Speed", 1);
		Knight.statsCap.put("MaxHealth", 6);
		Classes.list.put("Knight", Knight);
		Classes Bandit = new Classes("Bandit");
		Bandit.name = "Bandit";
		Bandit.stats.put("Attack", 50);
		Bandit.stats.put("Speed", 10);
		Bandit.stats.put("MaxHealth", 80);
		Bandit.statsCap.put("Attack", 2);
		Bandit.statsCap.put("Speed", 1);
		Bandit.statsCap.put("MaxHealth", 6);
		Bandit.name = "Bandit";
		Bandit.moves.put(4, "Punch");
		Bandit.moves.put(5, "Knife");
		Bandit.moves.put(10, "Sword");
		Classes.list.put("Bandit", Bandit);
	}

	public Unit levelToLevel(Unit unit, int Level)
	{
		while (unit.Level < Level + 1)
		{
			unit = unit.Class.levelUp(unit);
		}
		return unit;
	}

	public String getBackround()
	{
		return preBackround;
	}

	public void updateHealthBar(boolean yours, Unit yourUnit)
	{
		if (yours)
		{
			BattleGUI.health.setSize((int) ((yourUnit.getHealth() / yourUnit.getMaxHealth()) * 97), 10);
			GUI.f.getContentPane().repaint();
		}
		else
		{
			BattleGUI.health2.setSize((int) ((yourUnit.getHealth() / yourUnit.getMaxHealth()) * 97), 10);
			GUI.f.getContentPane().repaint();
		}
	}

	public ImageIcon miniSprite(String direction, String unit, boolean side)
	{
		try
		{
			if (side)
				return new ImageIcon(ImageIO
						.read(new File("liberary/images/sprites/mini/" + direction + "/"
								+ Unit.myUnits.get(unit).getSprite().file + ".png"))
						.getScaledInstance((int) (FieldClass.battle.SquareSize * 0.8),
								(int) (FieldClass.battle.SquareSize * 1.2), Image.SCALE_REPLICATE));
			else
				return new ImageIcon(ImageIO
						.read(new File("liberary/images/sprites/mini/" + direction + "/"
								+ FieldClass.battle.TheirUnits.get(unit).getSprite().file + ".png"))
						.getScaledInstance((int) (FieldClass.battle.SquareSize * 0.8),
								(int) (FieldClass.battle.SquareSize * 1.2), Image.SCALE_REPLICATE));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return new ImageIcon();
		}
	}

	public void setSprite(String name, int width, int hight, int XCords, int YCords)
	{
		try
		{
			GUI.sprite.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/sprites/" + name + ".png"))
					.getScaledInstance(width, hight, Image.SCALE_SMOOTH)));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		GUI.sprite.setLocation(XCords, YCords);
		GUI.sprite.setSize(width, hight);
		GUI.f.getContentPane().repaint();
	}

	public void clearSprite()
	{
		GUI.sprite.setIcon(new ImageIcon());
		GUI.f.getContentPane().repaint();
	}

	public void setSprite(String name)
	{
		setSprite(name, 200, 200, 125, 25);
	}

	public void setSprite(Sprite sprite)
	{
		setSprite(sprite.file, sprite.XSize, sprite.YSize, sprite.XLocation, sprite.YLocation);
	}

	public void setBackround(String Backround)
	{
		preBackround = Backround;
		try
		{
			GUI.backround
					.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/backrounds/" + Backround + ".png"))
							.getScaledInstance(300, 225, Image.SCALE_SMOOTH)));
			GUI.backround.setSize(300, 225);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		GUI.f.getContentPane().repaint();
	}

	public void setMapBackround(String Backround)
	{
		try
		{
			GUI.backround.setIcon(
					new ImageIcon(ImageIO.read(new File("liberary/images/backrounds/maps/" + Backround + ".png"))));
			GUI.backround.setSize(GUI.backround.getIcon().getIconWidth(), GUI.backround.getIcon().getIconHeight());
			GUI.backround.setLocation((int) FieldClass.battle.MapX * FieldClass.battle.SquareSize,
					(int) FieldClass.battle.MapY * FieldClass.battle.SquareSize);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		GUI.f.getContentPane().repaint();
	}

	public void setText(String name, String text)
	{
		try
		{
			GUI.textGUI.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/text.png"))
					.getScaledInstance(300, 30, Image.SCALE_REPLICATE)));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		GUI.text.setText(text);
		if (GUI.text.getForeground() != Color.black)
			GUI.text.setForeground(Color.black);
		if (!name.equals(""))
		{
			GUI.textName.setText(name);
			GUI.textName.setVisible(true);
			GUI.textNameGUI.setVisible(true);
		}
		else
		{
			GUI.textName.setVisible(false);
			GUI.textNameGUI.setVisible(false);
		}
		GUI.f.getContentPane().repaint();
	}

	public void setText(String name, String text, Color color)
	{
		try
		{
			GUI.textGUI.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/text.png"))
					.getScaledInstance(300, 30, Image.SCALE_REPLICATE)));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		GUI.text.setText(text);
		GUI.text.setForeground(color);
		if (!name.equals(""))
		{
			GUI.textName.setText(name);
			GUI.textName.setVisible(true);
			GUI.textNameGUI.setVisible(true);
		}
		else
		{
			GUI.textName.setVisible(false);
			GUI.textNameGUI.setVisible(false);
		}
		GUI.f.getContentPane().repaint();
	}

	public void clearText()
	{
		GUI.textGUI.setIcon(null);
		GUI.text.setText("");
		GUI.textName.setText("");
		GUI.textName.setVisible(false);
		GUI.textNameGUI.setVisible(false);
	}

	public void buttonLight()
	{
		try
		{
			CutsceneGUI.bpA.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/clickedbutton.png"))
					.getScaledInstance(150, 100, Image.SCALE_REPLICATE)));
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		GUI.f.getContentPane().repaint();
		CutsceneGUI.button.setEnabled(false);
		new java.util.Timer().schedule(new java.util.TimerTask()
		{
			@Override
			public void run()
			{
				try
				{
					CutsceneGUI.bpA.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/button.png"))
							.getScaledInstance(150, 100, Image.SCALE_REPLICATE)));
					CutsceneGUI.button.setEnabled(true);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				GUI.f.getContentPane().repaint();
			}
		}, 200);
	}

	private static Clip clip;

	public void playSoundEffect(String effect)
	{
		if (GUI.volume.getValue() != 0)
		{
			Clip clip;
			try
			{
				clip = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("liberary/music/" + effect + ".wav"));
				clip.open(ais);
				clip.start();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void setSong(String song)
	{
		setSong(song, 0);
	}

	public void setVolume(int volume)
	{
		try
		{
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue((float) volume);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setSong(String song, double Time)
	{
		if (!Song.equals(song))
		{
			Song = song;
			stopMusic();
			try
			{
				clip = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem
						.getAudioInputStream(new File("liberary/music/songs/" + song + ".wav"));
				clip.open(ais);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				setVolume(GUI.volume.getValue() - 80);
				clip.setMicrosecondPosition((long) (Time * 1000000));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Song already playing");
		}
	}

	public String getSong()
	{
		return Song;
	}

	public void stopMusic()
	{
		try
		{
			clip.stop();
		}
		catch (NullPointerException e)
		{
		}
	}

	public void openTextField(String text)
	{
		GUI.textBox.setVisible(true);
		GUI.textBox.setText(text);
	}

	public void closeTextField()
	{
		GUI.textBox.setVisible(false);
	}
}