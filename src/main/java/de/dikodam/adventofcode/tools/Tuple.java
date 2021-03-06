package de.dikodam.adventofcode.tools;

import java.util.Objects;

public class Tuple<X,Y> {
    protected X x;
    protected Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;

    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return x == tuple.x &&
            y == tuple.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Tuple[" + x + "|" + y + "]";
    }
}
