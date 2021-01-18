package co.amscraft.game.listeners;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import co.amscraft.game.gui.BattleGUI;
import co.amscraft.game.gui.CutsceneGUI;
import co.amscraft.game.gui.FieldGUI;
import co.amscraft.game.gui.GUI;

public class Key implements KeyListener
{

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	private boolean isComponentInPanel(Component component)
	{
		return java.util.Arrays.asList(GUI.jp.getComponents()).contains(component);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		Listener Listener = new Listener();
		ActionEvent event = null;
		try
		{
			if (e.getKeyCode() == 87 && FieldGUI.North.isEnabled() && isComponentInPanel(FieldGUI.North))
			{
				event = new ActionEvent(GUI.f, 0, "North");
			}
			else if (e.getKeyCode() == 68 && FieldGUI.East.isEnabled() && isComponentInPanel(FieldGUI.East))
			{
				event = new ActionEvent(GUI.f, 0, "East");
			}
			else if (e.getKeyCode() == 83 && FieldGUI.South.isEnabled() && isComponentInPanel(FieldGUI.South))
			{
				event = new ActionEvent(GUI.f, 0, "South");
			}
			else if (e.getKeyCode() == 65 && FieldGUI.West.isEnabled() && isComponentInPanel(FieldGUI.West))
			{
				event = new ActionEvent(GUI.f, 0, "West");
			}
			else if (e.getKeyCode() == 32 && CutsceneGUI.button.isEnabled() && isComponentInPanel(CutsceneGUI.button))
			{
				event = new ActionEvent(GUI.f, 0, "Main");
			}
			else if ((e.getKeyCode() == 38 || e.getKeyCode() == 87) && BattleGUI.Up.isEnabled()
					&& isComponentInPanel(BattleGUI.Up))
			{
				event = new ActionEvent(GUI.f, 0, "Up");
			}
			else if ((e.getKeyCode() == 40 || e.getKeyCode() == 83) && BattleGUI.Down.isEnabled()
					&& isComponentInPanel(BattleGUI.Down))
			{
				event = new ActionEvent(GUI.f, 0, "Down");
			}
			else if (e.getKeyCode() == 49 && BattleGUI.move1.isEnabled() && isComponentInPanel(BattleGUI.move1))
			{
				event = new ActionEvent(GUI.f, 0, "Move1");
			}
			else if (e.getKeyCode() == 50 && BattleGUI.move2.isEnabled() && isComponentInPanel(BattleGUI.move2))
			{
				event = new ActionEvent(GUI.f, 0, "Move2");
			}
			else if (e.getKeyCode() == 51 && BattleGUI.move3.isEnabled() && isComponentInPanel(BattleGUI.move3))
			{
				event = new ActionEvent(GUI.f, 0, "Move3");
			}
			if (event != null)
			{
				Listener.actionPerformed(event);
			}
		}
		catch (NullPointerException exception)
		{
			System.out.println("Empty Key Press Alert");
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

}
