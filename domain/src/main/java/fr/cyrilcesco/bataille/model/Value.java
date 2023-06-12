package fr.cyrilcesco.bataille.model;

public enum Value {
    DEUX(1),
    TROIS(2),
    QUATRE(3),
    CINQ(4),
    SIX(5),
    SEPT(6),
    HUIT(7),
    NEUF(8),
    DIX(9),
    VALET(10),
    DAME(11),
    ROIS(12),
    AS(13);

    private final int force;

    Value(int force) {
        this.force = force;
    }

    public int getForce() {
        return force;
    }
}
