package co.amscraft.game.gui;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import co.amscraft.game.listeners.VolumeListener;

public class GUI
{
	public static JPanel jp = new JPanel();
	public static JLabel sprite = new JLabel();
	public static JLabel backround = new JLabel();
	public static JFrame f = new JFrame("Cloudside");
	public static JLabel textGUI = new JLabel();
	public static JLabel text = new JLabel();
	public static JLabel textName = new JLabel();
	public static String GUI;
	public static JLabel textNameGUI = new JLabel();
	public static JTextField textBox = new javax.swing.JTextField();
	public static JSlider volume = new JSlider(JSlider.HORIZONTAL, 0, 86, 70);

	public void startUp()
	{
		volume.addChangeListener(new VolumeListener());
		volume.setFocusable(false);
		volume.setOpaque(false);
		f.setSize(300, 450);
		f.setLocation(550, 225);
//		f.setLocationByPlatform(true);
//		f.setResizable(false);
//		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setFocusable(true);
		f.requestFocusInWindow();
		f.addKeyListener(FieldGUI.key);
		/*
		 * f.addComponentListener(new ComponentAdapter() { public void
		 * componentResized(ComponentEvent evt) { try { jp.setSize(f.getSize());
		 * if (GUI.equals("Field")) { FieldGUI.controlSkin.setSize(f.getWidth(),
		 * f.getHeight() / 2); FieldGUI.controlSkin.setIcon( new
		 * ImageIcon(ImageIO.read(new
		 * File("liberary/images/pictures/controllskin.png"))
		 * .getScaledInstance(f.getWidth(), f.getHeight() / 2,
		 * Image.SCALE_SMOOTH))); FieldGUI.controlSkin.setLocation(0, (int)
		 * (f.getHeight() / 2)); FieldGUI.units.setLocation(f.getWidth() / 10,
		 * (int) (f.getHeight() / 1.8)); FieldGUI.units.setSize((int)
		 * (f.getWidth() / 3.6), f.getHeight() / 10);
		 * 
		 * FieldGUI.battle.setLocation((int)(f.getWidth() / 1.7), (int)
		 * (f.getHeight() / 1.8)); FieldGUI.battle.setSize((int) (f.getWidth() /
		 * 3.6), f.getHeight() / 10);
		 * 
		 * FieldGUI.North.setSize((int) (f.getWidth() / 5.5), (int)
		 * f.getHeight() / 8); FieldGUI.North.setIcon(new ImageIcon(
		 * ImageIO.read(new
		 * File("liberary/images/pictures/North.png")).getScaledInstance( (int)
		 * (f.getWidth() / 6), (int) (f.getHeight() / 8.5),
		 * Image.SCALE_SMOOTH))); FieldGUI.North.setLocation((int) (f.getWidth()
		 * / 2.5), (int) (f.getHeight() / 1.75));
		 * 
		 * FieldGUI.South.setLocation((int) (f.getWidth() / 2.5), (int)
		 * (f.getHeight() / 1.7)); FieldGUI.South.setSize((int) (f.getWidth() /
		 * 5.5), (int) f.getHeight() / 8); FieldGUI.South.setIcon(new ImageIcon(
		 * ImageIO.read(new
		 * File("liberary/images/pictures/South.png")).getScaledInstance( (int)
		 * (f.getWidth() / 6), (int) (f.getHeight() / 8.5),
		 * Image.SCALE_SMOOTH))); FieldGUI.South.setLocation((int) (f.getWidth()
		 * / 2.5), (int) (f.getHeight() / 1.25));
		 * 
		 * CutsceneGUI.button.setSize((int) (f.getWidth() / 5.5), (int)
		 * f.getHeight() / 8); CutsceneGUI.button.setLocation((int)
		 * (f.getWidth() / 2.5), (int) (f.getHeight() / 1.465));
		 * 
		 * FieldGUI.East.setSize((int) (f.getWidth() / 5.5), (int) f.getHeight()
		 * / 8); FieldGUI.East.setIcon(new ImageIcon( ImageIO.read(new
		 * File("liberary/images/pictures/East.png")).getScaledInstance( (int)
		 * (f.getWidth() / 6), (int) (f.getHeight() / 8.5),
		 * Image.SCALE_SMOOTH))); FieldGUI.East.setLocation((int) (f.getWidth()
		 * / 1.7), (int) (f.getHeight() / 1.465));
		 * 
		 * FieldGUI.West.setSize((int) (f.getWidth() / 5.7), (int) f.getHeight()
		 * / 8); FieldGUI.West.setIcon(new ImageIcon( ImageIO.read(new
		 * File("liberary/images/pictures/West.png")).getScaledInstance( (int)
		 * (f.getWidth() / 6), (int) (f.getHeight() / 8.5),
		 * Image.SCALE_SMOOTH))); FieldGUI.West.setLocation((int) (f.getWidth()
		 * / 4.5), (int) (f.getHeight() / 1.465));
		 * 
		 * FieldGUI.myStatusBar.setLocation((int) (f.getWidth() / 2.5), (int)
		 * (f.getHeight() / 1.9));
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * } f.repaint(); } catch (Exception e) { e.printStackTrace(); } } });
		 */
	}

	public void battleGUI()
	{
		BattleGUI battle = new BattleGUI();
		try
		{
			battle.battleScene();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void cutScene()
	{
		CutsceneGUI Scene = new CutsceneGUI();
		try
		{
			Scene.cutScene();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		f.getContentPane().repaint();
	}

}
