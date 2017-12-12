package de.dikodam.adventofcode.day11;

public enum Direction {
    NORTH("n") {
        @Override
        public Movement getMovement() {
            return new Movement(0d, 1d);
        }
    },
    SOUTH("s") {
        @Override
        public Movement getMovement() {
            return new Movement(0d, -1d);
        }
    },
    NORTHEAST("ne") {
        @Override
        public Movement getMovement() {
            return new Movement(1d, 0.5d);
        }
    },
    NORTWEST("nw") {
        @Override
        public Movement getMovement() {
            return new Movement(-1d, 0.5d);
        }
    },
    SOUTHEAST("se") {
        @Override
        public Movement getMovement() {
            return new Movement(1d, -0.5d);
        }
    },
    SOUTHWEST("sw") {
        @Override
        public Movement getMovement() {
            return new Movement(-1d, -0.5d);
        }
    };

    private String value;

    Direction(String value) {
        this.value = value;
    }

    public abstract Movement getMovement();

    public static Direction getByValue(String value) {
        for (Direction d : values()) {
            if (d.value.equals(value)) {
                return d;
            }
        }
        throw new IllegalArgumentException("No enum for value " + value);
    }
}
