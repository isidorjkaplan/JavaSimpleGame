package co.amscraft.game.listeners;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import co.amscraft.game.Library;

public class VolumeListener implements ChangeListener
{

	@Override
	public void stateChanged(ChangeEvent e)
	{
		// -80 to 6 range
		JSlider source = (JSlider) e.getSource();
		Library.Functions.setVolume((int) source.getValue() - 80);
	}

}
