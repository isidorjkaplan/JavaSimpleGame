package co.amscraft.game.battle;

public class Sprite
{
	public Sprite() {
		XSize = 200;
		YSize = 200;
		XLocation = 125;
		YLocation = 25;
	}

	public Sprite(String sprite) {
		XSize = 200;
		YSize = 200;
		XLocation = 125;
		YLocation = 25;
		file = sprite;
	}

	public int XSize;
	public int YSize;
	public int XLocation;
	public int YLocation;
	public String file;

	public void setSize(int X, int Y)
	{
		XSize = X;
		YSize = Y;
	}

	public void setLocation(int X, int Y)
	{
		XLocation = X;
		YLocation = Y;
	}

}
