package co.amscraft.game;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import co.amscraft.game.gui.LoadGUI;
import co.amscraft.game.gui.GUI;

public class Main
{

	static Library Liberary = new Library();
	public static String name = "Corrin";
	public static int Level = 0;
	public static int story = 0;

	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException
	{
		LoadGUI GUI = new LoadGUI();
		GUI gui = new GUI();
		gui.startUp();
		Library.buildDefaultClasses();
		Library.createDefaultAttacks();
		GUI.createGUI();
	}

}
