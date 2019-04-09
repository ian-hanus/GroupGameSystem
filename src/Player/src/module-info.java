module player {
    requires Engine;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;
    requires java.datatransfer;

    exports PlayerMain;
    exports Features;
    exports Regions;
}