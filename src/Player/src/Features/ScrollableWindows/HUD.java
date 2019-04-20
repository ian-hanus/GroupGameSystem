package Player.src.Features.ScrollableWindows;

import java.util.ArrayList;
import java.util.Arrays;

public class HUD extends ScrollableWindow {
    private ArrayList<HUDItem> myItems;

    public HUD(HUDItem[] hudItems) {
        super();
        myItems = new ArrayList(Arrays.asList(myItems));
        update();
    }

    @Override
    public void update() {
        clearText();
        for (HUDItem item : myItems) {
            addText(item.getCurrentString());
        }
    }
}
