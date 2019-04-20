package Player.src.Features.ScrollableWindows;

public class HUDItem {
    private String myName;
    private Object myValue;

    public HUDItem(String name, Object value) {
        myName = name;
        myValue = value;
    }

    public String getCurrentString() {
        return myName + ": " + myValue.toString();
    }
}
