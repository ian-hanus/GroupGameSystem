package auth.pagination;

import auth.Callback;
import auth.UIElement;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PaginationUIElement implements UIElement {
    private BorderPane mainView;
    private PaginationDots dots;
    private int index = 0, numPages = 0;
    private Arrow leftArrow;
    private Arrow rightArrow;
    private List<Node> pages;
    private String ID;
    private Callback onPageChanged;

    public PaginationUIElement(Node dynamicView, Callback onPageChanged, String ID) {
        leftArrow = new LeftArrow();
        rightArrow = new RightArrow();
        leftArrow.onClick((o) -> goBack());
        rightArrow.onClick((o) -> goForwards());
        pages = new ArrayList<>();
        pages.add(dynamicView);
        dots = new PaginationDots();
        mainView = new BorderPane();
        mainView.setCenter(dynamicView);
        mainView.setLeft(leftArrow.getView());
        mainView.setRight(rightArrow.getView());
        mainView.setBottom(dots.getView());
        this.ID = ID;
        this.onPageChanged = onPageChanged;
    }

    private void goForwards() {
        if (index < numPages) {
            mainView.setCenter(pages.get(++index));
            dots.setIndex(index);
        }
        onPageChanged.onCallback(index);
    }

    private void goBack() {
        if (index > 0) {
            mainView.setCenter(pages.get(--index));
            dots.setIndex(index);
        }
        onPageChanged.onCallback(index);
    }

    public void addPage(Node pageView) {
        pages.add(pageView);
        numPages++;
        dots.increment();
    }

    public void removePage(int index) {
        pages.remove(index);
        numPages--;
        dots.decrement();
        if (index == numPages) {
            goBack();
        }
    }

    @Override
    public Node getView() {
        return mainView;
    }

    @Override
    public String getID() {
        return ID;
    }
}
