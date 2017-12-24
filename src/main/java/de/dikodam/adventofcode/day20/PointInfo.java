package de.dikodam.adventofcode.day20;

import de.dikodam.adventofcode.tools.Triple;

public class PointInfo extends Triple<Vector3D, Vector3D, Vector3D> implements Comparable {

    private final int lineIndex;

    public PointInfo(Vector3D point, Vector3D velocity, Vector3D acceleration, int lineIndex) {
        super(point, velocity, acceleration);
        this.lineIndex = lineIndex;
    }

    public Vector3D getPosition() {
        return super.getX();
    }

    public Vector3D getVelocity() {
        return super.getY();
    }

    public Vector3D getAcceleration() {
        return super.getZ();
    }

    private void setPosition(Vector3D position) {
        this.x = position;
    }

    private void setVelocity(Vector3D velocity) {
        this.y = velocity;
    }

    public void tick() {
        // v += a;
        setVelocity(Vector3D.add(getVelocity(), getAcceleration()));
        // p += v;
        setPosition(Vector3D.add(getPosition(), getVelocity()));
    }

    public int getLineIndex() {
        return lineIndex;
    }

    @Override
    public int compareTo(Object o) {
        PointInfo other = (PointInfo) o;
        int accelerationComparison = getAcceleration().compareTo(other.getAcceleration());
        if (accelerationComparison != 0) {
            return accelerationComparison;
        }
        int velocityComparison = getVelocity().compareTo(other.getVelocity());
        if (velocityComparison != 0) {
            return velocityComparison;
        }
        return getPosition().compareTo(other.getPosition());
    }

}
