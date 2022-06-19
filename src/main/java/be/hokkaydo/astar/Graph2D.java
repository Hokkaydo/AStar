package be.hokkaydo.astar;

import javafx.scene.layout.Pane;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Graph2D extends Pane implements Graph<Cell> {

    int rows;
    int columns;

    double width;
    double height;

    Cell[][] cells;

    public Graph2D(int columns, int rows, double width, double height) {

        this.columns = columns;
        this.rows = rows;
        this.width = width;
        this.height = height;

        cells = new Cell[rows][columns];

    }

    @Override
    public void addNode(Cell cell) {

        cells[cell.getY()][cell.getX()] = cell;

        double w = width / columns;
        double h = height / rows;
        double x = w * cell.getX();
        double y = h * cell.getY();

        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(w);
        cell.setPrefHeight(h);

        getChildren().add(cell);

    }

    @Override
    public void clear() {
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                value.clear();
            }
        }
    }

    @Override
    public Cell getNode(int column, int row) {
        if(column >= columns || column < 0 || row >= rows || row < 0) return null;
        return cells[row][column];
    }

    @Override
    public String toString() {
        return "Grid{" +
                       "rows=" + rows +
                       ", columns=" + columns +
                       ", width=" + width +
                       ", height=" + height +
                       ", cells=" + Arrays.stream(cells).map(Arrays::toString).collect(Collectors.toList()) +
                       '}';
    }


}
