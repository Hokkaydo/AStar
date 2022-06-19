package be.hokkaydo.astar;

public interface Node extends Comparable<Node> {

    /**
     * Get distance between this Node and the given one
     * @param node the distant {@link Node} we want the distance from
     * @return distance
     * */
    double distance(Node node);

    /**
     * @return node's heuristic value
     * */
    double getHeuristic();

    /**
     * @return node's cost from start cell
     * */
    int getCost();

    /**
     * Get X coordinate
     * */
    int getX();

    /**
     * Get Y coordinate
     * */
    int getY();

    void setAncestor(Node node);

    void computeCostAndHeuristic(Node target);

    /**
     * Get the node visited previously to the current one
     * @return ancestor {@link Node} or null if no one exists
     * */
    Node getAncestor();

    /**
     * Clear information from a potential previous run
     * */
    void clear();

}
