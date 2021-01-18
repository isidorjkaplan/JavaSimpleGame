package co.amscraft.game.gui;

import java.awt.Color;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import co.amscraft.game.Library;
import co.amscraft.game.battle.FieldClass;
import co.amscraft.game.listeners.Key;
import co.amscraft.game.listeners.Listener;

public class FieldGUI
{

	public static JButton North = new JButton();
	public static JButton South = new JButton();
	public static JButton East = new JButton();
	public static JButton West = new JButton();
	public static JComboBox<String> units = new JComboBox<String>();
	public static JComboBox<String> battle = new JComboBox<String>();
	public static Key key = new Key();

	public void buildField()
	{
		try
		{
			GUI.GUI = "Field";
			Listener Listener = new Listener();
			GUI.jp.removeAll();
			GUI.jp.setOpaque(true);
			GUI.jp.setBackground(Color.WHITE);
			GUI.jp.setLayout(null);

			North = new JButton("");
			North.setSize(50, 50);
			North.setLocation(125, 265);
			North.setActionCommand("North");
			North.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/North.png"))
					.getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
			North.addActionListener(Listener);
			North.addKeyListener(key);
			South = new JButton("");
			South.setSize(50, 50);
			South.setLocation(125, 365);
			South.setActionCommand("South");
			South.addActionListener(Listener);
			South.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/South.png"))
					.getScaledInstance(45, 45, Image.SCALE_SMOOTH)));
			South.addKeyListener(key);
			East = new JButton("");
			East.setSize(50, 50);
			East.setLocation(175, 315);
			East.setActionCommand("East");
			East.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/East.png")).getScaledInstance(45,
					45, Image.SCALE_SMOOTH)));
			East.addActionListener(Listener);
			East.addKeyListener(key);
			West = new JButton("");
			West.setSize(50, 50);
			West.setLocation(75, 315);
			West.setActionCommand("West");
			West.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/West.png")).getScaledInstance(45,
					45, Image.SCALE_SMOOTH)));
			West.addActionListener(Listener);
			West.addKeyListener(key);
			if (Library.Functions.movesLocked || FieldClass.battle.talking)
			{
				Library.Functions.setMoveLock(true);
			}
			GUI.textGUI.setSize(300, 30);
			GUI.textGUI.setLocation(0, 195);
			GUI.text.setSize(295, 45);
			GUI.text.setLocation(35, 190);
			GUI.textName.setSize(75, 25);
			GUI.textName.setLocation(205, 185);
			GUI.textName.setForeground(Color.BLACK);
			GUI.textNameGUI.setSize(100, 50);
			GUI.textNameGUI.setLocation(180, 172);
			GUI.textNameGUI.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/brown.png"))
					.getScaledInstance(85, 20, Image.SCALE_SMOOTH)));
			GUI.sprite.setLocation(125, 25);
			GUI.sprite.setSize(200, 200);
			controlSkin.setSize(300, 205);
			controlSkin.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/controllskin.png"))
					.getScaledInstance(300, 205, Image.SCALE_SMOOTH)));
			controlSkin.setLocation(0, GUI.f.getHeight() / 2);
			GUI.backround.setSize(300, 225);
			GUI.backround.setIcon(new ImageIcon());
			GUI.backround.setLocation(0, 0);
			GUI.textBox.setSize(100, 45);
			GUI.textBox.setLocation(100, 230);
			GUI.textBox.setVisible(false);
			myStatusBar.setSize(125, 150);
			myStatusBar.setLocation(85, 180);
			myStatusBar.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/status.png"))
					.getScaledInstance(125, 150, Image.SCALE_SMOOTH)));

			white.setLocation(105, 247);
			white.setSize(97, 10);
			white.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/white.png"))
					.getScaledInstance(125, 150, Image.SCALE_SMOOTH)));
			units.setSize(100, 30);
			units.setLocation(20, 275);
			units.setActionCommand("Units");
			units.addActionListener(Listener);
			BattleGUI.health = new JLabel();
			BattleGUI.health.setLocation(110, 247);
			BattleGUI.health.setSize(97, 10);
			BattleGUI.health.setBackground(Color.WHITE);
			BattleGUI.health.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/health.png"))
					.getScaledInstance(97, 10, Image.SCALE_SMOOTH)));
			CutsceneGUI.button.setLocation(125, 315);
			CutsceneGUI.button.setSize(50, 50);
			battle.setSize(100, 30);
			battle.setLocation(175, 275);
			GUI.volume.setSize(100, 30);
			GUI.volume.setLocation(15, 375);

			GUI.jp.add(GUI.volume);
			GUI.jp.add(GUI.textBox);
			GUI.jp.add(GUI.textName);
			GUI.jp.add(GUI.textNameGUI);
			GUI.jp.add(GUI.text);
			GUI.jp.add(GUI.textGUI);
			GUI.jp.add(CutsceneGUI.button);
			GUI.jp.add(units);
			GUI.jp.add(battle);
			GUI.jp.add(myStatusBar);
			GUI.jp.add(BattleGUI.health);
			GUI.jp.add(white);
			GUI.jp.add(North);
			GUI.jp.add(South);
			GUI.jp.add(East);
			GUI.jp.add(West);
			GUI.jp.add(controlSkin);
			FieldClass.battle.placeUnits();
			GUI.jp.add(GUI.sprite);
			GUI.jp.add(GUI.backround);
			GUI.f.setContentPane(GUI.jp);
			GUI.f.setVisible(true);
			Library.Functions.setMoveLock(Library.Functions.movesLocked);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void reLayer()
	{
		FieldClass.battle.placeUnits();
		GUI.jp.add(CutsceneGUI.button);
		GUI.jp.add(units);
		GUI.jp.add(battle);
		GUI.jp.add(myStatusBar);
		GUI.jp.add(BattleGUI.health);
		GUI.jp.add(white);
		GUI.jp.add(GUI.textBox);
		GUI.jp.add(GUI.textName);
		GUI.jp.add(GUI.text);
		GUI.jp.add(GUI.textGUI);
		GUI.jp.add(North);
		GUI.jp.add(South);
		GUI.jp.add(East);
		GUI.jp.add(West);
		GUI.jp.add(controlSkin);
		GUI.jp.add(GUI.sprite);
		GUI.jp.add(GUI.backround);
		GUI.f.setContentPane(GUI.jp);
		GUI.f.setVisible(true);
	}

	public static JLabel controlSkin = new JLabel();
	public static JLabel white = new JLabel();
	public static JLabel myStatusBar = new JLabel();
}
