package co.amscraft.broken;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

	public static final int MAIN_MENU = 0;

	public Main() {
		super("Game Test");
		this.addState(new MainMenu());
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc = new AppGameContainer(new Main());
			appgc.setDisplayMode(400, 400, false);
			appgc.setShowFPS(false);
			// Resolution and Fullscrean
			appgc.start();

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		// Create new classes that Extend BaseGameState, then call
		// this.gc.init();
		this.getState(MAIN_MENU).init(gc, this);

	}
}
