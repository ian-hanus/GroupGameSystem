package ECS.Components;

public class ImpassableComponent {
    private boolean myImpassableRight;
    private boolean myImpassableLeft;
    private boolean myImpassableTop;
    private boolean myImpassableBottom;

    public ImpassableComponent(boolean impassableRight, boolean impassableLeft, boolean impassableTop, boolean impassableBottom) {
        this.myImpassableRight = impassableRight;
        this.myImpassableLeft = impassableLeft;
        this.myImpassableTop = impassableTop;
        this.myImpassableBottom = impassableBottom;
    }

    public boolean isImpassableRight() {
        return myImpassableRight;
    }

    public void setImpassableRight(boolean impassableRight) {
        this.myImpassableRight = impassableRight;
    }

    public boolean isImpassableLeft() {
        return myImpassableLeft;
    }

    public void setImpassableLeft(boolean impassableLeft) {
        this.myImpassableLeft = impassableLeft;
    }

    public boolean isImpassableTop() {
        return myImpassableTop;
    }

    public void setImpassableTop(boolean impassableTop) {
        this.myImpassableTop = impassableTop;
    }

    public boolean isImpassableBottom() {
        return myImpassableBottom;
    }

    public void setImpassableBottom(boolean impassableBottom) {
        this.myImpassableBottom = impassableBottom;
    }
}
