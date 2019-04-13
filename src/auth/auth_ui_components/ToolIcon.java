package auth.auth_ui_components;

import auth.Callback;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ToolIcon extends Icon{
    public ToolIcon(String iconID, String tooltipText, Callback onClickCallback) {
        super(iconID, tooltipText, onClickCallback);
    }
}
