module questgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens questgame to javafx.fxml;
    opens questgame.controllers to javafx.fxml;
    opens questgame.domain to javafx.fxml;

    exports questgame;
    exports questgame.domain;
    exports questgame.controllers;
}