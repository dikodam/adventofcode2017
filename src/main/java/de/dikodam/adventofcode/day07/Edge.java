package de.dikodam.adventofcode.day07;

import de.dikodam.adventofcode.tools.Tuple;

import java.util.Objects;

public class Edge extends Tuple<Node, Node> {

    public Edge(Node ancestor, Node child) {
        super(ancestor, child);
    }

    public Node getAncestor() {
        return getX();
    }

    public Node getChild() {
        return getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(getX(), edge.getX()) &&
            Objects.equals(getY(), edge.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
