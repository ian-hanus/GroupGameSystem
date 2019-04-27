package Player.src.Features.Sliders;

import hud.NumericalDataTracker;

/**
 * This class is used to set the number of lives during a game.
 * @author Carter Gay
 */
public class LivesSlider extends PlayerSlider {

    private static final String TITLE = "Lives";

    public LivesSlider() {

    }

    @Override
    protected void handleItemSelected(Number item) {
        double myLives = item.doubleValue();
    }

    @Override
    protected double[] getMinMaxCurrentVals() {
        return DEFAULT_MIN_MAX_CURRENT;
    }
}
