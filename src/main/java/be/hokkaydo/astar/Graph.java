package be.hokkaydo.astar;

public interface Graph<T extends Node> {

    /**
     * Get {@link Node} at given coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @return the {@link Node} at given coordinates if present, null if out of bounds
     * */
    T getNode(int x, int y);

    /**
     * Add a node to the graph
     * */
    void addNode(T node);

    /**
     * Clear information from potential previous run
     * */
    void clear();

}
