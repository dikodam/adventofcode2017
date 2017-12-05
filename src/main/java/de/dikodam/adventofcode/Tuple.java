package de.dikodam.adventofcode;

import java.util.Objects;

public class Tuple<X,Y> {
    private final X x;
    private final Y y;

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
