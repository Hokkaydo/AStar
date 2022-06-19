module be.hokkaydo.astar {
    requires javafx.controls;
    requires javafx.fxml;


    opens be.hokkaydo.astar to javafx.fxml;
    exports be.hokkaydo.astar;
}