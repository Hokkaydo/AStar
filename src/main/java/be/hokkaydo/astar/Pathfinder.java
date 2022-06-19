package be.hokkaydo.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Pathfinder {

    private final Graph<Cell> graph;
    public Pathfinder(Graph2D graph) {
        this.graph = graph;
    }
    private final List<Node> BLOCKED_NODES = new ArrayList<>();

    public void addBlockedNode(Node node) {
        this.BLOCKED_NODES.add(node);
    }

    public void removeBlockedNode(Node node) {
        this.BLOCKED_NODES.remove(node);
    }

    public List<Node> getBlockedNodes() {
        return List.copyOf(BLOCKED_NODES);
    }

    private final List<Node> closedList = new ArrayList<>();
    private final PriorityQueue<Node> openList = new PriorityQueue<>();
    public List<Node> getAStarPath(Cell start, Cell target) {
        openList.clear();
        closedList.clear();

        openList.add(start);
        while(!openList.isEmpty()) {
            Node u = openList.poll();
            if(u.getY() == target.row && u.getX() == target.column) {
                return getAncestors(u, new ArrayList<>());
            }
            List<Node> neighbours = getNeighbours(u);
            for (Node v : neighbours) {
                if(!(closedList.contains(v) || (openList.contains(v)))) {
                    v.setAncestor(u);
                    v.computeCostAndHeuristic(target);
                    openList.add(v);
                }
            }
            closedList.add(u);
        }
        return new ArrayList<>();
    }

    private List<Node> getAncestors(Node u, List<Node> ancestors) {
        if(u == null) return ancestors;
        ancestors.add(u);
        return getAncestors(u.getAncestor(), ancestors);
    }

    private List<Node> getNeighbours(Node u) {
        List<Node> neighbours = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            int x = i + u.getX();
            for (int j = -1; j < 2; j++) {
                int y = j + u.getY();
                if(i == 0 & j == 0) continue;
                Node cell = graph.getNode(x, y);
                if(cell == null) continue;
                if(BLOCKED_NODES.contains(cell)) continue;
                neighbours.add(cell);
            }
        }
        return neighbours;
    }
}
