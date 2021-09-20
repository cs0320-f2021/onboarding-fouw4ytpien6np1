package edu.brown.cs.student.main;

import java.util.List;

/**
 * Class to represent a Star.
 * @author fouw4ytpien6np1
 * @version 1.0
 */
public class Star {
    String starID;
    String name;
    Double x;
    Double y;
    Double z;
    Double euclideanDistance = null;

    /**
     * Creates a new Star with the specified parameters
     * @param starID - the Star's ID
     * @param name - the Star's name
     * @param x - Star's x coord
     * @param y - Star's y coord
     * @param z - Star's z coord
     */
    public Star(String starID, String name, Double x, Double y, Double z) {
        this.starID = starID;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    void setEuclideanDistance(Double dis) {
        this.euclideanDistance = dis;
    }
}
