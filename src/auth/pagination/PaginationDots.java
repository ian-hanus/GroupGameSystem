package auth.pagination;

import auth.Callback;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static auth.Dimensions.PAGINATION_DOT_SIZE;

public class PaginationDots {
    private GridPane mainView;
    private List<ImageView> dots;
    private int index;
    public PaginationDots() {
        dots = new ArrayList<>();
        var dot = new ImageView(new Image(PaginationDots.class.getResourceAsStream("/img/dotfilled.png")));
        dot.setFitHeight(PAGINATION_DOT_SIZE);
        dot.setFitWidth(PAGINATION_DOT_SIZE);
        dots.add(dot);
        mainView = new GridPane();
        mainView.getChildren().add(dots.get(0));
        mainView.setAlignment(Pos.CENTER);
        this.index = 0;
    }
    public GridPane getView() {
        return mainView;
    }
    public void increment() {
        var latest = new ImageView(new Image(PaginationDots.class.getResourceAsStream("/img/dotempty.png")));
        latest.setFitHeight(PAGINATION_DOT_SIZE);
        latest.setFitWidth(PAGINATION_DOT_SIZE);
        dots.add(latest);
        mainView.getChildren().add(latest);
    }
    public void decrement() {
        mainView.getChildren().remove(dots.size() - 1);
        dots.remove(dots.size() - 1);
    }
    public void setIndex(int index) {
        dots.remove(this.index);
        mainView.getChildren().remove(this.index);
        var latest = new ImageView((new Image(PaginationDots.class.getResourceAsStream("/img/dotfilled.png"))));
        latest.setFitHeight(PAGINATION_DOT_SIZE);
        latest.setFitWidth(PAGINATION_DOT_SIZE);
        dots.add(index, latest);
        mainView.getChildren().add(index, latest);
    }
}
