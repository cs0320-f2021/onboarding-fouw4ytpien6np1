package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@SuppressWarnings("checkstyle:TypeName")
public class naive_knn implements knn {
  private Star mainStar;
  private ArrayList<Star> starsFromCSV;
  private ArrayList<Star> kStars = new ArrayList<>();
  private int k;
  private ArrayList<Integer> intsLessThanK = new ArrayList<>();
  private ArrayList<Integer> intsGreaterThanK = new ArrayList<>();
  private Random rand = new Random();
  private ArrayList<Integer> starsToRandomlyChooseFrom = new ArrayList<>();

  /**
   * Returns mainStar.
   *
   * @return mainStar
   */
  public Star returnMainStar() {
    return this.mainStar;
  }

  /**
   * Returns starsFromCSV.
   *
   * @return starsFromCSV
   */
  public ArrayList<Star> returnStarsFromCSV() {
    return this.starsFromCSV;
  }

  /**
   * Returns kStars.
   *
   * @return kStars
   */
  public ArrayList<Star> returnKStars() {
    return this.kStars;
  }

  /**
   * Creates a new naive_knn object.
   *
   * @param mainStar     - Star to find nearest neighbors from.
   * @param starsFromCSV - List of Stars from CSV Reader.
   * @param k            - number of nearest neighbors to find.
   */
  public naive_knn(Star mainStar, ArrayList<Star> starsFromCSV, int k) {
    this.mainStar = mainStar;
    this.starsFromCSV = starsFromCSV;
    if (k < 0) {
      throw new IllegalArgumentException("K must be positive.");
    }
    this.k = k;
  }

  /**
   * naive_knn method that calculates the Stars closest to the inputted mainStar.
   */
  @Override
  public void knnAlgorithm() {
    if (k != 0) {
      // first, calculate the euclidean distance of each star to the mainStar(star inputted by user)
      this.setDistances();

      // Second, sort this Star List by Euclidean Distance
      this.sortDistances();

      // Finally, based on how many k nearest neighbors we are trying to find, traverse the list up
      // and down from the kth position to see if there are any ties and randomly choose the proper
      // number of Stars from those ties so that a total of k neighbors are chosen.

      this.getTopKEntries();
    }
  }

  /**
   * Sets the top K stars by distance to mainStar.
   */
  @Override
  public void getTopKEntries() {
    if (k == 1) {
      kStars.add(starsFromCSV.get(0));
    } else {

      for (int i = k - 2; i > -1; i--) {
        if (Math
            .abs(starsFromCSV.get(i).returnEuclideanDis() - starsFromCSV.get(k - 1).returnEuclideanDis())
            < epsilon) {
          intsLessThanK.add(i);
        } else {
          break;
        }
      }
      for (int i = k; i < starsFromCSV.size(); i++) {
        if (Math
            .abs(starsFromCSV.get(i).returnEuclideanDis() - starsFromCSV.get(k - 1).returnEuclideanDis())
            < epsilon) {
          intsGreaterThanK.add(i);
        } else {
          break;
        }
      }

      if (intsLessThanK.size() == 0 && intsGreaterThanK.size() == 0) {
        kStars = (ArrayList<Star>) starsFromCSV.subList(0, k);
      } else {
        List<Star> firstPartOfList = starsFromCSV.subList(0, (k - 1) - intsLessThanK.size());
        starsToRandomlyChooseFrom.addAll(intsLessThanK);
        starsToRandomlyChooseFrom.addAll(intsGreaterThanK);
        starsToRandomlyChooseFrom.add(k - 1);
        while (firstPartOfList.size() < k) {
          int randomIndex = rand.nextInt(starsToRandomlyChooseFrom.size());
          int index1 = starsToRandomlyChooseFrom.get(randomIndex);
          firstPartOfList.add(starsFromCSV.get(index1));
          starsToRandomlyChooseFrom.remove(randomIndex);
        }
        kStars = (ArrayList<Star>) firstPartOfList;
      }
    }
  }

  /**
   * Sets the Euclidean Distances for each star in relation to mainStar.
   */
  public void setDistances() {
    for (Star star : starsFromCSV) {
      Double dis =
          distanceBetweenPoints(mainStar.returnX(), mainStar.returnY(), mainStar.returnZ(), star.returnX(), star.returnY(), star.returnZ());
      star.setEuclideanDistance(dis);
    }
  }


  /**
   * Calculates the Euclidean distance between two 3-Dimensional points.
   *
   * @param x1 - Double; x-coord of first star
   * @param y1 - Double; y-coord of first star
   * @param z1 - Double; z-coord of first star
   * @param x2 - Double; x-coord of second star
   * @param y2 - Double; y-coord of second star
   * @param z2 - Double; z-coord of second star
   * @return Double
   */
  public Double distanceBetweenPoints(Double x1, Double y1, Double z1, Double x2, Double y2,
                                      Double z2) {
    return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2) + Math.pow((z2 - z1), 2));
  }


  /**
   * Sort Distances by their distance to mainStar.
   */
  @Override
  public void sortDistances() {
    starsFromCSV.sort(Comparator.comparing(Star::returnEuclideanDis));
  }

  /**
   * Displays Results in the specified format.
   */
  @Override
  public void displayResults() {
    for (Star star : this.kStars) {
      System.out.println(star.returnStarID());
    }
  }

  /**
   * setsMainStarByName.
   *
   * @param name - String representing the name of the star to be found
   */
  public void setMainObject(String name) {
    for (Star star : starsFromCSV) {
      if (star.returnStarName().equals(name)) {
        starsFromCSV.remove(star);
        mainStar = star;
      }
    }
  }
}
