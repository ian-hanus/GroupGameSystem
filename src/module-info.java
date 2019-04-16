module voogasalad.crackingopen {

    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;
    requires javafx.fxml;
    requires org.json;

    exports GameCenter;
    exports auth.screens;
    exports Launcher.src.Initial;
    exports auth.auth_fxml_controllers;

}

