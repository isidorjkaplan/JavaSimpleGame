package co.amscraft.game.gui;

import java.awt.Color;

import co.amscraft.game.battle.FieldClass;

import co.amscraft.game.listeners.Listener;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class BattleGUI
{
	public static JButton move1;
	public static JButton move2;
	public static JButton move3;
	public static JLabel health = new JLabel();
	public static JLabel health2 = new JLabel();
	public static JLabel MiniUnit = new JLabel();
	public static JLabel MiniUnit2 = new JLabel();
	public static JButton Up;
	public static JButton Down;
	public static JLabel animation = new JLabel();

	public void battleScene() throws IOException
	{
		Listener Listener = new Listener();
		GUI.jp.removeAll();
		GUI.jp.setOpaque(true);
		GUI.jp.setBackground(Color.WHITE);
		GUI.jp.setLayout(null);
		Up = new JButton("");
		Up.setSize(25, 50);
		Up.setLocation(200, 275);
		Up.setActionCommand("Up");
		Up.addActionListener(Listener);
		Down = new JButton("");
		Down.setSize(25, 50);
		Down.setLocation(200, 325);
		Down.setActionCommand("Down");
		Down.addActionListener(Listener);

		move1 = new JButton("");
		move1.setSize(125, 35);
		move1.setLocation(75, 275);
		move1.setActionCommand("Move1");
		move1.addActionListener(Listener);
		move2 = new JButton("");
		move2.setSize(125, 35);
		move2.setLocation(75, 310);
		move2.setActionCommand("Move2");
		move2.addActionListener(Listener);
		move3 = new JButton("");
		move3.setSize(125, 35);
		move3.setLocation(75, 345);
		move3.setActionCommand("Move3");
		move3.addActionListener(Listener);

		animation.setLocation(-50, -50);
		animation.setSize(400, 300);

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
		JLabel controlSkin = new JLabel();
		controlSkin.setSize(300, 205);
		controlSkin.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/controllskin.png"))
				.getScaledInstance(300, 205, Image.SCALE_SMOOTH)));
		controlSkin.setLocation(0, 225);
		GUI.backround.setSize(300, 225);
		GUI.backround.setIcon(new ImageIcon());
		GUI.backround.setLocation(0, 0);
		GUI.textBox.setSize(100, 45);
		GUI.textBox.setLocation(100, 230);
		GUI.textBox.setVisible(false);
		JLabel myStatusBar = new JLabel();
		myStatusBar.setSize(125, 150);
		myStatusBar.setLocation(85, 180);
		myStatusBar.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/status.png"))
				.getScaledInstance(125, 150, Image.SCALE_SMOOTH)));
		JLabel white = new JLabel();
		white.setLocation(105, 247);
		white.setSize(100, 10);
		white.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/white.png")).getScaledInstance(125,
				150, Image.SCALE_SMOOTH)));
		health = new JLabel();
		health.setLocation(110, 247);
		health.setSize(97, 10);
		health.setBackground(Color.WHITE);
		health.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/health.png")).getScaledInstance(97,
				10, Image.SCALE_SMOOTH)));
		JLabel theirStatusBar = new JLabel();
		theirStatusBar.setSize(125, 150);
		theirStatusBar.setLocation(85, 320);
		theirStatusBar.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/status.png"))
				.getScaledInstance(125, 150, Image.SCALE_SMOOTH)));
		JLabel white2 = new JLabel();
		white2.setLocation(105, 385);
		white2.setSize(97, 10);
		white2.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/white.png")).getScaledInstance(125,
				150, Image.SCALE_SMOOTH)));
		health2 = new JLabel();
		health2.setLocation(110, 385);
		health2.setSize(97, 10);
		health2.setBackground(Color.WHITE);
		health2.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/health.png"))
				.getScaledInstance(97, 10, Image.SCALE_SMOOTH)));
		JLabel MiniField = new JLabel();
		MiniField.setLocation(225, 247);
		MiniField.setSize(25, 150);
		MiniField.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/field.png"))
				.getScaledInstance(25, 150, Image.SCALE_SMOOTH)));

		MiniUnit.setLocation(225, 247);
		MiniUnit.setSize(25, 35);
		MiniUnit2.setLocation(225, 247);
		MiniUnit2.setSize(25, 35);
		CutsceneGUI.button.setLocation(15, 275);
		CutsceneGUI.button.setSize(50, 100);
		GUI.volume.setSize(100, 30);
		GUI.volume.setLocation(15, 375);

		GUI.jp.add(GUI.volume);
		GUI.jp.add(CutsceneGUI.button);
		GUI.jp.add(animation);
		GUI.jp.add(MiniUnit);
		GUI.jp.add(MiniUnit2);
		GUI.jp.add(theirStatusBar);
		GUI.jp.add(health2);
		GUI.jp.add(white2);
		GUI.jp.add(myStatusBar);
		GUI.jp.add(health);
		GUI.jp.add(white);
		GUI.jp.add(GUI.textBox);
		GUI.jp.add(GUI.textName);
		GUI.jp.add(GUI.textNameGUI);
		GUI.jp.add(GUI.text);
		GUI.jp.add(GUI.textGUI);
		GUI.jp.add(MiniField);
		GUI.jp.add(Up);
		GUI.jp.add(Down);
		GUI.jp.add(move1);
		GUI.jp.add(move2);
		GUI.jp.add(move3);
		GUI.jp.add(controlSkin);
		FieldClass.battle.placeUnits();
		GUI.jp.add(GUI.backround);
		GUI.f.setContentPane(GUI.jp);
		GUI.f.setVisible(true);
		GUI.GUI = "Battle";
	}
}
