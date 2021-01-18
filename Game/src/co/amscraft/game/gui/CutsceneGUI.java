package co.amscraft.game.gui;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import co.amscraft.game.listeners.Listener;

public class CutsceneGUI
{
	public static JButton button;
	public static JLabel bpA = new JLabel();

	public void cutScene() throws IOException
	{
		GUI.GUI = "Cutscene";
		Listener Listener = new Listener();
		GUI.jp.removeAll();
		GUI.jp.setOpaque(true);
		GUI.jp.setBackground(Color.WHITE);
		GUI.jp.setLayout(null);

		button = new JButton("");
		bpA.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/button.png")).getScaledInstance(150,
				100, Image.SCALE_SMOOTH)));
		button.setSize(150, 100);
		button.setLocation(75, 275);
		button.add(bpA);
		button.setActionCommand("Main");
		button.addActionListener(Listener);
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
		JLabel controlSkin = new JLabel();
		controlSkin.setSize(300, 205);
		controlSkin.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/controllskin.png"))
				.getScaledInstance(300, 205, Image.SCALE_SMOOTH)));
		controlSkin.setLocation(0, 225);
		GUI.backround.setSize(300, 225);
		GUI.backround.setIcon(new ImageIcon());
		GUI.backround.setLocation(0, 0);
		GUI.textBox.setSize(100, 45);
		GUI.textBox.setLocation(100, 235);
		GUI.textBox.setVisible(false);
		GUI.volume.setSize(100, 30);
		GUI.volume.setLocation(15, 375);

		GUI.jp.add(GUI.volume);
		GUI.jp.add(GUI.textBox);
		GUI.jp.add(GUI.textName);
		GUI.jp.add(GUI.textNameGUI);
		GUI.jp.add(GUI.text);
		GUI.jp.add(GUI.textGUI);
		GUI.jp.add(button);
		GUI.jp.add(controlSkin);
		GUI.jp.add(GUI.sprite);
		GUI.jp.add(GUI.backround);
		GUI.f.setContentPane(GUI.jp);
		GUI.f.setVisible(true);
	}

}
