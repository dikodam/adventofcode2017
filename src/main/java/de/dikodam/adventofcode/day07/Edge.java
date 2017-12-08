package de.dikodam.adventofcode.day07;

import java.util.Objects;

public class Edge {
    private Node ancestor;
    private Node child;

    public Edge(Node ancestor, Node child) {
        this.ancestor = ancestor;
        this.child = child;
    }

    public Node getAncestor() {
        return ancestor;
    }

    public Node getChild() {
        return child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(ancestor, edge.ancestor) &&
            Objects.equals(child, edge.child);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ancestor, child);
    }
}
