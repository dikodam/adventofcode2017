package de.dikodam.adventofcode.day20;

import de.dikodam.adventofcode.tools.Triple;

public class Vector3D extends Triple<Integer, Integer, Integer> implements Comparable {
    public Vector3D(Integer integer, Integer integer2, Integer integer3) {
        super(integer, integer2, integer3);
    }

    public int getManhattanDistance() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    @Override
    public int compareTo(Object o) {
        return getManhattanDistance() - ((Vector3D) o).getManhattanDistance();
    }

    public static Vector3D add(Vector3D v1, Vector3D v2) {
        return new Vector3D(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

}
