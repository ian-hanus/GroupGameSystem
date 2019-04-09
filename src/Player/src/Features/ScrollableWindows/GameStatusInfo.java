package Features.ScrollableWindows;

public class GameStatusInfo extends ScrollableWindow {

    String gameStatus = "";

    public GameStatusInfo() {

    }

    protected void updateGameStatus() {
        gameStatus = "New Info";
        addText(gameStatus);
    }

    @Override
    protected void refreshWindow() {
        gameStatus = "";
        addText(gameStatus);
    }

}
