package be.hokkaydo.astar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

public class Main extends Application {

    int rows = 15;
    int columns = 20;
    int width = 1400;
    int height = 900;

    public static final String CELL_BASE_STYLE = "-fx-border-color: black; -fx-border-width: 1px;";

    @Override
    public void start(Stage stage) {
        Graph2D graph = new Graph2D(columns, rows, width, height);
        Pathfinder pathfinder = new Pathfinder(graph);
        StackPane root = new StackPane();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell cell = new Cell(graph, column, row);
                graph.addNode(cell);
                cell.styleProperty().set(CELL_BASE_STYLE);
                cell.setOnMouseClicked(e -> {
                    if(pathfinder.getBlockedNodes().contains(cell)) {
                        pathfinder.removeBlockedNode(cell);
                        cell.setStyle(cell.getStyle() + "-fx-background-color:white;");
                    }else {
                        pathfinder.addBlockedNode(cell);
                        cell.setStyle(cell.getStyle() + "-fx-background-color:grey;");
                    }
                });
            }
        }
        Cell[] cells = initializeStartAndTarget(graph);
        Cell start = cells[0];
        Cell target = cells[1];
        Button button = new Button("Run !");
        button.setTranslateY(-height/2.0 + 40);
        button.setTranslateX(-width/2.0 + 40);
        button.setOnMouseClicked(e -> {
            graph.clear();
            pathfinder.getBlockedNodes().forEach((c -> ((Cell)c).setStyle(CELL_BASE_STYLE + "-fx-background-color: grey;")));
            List<Node> path = pathfinder.getAStarPath(start, target);
            for (Node cell : path) {
                ((Cell)cell).setStyle(CELL_BASE_STYLE + " -fx-background-color: cyan;");
            }
            start.setStyle(CELL_BASE_STYLE + " -fx-background-color: green;");
            target.setStyle(CELL_BASE_STYLE + " -fx-background-color: red;");
        });
        root.getChildren().addAll(graph);
        root.getChildren().add(button);
        Scene scene = new Scene(root, width, height);
        stage.setTitle("A*");
        stage.setScene(scene);
        stage.show();

    }
    public Cell[] initializeStartAndTarget(Graph<Cell> graph) {
        Random random = new Random();
        int row = random.nextInt(rows);
        int column = random.nextInt(columns);
        Cell start = graph.getNode(column, row);
        start.setStyle(CELL_BASE_STYLE + "-fx-background-color: green;");
        row = random.nextInt(rows);
        column = random.nextInt(columns);
        Cell target = graph.getNode(column, row);
        target.setStyle(CELL_BASE_STYLE + "-fx-background-color: red;");
        return new Cell[]{start, target};
    }

    public static void main(String[] args) {
        launch();
    }

}