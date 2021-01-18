package co.amscraft.game.gui;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import co.amscraft.game.Library;
import co.amscraft.game.listeners.Listener;

public class LoadGUI
{
	public void createGUI()
	{

		Listener Listener = new Listener();
		GUI.GUI = "Files";
		GUI.jp.removeAll();
		GUI.jp.setOpaque(true);
		GUI.jp.setBackground(Color.WHITE);
		GUI.jp.setLayout(null);
		JButton load = new JButton("Load/Resume Game");
		load.setSize(200, 35);
		load.setLocation(50, 280);
		load.setActionCommand("load");
		load.addActionListener(Listener);
		JButton file = new JButton("New Game");
		file.setSize(200, 35);
		file.setLocation(50, 335);
		file.setActionCommand("new");
		file.addActionListener(Listener);
		JLabel controlSkin = new JLabel();
		controlSkin.setSize(300, 205);
		JLabel cloudside = new JLabel();
		cloudside.setSize(300, 225);
		try
		{
			controlSkin.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/controllskin.png"))
					.getScaledInstance(300, 205, Image.SCALE_SMOOTH)));
			cloudside.setIcon(new ImageIcon(ImageIO.read(new File("liberary/images/pictures/cloudside.png"))
					.getScaledInstance(300, 225, Image.SCALE_SMOOTH)));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Library.Functions.setSong("Heroic_Demise_New_0");
		controlSkin.setLocation(0, 225);
		cloudside.setLocation(0, 0);
		GUI.volume.setSize(100, 30);
		GUI.volume.setLocation(15, 375);

		GUI.jp.add(GUI.volume);
		GUI.jp.add(load);
		GUI.jp.add(file);
		GUI.jp.add(controlSkin);
		GUI.jp.add(cloudside);
		GUI.f.setContentPane(GUI.jp);
		GUI.f.setVisible(true);
		// GUI.jp.add(, constraints);

	}
}
