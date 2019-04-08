package auth.pagination;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class PaginationDots {
    private GridPane mainView;
    private List<ImageView> dots;
    private int index;
    public PaginationDots() {
        dots = new ArrayList<>();
        dots.add(new ImageView(new Image(PaginationDots.class.getResourceAsStream("/img/dotfilled.png"))));
        mainView = new GridPane();
        mainView.getChildren().add(dots.get(0));
        int index = 0;
    }
    public GridPane getView() {
        return mainView;
    }
    public void increment() {
        var latest = new ImageView(new Image(PaginationDots.class.getResourceAsStream("/img/dotempty.png")));
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
        dots.add(index, latest);
        mainView.getChildren().add(index, latest);
    }
}
