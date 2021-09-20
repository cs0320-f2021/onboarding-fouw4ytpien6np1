package edu.brown.cs.student.main;

/**
 * Class to represent a Star.
 *
 * @author fouw4ytpien6np1
 * @version 1.0
 */
public class Star {
  private String starID;
  private String name;
  private Double x;
  private Double y;
  private Double z;
  private Double euclideanDistance = null;

  /**
   * Creates a new Star with the specified parameters.
   *
   * @param starID - the Star's ID
   * @param name   - the Star's name
   * @param x      - Star's x coord
   * @param y      - Star's y coord
   * @param z      - Star's z coord
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

  String returnStarID() {
    return this.starID;
  }

  String returnStarName() {
    return this.name;
  }

  Double returnX() {
    return this.x;
  }

  Double returnY() {
    return this.y;
  }

  Double returnZ() {
    return this.z;
  }

  Double returnEuclideanDis() {
    return this.euclideanDistance;
  }
}
