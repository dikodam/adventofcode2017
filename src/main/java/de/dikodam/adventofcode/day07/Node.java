package de.dikodam.adventofcode.day07;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String name;
    private final int weight;
    private Node ancestor;
    private List<Node> children;

    public Node(String name, int weight) {
        this.name = name;
        this.weight = weight;
        ancestor = null;
        children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public Node getAncestor() {
        return ancestor;
    }

    public void setAncestor(Node ancestor) {
        this.ancestor = ancestor;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }



    @Override
    public String toString() {
        return String.format("%s: (%d)", name, weight);
    }
}
