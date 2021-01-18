package co.amscraft.game.battle.ai;

import co.amscraft.game.Library;
import co.amscraft.game.battle.Battle;

public class BattleAI
{
	public int calculateAIDistance(int movement)
	{
		if (Battle.battle.Unit1Y > Battle.battle.Unit2Y - movement)
		{
			return Battle.battle.Unit1Y - Battle.battle.Unit2Y - movement;
		}
		else
		{
			return Battle.battle.Unit2Y - Battle.battle.Unit1Y - movement;
		}
	}

	public int battleAI()
	{
		Library Library = new Library();
		int attack = 1;
		boolean attackSelected = false;
		for (int i = 1; i < 4; i++)
		{
			if (Battle.battle.Unit2.getAttack(i).damage >= Battle.battle.Unit2.getAttack(attack).damage
					&& Battle.battle.Unit2.getAttack(i).distance.get(Battle.battle.calculateDistance()))
			{
				attack = i;
				attackSelected = true;
			}

		}
		if (!attackSelected)
		{
			int attackDamage = 0;
			if ((Battle.battle.Unit2Y + 1 != Battle.battle.Unit1Y) && (Battle.battle.Unit2Y + 1 != 7))
			{
				for (int i = 1; i < 4; i++)
				{
					if (Battle.battle.Unit2.getAttack(i).distance.get(calculateAIDistance(1)))
					{
						attack = 5;
						attackDamage = (int) Battle.battle.Unit2.getAttack(i).damage;
						attackSelected = true;
					}

				}
			}
			if ((Battle.battle.Unit2Y - 1 != Battle.battle.Unit1Y) && (Battle.battle.Unit2Y - 1 != 0))
			{
				for (int i = 1; i < 4; i++)
				{
					if (Battle.battle.Unit2.getAttack(i).distance.get(calculateAIDistance(-1)) && !attackSelected)
					{
						attack = 4;
						attackSelected = true;
					}
					else if (Battle.battle.Unit2.getAttack(i).distance.get(calculateAIDistance(-1)) && attackSelected
							&& attack != 4)
					{
						if (Battle.battle.Unit2.getAttack(i).damage >= attackDamage)
						{
							attack = 4;
							attackSelected = true;
						}
					}
				}
			}
			if (!attackSelected)
			{
				attackDamage = 0;
				if ((Battle.battle.Unit2Y + 2 != Battle.battle.Unit1Y
						&& Battle.battle.Unit2Y + 1 != Battle.battle.Unit1Y) && (Battle.battle.Unit2Y + 2 != 7))
				{
					for (int i = 1; i < 4; i++)
					{
						if (Battle.battle.Unit2.getAttack(i).distance.get(calculateAIDistance(2)))
						{
							attack = 5;
							attackDamage = (int) Battle.battle.Unit2.getAttack(i).damage;
							attackSelected = true;
						}

					}
				}
				if ((Battle.battle.Unit2Y - 2 != Battle.battle.Unit1Y
						&& Battle.battle.Unit2Y - 1 != Battle.battle.Unit1Y) && (Battle.battle.Unit2Y - 2 != 0))
				{
					for (int i = 1; i < 4; i++)
					{
						if (Battle.battle.Unit2.getAttack(i).distance.get(calculateAIDistance(-2)) && !attackSelected)
						{
							attack = 4;
							attackSelected = true;

						}
						else if (Battle.battle.Unit2.getAttack(i).distance.get(calculateAIDistance(-2))
								&& attackSelected && attack != 4 && attack != 5)
						{
							if (Battle.battle.Unit2.getAttack(i).damage >= attackDamage)
							{
								attack = 4;
								attackSelected = true;
							}
						}
					}

				}
			}
			if (!attackSelected)
			{
				if (((Battle.battle.Unit2Y - 1 != Battle.battle.Unit1Y) && (Battle.battle.Unit2Y - 1 != 0)))
				{
					attack = 4;
					if (!(((Battle.battle.Unit2Y + 1 != Battle.battle.Unit1Y) && (Battle.battle.Unit2Y + 1 != 7))
							&& !attackSelected))
						attackSelected = true;
					else
						attackSelected = Library.diceRoll(50);
				}
				if (((Battle.battle.Unit2Y + 1 != Battle.battle.Unit1Y) && (Battle.battle.Unit2Y + 1 != 7))
						&& !attackSelected)
				{
					attackSelected = true;
					attack = 5;
				}

			}

		}
		if (!attackSelected)
		{
			attack = 1;
		}

		return attack;
	}
}
