package be.hokkaydo.astar;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Formatter;

public class Cell extends StackPane implements Node {

    int column;
    int row;

    private static final int hweight = 2;

    int cost = 0;
    double heuristic = cost;
    Node ancestor;

    public Cell(Pane grid, int column, int row) {
        this.column = column;
        this.row = row;
        setOpacity(0.9);
        text = new Text(70 * column + 7, 60 * row + 20, "");
        text.setFont(Font.font("Comic Sans MS", 14));
        grid.getChildren().add(text);
    }

    @Override
    public String toString() {
        return "Cell{" +
                       "column=" + column +
                       ", row=" + row +
                       ", cost=" + cost +
                       ", heuristic=" + heuristic +
                       '}';
    }

    private final Text text;

    @Override
    public void computeCostAndHeuristic(Node target) {

        if (this.ancestor != null) {
            this.cost = this.ancestor.getCost() + 1;
        }
        this.heuristic = this.cost + distance(target);
        this.heuristic *= hweight;

        text.setText("H: " + new Formatter().format("%.1f", this.heuristic) + " \nC:" + this.cost);
    }

    public void clear() {
        this.ancestor = null;
        this.cost = 0;
        this.heuristic = 0;
        text.setText("");
        this.setStyle(Main.CELL_BASE_STYLE);
    }

    @Override
    public double distance(Node target) {
        return Math.sqrt(Math.pow(target.getX() - this.getX(), 2) + Math.pow(target.getY() - this.getY(), 2));
    }

    @Override
    public int compareTo(Node cell) {
        return Double.compare(this.getHeuristic(), cell.getHeuristic());
    }

    @Override
    public int getX() {
        return this.column;
    }

    @Override
    public int getY() {
        return this.row;
    }

    @Override
    public Node getAncestor() {
        return ancestor;
    }

    @Override
    public double getHeuristic() {
        return heuristic;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void setAncestor(Node node) {
        this.ancestor = node;
    }

}