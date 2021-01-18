package co.amscraft.game.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JPanel;

import co.amscraft.game.Classes;
import co.amscraft.game.Library;
import co.amscraft.game.Main;
import co.amscraft.game.Unit;
import co.amscraft.game.battle.Battle;
import co.amscraft.game.battle.FieldClass;
import co.amscraft.game.battle.ai.FieldAI;
import co.amscraft.game.gui.BattleGUI;
import co.amscraft.game.gui.FieldGUI;
import co.amscraft.game.gui.GUI;

@SuppressWarnings("serial")
public class Listener extends JPanel implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
			Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
			int threadCount = threadArray.length;
			for (Thread thread : threadArray)
			{
				if (thread == Thread.currentThread())
				{
					thread.setName("MyThread");
				}
				else
				{
					threadCount = threadCount - 1;
				}
			}
			if (threadCount == 1)
			{

				Library Library = new Library();
				Classes Class = new Classes("");
				try
				{

					if ("Main".equals(e.getActionCommand()))
					{

						Library.playSoundEffect("interface/interface1");
						Library.buttonLight();
						GUI gui = new GUI();
						if (FieldClass.inBattle && !Battle.inBattle && !GUI.GUI.equals("Field"))
						{
							FieldClass.battle.resumeField();
							Library.playSoundEffect("NPC/thud");
						}
						else if (FieldClass.inBattle
								&& (FieldClass.battle.checkObjectives() || FieldClass.battle.talking))
						{
							Library.runStory();
						}
						else if (!FieldClass.inBattle && !Battle.inBattle)
						{
							if (!GUI.GUI.equals("Cutscene"))
							{
								gui.cutScene();
							}
							Library.runStory();
						}
						else if (FieldClass.inBattle && !Battle.inBattle && FieldGUI.battle.isEnabled())
						{
							FieldClass.battle.startBattle(FieldClass.battle.TheirUnits
									.get(FieldClass.battle.getEnemyUnit(FieldGUI.battle.getSelectedItem().toString())));
						}
						else if (FieldClass.inBattle && !Battle.inBattle && !FieldGUI.battle.isEnabled()
								&& !FieldAI.turn)
						{
							Library.clearSprite();
							Library.clearText();
							new java.util.Timer().schedule(new java.util.TimerTask()
							{
								@Override
								public void run()
								{
									FieldAI.AI.RunAI();
								}
							}, 1);
						}

					}
					else if ("Move1".equals(e.getActionCommand()))
					{
						if (!Classes.choosingSpell)
						{
							Library.playSoundEffect("interface/interface1");
							Battle.battle.turnStart(1);
						}
						else
						{
							Class.selectSpell(1);
						}

					}
					else if ("Move2".equals(e.getActionCommand()))
					{
						if (!Classes.choosingSpell)
						{
							Library.playSoundEffect("interface/interface1");
							Battle.battle.turnStart(2);
						}
						else
						{
							Class.selectSpell(2);
						}
					}
					else if ("Move3".equals(e.getActionCommand()))
					{
						if (!Classes.choosingSpell)
						{
							Library.playSoundEffect("interface/interface1");
							Battle.battle.turnStart(3);
						}
						else
						{
							Class.selectSpell(3);
						}
					}
					else if ("Up".equals(e.getActionCommand()))
					{
						Library.playSoundEffect("interface/interface1");
						if (Battle.battle.Unit1Y < 3 || (Battle.battle.Unit1Y - 1 == Battle.battle.Unit2Y))
							BattleGUI.Up.setEnabled(false);
						if (!BattleGUI.Down.isEnabled())
							BattleGUI.Down.setEnabled(true);
						if (Battle.battle.Unit1Y > 1 && !(Battle.battle.Unit1Y - 1 == Battle.battle.Unit2Y))
						{
							Battle.battle.turnStart(4);
						}

					}
					else if ("Down".equals(e.getActionCommand()))
					{
						Library.playSoundEffect("interface/interface1");
						if (Battle.battle.Unit1Y > 4 || (Battle.battle.Unit1Y + 1 == Battle.battle.Unit2Y))
							BattleGUI.Down.setEnabled(false);
						if (!BattleGUI.Up.isEnabled())
							BattleGUI.Up.setEnabled(true);
						if (Battle.battle.Unit1Y < 6 && !(Battle.battle.Unit1Y + 1 == Battle.battle.Unit2Y))
						{
							Battle.battle.turnStart(5);
						}

					}
					else if ("South".equals(e.getActionCommand()))
					{
						Library.clearSprite();
						Library.clearText();
						Library.playSoundEffect("interface/interface1");
						FieldClass.battle.moveUnit(Unit.myUnits.get(FieldClass.battle.getActiveUnit().name).LocationX,
								Unit.myUnits.get(FieldClass.battle.getActiveUnit().name).LocationY + 1,
								FieldClass.battle.getActiveUnit().name);
					}
					else if ("North".equals(e.getActionCommand()))
					{
						Library.clearSprite();
						Library.clearText();
						Library.playSoundEffect("interface/interface1");
						FieldClass.battle.moveUnit(Unit.myUnits.get(FieldClass.battle.getActiveUnit().name).LocationX,
								Unit.myUnits.get(FieldClass.battle.getActiveUnit().name).LocationY - 1,
								FieldClass.battle.getActiveUnit().name);
					}
					else if ("West".equals(e.getActionCommand()))
					{
						Library.clearSprite();
						Library.clearText();
						Library.playSoundEffect("interface/interface1");
						FieldClass.battle.moveUnit(
								Unit.myUnits.get(FieldClass.battle.getActiveUnit().name).LocationX - 1,
								Unit.myUnits.get(FieldClass.battle.getActiveUnit().name).LocationY,
								FieldClass.battle.getActiveUnit().name);
					}
					else if ("East".equals(e.getActionCommand()))
					{
						Library.clearSprite();
						Library.clearText();
						Library.playSoundEffect("interface/interface1");
						FieldClass.battle.moveUnit(
								Unit.myUnits.get(FieldClass.battle.getActiveUnit().name).LocationX + 1,
								Unit.myUnits.get(FieldClass.battle.getActiveUnit().name).LocationY,
								FieldClass.battle.getActiveUnit().name);
					}
					else if ("Units".equals(e.getActionCommand()))
					{
						Unit activeUnit = FieldClass.battle.getActiveUnit();
						Library.updateHealthBar(true, activeUnit);
						FieldClass.battle.setMapToUnit(activeUnit);
						FieldClass.battle.possibleMovesCheck();
					}
					else if ("load".equals(e.getActionCommand()))
					{
						Library.load();
						GUI GUI = new GUI();
						GUI.cutScene();
						Library.stopMusic();
						Library.playSoundEffect("interface/interface1");
						Library.runStory();
					}
					else if ("new".equals(e.getActionCommand()))
					{
						GUI GUI = new GUI();
						GUI.cutScene();
						Main.Level = 0;
						Library.playSoundEffect("interface/interface1");
						Main.story = 0;
						Library.stopMusic();
						Library.runStory();
					}

				}
				catch (Exception e1)
				{
					// e1.printStackTrace();
				}
			}
			else
			{
				System.out.println("Threads Count: " + threadCount);
			}
		}
		catch (Exception e1)
		{
			System.out.println(e1 + " avoided");
		}
	}

}
